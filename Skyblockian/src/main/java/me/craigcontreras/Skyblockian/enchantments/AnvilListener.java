package me.craigcontreras.Skyblockian.enchantments;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;

public class AnvilListener 
implements Listener
{
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		if (!(e.isCancelled()))
		{
			Player p = (Player) e.getWhoClicked();
			
			Inventory inv = e.getInventory();
			
			if (inv instanceof AnvilInventory)
			{
				AnvilInventory anvil = (AnvilInventory) inv;
				InventoryView view = e.getView();
				int rawSlot = e.getRawSlot();
				
				if (rawSlot == view.convertSlot(rawSlot))
				{
					if (rawSlot == 2)
					{
						ItemStack[] items = anvil.getContents();
						ItemStack item1 = items[0];
						ItemStack item2 = items[1];
						
						ArrayList<String> newLore = new ArrayList<>();
						
						if (item1 != null && item2 != null)
						{
							ItemStack item3 = e.getCurrentItem();
							
							if (item3 != null)
							{
								ItemMeta meta = item3.getItemMeta();
								
								if (meta != null)
								{
									if (meta instanceof Repairable)
									{
										Repairable re = (Repairable) meta;
										int repairCost = re.getRepairCost();
										
										if (p.getLevel() >= repairCost)
										{
											if (item2.hasItemMeta())
											{
												if (item2.getItemMeta().hasLore())
												{
													newLore.addAll(item2.getItemMeta().getLore());
												}
											}else if (item1.hasItemMeta())
											{
												if (item1.getItemMeta().hasLore())
												{
													newLore.addAll(item1.getItemMeta().getLore());
												}
											}else if (item1.hasItemMeta() && item2.hasItemMeta())
											{
												if (item1.getItemMeta().hasLore() && item2.getItemMeta().hasLore())
												{
													newLore.addAll(item1.getItemMeta().getLore());
													newLore.addAll(item2.getItemMeta().getLore());
												}
											}
											
											newLore.add(ChatColor.translateAlternateColorCodes('&', item2.getItemMeta().getLore().toString()));
											newLore.add(ChatColor.translateAlternateColorCodes('&', item1.getItemMeta().getLore().toString()));
											ItemMeta i3meta = item3.getItemMeta();
											i3meta.setLore(newLore);
											item3.setItemMeta(i3meta);
										}
										else {
											return;
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
