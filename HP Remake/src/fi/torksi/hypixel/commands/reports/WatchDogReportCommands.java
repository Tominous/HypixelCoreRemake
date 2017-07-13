package fi.torksi.hypixel.commands.reports;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import fi.torksi.hypixel.Hypixel;
import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_11_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle.EnumTitleAction;


public class WatchDogReportCommands implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use that command!");
			return true;
		}

		Player p = (Player) sender;

		if (command.getName().equalsIgnoreCase("watchdogreport") || command.getName().equalsIgnoreCase("wdr")) {
			if (args.length < 2) {
				sender.sendMessage(ChatColor.RED + "Use: /watchdogreport (username) (type of hacks)");
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				sender.sendMessage(ChatColor.WHITE + "[WATCHDOG] " + ChatColor.RED + "I couldn't find anyone named " + ChatColor.YELLOW + args[0] + ChatColor.RED + ". Please check the spelling!");
				return true;
			}
			if (args[1].equalsIgnoreCase("Fly") || args[1].equalsIgnoreCase("KillAura") || args[1].equalsIgnoreCase("AutoClicker") || args[1].equalsIgnoreCase("Speed") || args[1].equalsIgnoreCase("AntiKnockback") || args[1].equalsIgnoreCase("Reach") || args[1].equalsIgnoreCase("Dolphin")) {
				
				
			} else {
				sender.sendMessage(ChatColor.WHITE + "[WATCHDOG] " + ChatColor.RED + "I didn't recogzine those hacks! Maybe try: " + ChatColor.YELLOW + "Fly, KillAura, AutoClicker, Speed, AntiKnockback, Reach, Dolphin");
				sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "If the type of hack isn't listed, please open a report at " + Hypixel.getPlugin().getConfig().getString("report-website"));
				return true;
			}
			
			String hack = "Unknown";
			
			if (args[1].equalsIgnoreCase("Fly")) {
				hack = "Fly";
			} else if (args[1].equalsIgnoreCase("KillAura")) {
				hack = "KillAura";
			} else if (args[1].equalsIgnoreCase("AutoClicker")) {
				hack = "AutoClicker";
			} else if (args[1].equalsIgnoreCase("Speed")) {
				hack = "Speed";
			} else if (args[1].equalsIgnoreCase("AntiKnockback")) {
				hack = "AntiKnockback";
			} else if (args[1].equalsIgnoreCase("Reach")) {
				hack = "Reach";
			} else if (args[1].equalsIgnoreCase("Dolphin")) {
				hack = "Dolphin";
			}
			
			int currentReportId = Hypixel.getPlugin().getConfig().getInt("watchdog-report-id");
			int newReportId = currentReportId + 1;
			
			
			sender.sendMessage(ChatColor.WHITE + "[WATCHDOG] §aYou reported §e" + target.getName() + " §a for §e[" + hack + "]§a");
			sender.sendMessage(ChatColor.WHITE + "[WATCHDOG] §aThanks for your report! Watchdog or a staff member will respond to it as soon as possible.");
			for (Player stf : Bukkit.getOnlinePlayers()) {
				if (stf.hasPermission("hypixel.helper")) {
					stf.sendMessage(ChatColor.WHITE + "[WATCHDOG #"+newReportId+"] §e"+sender.getName()+" §areported §e" + target.getName() + " §a for §e[" + hack + "]§a");
				}
			}
			
			Hypixel.getPlugin().getConfig().set("watchdog-report-id", newReportId);
			Hypixel.getPlugin().getConfig().set("watchdogreports." + newReportId + ".name", target.getName());
			Hypixel.getPlugin().getConfig().set("watchdogreports." + newReportId + ".uuid", target.getUniqueId());
			Hypixel.getPlugin().getConfig().set("watchdogreports." + newReportId + ".reporter", sender.getName());
			Hypixel.getPlugin().getConfig().set("watchdogreports." + newReportId + ".hack", hack);
			Hypixel.getPlugin().getConfig().set("watchdogreports." + newReportId + ".status", "OPEN");
			Hypixel.getPlugin().saveConfig();
			return true;
		} else if (command.getName().equalsIgnoreCase("watchdogreport-claim") || command.getName().equalsIgnoreCase("wdrc")) {
			if (args.length != 1) {
				sender.sendMessage(ChatColor.RED + "Use: /watchdogreport-claim <id>");
				return true;
			}
			
			int id = Integer.parseInt(args[0]);
			
			if (Hypixel.getPlugin().getConfig().contains("watchdogreports." + id)) {
				OfflinePlayer target = Bukkit.getOfflinePlayer(Hypixel.getPlugin().getConfig().getString("watchdogreports." + id + ".name"));
				String hack = Hypixel.getPlugin().getConfig().getString("watchdogreports." + id + ".hack");
				String status = Hypixel.getPlugin().getConfig().getString("watchdogreports." + id + ".status");
				p.sendMessage("[WATCHDOG] §aReport #" + id);
				p.sendMessage("[WATCHDOG] §aSuspect: " + target);
				p.sendMessage("[WATCHDOG] §aHack: " + hack);
				if (target.isOnline()) {
					p.chat("/vanish");
					Player targetOn = (Player) target;
					Location loc = targetOn.getLocation();
					p.teleport(loc);
					p.sendMessage("[WATCHDOG] §aYou are now spectating the suspect.");
					p.sendMessage("[WATCHDOG] §aIf the suspect is hacking you can accept the report using §e/wdra <id>");
					p.sendMessage("[WATCHDOG] §aIf the suspect is not hacking you can reject the report using §e/wdrr <id>");
					return true;
				} else {
					p.sendMessage("[WATCHDOG] §aThe Suspect is not online.");
					p.sendMessage("[WATCHDOG] §aYou can now reject the report using §e/wdrr <id>");
					return true;
				}
				
			} else {
				sender.sendMessage(ChatColor.WHITE + "[WATCHDOG] §aReport #§e" + id + "§a cannot be found. It might be already closed.");
				return true;
			}
			
		} else if (command.getName().equalsIgnoreCase("watchdogreport-reject") || command.getName().equalsIgnoreCase("wdrr")) {
			if (args.length != 1) {
				sender.sendMessage(ChatColor.RED + "Use: /watchdogreport-reject <id>");
				return true;
			}
			int id = Integer.parseInt(args[0]);
			
			if (Hypixel.getPlugin().getConfig().contains("watchdogreports." + id)) {
				OfflinePlayer suspect = Bukkit.getOfflinePlayer(Hypixel.getPlugin().getConfig().getString("watchdogreports." + id + ".name"));
				OfflinePlayer target = Bukkit.getOfflinePlayer(Hypixel.getPlugin().getConfig().getString("watchdogreports." + id + ".reporter"));
				if (target.isOnline()) {
					Player targetOn = (Player) target;
					targetOn.sendMessage("[WATCHDOG] §cYour report against §e" + suspect.getName() + " §cwas rejected by a staff member.");
				} 
				p.sendMessage("[WATCHDOG] §cYou have succesfully closed the report §e#" + id);
				Hypixel.getPlugin().getConfig().set("watchdogreports." + id, null);
				Hypixel.getPlugin().saveConfig();
				return true;
				
			} else {
				sender.sendMessage(ChatColor.WHITE + "[WATCHDOG] §aReport #§e" + id + "§a cannot be found. It might be already closed.");
				return true;
			}
			
		} else if (command.getName().equalsIgnoreCase("watchdogreport-accept") || command.getName().equalsIgnoreCase("wdra")) {
			if (args.length != 1) {
				sender.sendMessage(ChatColor.RED + "Use: /watchdogreport-accept <id>");
				return true;
			}
			int id = Integer.parseInt(args[0]);
			
			if (Hypixel.getPlugin().getConfig().contains("watchdogreports." + id)) {
				OfflinePlayer suspect = Bukkit.getOfflinePlayer(Hypixel.getPlugin().getConfig().getString("watchdogreports." + id + ".name"));
				OfflinePlayer target = Bukkit.getOfflinePlayer(Hypixel.getPlugin().getConfig().getString("watchdogreports." + id + ".reporter"));
				if (target.isOnline()) {
					Player targetOn = (Player) target;
					targetOn.sendMessage("[WATCHDOG] §aYour report against §e" + suspect.getName() + " §awas accepted and the suspect has been punished. Thanks for the report!");
				} 
				p.sendMessage("[WATCHDOG] §cYou have succesfully accepted the report §e#" + id + " §cand the suspect has been punished.");
				Hypixel.getPlugin().getConfig().set("watchdogreports." + id, null);
				Hypixel.getPlugin().saveConfig();
				
				if (suspect.isOnline()) {
					Player suspectOn = (Player) suspect;
					suspectOn.kickPlayer("§cYou are permanently banned from this server!\n\r\n\r§7Reason: §fWATCHDOG CHEAT DETECTION\n\r§7Find out more: §b" + Hypixel.getPlugin().getConfig().getString("watchdog-website"));
				}
				Hypixel.getPlugin().getConfig().set("watchdog_bans."+suspect.getName(), "Watchdog Report");
				Hypixel.getPlugin().saveConfig();
				
				return true;
				
			} else {
				sender.sendMessage(ChatColor.WHITE + "[WATCHDOG] §aReport #§e" + id + "§a cannot be found. It might be already closed.");
				return true;
			}
			
		}
		return true;
	} 
}
