package dev.m69.iessentials.command.gamemode;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.m69.iessentials.utils.Utils;

public class Gamemode implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println("Players only.");
		} else {
			Player p = (Player) sender;
			if (p.hasPermission("command.gamemode")) {
				if (args.length < 1 || args.length > 1) {
					p.sendMessage(Utils.c("&cUsage: /gamemode <gamemode> <player>"));
					return true;
				}
				
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("0") 
							|| args[0].equalsIgnoreCase("survival") 
							|| args[0].equalsIgnoreCase("s")) {
						p.setGameMode(GameMode.SURVIVAL);
						p.sendMessage(Utils.c("&eYou have set your gamemode to &fSURVIVAL."));
					} else if (args[0].equalsIgnoreCase("1")
							|| args[0].equalsIgnoreCase("creative")
							|| args[0].equalsIgnoreCase("c")) {
						p.setGameMode(GameMode.CREATIVE);
						p.sendMessage(Utils.c("&eYou have set your gamemode to &fCREATIVE."));
					} else if (args[0].equalsIgnoreCase("3")
							|| args[0].equalsIgnoreCase("adventure")
							|| args[0].equalsIgnoreCase("a")) {
						p.setGameMode(GameMode.ADVENTURE);
						p.sendMessage(Utils.c("&eYou have set your gamemode to &fADVENTURE"));
					} else {
						p.sendMessage(Utils.c("&cUsage: /gamemode <gamemode> <player>"));
					}
				}
				
				if (args.length == 2) {
					if (p.hasPermission("command.gamemode.others")) {
						Player t = Bukkit.getPlayer(args[1]);
						
						if (t == null) {
							p.sendMessage(Utils.c("&c" + args[1] + " not found!"));
							return true;
						}
						
						if (args[0].equalsIgnoreCase("0") 
								|| args[0].equalsIgnoreCase("survival") 
								|| args[0].equalsIgnoreCase("s")) {
							t.setGameMode(GameMode.SURVIVAL);
							p.sendMessage(Utils.c("&eYou have set &f" + t.getName() + "'s &egamemode to &fSURVIVAL"));
						} else if (args[0].equalsIgnoreCase("1")
								|| args[0].equalsIgnoreCase("creative")
								|| args[0].equalsIgnoreCase("c")) {
							t.setGameMode(GameMode.CREATIVE);
							p.sendMessage(Utils.c("&eYou have set &f" + t.getName() + "'s &egamemode to &fCREATIVE"));
						} else if (args[0].equalsIgnoreCase("3")
								|| args[0].equalsIgnoreCase("adventure")
								|| args[0].equalsIgnoreCase("a")) {
							t.setGameMode(GameMode.ADVENTURE);
							p.sendMessage(Utils.c("&eYou have set &f" + t.getName() + "'s gamemode to &fADVENTURE"));
						} else {
							p.sendMessage(Utils.c("&cUsage: /gamemode <gamemode> <player>"));
						}
					} else {
						p.sendMessage(Utils.c("&cNo permission."));
					}
				}
				
			} else {
				p.sendMessage(Utils.c("&cNo permission."));
			}
		}
		return false;
	}
	
	

}
