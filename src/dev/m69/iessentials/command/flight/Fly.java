package dev.m69.iessentials.command.flight;

import java.io.PrintStream;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


@SuppressWarnings("unused")
public class Fly implements CommandExecutor 
	{

@SuppressWarnings("deprecation")
public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args)
{
  if ((sender instanceof Player))
  {
    if (cmd.getName().equalsIgnoreCase("fly"))
    {
      Player player = (Player)sender;
      if (args.length == 0) {
        if (player.hasPermission("command.fly"))
        {
          if (!player.getAllowFlight())
          {
            player.sendMessage(ChatColor.YELLOW + "You have " + ChatColor.GREEN + "Enabled " + ChatColor.YELLOW + "your flight");
            player.setAllowFlight(true);
          }
          else
          {
            sender.sendMessage(ChatColor.YELLOW + "You have " + ChatColor.RED + "Disabled " + ChatColor.YELLOW + "your flight");
            player.setAllowFlight(false);
          }
        }
        else {
          sender.sendMessage(ChatColor.RED + "No Permissions");
        }
      }
      if (args.length == 1) {
        if (player.hasPermission("permission.admin"))
        {
          Player player2 = Bukkit.getPlayer(args[0]);
          if (player2 == null)
          {
            sender.sendMessage(ChatColor.RED + "Player offline");
            return true;
          }
          if (!player2.getAllowFlight())
          {
            sender.sendMessage(ChatColor.YELLOW + "You have " + ChatColor.GREEN + "enabled " + ChatColor.YELLOW + "flight mode for " + ChatColor.WHITE + player2.getName());
            player2.setAllowFlight(true);
            player2.sendMessage(ChatColor.YELLOW + "Your flight has been " + ChatColor.GREEN + "Enabled " + ChatColor.YELLOW + "by " + ChatColor.WHITE + player.getName());
          }
          else
          {
            sender.sendMessage(ChatColor.YELLOW + "You have " + ChatColor.RED + "disabled " + ChatColor.YELLOW + "flight mode for " + ChatColor.WHITE + player2.getName());
            player2.setAllowFlight(false);
            player2.sendMessage(ChatColor.YELLOW + "Your flight has been " + ChatColor.RED + "Disabled " + ChatColor.YELLOW + "by " + ChatColor.WHITE + player.getName());
          }
        }
        else
        {
          sender.sendMessage(ChatColor.RED + "No permissions");
        }
      }
    }
    Player[] arrayOfPlayer1;
    int j;
    int i;
    if ((cmd.getName().equalsIgnoreCase("flyall")) && 
      (sender.hasPermission("permission.owner")))
    {
      Player[] allplayers = Bukkit.getServer().getOnlinePlayers();
      j = (arrayOfPlayer1 = allplayers).length;
      for (i = 0; i < j; i++)
      {
        Player allserver = arrayOfPlayer1[i];
        allserver.setAllowFlight(true);
        allserver.sendMessage(ChatColor.GREEN + "Fly Enabled for ALL the server!");
      }
    }
    if ((cmd.getName().equalsIgnoreCase("disableflyall")) && 
      (sender.hasPermission("permission.owner")))
    {
      Player[] allplayers = Bukkit.getServer().getOnlinePlayers();
      j = (arrayOfPlayer1 = allplayers).length;
      for (i = 0; i < j; i++)
      {
        Player allserver = arrayOfPlayer1[i];
        allserver.setAllowFlight(false);
        allserver.sendMessage(ChatColor.GREEN + "Flight has been disabled for ALL the server!");
      }
    }
  }
  else
  {
    sender.sendMessage(ChatColor.RED + "You must be an user!");
  }
  return false;
}
}
