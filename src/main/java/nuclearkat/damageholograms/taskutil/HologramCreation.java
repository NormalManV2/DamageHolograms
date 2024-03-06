package nuclearkat.damageholograms.taskutil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;

import java.util.ArrayList;
import java.util.List;

//
public class HologramCreation {

    private final String id;
    private final Location location;
    private final List<String> lines;
    private final List<TextDisplay> textDisplays;
    private final List<Location> offsets;

    public HologramCreation(String id, Location location, List<String> lines) {
        this.id = id;
        this.location = location;
        this.lines = lines;
        this.textDisplays = new ArrayList<>();
        this.offsets = new ArrayList<>();
        createTextDisplays();
    }

    private void createTextDisplays() {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            TextDisplay textDisplay = Bukkit.getWorld("world").spawn(location.clone().add(0, -i * 0.25, 0), TextDisplay.class, display -> {
                display.setText(ChatColor.translateAlternateColorCodes('&', line));
                display.setBillboard(Display.Billboard.CENTER);
                int color = 0x00000000;
                display.setBackgroundColor(Color.fromARGB(color));
            });
            textDisplays.add(textDisplay);

            offsets.add(textDisplay.getLocation().subtract(location));
        }
    }

    public void teleportHologram(Location newLocation) {
        for (int i = 0; i < textDisplays.size(); i++) {
            Location offset = offsets.get(i);
            Location displayLocation = newLocation.clone().add(offset);
            textDisplays.get(i).teleport(displayLocation);
        }
    }

    public Location getLocation() {
        return location;
    }

    public void despawn() {
        for (TextDisplay textDisplay : textDisplays) {
            textDisplay.remove();
        }
    }
}

