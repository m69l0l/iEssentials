package dev.m69.iessentials.staff;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.permissions.Permissible;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import dev.m69.iessentials.main.iEssentials;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Slowchat implements CommandExecutor, Listener {

    private long slowChatTime;
    private BukkitTask bukkitTask;
    private final Map<UUID, Long> playerChatTimes = new HashMap<>();
    private final iEssentials plugin;

    public Slowchat(iEssentials plugin) {
        this.plugin = plugin;
        this.slowChatTime = 0;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    private long convertToMillis(long time, TimeUnit unit) {
        return TimeUnit.MILLISECONDS.convert(time, unit);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (this.slowChatTime > 0 && !player.hasPermission("permission.staff")) {
            Long timestamp = this.playerChatTimes.get(event.getPlayer().getUniqueId());
            long millis = System.currentTimeMillis();
            long remaining = timestamp == null ? 0L : timestamp - millis;
            if (remaining <= 0L) {
                this.playerChatTimes.put(event.getPlayer().getUniqueId(), millis + this.slowChatTime);
                return;
            }

            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "The chat has been slowed. You can speak in " + DurationFormatUtils.formatDurationWords(remaining, true, true) + ".");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /" + label + " {seconds}/false");
            return true;
        }

        if (args[0].equalsIgnoreCase("false")) {
            if (this.slowChatTime == 0) {
                sender.sendMessage(ChatColor.RED + "Slowchat is already off");
                return true;
            }

            this.slowChatTime = 0;

            if (this.bukkitTask != null) {
                this.bukkitTask.cancel();
                this.bukkitTask = null;
            }

            this.playerChatTimes.clear();
            this.plugin.getServer().broadcastMessage(ChatColor.GREEN + "Chat is no longer being slowed.");
            return true;
        }

        Integer time;
        try {
            time = Integer.parseInt(args[0]);
        } catch (IllegalArgumentException ex) {
            sender.sendMessage(ChatColor.RED + "You must provide a valid number.");
            return true;
        }

        if (time == 0) {
            sender.sendMessage(ChatColor.RED + "Invalid Argument");
            sender.sendMessage(ChatColor.RED + "Turn off slowchat my /slowchat false");
            return true;
        }

        if (this.bukkitTask != null) {
            this.bukkitTask.cancel();
        }

        long interval = 600L;
        this.bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                String slowChatMessage = ChatColor.RED.toString() + "The chat is still being slowed (delay of " +
                        DurationFormatUtils.formatDurationWords(slowChatTime, true, true) + ").";

                Collection<Permissible> recipients = Bukkit.getPluginManager().getPermissionSubscriptions("slowchat.true");
                for (Permissible permissible : recipients) {
                    if (permissible instanceof CommandSender) {
                        CommandSender recipient = (CommandSender) permissible;
                        recipient.sendMessage(slowChatMessage);
                    }
                }
            }
        }.runTaskTimer(this.plugin, interval, interval);

        this.slowChatTime = this.convertToMillis(time, TimeUnit.SECONDS);
        this.plugin.getServer().broadcastMessage(ChatColor.YELLOW + "Chat has been slowed by " + ChatColor.WHITE + sender.getName() + ChatColor.YELLOW + ".");
        return true;
    }
}