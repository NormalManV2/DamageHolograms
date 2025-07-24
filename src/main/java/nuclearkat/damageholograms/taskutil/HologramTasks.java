package nuclearkat.damageholograms.taskutil;

import org.bukkit.Location;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HologramTasks implements Runnable {

    private static HologramTasks instance = new HologramTasks();
    private final Map<HologramCreation, Integer> activeHolograms = new ConcurrentHashMap<>();

    public void trackHologram(HologramCreation hologram) {
        this.activeHolograms.putIfAbsent(hologram, 0);
    }

    private void stopTrackingHologram(HologramCreation hologram) {
        this.activeHolograms.remove(hologram);
        hologram.despawn();
    }

    public void despawnAll() {
        this.activeHolograms.forEach((hologram, counter) -> this.stopTrackingHologram(hologram));
    }

    @Override
    public void run() {
        for (Map.Entry<HologramCreation, Integer> entry : List.copyOf(this.activeHolograms.entrySet())) {
            HologramCreation hologram = entry.getKey();
            Integer counter = entry.getValue();
            Location loc = hologram.getLocation().add(0, 0.09, 0);
            hologram.teleportHologram(loc);
            this.activeHolograms.replace(hologram, counter + 1);

            if (counter == 20) {
                this.stopTrackingHologram(hologram);
            }
        }
    }

    private HologramTasks() {
        instance = this;
    }

    public static HologramTasks getInstance() {

        if (instance == null) {
            instance = new HologramTasks();
        }

        return instance;
    }
}



