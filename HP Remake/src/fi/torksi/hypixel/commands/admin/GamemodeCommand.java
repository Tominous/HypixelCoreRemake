package fi.torksi.hypixel.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use that command!");
			return true;
		}

		Player p = (Player) sender;

		if (!p.hasPermission("hypixel.admin")) {
			sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
			return true;
		}

		if (args.length == 0) {
			if (p.getGameMode() == GameMode.CREATIVE) {
				p.setGameMode(GameMode.SURVIVAL);
				p.sendMessage("§e" + p.getName() + "'s gamemode was changed to Survival.");
			} else {
				p.setGameMode(GameMode.CREATIVE);
				p.sendMessage("§e" + p.getName() + "'s gamemode was changed to Creative.");
			}
		} else {
			Player target = Bukkit.getPlayer(args[0]);

			if (target == null) {
				p.sendMessage(ChatColor.RED + "That player is not online.");
				return true;
			}

			if (target.getGameMode() == GameMode.CREATIVE) {
				target.setGameMode(GameMode.SURVIVAL);
				target.sendMessage("§e" + target.getName() + "'s gamemode was changed to Survival.");
				p.sendMessage("§e" + target.getName() + "'s gamemode was changed to Survival.");
			} else {
				target.setGameMode(GameMode.CREATIVE);
				target.sendMessage("§e" + target.getName() + "'s gamemode was changed to Creative.");
				p.sendMessage("§e" + target.getName() + "'s gamemode was changed to Creative.");
			}

			return true;
		}
		return true;
	}
}
