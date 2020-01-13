package dev.m69.iessentials.main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.m69.iessentials.utils.Utils;

@SuppressWarnings("unused")
public class Core implements CommandExecutor {

	  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	  {
	  if (cmd.getName().equalsIgnoreCase("iessentials"))
	    {
	      sender.sendMessage("§f");
	      sender.sendMessage("§ePlugin Name: §fiEssentials");
	      sender.sendMessage("§eVersion: §f1.0.4-FIX");
	      sender.sendMessage("§eAuthor: §fm69");
	      sender.sendMessage("§eBuy your own copy at: §fhttps://www.mc-market.org/threads/461979/");
	      sender.sendMessage("§f");
             
	    }
		return false;
	  }
}
