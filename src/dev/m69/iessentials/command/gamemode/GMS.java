package dev.m69.iessentials.command.gamemode;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.m69.iessentials.utils.Utils;

public class GMS implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println("Players only.");
		} else {
			Player p = (Player) sender;
			if (p.hasPermission("command.gamemode")) {
				if (args.length == 2) {
					Player t = Bukkit.getPlayer(args[0]);
					
					if (t == null) {
						p.sendMessage(Utils.c("&c" + args[0] + " not found!"));
						return true;
					}
					
					t.setGameMode(GameMode.SURVIVAL);
					p.sendMessage(Utils.c("&eYou have set &f" + t.getName() + "'s &egamemode to &fSURVIVAL"));
				} else {
					p.setGameMode(GameMode.SURVIVAL);
					p.sendMessage(Utils.c("&eYou have set your gamemode to &fSURVIVAL"));
				}
			}
		}
		return false;
	}

}
