package dev.m69.iessentials.staff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.Main;
import org.bukkit.entity.Player;

public class WhoIs
  implements CommandExecutor
{
  public Main plugin;
  
  
  public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString)
  {
    if (paramCommand.getName().equalsIgnoreCase("whois"))
    {
      if ((paramCommandSender.hasPermission("command.whois")) || (paramCommandSender.isOp()))
        {
          if (paramArrayOfString.length == 0)
          {
            paramCommandSender.sendMessage(ChatColor.RED + "/whois <player>");
            return false;
          }
          if (paramArrayOfString.length > 1)
          {
            paramCommandSender.sendMessage(ChatColor.RED + "Invalid arguments");
            return false;
          }
          Player localPlayer = Bukkit.getServer().getPlayer(paramArrayOfString[0]);
          if (localPlayer == null)
          {
            paramCommandSender.sendMessage(ChatColor.RED + "The player '" + paramArrayOfString[0] + "' is not online.");
            return false;
          }
          if ((paramArrayOfString.length == 1) && (localPlayer != null))
          {
        	paramCommandSender.sendMessage(ChatColor.GRAY + "§m--------------------------------------");
        	paramCommandSender.sendMessage(ChatColor.GOLD + "§lInfomation: ");
            paramCommandSender.sendMessage(ChatColor.YELLOW + " Username " + ChatColor.WHITE + localPlayer.getName());
            paramCommandSender.sendMessage(ChatColor.YELLOW + " Gamemode: " + ChatColor.WHITE + localPlayer.getGameMode().name().toUpperCase());
            paramCommandSender.sendMessage(ChatColor.YELLOW + " UUID: " + ChatColor.WHITE + localPlayer.getUniqueId());
            paramCommandSender.sendMessage(ChatColor.GOLD + "§l Location: ");
            paramCommandSender.sendMessage(ChatColor.YELLOW + " World: " + ChatColor.WHITE + localPlayer.getWorld().getName());
            paramCommandSender.sendMessage(ChatColor.YELLOW + " Coords: " + ChatColor.WHITE + localPlayer.getLocation().getBlock().getX() + ChatColor.YELLOW + ", " + ChatColor.WHITE + localPlayer.getLocation().getBlock().getY() + ChatColor.YELLOW + ", " + ChatColor.WHITE + localPlayer.getLocation().getBlock().getZ() + ChatColor.YELLOW + ".");
            paramCommandSender.sendMessage(ChatColor.GOLD + "§lClient Infomation: ");
            paramCommandSender.sendMessage(ChatColor.YELLOW + " Version: §fComming Soon");
            paramCommandSender.sendMessage(ChatColor.YELLOW + " Type: §fComming Soon");
            paramCommandSender.sendMessage(ChatColor.GRAY + "§m--------------------------------------");
            return true;
          }
        }
      }
      if ((!paramCommandSender.hasPermission("command.whois")) && (!paramCommandSender.isOp()))
      {
        paramCommandSender.sendMessage(ChatColor.RED + "No Permissions");
        return false;
      }
    return false;
  }


@SuppressWarnings("unused")
private ChatColor getHandle() {
	// TODO Auto-generated method stub
	return null;
}


@SuppressWarnings("unused")
private Object getVersion() {
	// TODO Auto-generated method stub
	return null;
}
}
