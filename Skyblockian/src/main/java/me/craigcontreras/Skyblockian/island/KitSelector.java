package me.craigcontreras.Skyblockian.island;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class KitSelector
implements Listener, TextFormat
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
		
		if (e.getInventory().getName().contains(ChatColor.translateAlternateColorCodes('&', "&bAvailable Kits")))
		{
			if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bIron Pickaxe"))
					&& i.getType().equals(Material.IRON_PICKAXE) && 
					i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7&o1x Iron Pickaxe"))))
			{
				e.setCancelled(true);			
				p.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE, 1));
				p.closeInventory();			
				
				return;
			}
		}		
	}
}
