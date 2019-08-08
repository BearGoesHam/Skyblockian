package com.skyblockian.Skyblockian.enchantments.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.skyblockian.Skyblockian.Skyblockian;
import com.skyblockian.Skyblockian.interfaces.TextFormat;

public class PoisonEnchantmentListener 
implements Listener, TextFormat
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e)
	{
		Player p = (Player) e.getEntity();
		Player shooter = (Player) ((Arrow) e.getDamager()).getShooter();
		ItemStack item = shooter.getItemInHand();

		if (shooter.getLocation().getWorld().equals(Skyblockian.getCore().world)) return;
		
		if (!(e.getEntity() instanceof Player)) return;

		if (!(e.getDamager() instanceof Arrow)) return;

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

						if (eLore.contains("Poison"))
						{
							if (!Skyblockian.getCore().ifInRegion(shooter))
							{
								shooter.sendMessage(prefix + "You're in a protected region! You cannot use this custom enchantment!");
								e.setCancelled(true);
							}
							else {
								p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 30 * 20, 1));
								p.sendMessage(prefix + "You've been poisoned!");
							}
						}
					}
				}
			}
		}
	}
}