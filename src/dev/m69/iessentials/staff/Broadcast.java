package dev.m69.iessentials.staff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class Broadcast implements CommandExecutor {

    @SuppressWarnings("unused")
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("broadcast")) {
            if (sender.hasPermission("command.broadcast")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou must supply a message!"));
                }
                final Collection<? extends Player> onlinePlayers;
                    final StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < args.length; ++i) {
                        sb.append(String.valueOf(String.valueOf(args[i])) + " ");
                    }
                    final String message = sb.toString();
                    if (sender instanceof Player) {
                        final Player player = (Player)sender;
                    }
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&4Alert&8] " + ChatColor.WHITE + message));
                }
            }
            else {
                sender.sendMessage("Unknown Command.");
            }
        return false;
    }
}