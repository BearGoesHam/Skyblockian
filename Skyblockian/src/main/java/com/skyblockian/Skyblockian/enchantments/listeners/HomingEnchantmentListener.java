package com.skyblockian.Skyblockian.enchantments.listeners;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.skyblockian.Skyblockian.Skyblockian;
import com.skyblockian.Skyblockian.interfaces.TextFormat;

public class HomingEnchantmentListener 
implements Listener, TextFormat
{
	private HashMap<Player, Entity> shooter = new HashMap<>();
	private HashMap<UUID, Long> cooldown = new HashMap<>();
	private int cooldownTime = 30;
	
	public HomingEnchantmentListener()
	{
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Skyblockian.getCore(), new Runnable()
		{
			@Override
			public void run()
			{
				for (Player pl : shooter.keySet())
				{
					Entity f = shooter.get(pl);
					
					if (f.isDead() || pl.getLocation().distance(f.getLocation()) > 80)
					{
						f.remove();
						shooter.remove(pl);
						return;
					}
					
					Vector v = pl.getLocation().getDirection();
					f.setVelocity(v);
				}
			}
		}, 0, 1);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRightClick(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		ItemStack item = p.getItemInHand();
		Action a = e.getAction();
		
		if (p.getLocation().getWorld().equals(Skyblockian.getCore().world)
				|| p.getLocation().getWorld().getName().equals("spawn")) return;

		if (a.equals(Action.RIGHT_CLICK_AIR)
				|| a.equals(Action.RIGHT_CLICK_BLOCK))
		{
			if (item.getType().equals(Material.WOOD_SWORD)
					|| item.getType().equals(Material.STONE_SWORD)
					|| item.getType().equals(Material.GOLD_SWORD)
					|| item.getType().equals(Material.IRON_SWORD)
					|| item.getType().equals(Material.DIAMOND_SWORD))
			{
				if (item.hasItemMeta())
				{
					if (item.getItemMeta().hasLore())
					{
						for (int i = 0; i < item.getItemMeta().getLore().size(); i++)
						{
							String[] fLore = ChatColor.stripColor(item.getItemMeta().getLore().get(i)).split(" ");
							String eLore = fLore[0];

							if (eLore.contains("Homing"))
							{
								if (!Skyblockian.getCore().ifInRegion(p))
								{
									p.sendMessage(prefix + "You're in a protected region! You cannot use this custom enchantment!");
									e.setCancelled(true);
								}
								else {
									if (cooldown.containsKey(p.getUniqueId()))
									{
										long secondsleft = ((cooldown.get(p.getUniqueId()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);

										if (secondsleft > 0)
										{
											p.sendMessage(prefix + "The Homing enchantment is on cooldown for " + secondsleft + " seconds.");
											return;
										}

										cooldown.remove(p.getUniqueId());
										p.sendMessage(prefix + "The Homing enchantment is ready.");
									}

									Location l = p.getLocation();
									Entity f = l.getWorld().spawnEntity(l.add(l.getDirection()), EntityType.FIREBALL);
									f.setVelocity(l.getDirection().multiply(1));
									shooter.put(p, f);
									cooldown.put(p.getUniqueId(), System.currentTimeMillis());
								}
							}
						}
					}
				}
			}
		}
	}
}