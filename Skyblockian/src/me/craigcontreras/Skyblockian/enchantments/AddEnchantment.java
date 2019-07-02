package me.craigcontreras.Skyblockian.enchantments;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AddEnchantment 
implements Listener
{
	@EventHandler
	public void onEnchantEvent(EnchantItemEvent e)
	{
		ItemStack item = e.getItem();
		
		if (item.getType().equals(Material.BOW))
		{
			item.setItemMeta(addEnchantment(item, "&7Explosion", e.getExpLevelCost(), 45, 1));
			item.setItemMeta(addEnchantment(item, "&7Poison", e.getExpLevelCost(), 35, 1));
		}else if (item.getType().equals(Material.WOOD_SWORD)
				|| item.getType().equals(Material.STONE_SWORD)
				|| item.getType().equals(Material.IRON_SWORD)
				|| item.getType().equals(Material.GOLD_SWORD)
				|| item.getType().equals(Material.DIAMOND_SWORD))
		{
			item.setItemMeta(addEnchantment(item, "&7Homing", e.getExpLevelCost(), 30, 1));
			item.setItemMeta(addEnchantment(item, "&7Withering", e.getExpLevelCost(), 45, 1));
			item.setItemMeta(addEnchantment(item, "&7Lifesteal", e.getExpLevelCost(), 20, 1));
		}
	}
	
	public ItemMeta addEnchantment(ItemStack item, String enchantmentName, int xp, int chance, int maxLevel)
	{
		if (item == null || enchantmentName == null)
		{
			return null;
		}
		
		int successRate = (int) Math.round(Math.random() * 100);
		
		if (successRate <= chance)
		{
			int level = Math.round(xp / Math.round(30 / maxLevel));
			
			ArrayList<String> newLore = new ArrayList<String>();
			
			if (level == 0)
			{
				
			}
			else {
				if (item.hasItemMeta())
				{
					if (item.getItemMeta().hasLore())
					{
						newLore.addAll(item.getItemMeta().getLore());
					}
				}
				
				newLore.add(ChatColor.translateAlternateColorCodes('&', enchantmentName) + " " + level);
				
				ItemMeta itemMeta = item.getItemMeta();
				itemMeta.setLore(newLore);
				return itemMeta;
			}			
		}
		
		return item.getItemMeta();
	}
	
	/*
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
	            	a.getWorld().createExplosion(a.getLocation(), 1.0F);
	            }
	        }
	    }
	}*/
}
