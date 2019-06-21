package me.craigcontreras.Skyblockian.island;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class IslandSelector
implements Listener
{
	@EventHandler
	public void onInventorySelect(InventoryClickEvent e)
	{
		Player p = (Player) e.getWhoClicked();
		ItemStack i = (ItemStack) e.getCurrentItem();
		
		if (e.getCurrentItem() == null)
		{
			return;
		}
		
		if (!(e.getCurrentItem().hasItemMeta()))
		{
			return;
		}
		
		if (e.getInventory().getName().contains(ChatColor.translateAlternateColorCodes('&', "&bAvailable Island Types")))
		{
			if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bOriginal"))
					&& i.getType().equals(Material.GRASS) && 
					i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Difficulty: &aEasy"))))
			{
				e.setCancelled(true);
				IslandManager.createIsland(p);
				p.closeInventory();
				
				Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bAvailable Kits"));
				
				ItemStack ironpickaxe = new ItemStack(Material.IRON_PICKAXE, 1);
				ItemMeta ipmeta = ironpickaxe.getItemMeta();
				ipmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bIron Pickaxe"));
				ipmeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7&o1x Iron Pickaxe")));
				ironpickaxe.setItemMeta(ipmeta);
				
				inv.setItem(4, ironpickaxe);
				
				p.openInventory(inv);
				
				return;
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&cMore soon."))
					&& i.getType().equals(Material.BARRIER) &&
					i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Developers are lazy."))))
			{
				e.setCancelled(true);
				p.closeInventory();
				return;
			}
				
			return;
		}		
	}
}
