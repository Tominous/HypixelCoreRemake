package fi.torksi.hypixel.commands.staff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fi.torksi.hypixel.Hypixel;

public class AReplyCommand implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player msgsender;
		if (args.length < 2) {
			sender.sendMessage(ChatColor.RED + "Use: /ar (player) (message)");
			return true;
		} else {
			
			
			
			if (!(sender instanceof Player)) {
				return true;
			} else {
				msgsender = (Player) sender;
			}
			
			if (!msgsender.hasPermission("hypixel.helper")) {
				sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
				return true;
			}
			
			Player msgtarget = Bukkit.getPlayerExact(args[0]);
			
			if (msgtarget == null) {
				sender.sendMessage(ChatColor.RED + "That player isn't online!");
				return true;
			}
			
			StringBuilder str = new StringBuilder();
			for (int i = 1; i < args.length; i++) {
				str.append(args[i] + " ");
			}

			String senderRankPr;
			String receiverRankPr;
			Object receiverRank = Hypixel.getPlugin().getConfig().get("playerdata." + msgtarget.getUniqueId() + ".rank");
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
			
			if (Hypixel.getPlugin().getConfig().contains("playerdata." + msgtarget.getUniqueId() + ".rank")) {
				if(receiverRank.equals("VIP")){
					receiverRankPr = "§a[VIP] ";
			    } else if (receiverRank.equals("VIP+")){
			    	receiverRankPr = "§a[VIP§6+§a] ";
			    } else if (receiverRank.equals("MVP")){
			    	receiverRankPr = "§b[MVP] ";
			    } else if (receiverRank.equals("MVP+")){
			    	receiverRankPr = "§b[MVP§c+§b] ";
			    } else if (receiverRank.equals("YT")){
			    	receiverRankPr = "§6[YT] ";
			    } else if (receiverRank.equals("MOJANG")){
			    	receiverRankPr = "§6[MOJANG] ";
			    } else if (receiverRank.equals("APPLE")){
			    	receiverRankPr = "§6[APPLE] ";
			    } else if (receiverRank.equals("BUILDTEAM")){
			    	receiverRankPr = "§3[BUILD TEAM] ";
			    } else if (receiverRank.equals("HELPER")){
			    	receiverRankPr = "§9[HELPER] ";
			    } else if (receiverRank.equals("MOD")){
			    	receiverRankPr = "§2[MOD] ";
			    } else if (receiverRank.equals("ADMIN")){
			    	receiverRankPr = "§c[ADMIN] ";
			    } else if (receiverRank.equals("OWNER")){
			    	receiverRankPr = "§c[OWNER] ";
			    } else {
			    	receiverRankPr = "§7";
			    }
			} else {
				receiverRankPr = "§7";
			}
			
			msgtarget.sendMessage(ChatColor.RED + "[REPLY] " + senderRankPr + msgsender.getName() + ChatColor.GRAY + ": " + str);
				
			for (Player stf : Bukkit.getOnlinePlayers()) {
				if (stf.hasPermission("hypixel.helper")) {
					stf.sendMessage(ChatColor.RED + "[REPLY] " + senderRankPr + msgsender.getName() + "§e -> " + receiverRankPr + msgtarget.getName() + ChatColor.GRAY + ": " + str);
				}
			}
			
			
			
		}
		
		return true;
	}
}
