package fi.torksi.hypixel.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use that command!");
			return true;
		}

		Player p = (Player) sender;
		
		if (!p.hasPermission("hypixel.vip")) {
			sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
			return true;
		}
		
		if (p.getAllowFlight()) {
			p.setAllowFlight(false);
			p.sendMessage("§aYou turned off flight.");
		} else if (!p.getAllowFlight()) {
			p.setAllowFlight(true);
			p.sendMessage("§aYou turned on flight.");
		}
		
		return true;
	}
}
