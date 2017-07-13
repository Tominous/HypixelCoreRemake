package fi.torksi.hypixel.menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import fi.torksi.hypixel.Hypixel;

public class CollectiblesCommand implements CommandExecutor {
	org.bukkit.inventory.Inventory collectibles = Bukkit.createInventory(null, 54,
			"Collectibles");
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use that command!");
			return true;
		}

		Player p = (Player) sender;

		ItemStack pets = new ItemStack(Material.BONE, 1);
		ItemMeta pd = pets.getItemMeta();
		pd.setDisplayName(ChatColor.GREEN + "Pets");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§7Collect, feed, and play");
		lore.add("§7with your favourite pets!");
		lore.add("§7Level them up by taking");
		lore.add("§7care of them!");
		lore.add("");
		lore.add("§7Unlocked: §c31/112");
		lore.add("§6Halloween: §e1/2");
		lore.add("§eSummer: §c0/1");
		lore.add("§aHoliday: §e2/3");
		lore.add("");
		lore.add("§eClick to browse!");
		pd.setLore(lore);
		pets.setItemMeta(pd);
		collectibles.setItem(9, pets);
		
		
		ItemStack companions = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
		SkullMeta cd = (SkullMeta) companions.getItemMeta();
		cd.setDisplayName(ChatColor.GREEN + "Companions");
		ArrayList<String> lore2 = new ArrayList<String>();
		cd.setOwner("Pugs");
		lore2.add("§7Support " + Hypixel.getPlugin().getConfig().getString("server-name") + " with these");
		lore2.add("§7custom-built Companions - ");
		lore2.add("§7most are available to");
		lore2.add("§7unlock on the " + Hypixel.getPlugin().getConfig().getString("server-name"));
		lore2.add("§7Store! How many can you");
		lore2.add("§7collect?");
		lore2.add("");
		lore2.add("§7Unlocked: §c0/15");
		lore2.add("");
		lore2.add("§eClick to browse!");
		
		cd.setLore(lore2);
		companions.setItemMeta(cd);
		collectibles.setItem(11, companions);
		
		ItemStack particlepacks = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta pad = particlepacks.getItemMeta();
		pad.setDisplayName(ChatColor.GREEN + "Particle Pack");
		ArrayList<String> lore3 = new ArrayList<String>();
		lore3.add("§7Make particles appear above");
		lore3.add("§7your head while standing in");
		lore3.add("§7lobbies using these cool");
		lore3.add("§7particle effects!");
		lore3.add("");
		lore3.add("§7Unlocked: §a8/8");
		lore3.add("");
		lore3.add("§eClick to browse!");
		pad.setLore(lore3);
		particlepacks.setItemMeta(pad);
		collectibles.setItem(13, particlepacks);
		
		ItemStack hats = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemMeta hd = hats.getItemMeta();
		hd.setDisplayName(ChatColor.GREEN + "Hats");
		ArrayList<String> lore4 = new ArrayList<String>();
		lore4.add("§7Collect all the hats and");
		lore4.add("§7wear them while in a lobby.");
		lore4.add("§7Some hats are disabled in");
		lore4.add("§7certain game lobbies.");
		lore4.add("");
		lore4.add("§7Unlocked: §e82/162");
		lore4.add("§6Halloween: §e7/8");
		lore4.add("§eSummer: §e6/12");
		lore4.add("§aHoliday: §e8/11");
		lore4.add("");
		lore4.add("§eClick to browse!");
		hd.setLore(lore4);
		hats.setItemMeta(hd);
		collectibles.setItem(15, hats);
		
		ItemStack banners = new ItemStack(Material.BANNER, 1, (byte)11);
		ItemMeta bd = banners.getItemMeta();
		bd.setDisplayName(ChatColor.GREEN + "Banners");
		ArrayList<String> lore6 = new ArrayList<String>();
		lore6.add("§7Unlock all the Banners and");
		lore6.add("§7wear them as hats while in");
		lore6.add("§7a lobby. They can also be");
		lore6.add("§7used as items in housing!");
		lore6.add("");
		lore6.add("§7Unlocked: §c0/12");
		lore6.add("");
		lore6.add("§eClick to browse!");
		bd.setLore(lore6);
		banners.setItemMeta(bd);
		collectibles.setItem(17, banners);
		
		ItemStack suits = new ItemStack(Material.GOLD_LEGGINGS, 1);
		ItemMeta sd = suits.getItemMeta();
		sd.setDisplayName(ChatColor.GREEN + "Suits");
		ArrayList<String> lore7 = new ArrayList<String>();
		lore7.add("§7Collect and wear all the");
		lore7.add("§7pieces from a specific suit");
		lore7.add("§7while in a lobby to gain");
		lore7.add("§7unique effects!");
		lore7.add("");
		lore7.add("§7Unlocked: §c37/120");
		lore7.add("§6Halloween: §e4/8");
		lore7.add("§eSummer: §c6/24");
		lore7.add("§eRewards: §c0/4");
		lore7.add("§aHoliday: §c6/16");
		lore7.add("");
		lore7.add("§eClick to browse!");
		sd.setLore(lore7);
		suits.setItemMeta(sd);
		collectibles.setItem(27, suits);
		
		int sbDust;
		
		if (Hypixel.getPlugin().getConfig().contains("playerdata." + p.getUniqueId() + ".dust")) {
			Object dust = Hypixel.getPlugin().getConfig().get("playerdata." + p.getUniqueId() + ".dust");

			sbDust = (int) dust;

		} else {
			sbDust = 0;
		}
		
		ItemStack colchest = new ItemStack(Material.CHEST, 1);
		ItemMeta ccd = colchest.getItemMeta();
		ccd.setDisplayName(ChatColor.GREEN + "Collectibles");
		ArrayList<String> lore5 = new ArrayList<String>();
		lore5.add("§7Mystery Dust: §b" + sbDust);
		lore5.add("§7Hypixel Credits: §b1,060");
		lore5.add("");
		lore5.add("§7Collect fun cosmetic items!");
		lore5.add("§7Get new items by opening");
		lore5.add("§bMystery Boxes §7or hitting milestone rewards.");
		lore5.add("§7Some can also be crafted using");
		lore5.add("§bMystery Dust§7.");
		lore5.add("");
		lore5.add("§bMystery Dust can be earned by");
		lore5.add("§7opening §bMystery Boxes.");
		lore5.add("");
		lore5.add("§7You can support the " + Hypixel.getPlugin().getConfig().getString("store-website") + " server by");
		lore5.add("§7buying §bMystery Boxes §7on our store:");
		lore5.add("");
		lore5.add("§e" + Hypixel.getPlugin().getConfig().getString("store-website"));
		ccd.setLore(lore5);
		colchest.setItemMeta(ccd);
		collectibles.setItem(49, colchest);
		
		p.openInventory(collectibles);
		
		
		return true;
		
	}
}
