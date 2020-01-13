package dev.m69.iessentials.command.teleport;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.m69.iessentials.main.iEssentials;
import dev.m69.iessentials.playerdata.PlayerData;
import dev.m69.iessentials.utils.Utils;

public class Teleport implements CommandExecutor {
	
	iEssentials plugin;
	PlayerData data;
	public Teleport(final iEssentials plugin, final PlayerData data) {
		this.plugin = plugin;
		this.data = data;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println("Players only.");
		} else {
			Player p = (Player) sender;
			if (p.hasPermission("permission.teleport")) {
				if (args.length < 1 || args.length == 0) {
					p.sendMessage(Utils.c("&cUsage: /tp <player> <player>"));
					return true;
				}
				
				if (args.length == 1) {
					OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
					
					if (!t.hasPlayedBefore()) {
						p.sendMessage(Utils.c("&c" + args[0] + " not found!"));
						return true;
					}
					
					if (t.isOnline()) {
						p.teleport((Player) t);
						p.sendMessage(Utils.c("&eTeleporting to &f" + t.getName() + " &elocation"));
					} else {
						p.teleport(this.data.getL(t));
						p.sendMessage(Utils.c("&eTeleporting to &f" + t.getName() + "'s &elast location where they logged off") );
					}
				}
				
				if (args.length == 2 && p.hasPermission("command.teleport.here")) {
					OfflinePlayer p1 = Bukkit.getOfflinePlayer(args[0]);
					OfflinePlayer p2 = Bukkit.getOfflinePlayer(args[1]);
					
					if (!p1.hasPlayedBefore()) {
						p.sendMessage(Utils.c("&c" + p1.getName() + " not found!"));
						return true;
					}
					
					if (!p2.hasPlayedBefore()) {
						p.sendMessage(Utils.c("&c" + p2.getName() + " not found!"));
					}
					
					if (p1.isOnline()) {
						Player op1 = (Player) p1;
						if (p2.isOnline()) {
							Player op2 = (Player) p2;
							
							op1.teleport(op2);
							p.sendMessage(Utils.c("&eTeleporting &f" + p1.getName() + " &eto &f" + p2.getName() + "'s &elocation"));
						} else {
							op1.teleport(this.data.getL(p2));
							
							p.sendMessage(Utils.c("&eTeleporting &f" + p1.getName() + " &eto &f" + p2.getName() + "'s &elast location where they logged off"));
						}
					}

				}
			} else {
				p.sendMessage(Utils.c("&cNo permission."));
				return true;
			}
		}
		return false;
	}
	
	

}
