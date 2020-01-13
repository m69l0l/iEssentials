package dev.m69.iessentials.command.helpop;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.m69.iessentials.utils.Utils;
import net.minecraft.util.com.google.common.base.Joiner;

public class HelpOp implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println("Players only.");
		} else {
			Player p = (Player) sender;
			if (args.length == 0) {
				p.sendMessage(Utils.c("&cUsage: /helpop <message>"));
				return true;
			}
			
			String message = Joiner.on(' ').join(args);
			for (Player p1 : Bukkit.getOnlinePlayers()) {
				if (p1.hasPermission("permission.staff")) {
					p1.sendMessage(Utils.c("&f"));
					p1.sendMessage(Utils.c("&6&lPLAYER REQUEST:"));
					p1.sendMessage(Utils.c("&f"));
					p1.sendMessage(Utils.c("&eUsername: &f" + p.getName()));
					p1.sendMessage(Utils.c("&eRequest: &f" + message));
					p1.sendMessage(Utils.c("&f"));
				}
			}
			
			p.sendMessage(Utils.c("&aYour request has been sent to all online staff members."));
		}
		return false;
	}

}
