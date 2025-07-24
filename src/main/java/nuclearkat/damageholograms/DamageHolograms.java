package nuclearkat.damageholograms;

import nuclearkat.damageholograms.listeners.DamageEventListener;
import nuclearkat.damageholograms.taskutil.HologramCreation;
import nuclearkat.damageholograms.taskutil.HologramTasks;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class DamageHolograms extends JavaPlugin {

    private HologramTasks hologramTasks;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.hologramTasks = HologramTasks.getInstance();
        this.registerListeners();
        this.registerCommands();
        Bukkit.getScheduler().runTaskTimer(this, () -> this.hologramTasks.run(), 0, 2);
    }

    private void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new DamageEventListener(this), this);
    }
    private void registerCommands(){
        getCommand("resetholograms").setExecutor(new ResetHologramCommand(this));
    }

    @Override
    public void onDisable() {

    }
}
