package dev.m69.iessentials.command.helpop;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Report implements CommandExecutor {

    @Getter
    public static String arraylist;

    @SuppressWarnings("deprecation")
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("report")) {
            if (sender instanceof Player) {
                final Player p = (Player)sender;
                if (args.length >= 2) {
                    final Player target = Bukkit.getPlayer(args[0]);
                    if (sender == target) {
                        sender.sendMessage(ChatColor.RED + "You cannot report yourself!");
                        return true;
                    }
                    if (target != null) {
                        final String report = ChatColor.GREEN + "Your report has been sent to all online staff members.";
                        final StringBuilder buffer = new StringBuilder();
                        for (int i = 1; i < args.length; ++i) {
                            buffer.append(' ').append(args[i]);
                        }
                        final String reason = buffer.toString();
                        p.sendMessage(String.valueOf("" + report));
                        for (final Player staffer : Bukkit.getServer().getOnlinePlayers()) {
                            if (staffer.hasPermission("permission.staff")) {
                            	staffer.sendMessage("§6");
                                staffer.sendMessage("§6§lPLAYER REPORT:");
                                staffer.sendMessage("§6");
                                staffer.sendMessage("§eReporter: " + ChatColor.WHITE + p.getName());
                                staffer.sendMessage("§eReported: " + ChatColor.WHITE + target.getName());
                                staffer.sendMessage("§eReason: " + ChatColor.WHITE + reason);
                                staffer.sendMessage("§6");
                            }
                        }
                    }
                    else {
                        p.sendMessage("Player is not online");
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED + "/report <player> <reason>");
                }
            }
            else {
                sender.sendMessage("");
            }
        }
        return true;
    }
}
