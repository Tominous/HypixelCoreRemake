package fi.torksi.hypixel.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener{
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onJoin(PlayerJoinEvent e) {
		SManager.getPlayers().put(e.getPlayer().getUniqueId(), new SPlayer(e.getPlayer().getName()));
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerChangeWorld(PlayerChangedWorldEvent e) {
		e.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerKick(PlayerKickEvent e) {
		SManager.getPlayers().remove(e.getPlayer().getUniqueId());
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onQuit(PlayerQuitEvent e) {
		SManager.getPlayers().remove(e.getPlayer().getUniqueId());
	}
}
