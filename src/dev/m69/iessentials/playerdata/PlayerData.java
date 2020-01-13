package dev.m69.iessentials.playerdata;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import dev.m69.iessentials.main.iEssentials;

public class PlayerData implements Listener {
	
	iEssentials plugin;
	public PlayerData(final iEssentials plugin) {
		this.plugin = plugin;
	}
	
	public File getPlayerFile(OfflinePlayer p) {
		final File playerDataFile = new File(this.plugin.getDataFolder() + File.separator + "playerdata", p.getUniqueId() + ".yml");
		return playerDataFile;
	}
	
	public FileConfiguration getPlayerData(OfflinePlayer p) {
		File file = this.getPlayerFile(p);
		FileConfiguration playerData = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
		return playerData;
	}
	
	@EventHandler
	public void onJoin(final PlayerJoinEvent e) {
		Player p = e.getPlayer();
		File file = this.getPlayerFile(p);
		FileConfiguration playerData = this.getPlayerData(p);
		
		playerData.set("Player IGN:", p.getName());
		
		try {
			playerData.save(file);
		} catch (IOException e1) {
			System.out.println("Failed to save " + p.getUniqueId() + ".yml!");
			p.kickPlayer("§cAn error occured.");
		}
		
	}
	
	@EventHandler
	public void onQuit(final PlayerQuitEvent e) {
		Player p = e.getPlayer();
		File file = this.getPlayerFile(p);
		FileConfiguration playerData = this.getPlayerData(p);
		
		Location l = p.getLocation();
		
		playerData.set("location.x", l.getX());
		playerData.set("location.y", l.getY());
		playerData.set("location.z", l.getZ());
		playerData.set("location.world", p.getWorld().getName());
		
		try {
			playerData.save(file);
		} catch (IOException e1) {
			System.out.println("Failed to save " + p.getUniqueId() + ".yml!");
		}
	}
	
	public Location getL(OfflinePlayer p) {
		FileConfiguration playerData = this.getPlayerData(p);
		Location l = new Location(Bukkit.getWorld(playerData.getString("location.world")), playerData.getDouble("location.x"), playerData.getDouble("location.y"), playerData.getDouble("location.z"));
		
		return l;
		
	}
	
	public void setL(OfflinePlayer p, double x, double y, double z, String world) {
		File file = this.getPlayerFile(p);
		FileConfiguration playerData = this.getPlayerData(p);
		
		playerData.set("location.x", x);
		playerData.set("location.y", y);
		playerData.set("location.z", z);
		playerData.set("location.world", world);
		
		try {
			playerData.save(file);
		} catch (IOException e) {
			System.out.println("Failed to save " + p.getUniqueId() + ".yml!");
		}
	}

}
