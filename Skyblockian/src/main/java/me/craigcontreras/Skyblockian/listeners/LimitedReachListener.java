package me.craigcontreras.Skyblockian.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class LimitedReachListener 
implements Listener
{
	public static double reachLimit = 3.0D;
	
	@EventHandler
	public void onEntityHit(EntityDamageByEntityEvent e)
	{
		if (e.getDamager() instanceof Player)
		{
			Player k = (Player) e.getDamager();
			Entity d = e.getEntity();
			double dreach = k.getLocation().distance(d.getLocation());
			
			if (dreach > reachLimit)
			{
				e.setCancelled(true);
			}
		}
	}
}
