package nuclearkat.damageholograms;

import nuclearkat.damageholograms.taskutil.HologramTasks;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetHologramCommand implements CommandExecutor {

    private final DamageHolograms plugin;
    private final HologramTasks hologramTasks = HologramTasks.getInstance();

    public ResetHologramCommand(DamageHolograms plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)){
            return false;
        }

        Player player = (Player) commandSender;
        if (!player.hasPermission("dh.resetcommand")){
            String noPermissionMessage = plugin.getConfig().getString("reset_command.no_permission_message", "&cYou do not have permission to use this command!");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermissionMessage));
            return false;
        }
        this.hologramTasks.despawnAll();
        String hologramResetSuccessMessage = this.plugin.getConfig().getString("reset_command.hologram_reset_success_message", "&bHolograms reset successfully!");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', hologramResetSuccessMessage));

        return true;
    }
}
