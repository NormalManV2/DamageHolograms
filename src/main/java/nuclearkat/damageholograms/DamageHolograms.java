package nuclearkat.damageholograms;

import nuclearkat.damageholograms.listeners.DamageEventListener;
import nuclearkat.damageholograms.taskutil.HologramCreation;
import nuclearkat.damageholograms.taskutil.HologramTasks;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class DamageHolograms extends JavaPlugin {

    private static final DamageHolograms instance = new DamageHolograms();
    private HologramTasks hologramTasks;
    private HologramCreation hologramCreation;

    @Override
    public void onEnable() {
        hologramTasks = HologramTasks.getInstance();
        registerListeners();
        new BukkitRunnable(){
            @Override
            public void run() {
                hologramTasks.run();
            }
        }.runTaskTimer(this, 0, 1);
    }

    private void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new DamageEventListener(hologramTasks, this), this);
    }
    private void registerCommands(){
        getCommand("resetholograms").setExecutor(new ResetHologramCommand(hologramCreation));
    }

    private DamageHolograms(){

    }
    public static DamageHolograms getInstance(){
        return instance;
    }

    @Override
    public void onDisable() {

    }
}
