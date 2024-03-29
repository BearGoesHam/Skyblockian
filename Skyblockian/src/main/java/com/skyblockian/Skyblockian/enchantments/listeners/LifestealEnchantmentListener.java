package com.skyblockian.Skyblockian.enchantments.listeners;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.skyblockian.Skyblockian.Skyblockian;

public class LifestealEnchantmentListener 
implements Listener, TextFormat
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e)
	{
		if (((e.getDamager() instanceof Player))
				&& ((e.getEntity() instanceof Player)))
		{
			Player k = (Player) e.getDamager();
			Player p = (Player) e.getEntity();
			
			ItemStack item = k.getItemInHand();

			if (item.hasItemMeta())
			{
				if (item.getItemMeta().hasLore())
				{
					for (int i = 0; i < item.getItemMeta().getLore().size(); i++)
					{
						String[] fLore = ChatColor.stripColor(item.getItemMeta().getLore().get(i)).split(" ");
						String eLore = fLore[0];

						if (eLore.contains("Lifesteal"))
						{
							if (!Skyblockian.getCore().ifInRegion(p))
							{
								p.sendMessage(prefix + "You're in a protected region! You cannot use this custom enchantment!");
								e.setCancelled(true);
							}
							else {
								if (Skyblockian.getCore().randomize(1, 25) == 1)
								{
									double kHealth = k.getHealth();
									double pHealth = p.getHealth();

									p.setHealth(pHealth - 2.0D);
									k.setHealth(kHealth + 2.0D);
								}
							}
						}
					}
				}
			}
		}
	}
}
