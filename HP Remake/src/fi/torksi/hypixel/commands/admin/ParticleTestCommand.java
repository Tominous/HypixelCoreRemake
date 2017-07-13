package fi.torksi.hypixel.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import fi.torksi.hypixel.Hypixel;
import fi.torksi.hypixel.Permissions;

public class ParticleTestCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use that command!");
			return true;
		}

		Player p = (Player) sender;

		if (command.getName().equalsIgnoreCase("particletest")) {
			
			PermissionAttachment attachment = p.addAttachment(Hypixel.getPlugin());
			Permissions.perms.put(p.getUniqueId(), attachment);
			
				if (!p.hasPermission("hypixel.admin")) {
					sender.sendMessage(ChatColor.RED + "You are not of rank to use this command! You must be of the admin rank or higher!");
					return true;
				}
				if (args.length != 2) {
					p.sendMessage(ChatColor.RED + "Use: /particletest (player) (effect)");
					return true;
				}
				Player target = Bukkit.getPlayer(args[0]);
				
				if (target == null) {
					sender.sendMessage(ChatColor.RED+"That player is not online!");
					return true;
				}
				
				Location loc = target.getLocation();
				double y = loc.getY() + 3;
				
				//sender.sendMessage(y + "");
				
				String ef;
				if (args[1].equals("HAPPY_VILLAGER")) {
					target.playEffect(target.getEyeLocation(), Effect.HAPPY_VILLAGER , 0);
				} else if (args[1].equals("ANGRY_VILLAGER")) {
					target.playEffect(target.getEyeLocation(), Effect.VILLAGER_THUNDERCLOUD , 0);
				} else if (args[1].equals("HEART")) {
					target.playEffect(target.getEyeLocation(), Effect.HEART , 0);
				} else if (args[1].equals("EXPLOSION_HUGE")) {
					target.playEffect(target.getEyeLocation(), Effect.EXPLOSION_HUGE , 0);
				} else if (args[1].equals("EXPLOSION_LARGE")) {
					target.playEffect(target.getEyeLocation(), Effect.EXPLOSION_LARGE , 0);
				} else if (args[1].equals("FLAME")) {
					target.playEffect(target.getEyeLocation(), Effect.FLAME , 0);
				} else if (args[1].equals("MOBSPAWNER_FLAMES")) {
					target.playEffect(target.getEyeLocation(), Effect.MOBSPAWNER_FLAMES , 0);
				}
				
				target.playEffect(target.getLocation(), Effect.HAPPY_VILLAGER , 10);
				

		}
		return true;
	}
}
