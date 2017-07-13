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

public class AddXpCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use that command!");
			return true;
		}

		Player p = (Player) sender;

		if (command.getName().equalsIgnoreCase("addxp") || command.getName().equalsIgnoreCase("xpadd")) {
			
			PermissionAttachment attachment = p.addAttachment(Hypixel.getPlugin());
			Permissions.perms.put(p.getUniqueId(), attachment);
			
				if (!p.hasPermission("hypixel.admin")) {
					sender.sendMessage(ChatColor.RED + "You are not of rank to use this command! You must be of the admin rank or higher!");
					return true;
				}
				if (args.length != 2) {
					p.sendMessage(ChatColor.RED + "Use: /addxp (player) (level)");
					return true;
				}
				Player target = Bukkit.getPlayer(args[0]);
				
				if (target == null) {
					sender.sendMessage(ChatColor.RED+"That player is not online!");
					return true;
				}
				
				int level;
				level = Integer.parseInt(args[1]);
				
				if (level > 250 || level < 0) {
					sender.sendMessage(ChatColor.RED + "Error, there was a problem parsing your request");
					return true;
				}
				
				sender.sendMessage(ChatColor.GRAY + target.getName() + "'s Network level was set to " + level);
				
				target.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "---------------------------------------------");
				target.sendMessage("                             "+ChatColor.GREEN + "" + ChatColor.MAGIC + "&" + ChatColor.GOLD + "LEVEL UP! " + ChatColor.GREEN + "" + ChatColor.MAGIC + "&");
				target.sendMessage("");
				target.sendMessage(ChatColor.GRAY + "                    You are now " + ChatColor.DARK_AQUA + Hypixel.getPlugin().getConfig().getString("server-name") + " Level " + ChatColor.GREEN + level + ChatColor.GRAY + "!");
				target.sendMessage("");
				target.sendMessage(ChatColor.YELLOW + "                   Claim your reward in the lobby!");
				target.sendMessage(ChatColor.GREEN + "-----------------------------------------------------");
				target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 0.2f);
				Hypixel.getPlugin().getConfig().set("playerdata." + target.getUniqueId() + ".level", level);
				Hypixel.getPlugin().saveConfig();
				

				

		}
		return true;
	}
}
