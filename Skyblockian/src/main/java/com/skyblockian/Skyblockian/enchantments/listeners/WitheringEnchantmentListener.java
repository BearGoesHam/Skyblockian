package com.skyblockian.Skyblockian.enchantments.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.skyblockian.Skyblockian.Skyblockian;
import com.skyblockian.Skyblockian.interfaces.TextFormat;

public class WitheringEnchantmentListener
implements Listener, TextFormat
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e)
	{
		Player k = (Player) e.getDamager();
		Player p = (Player) e.getEntity();
		
		ItemStack item = p.getItemInHand();
		
		if (k.getLocation().getWorld().equals(Skyblockian.getCore().world)) return;
		
		if (!(e.getEntity() instanceof Player)) return;

		if (item.hasItemMeta())
		{
			if (item.getItemMeta().hasLore())
			{
				for (int i = 0; i < item.getItemMeta().getLore().size(); i++)
				{
					String[] fLore = ChatColor.stripColor(item.getItemMeta().getLore().get(i)).split(" ");
					String eLore = fLore[0];

					if (eLore.contains("Withering"))
					{
						if (!Skyblockian.getCore().ifInRegion(p))
						{
							p.sendMessage(prefix + "You're in a protected region! You cannot use this custom enchantment!");
							e.setCancelled(true);
						}
						else {
							if (Skyblockian.getCore().randomize(1, 25) == 1)
							{
								p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 30 * 20, 1));
							}
						}
					}
				}
			}
		}
	}
}