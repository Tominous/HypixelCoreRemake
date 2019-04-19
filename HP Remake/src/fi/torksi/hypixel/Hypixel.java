package fi.torksi.hypixel;

import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import de.robingrether.idisguise.api.DisguiseAPI;
import fi.torksi.hypixel.commands.FlyCommand;
import fi.torksi.hypixel.commands.MsgCommand;
import fi.torksi.hypixel.commands.MsgReplyCommand;
import fi.torksi.hypixel.commands.NickCommand;
import fi.torksi.hypixel.commands.VanishCommand;
import fi.torksi.hypixel.commands.admin.AddDustCommand;
import fi.torksi.hypixel.commands.admin.AddXpCommand;
import fi.torksi.hypixel.commands.admin.GamemodeCommand;
import fi.torksi.hypixel.commands.admin.ParticleTestCommand;
import fi.torksi.hypixel.commands.admin.RankCommand;
import fi.torksi.hypixel.commands.reports.WatchDogReportCommands;
import fi.torksi.hypixel.commands.staff.AChatCommand;
import fi.torksi.hypixel.commands.staff.AReplyCommand;
import fi.torksi.hypixel.events.PlayerChat;
import fi.torksi.hypixel.events.PlayerJoin;
import fi.torksi.hypixel.menus.CollectiblesCommand;
import fi.torksi.hypixel.scoreboard.PlayerListener;
import fi.torksi.hypixel.scoreboard.SManager;
import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle.EnumTitleAction;

public class Hypixel extends JavaPlugin {
	
	public static DisguiseAPI api;
	
	public static Hypixel getPlugin() {
		return JavaPlugin.getPlugin(Hypixel.class);
	}
	
	@Override
	public void onEnable() {
		
		// Rekisteröi eventit, komennot jne.
		registerEvents();
		registerCommands();
		registerConfig();
		SManager.onEnable();
		
		
		
		
		// iDisguise API ja siihen liittyvät jutut
		api = getServer().getServicesManager().getRegistration(DisguiseAPI.class).getProvider();
		BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	for (Player p : VanishCommand.vanished) {
            		IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\":\"You are currently §cVANISHED\"}");

					PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.ACTIONBAR, chatTitle);
					PacketPlayOutTitle length = new PacketPlayOutTitle(0, 240, 0);


					((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
					((CraftPlayer) p).getHandle().playerConnection.sendPacket(length);
    			}
            	for (Player p : NickCommand.nicked) {
            		IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\":\"You are currently §cNICKED\"}");

					PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.ACTIONBAR, chatTitle);
					PacketPlayOutTitle length = new PacketPlayOutTitle(0, 240, 0);


					((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
					((CraftPlayer) p).getHandle().playerConnection.sendPacket(length);
    			}
            }
        }, 0L, 20L);
	}
	
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoin(this), this);
		pm.registerEvents(new VanishCommand(), this);
		pm.registerEvents(new NickCommand(), this);
		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new PlayerChat(this), this);
	}
	
	public void registerCommands() {
		getCommand("msg").setExecutor(new MsgCommand());
		getCommand("vanish").setExecutor(new VanishCommand());
		getCommand("nick").setExecutor(new NickCommand());
		getCommand("rank").setExecutor(new RankCommand());
		getCommand("addxp").setExecutor(new AddXpCommand());
		getCommand("xpadd").setExecutor(new AddXpCommand());
		getCommand("deldust").setExecutor(new DelDustCommand());
		getCommand("setdust").setExecutor(new AddDustCommand());
		getCommand("particletest").setExecutor(new ParticleTestCommand());
		getCommand("collectibles").setExecutor(new CollectiblesCommand());
		getCommand("achat").setExecutor(new AChatCommand());
		getCommand("ac").setExecutor(new AChatCommand());
		getCommand("areply").setExecutor(new AReplyCommand());
		getCommand("ar").setExecutor(new AReplyCommand());
		getCommand("watchdogreport").setExecutor(new WatchDogReportCommands());
		getCommand("wdr").setExecutor(new WatchDogReportCommands());
		getCommand("watchdogreport-claim").setExecutor(new WatchDogReportCommands());
		getCommand("wdrc").setExecutor(new WatchDogReportCommands());
		getCommand("watchdogreport-reject").setExecutor(new WatchDogReportCommands());
		getCommand("wdrr").setExecutor(new WatchDogReportCommands());
		getCommand("watchdogreport-accept").setExecutor(new WatchDogReportCommands());
		getCommand("wdra").setExecutor(new WatchDogReportCommands());
		getCommand("gm").setExecutor(new GamemodeCommand());
		getCommand("gamemode").setExecutor(new GamemodeCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("r").setExecutor(new MsgReplyCommand());
		getCommand("reply").setExecutor(new MsgReplyCommand());
	}
	
	public void registerConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
}
