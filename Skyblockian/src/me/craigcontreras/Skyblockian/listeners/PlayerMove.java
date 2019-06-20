package me.craigcontreras.Skyblockian.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import me.craigcontreras.Skyblockian.island.Island;
import me.craigcontreras.Skyblockian.island.Island2;
import me.craigcontreras.Skyblockian.island.IslandManager;

public class PlayerMove 
implements Listener, TextFormat
{
	@EventHandler
	public void onMove(PlayerMoveEvent e)
	{
		Player p = e.getPlayer();
		if (p.getWorld().getName().equals(
				Skyblockian.getCore().world.getName()))
		{
			Island i = IslandManager.getIM().getIsland(p);
			Island2 i2 = IslandManager.getIM().getIsland2(p);
			
			if ((i != null)
					&& (i.isAt(e.getFrom())) && 
					(!i.isAt(e.getTo())))
			{
				e.setCancelled(true);
				p.sendMessage(prefix + "You may not leave the bounds of your island.");
			}else if ((i2 != null)
					&& (i2.isAt(e.getFrom())) && 
					(!i2.isAt(e.getTo())))
			{
				e.setCancelled(true);
				p.sendMessage(prefix + "You may not leave the bounds of your island.");
			}
		}
	}
}
