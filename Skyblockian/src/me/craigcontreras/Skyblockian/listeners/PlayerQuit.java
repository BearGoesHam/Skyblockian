package me.craigcontreras.Skyblockian.listeners;

import org.bukkit.event.EventHandler;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.craigcontreras.Skyblockian.commands.admin.VanishCommand;
import me.craigcontreras.Skyblockian.island.IslandManager;

public class PlayerQuit
implements Listener
{
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e)
	{
		IslandManager.getIM().unloadPlayer(e.getPlayer());
		
		if (VanishCommand.vanish.contains(e.getPlayer().getUniqueId()))
		{
			VanishCommand.vanish.remove(e.getPlayer().getUniqueId());
		}
		
		e.setQuitMessage(null);
	}
}
