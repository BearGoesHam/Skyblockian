package com.skyblockian.Skyblockian.listeners;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffectType;

public class ItemManager
{
	private static ItemManager im = new ItemManager();
	private ItemManager() {}
	
	public static ItemManager getIManager()
	{
		return im;
	}
	
	public ItemStack createAnItem(Material mat, String name, List<String> lore)
	{
		ItemStack i = new ItemStack(mat, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(name);
		iMeta.setLore(lore);
		i.setItemMeta(iMeta);
		return i;
	}

	public ItemStack createAnItem(Material mat, String name, String lore)
	{
		ItemStack i = new ItemStack(mat, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		iMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', lore)));
		i.setItemMeta(iMeta);
		return i;
	}

	public ItemStack potionItem(PotionEffectType pet, String name, String lore)
	{
		ItemStack i = new ItemStack(Material.POTION);
		PotionMeta meta = (PotionMeta) i.getItemMeta();
		meta.setMainEffect(pet);
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		meta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', lore)));
		i.setItemMeta(meta);

		return i;
	}

	public ItemStack createItem(Material mat, String name, String lore)
	{
		ItemStack i = new ItemStack(mat, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(name);
		iMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"), 
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
							ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
							ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
							lore));
		i.setItemMeta(iMeta);
		return i;
	}
}
