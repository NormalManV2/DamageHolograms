package nuclearkat.damageholograms.taskutil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class HologramCreation {

    private final Location location;
    private final List<String> lines;
    private final List<TextDisplay> textDisplays;
    private final List<Location> offsets;

    public HologramCreation(Location location, List<String> lines) {
        this.location = location;
        this.lines = lines;
        this.textDisplays = new ArrayList<>();
        this.offsets = new ArrayList<>();
        this.createTextDisplays();
    }

    private void createTextDisplays() {

        if (this.location.getWorld() == null) {
            throw new RuntimeException("World is null when creating holograms!");
        }

        for (int i = 0; i < this.lines.size(); i++) {
            String line = this.lines.get(i);
            TextDisplay textDisplay = this.location.getWorld().spawn(this.location.clone().add(0, -i * 0.25, 0), TextDisplay.class, display -> {
                display.setText(line);
                display.setBillboard(Display.Billboard.CENTER);
                int color = 0x00000000;
                display.setBackgroundColor(Color.fromARGB(color));
            });
            this.textDisplays.add(textDisplay);

            this.offsets.add(textDisplay.getLocation().subtract(this.location));
        }
    }

    public void teleportHologram(Location newLocation) {
        for (int i = 0; i < this.textDisplays.size(); i++) {
            if (newLocation == null) {
                Bukkit.getLogger().log(Level.WARNING, "New location for hologram is null!");
                return;
            }
            Location offset = this.offsets.get(i);
            Location displayLocation = newLocation.clone().add(offset);
            this.textDisplays.get(i).teleport(displayLocation);
        }
    }

    public Location getLocation() {
        return this.location;
    }

    public void despawn() {
        for (TextDisplay textDisplay : this.textDisplays) {
            textDisplay.remove();
        }
    }
}
