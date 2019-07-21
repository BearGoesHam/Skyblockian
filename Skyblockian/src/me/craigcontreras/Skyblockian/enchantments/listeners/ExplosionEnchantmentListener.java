package me.craigcontreras.Skyblockian.enchantments.listeners;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.craigcontreras.Skyblockian.Skyblockian;

public class ExplosionEnchantmentListener 
implements Listener, TextFormat
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e)
	{
		Player shooter = (Player) ((Arrow) e.getDamager()).getShooter();
		
		ItemStack item = shooter.getItemInHand();
		
		if (shooter.getLocation().getWorld().equals(Skyblockian.getCore().world)) return;
		
		if (!(e.getEntity() instanceof Player)) return;
		
		if (e.getDamager() instanceof Arrow)
		{
			if (item.hasItemMeta())
			{
				if (item.getItemMeta().hasLore())
				{
					for (int i = 0; i < item.getItemMeta().getLore().size(); i++)
					{
						String[] fLore = ChatColor.stripColor(item.getItemMeta().getLore().get(i)).split(" ");
						String eLore = fLore[0];

						if (eLore.contains("Explosion"))
						{
							if (!Skyblockian.getCore().ifInRegion(shooter))
							{
								shooter.sendMessage(prefix + "You're in a protected region! You cannot use this custom enchantment!");
								e.setCancelled(true);
							}
							else{
								e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 1.3F);
							}
						}
					}
				}
			}
		}
	}
}
