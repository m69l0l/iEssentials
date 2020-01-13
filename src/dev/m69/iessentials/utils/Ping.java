package dev.m69.iessentials.utils;

import dev.m69.iessentials.main.iEssentials;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public class Ping implements TabExecutor {

    @Getter
    private iEssentials plugin;

    public Ping() {
        this.plugin = iEssentials.getPlugin();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player toCheck;
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Usage: /ping <player>");
                return true;
            }
            toCheck = (Player)sender;
        }
        else {
            toCheck = Bukkit.getPlayer(StringUtils.join((Object[])args));
        }
        if (toCheck == null) {
            sender.sendMessage(ChatColor.RED + "No player named '" + StringUtils.join((Object[])args) + "' found online.");
            return true;
        }
        sender.sendMessage(ChatColor.YELLOW + toCheck.getName() + (toCheck.getName().endsWith("s") ? "'" : "'s") + ChatColor.WHITE + " ping: " + ChatColor.YELLOW + toCheck.getPing() + "ms");
        if (sender instanceof Player && !toCheck.getName().equals(sender.getName())) {
            final Player senderPlayer = (Player)sender;
            sender.sendMessage(ChatColor.WHITE + "Ping difference: " + ChatColor.YELLOW + (Math.max(senderPlayer.getPing(), toCheck).getPing()) - Math.min(senderPlayer.getPing(), toCheck.getPing())) + "ms" + ChatColor.YELLOW + ".");
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
        List<String> list = new ArrayList<>();
        for (Player players : Bukkit.getOnlinePlayers()) {
            list.add(players.getName());
        }
        return list;
    }
    
}