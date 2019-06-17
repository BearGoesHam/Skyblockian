package me.craigcontreras.Skyblockian.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.island.Island;
import me.craigcontreras.Skyblockian.island.IslandManager;

public class PlayerRespawn 
implements Listener
{
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e)
	{
		Player p = e.getPlayer();
		if ((p.getWorld().getName().contentEquals(Skyblockian.getCore().world.getName()))
				&& (IslandManager.getIM().hasIsland(p)))
		{
			Island i = IslandManager.getIM().getIsland(p);
			e.setRespawnLocation(i.getSpawnLoc());
		}
	}
}
