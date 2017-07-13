package fi.torksi.hypixel.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fi.torksi.hypixel.Hypixel;

public class MsgCommand implements CommandExecutor {
	
	public static HashMap<Player, Player> recentMessages = new HashMap<Player, Player>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player msgsender;
		if (args.length < 2) {
			sender.sendMessage(ChatColor.RED + "Use: /msg (player) (message)");
			return true;
		} else {
			if (!(sender instanceof Player)) {
				return true;
			} else {
				msgsender = (Player) sender;
			}
			Player msgtarget = Bukkit.getPlayerExact(args[0]);
			StringBuilder str = new StringBuilder();
			for (int i = 1; i < args.length; i++) {
				str.append(args[i] + " ");
			}
			if (msgtarget == null) {
				sender.sendMessage(ChatColor.RED + "That player isn't online!");
				return true;
			}
			
			if (msgtarget.getName().equals(msgsender)) {
				sender.sendMessage(ChatColor.RED + "You can not talk with yourself!");
				return true;
			}
			
			String senderRankPr;
			String receiverRankPr;
			Object targetRank = Hypixel.getPlugin().getConfig().get("playerdata." + msgtarget.getUniqueId() + ".rank");
			Object senderRank = Hypixel.getPlugin().getConfig().get("playerdata." + msgsender.getUniqueId() + ".rank");
			
			if (Hypixel.getPlugin().getConfig().contains("playerdata." + msgsender.getUniqueId() + ".rank")) {
				if(senderRank.equals("VIP")){
					senderRankPr = "§a[VIP] ";
			    } else if (senderRank.equals("VIP+")){
			    	senderRankPr = "§a[VIP§6+§a] ";
			    } else if (senderRank.equals("MVP")){
			    	senderRankPr = "§b[MVP] ";
			    } else if (senderRank.equals("MVP+")){
			    	senderRankPr = "§b[MVP§c+§b] ";
			    } else if (senderRank.equals("YT")){
			    	senderRankPr = "§6[YT] ";
			    } else if (senderRank.equals("MOJANG")){
			    	senderRankPr = "§6[MOJANG] ";
			    } else if (senderRank.equals("APPLE")){
			    	senderRankPr = "§6[APPLE] ";
			    } else if (senderRank.equals("BUILDTEAM")){
			    	senderRankPr = "§3[BUILD TEAM] ";
			    } else if (senderRank.equals("HELPER")){
			    	senderRankPr = "§9[HELPER] ";
			    } else if (senderRank.equals("MOD")){
			    	senderRankPr = "§2[MOD] ";
			    } else if (senderRank.equals("ADMIN")){
			    	senderRankPr = "§c[ADMIN] ";
			    } else if (senderRank.equals("OWNER")){
			    	senderRankPr = "§c[OWNER] ";
			    } else {
			    	senderRankPr = "§7";
			    }
			} else {
				senderRankPr = "§7";
			}
			
			if (Hypixel.getPlugin().getConfig().contains("playerdata." + msgtarget.getUniqueId() + ".rank")) {
				if(targetRank.equals("VIP")){
					receiverRankPr = "§a[VIP] ";
			    } else if (targetRank.equals("VIP+")){
			    	receiverRankPr = "§a[VIP§6+§a] ";
			    } else if (targetRank.equals("MVP")){
			    	receiverRankPr = "§b[MVP] ";
			    } else if (targetRank.equals("MVP+")){
			    	receiverRankPr = "§b[MVP§c+§b] ";
			    } else if (targetRank.equals("YT")){
			    	receiverRankPr = "§6[YT] ";
			    } else if (targetRank.equals("MOJANG")){
			    	receiverRankPr = "§6[MOJANG] ";
			    } else if (targetRank.equals("APPLE")){
			    	receiverRankPr = "§6[APPLE] ";
			    } else if (targetRank.equals("BUILDTEAM")){
			    	receiverRankPr = "§3[BUILD TEAM] ";
			    } else if (targetRank.equals("HELPER")){
			    	receiverRankPr = "§9[HELPER] ";
			    } else if (targetRank.equals("MOD")){
			    	receiverRankPr = "§2[MOD] ";
			    } else if (targetRank.equals("ADMIN")){
			    	receiverRankPr = "§c[ADMIN] ";
			    } else if (targetRank.equals("OWNER")){
			    	receiverRankPr = "§c[OWNER] ";
			    } else {
			    	receiverRankPr = "§7";
			    }
			} else {
				receiverRankPr = "§7";
			}
			
			if (recentMessages.containsKey(msgsender)) {
				recentMessages.remove(msgsender);
			}
			recentMessages.put(msgsender, msgtarget);
			
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "To: " + receiverRankPr + msgtarget.getName() + ChatColor.GRAY + ": " + str);
			msgtarget.sendMessage(ChatColor.LIGHT_PURPLE + "From: " + senderRankPr + msgsender.getName() + ChatColor.GRAY + ": " + str);
			
		}
		
		return true;
	}
	
}
