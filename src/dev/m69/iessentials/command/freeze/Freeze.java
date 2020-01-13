package dev.m69.iessentials.command.freeze;

import dev.m69.iessentials.main.iEssentials;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Freeze
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
  {
    if (sender.hasPermission("permission.staff"))
    {
      Player p = (Player)sender;
      if (args.length != 1)
      {
        sender.sendMessage("§cUsage: /freeze <player>");
      }
      else if (args.length == 1)
      {
        Player cible = Bukkit.getPlayer(args[0]);
        if (iEssentials.freeze.contains(cible))
        {
          iEssentials.freeze.remove(cible);
          sender.sendMessage("§eYou have unfrozed §f" + cible.getName());
          cible.sendMessage("§aYou have been unfrozen, thanks for your cooperation");
        }
        else
        {
          iEssentials.freeze.add(cible);
          
          p.sendMessage("§eYou have frozed §f" + cible.getName());
          cible.sendMessage("§f");
          cible.sendMessage("§eYou have been §c§lFROZEN §eby a staffmember");
          cible.sendMessage("§eYou have §f3 minutes §eto join our discord");
          cible.sendMessage("§eDiscord: §fYour Discord Link");
          cible.sendMessage("§4§lDO NOT §elogout or else you will be instantly banned");
          cible.sendMessage("§f");
        }
      }
    }
    else
    {
      sender.sendMessage("§cNo Permissions");
    }
    return true;
  }
}
