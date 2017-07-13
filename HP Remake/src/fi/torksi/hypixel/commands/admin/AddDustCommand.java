package fi.torksi.hypixel.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import fi.torksi.hypixel.Hypixel;
import fi.torksi.hypixel.Permissions;

public class AddDustCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use that command!");
			return true;
		}

		Player p = (Player) sender;

		if (command.getName().equalsIgnoreCase("setdust")) {
			
			PermissionAttachment attachment = p.addAttachment(Hypixel.getPlugin());
			Permissions.perms.put(p.getUniqueId(), attachment);
			
				if (!p.hasPermission("hypixel.admin")) {
					sender.sendMessage(ChatColor.RED + "You are not of rank to use this command! You must be of the admin rank or higher!");
					return true;
				}
				if (args.length != 2) {
					p.sendMessage(ChatColor.RED + "Use: /setdust (player) (amount)");
					return true;
				}
				Player target = Bukkit.getPlayer(args[0]);
				
				if (target == null) {
					sender.sendMessage(ChatColor.RED+"That player is not online!");
					return true;
				}
				
				int dust;
				dust = Integer.parseInt(args[1]);
				
				
				sender.sendMessage(ChatColor.GRAY + target.getName() + " got " + ChatColor.GREEN + dust + ChatColor.GRAY + " Mystery Dust.");
				Hypixel.getPlugin().getConfig().set("playerdata." + target.getUniqueId() + ".dust", dust);
				Hypixel.getPlugin().saveConfig();
				

				

		}
		return true;
	}
}
