package me.craigcontreras.Skyblockian.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

public class CriticalDamageListener 
implements Listener
{
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e)
	{
		if (e.getDamager() instanceof Player)
		{
			Player damager = (Player) e.getDamager();
			
			if (damager.getVelocity().getY() < 0
					&& !(damager.isOnGround())
					&& !(damager.getLocation().getBlock().getType().equals(Material.WATER))
					&& !(damager.getLocation().getBlock().getType().equals(Material.LADDER))
					&& !(damager.getLocation().getBlock().getType().equals(Material.VINE))
					&& !(damager.getLocation().getBlock().getRelative(0, 1, 0).getType().equals(Material.VINE)
							&& !(damager.hasPotionEffect(PotionEffectType.BLINDNESS)
									&& damager.getVehicle() == null)))
			{
				double origDamage = e.getDamage();
				e.setDamage(origDamage * 0.3);
			}
		}
	}
}
