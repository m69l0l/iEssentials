package dev.m69.iessentials.command.teleport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.m69.iessentials.utils.Utils;

public class TeleportHere implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println("Players only.");
		} else {
			final Player p = (Player) sender;
			
			if (p.hasPermission("command.teleport.here")) {
				if (args.length != 1) {
					p.sendMessage(Utils.c("&cUsage: /tphere <player>"));
					return true;
				}
				
				if (args.length == 1) {
					Player t = Bukkit.getPlayer(args[0]);
					
					if (t == null) {
						p.sendMessage(Utils.c("&c" + args[0] + " not found!"));
						return true;
					}
					
					t.teleport(p);
					p.sendMessage(Utils.c("&eTeleporting &f" + t.getName() + " &eto your location"));
				}
			} else {
				p.sendMessage(Utils.c("&cNo permission."));
				return true;
			}
		}
		return false;
	}

}
