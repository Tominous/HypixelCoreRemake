package fi.torksi.hypixel.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import fi.torksi.hypixel.Hypixel;
import fi.torksi.hypixel.Permissions;

public class RankCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equalsIgnoreCase("rank")) {
			if ((sender instanceof Player)) {
				PermissionAttachment attachment = sender.addAttachment(Hypixel.getPlugin());
				Permissions.perms.put(((Entity) sender).getUniqueId(), attachment);
				
					if (!sender.hasPermission("hypixel.admin")) {
						sender.sendMessage(ChatColor.RED + "You are not allowed to do this!");
						return true;
					}
			}
			
				if (args.length != 2) {
					sender.sendMessage(ChatColor.RED + "[HYPIXEL] Rank System is up to date!");
					sender.sendMessage(ChatColor.GOLD + "/rank <player> <rank>: " + ChatColor.YELLOW + "Updates chosen player's rank to chosen rank.");
					sender.sendMessage(ChatColor.GOLD + "Ranks: " + ChatColor.WHITE + "Default VIP VIP+ MVP MVP+ YT APPLE MOJANG BUILDTEAM HELPER MOD ADMIN OWNER");
					return true;
				}
				Player target = Bukkit.getPlayer(args[0]);
				String rank = "";
				String rankLower = args[1].toLowerCase();
				
				
				if (!target.isOnline()) {
					if (rankLower == "default") {
						sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to Default!");
						rank = "DEFAULT";
					} else if (rankLower == "vip") {
						sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.GREEN+"VIP!");
						rank = "VIP";
					} else if (rankLower == "vip+") {
						sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.GREEN+"VIP"+ChatColor.GOLD+"+"+ChatColor.GREEN+"!");
						rank = "VIP+";
					} else if (rankLower == "mvp") {
						sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.AQUA+"MVP!");
						rank = "MVP";
					} else if (rankLower == "mvp+") {
						sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.AQUA+"MVP"+ChatColor.RED+"+"+ChatColor.AQUA+"!");
						rank = "MVP+";
					} else if (rankLower == "yt") {
						sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.GOLD+"YT!");
						rank = "YT";
					} else if (rankLower == "apple") {
						sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.GOLD+"APPLE!");
						rank = "APPLE";
					} else if (rankLower == "mojang") {
						sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.GOLD+"MOJANG!");
						rank = "MOJANG";
					} else if (rankLower == "buildteam") {
						sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.DARK_AQUA+"BUILD TEAM!");
						rank = "BUILDTEAM";
					} else if (rankLower == "helper") {
						sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.BLUE+"HELPER!");
						rank = "HELPER";
					} else if (rankLower == "mod") {
						sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.DARK_GREEN+"MOD!");
						rank = "MOD";
					} else if (rankLower == "admin") {
						sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.RED+"ADMIN!");
						rank = "ADMIN";
					} else if (rankLower == "owner") {
						sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.RED+"OWNER!");
						rank = "OWNER";
						
					} else {
						sender.sendMessage(ChatColor.RED + "Invalid rank!");
						return true;
						
					}
					Hypixel.getPlugin().getConfig().set("playerdata." + target.getUniqueId() + ".rank", rank);
					Hypixel.getPlugin().saveConfig();
					return true;
				}
				
				
				
				if (rankLower.equals("default")) {
					sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to Default!");
					rank = "DEFAULT";
					
					target.setPlayerListName(ChatColor.GRAY + target.getDisplayName());
					
				} else if (rankLower.equals("vip")) {
					sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.GREEN+"VIP!");
					rank = "VIP";
					
					target.setPlayerListName(ChatColor.GREEN + "[VIP] " + target.getDisplayName());

				} else if (rankLower.equals("vip+")) {
					sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.GREEN+"VIP"+ChatColor.GOLD+"+"+ChatColor.GREEN+"!");
					rank = "VIP+";
					
					target.setPlayerListName(ChatColor.GREEN + "[VIP"+ChatColor.GOLD+"+"+ChatColor.GREEN+"] " + target.getDisplayName());

				} else if (rankLower.equals("mvp")) {
					sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.AQUA+"MVP!");
					rank = "MVP";
					
					target.setPlayerListName(ChatColor.AQUA + "[MVP] " + target.getDisplayName());

				} else if (rankLower.equals("mvp+")) {
					sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.AQUA+"MVP"+ChatColor.RED+"+"+ChatColor.AQUA+"!");
					rank = "MVP+";
					
					target.setPlayerListName(ChatColor.AQUA + "[MVP"+ChatColor.RED+"+"+ChatColor.AQUA+"] " + target.getDisplayName());

				} else if (rankLower.equals("yt")) {
					sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.GOLD+"YT!");
					rank = "YT";
					
					target.setPlayerListName(ChatColor.GOLD + "[YT] " + target.getDisplayName());
					
				} else if (rankLower.equals("apple")) {
					sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.GOLD+"APPLE!");
					rank = "APPLE";
					
					target.setPlayerListName(ChatColor.GOLD + "[APPLE] " + target.getDisplayName());

				} else if (rankLower.equals("mojang")) {
					sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.GOLD+"MOJANG!");
					rank = "MOJANG";
					
					target.setPlayerListName(ChatColor.GOLD + "[MOJANG] " + target.getDisplayName());
					
				} else if (rankLower.equals("buildteam")) {
					sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.DARK_AQUA+"BUILD TEAM!");
					rank = "BUILDTEAM";
					
					target.setPlayerListName(ChatColor.DARK_AQUA + "[BUILD TEAM] " + target.getDisplayName());

				} else if (rankLower.equals("helper")) {
					sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.BLUE+"HELPER!");
					rank = "HELPER";
					
					target.setPlayerListName(ChatColor.BLUE + "[HELPER] " + target.getDisplayName());

				} else if (rankLower.equals("mod")) {
					sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.DARK_GREEN+"MOD!");
					rank = "MOD";
					
					target.setPlayerListName(ChatColor.DARK_GREEN + "[MOD] " + target.getDisplayName());

				} else if (rankLower.equals("admin")) {
					sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.RED+"ADMIN!");
					rank = "ADMIN";
					
					target.setPlayerListName(ChatColor.RED + "[ADMIN] " + target.getDisplayName());

				} else if (rankLower.equals("owner")) {
					sender.sendMessage(ChatColor.GRAY + target.getName() + "'s rank has been updated to "+ChatColor.RED+"OWNER!");
					rank = "OWNER";
					
					target.setPlayerListName(ChatColor.RED + "[OWNER] " + target.getDisplayName());

				} else {
					sender.sendMessage(ChatColor.RED + "Invalid rank!");
					return true;
					
				}
				
				sender.sendMessage(ChatColor.RED + target.getName() + " must relog before they get their permissions.");
				
				
				Hypixel.getPlugin().getConfig().set("playerdata." + target.getUniqueId() + ".rank", rank);
				Hypixel.getPlugin().saveConfig();
				

				

		}
		return true;
	}
}
