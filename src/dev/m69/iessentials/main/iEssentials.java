package dev.m69.iessentials.main;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dev.m69.iessentials.command.flight.Fly;
import dev.m69.iessentials.command.freeze.Freeze;
import dev.m69.iessentials.command.freeze.FreezeListener;
import dev.m69.iessentials.command.gamemode.GMA;
import dev.m69.iessentials.command.gamemode.GMC;
import dev.m69.iessentials.command.gamemode.GMS;
import dev.m69.iessentials.command.gamemode.Gamemode;
import dev.m69.iessentials.command.helpop.HelpOp;
import dev.m69.iessentials.command.helpop.Report;
import dev.m69.iessentials.command.list.List;
import dev.m69.iessentials.command.teleport.Teleport;
import dev.m69.iessentials.command.teleport.TeleportAll;
import dev.m69.iessentials.command.teleport.TeleportHere;
import dev.m69.iessentials.playerdata.PlayerData;
import dev.m69.iessentials.staff.Broadcast;
import dev.m69.iessentials.staff.Slowchat;
import dev.m69.iessentials.staff.Vanish;
import dev.m69.iessentials.staff.WhoIs;
import dev.m69.iessentials.utils.Ping;
import lombok.Getter;
import dev.m69.iessentials.main.Core;

@SuppressWarnings("unused")
public class iEssentials extends JavaPlugin implements Listener {
	
    @Getter
    private FreezeListener freezeListener;
	
	public static ArrayList<Player> freeze = new ArrayList<>();
	
	PlayerData data = new PlayerData(this);
	public Object nomsg;
	public Object reply;
    public final String bypassPerm = "chatslow.bypass";
    public volatile long chatSlow = 0;


    public String muteMessage;
    public String muteStartBroadcast;
    public String muteStopBroadcast;

    public String slowMessage;
    public String slowStartBroadcast;
    public String slowStopBroadcast;
	
	public void onEnable() {
		System.out.println("iEssentials has been enabled successfully");
		System.out.println("iEssentials was created by m69");
		
		registerCommands();
		registerEvents();
	}
	
	public void registerCommands() {
		getCommand("teleport").setExecutor(new Teleport(this, data));
		getCommand("teleportall").setExecutor(new TeleportAll());
		getCommand("teleporthere").setExecutor(new TeleportHere());
		getCommand("gamemode").setExecutor(new Gamemode());
		getCommand("gmc").setExecutor(new GMC());
		getCommand("gms").setExecutor(new GMS());
		getCommand("gma").setExecutor(new GMA());
		getCommand("list").setExecutor(new List());
		getCommand("helpop").setExecutor(new HelpOp());
		getCommand("iessentials").setExecutor(new Core());
	    getCommand("whois").setExecutor(new WhoIs());
	    getCommand("broadcast").setExecutor(new Broadcast());
	    getCommand("report").setExecutor(new Report());
	    getCommand("fly").setExecutor(new Fly());
	    getCommand("flyall").setExecutor(new Fly());
		getCommand("freeze").setExecutor(new Freeze());
		getCommand("vanish").setExecutor(new Vanish());
		getCommand("ping").setExecutor(new Ping());
		getCommand("slowchat").setExecutor(new Slowchat(this));
		
	    Bukkit.getPluginManager().registerEvents(new FreezeListener(), this);
	}
	
	public void registerEvents() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new PlayerData(this), this);
	}
	
	public void registerConfig() {
		this.saveDefaultConfig();
		
		this.reloadConfig();
	}

	public Object getManagerHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	public static iEssentials getPlugin() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getFreezeListener() {
		// TODO Auto-generated method stub
		return null;
	}

}
