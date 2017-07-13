package fi.torksi.hypixel.scoreboard;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import fi.torksi.hypixel.Hypixel;
public class SManager {
	private static ConcurrentHashMap<UUID, SPlayer> players = new ConcurrentHashMap<>();
	
	public static ConcurrentHashMap<UUID, SPlayer> getPlayers() {
		return players;
	}
	
	public static void onEnable() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			
			players.put(p.getUniqueId(), new SPlayer(p.getName()));
			p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		}
		
		Bukkit.getScheduler().runTaskTimerAsynchronously(Hypixel.getPlugin(), new Runnable() {
			
			@Override
			public synchronized void run() {
				for(SPlayer player : players.values()) {
					player.updateScoreboard();
				}
			}
		}, 10L, 10L);
	}
}
