package me.craigcontreras.Skyblockian.permissions;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.craigcontreras.Skyblockian.permissions.managers.PermissionsManager;

public class PermissionListeners 
implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		
		PermissionsManager.getPManager().reload(p);
		PermissionsManager.getPManager().refresh(p);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		PermissionsManager.getPManager().clear(p);
	}
}
