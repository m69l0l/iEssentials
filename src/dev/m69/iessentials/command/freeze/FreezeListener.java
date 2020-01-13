package dev.m69.iessentials.command.freeze;

import dev.m69.iessentials.main.iEssentials;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeListener
  implements Listener
{
  @EventHandler
  public void onMove(PlayerMoveEvent e)
  {
    if (iEssentials.freeze.contains(e.getPlayer())) {
      e.setCancelled(true);
    }
  }
}
