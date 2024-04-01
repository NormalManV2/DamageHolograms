package nuclearkat.damageholograms;

import nuclearkat.damageholograms.taskutil.HologramCreation;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetHologramCommand implements CommandExecutor {

    private final DamageHolograms damageHolograms = DamageHolograms.getInstance();

    private HologramCreation hologramCreation;

    public ResetHologramCommand(HologramCreation hologramCreation){
        this.hologramCreation = hologramCreation;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)){
            return false;
        }

        Player player = (Player) commandSender;
        if (!player.hasPermission("dh.resetcommand")){
            String noPermissionMessage = damageHolograms.getConfig().getString("resetcommand.nopermissionmessage");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermissionMessage));
            return false;
        }
        hologramCreation.despawn();
        String hologramResetSuccessMessage = damageHolograms.getConfig().getString("resetcommand.hologramresetsuccessmessage");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', hologramResetSuccessMessage));

        return true;
    }
}
