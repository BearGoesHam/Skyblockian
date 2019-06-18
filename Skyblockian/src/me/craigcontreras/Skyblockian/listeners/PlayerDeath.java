package me.craigcontreras.Skyblockian.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitScheduler;

import me.craigcontreras.Skyblockian.Skyblockian;

public class PlayerDeath 
implements Listener
{
	@EventHandler
	public void onDeath(PlayerDeathEvent e)
	{
		Player p = (Player) e.getEntity();
		e.setDeathMessage(null);
		p.getInventory().clear();
		
		BukkitScheduler scheduler = Skyblockian.getCore().getServer().getScheduler();
		scheduler.scheduleSyncDelayedTask(Skyblockian.getCore(), new Runnable()
		{
			@Override
			public void run()
			{
				p.spigot().respawn();
			}
		}, 15L);
	}
}
