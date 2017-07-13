package fi.torksi.hypixel.commands.staff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fi.torksi.hypixel.Hypixel;

public class AChatCommand implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player msgsender;
		if (args.length < 1) {
			sender.sendMessage(ChatColor.RED + "Use: /ac (message)");
			return true;
		} else {
			if (!(sender instanceof Player)) {
				return true;
			} else {
				msgsender = (Player) sender;
			}
			
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < args.length; i++) {
				str.append(args[i] + " ");
			}

			String senderRankPr;
			Object targetRank = Hypixel.getPlugin().getConfig().get("playerdata." + msgsender.getUniqueId() + ".rank");
			
			
			if (Hypixel.getPlugin().getConfig().contains("playerdata." + msgsender.getUniqueId() + ".rank")) {
				if(targetRank.equals("VIP")){
					senderRankPr = "§a[VIP] ";
			    } else if (targetRank.equals("VIP+")){
			    	senderRankPr = "§a[VIP§6+§a] ";
			    } else if (targetRank.equals("MVP")){
			    	senderRankPr = "§b[MVP] ";
			    } else if (targetRank.equals("MVP+")){
			    	senderRankPr = "§b[MVP§c+§b] ";
			    } else if (targetRank.equals("YT")){
			    	senderRankPr = "§6[YT] ";
			    } else if (targetRank.equals("MOJANG")){
			    	senderRankPr = "§6[MOJANG] ";
			    } else if (targetRank.equals("APPLE")){
			    	senderRankPr = "§6[APPLE] ";
			    } else if (targetRank.equals("BUILDTEAM")){
			    	senderRankPr = "§3[BUILD TEAM] ";
			    } else if (targetRank.equals("HELPER")){
			    	senderRankPr = "§9[HELPER] ";
			    } else if (targetRank.equals("MOD")){
			    	senderRankPr = "§2[MOD] ";
			    } else if (targetRank.equals("ADMIN")){
			    	senderRankPr = "§c[ADMIN] ";
			    } else if (targetRank.equals("OWNER")){
			    	senderRankPr = "§c[OWNER] ";
			    } else {
			    	senderRankPr = "§7";
			    }
			} else {
				senderRankPr = "§7";
			}
			if (!sender.hasPermission("hypixel.helper")) {
				sender.sendMessage(ChatColor.RED + "[STAFF] " + senderRankPr + msgsender.getName() + ChatColor.GRAY + ": " + str);
				
			}
			for (Player stf : Bukkit.getOnlinePlayers()) {
				if (stf.hasPermission("hypixel.helper")) {
					stf.sendMessage(ChatColor.RED + "[STAFF] " + senderRankPr + msgsender.getName() + ChatColor.GRAY + ": " + str);
				}
			}
			
			
			
		}
		
		return true;
	}
}
