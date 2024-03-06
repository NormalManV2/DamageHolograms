package nuclearkat.damageholograms.taskutil;

import org.bukkit.Location;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//
public class HologramTasks implements Runnable {

    private static final HologramTasks instance = new HologramTasks();
    private final Map<HologramCreation, Integer> activeHolograms = new ConcurrentHashMap<>();

    public void trackHologram(HologramCreation hologram) {
        activeHolograms.put(hologram, 0);
    }

    private void stopTrackingHologram(HologramCreation hologram) {
        activeHolograms.remove(hologram);
        hologram.despawn();
    }

    @Override
    public void run() {
        for (Map.Entry<HologramCreation, Integer> entry : List.copyOf(activeHolograms.entrySet())) {
            HologramCreation hologram = entry.getKey();
            Integer counter = entry.getValue();
            Location loc = hologram.getLocation().add(0, 0.09, 0);
            hologram.teleportHologram(loc);
            activeHolograms.put(hologram, counter + 1);

            if (counter == 20) {
                stopTrackingHologram(hologram);
            }
        }
    }

    private HologramTasks() {
    }

    public static HologramTasks getInstance() {
        return instance;
    }
}



