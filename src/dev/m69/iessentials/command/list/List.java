package dev.m69.iessentials.command.list;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.m69.iessentials.utils.Utils;

public class List implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println("Players only.");
		} else {
			Player p = (Player) sender;
			int staff = 0;
			for (Player p1 : Bukkit.getOnlinePlayers()) {
				if (p1.hasPermission("permission.staff")) {
					staff++;
				}
			}
			p.sendMessage(Utils.c("&7"));
			p.sendMessage(Utils.c("&eThere are currently &f" + Bukkit.getServer().getOnlinePlayers().length + " &eonline players."));
			p.sendMessage(Utils.c("&eThere are currently &f" + staff + " &estaff members online."));
			p.sendMessage(Utils.c("&7"));
			staff = 0;
		}
		return false;
	}
	
	

}
