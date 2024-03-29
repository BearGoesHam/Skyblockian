package com.skyblockian.Skyblockian.listeners;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.skyblockian.Skyblockian.Skyblockian;
import com.skyblockian.Skyblockian.commands.admin.FreezeCommand;
import com.skyblockian.Skyblockian.commands.admin.SetSpawnCommand;
import com.skyblockian.Skyblockian.island.Island;
import com.skyblockian.Skyblockian.island.IslandManager;

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
			
			if ((i != null)
					&& (i.isAt(e.getFrom())) && 
					(!i.isAt(e.getTo())))
			{
				e.setCancelled(true);
				p.sendMessage(prefix + "You may not leave the bounds of your island.");
			}
		}
		
		if (p.getWorld().getName().equals("spawn") && p.getLocation().getY() < 0)
		{
			SetSpawnCommand.teleportToSpawn(p);
		}
		
		if (FreezeCommand.frozen.contains(p.getUniqueId()))
		{
			e.setCancelled(true);
			p.sendMessage(prefix + "You have been frozen.");
			p.sendMessage(prefix + "To be unfrozen, please go on the Discord (https://discord.gg/7aGRssC) and enter a voice chat.");
			p.sendMessage(prefix + "You'll be moved into another lobby to speak to the staff member that has froze you.");
			p.sendMessage(prefix + "You'll most likely be screenshared.");
			p.sendMessage(prefix + "If you log out now, you'll be automatically banned.");
			p.sendMessage(prefix + "If you do not follow set instructions, you'll be banned.");
		}
		else {
			return;
		}
	}
}
