package dev.m69.iessentials.command.teleport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.m69.iessentials.utils.Utils;

public class TeleportAll implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println("Players only.");
		} else {
			Player p = (Player) sender;
			
			if (p.hasPermission("command.teleport.all")) {
				for (Player p1 : Bukkit.getServer().getOnlinePlayers()) {
					p1.teleport(p);
				}
				p.sendMessage(Utils.c("&eTeleporting all players to your current location"));
			} else {
				p.sendMessage(Utils.c("&cNo permission."));
			}
		}
		return false;
	}

}
