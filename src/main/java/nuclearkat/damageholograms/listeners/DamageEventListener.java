package nuclearkat.damageholograms.listeners;

import nuclearkat.damageholograms.DamageHolograms;
import nuclearkat.damageholograms.taskutil.HologramCreation;
import nuclearkat.damageholograms.taskutil.HologramTasks;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DamageEventListener implements Listener {

    private final HologramTasks hologramTasks;
    private final DamageHolograms damageHolograms;

    public DamageEventListener(HologramTasks hologramTasks, DamageHolograms damageHolograms) {
        this.hologramTasks = hologramTasks;
        this.damageHolograms = damageHolograms;
    }

    @EventHandler
    private void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Location location = e.getEntity().getLocation().add(0, 1.0, 0);
            List<String> damageAmountList = new ArrayList<>();
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String damageAmount = ChatColor.translateAlternateColorCodes('&', damageHolograms.getConfig().get("damagehologram.color") + decimalFormat.format(e.getDamage()));
            damageAmountList.add(damageAmount);

            HologramCreation damageAmountHologram = new HologramCreation(location, damageAmountList);

            hologramTasks.trackHologram(damageAmountHologram);
        }
    }
}