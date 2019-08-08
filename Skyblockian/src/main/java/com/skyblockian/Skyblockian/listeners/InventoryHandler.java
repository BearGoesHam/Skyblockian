package com.skyblockian.Skyblockian.listeners;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryHandler 
{
	public static InventoryHandler ih = new InventoryHandler();
	
	private HashMap<Player, ItemStack[]> inventories;
	private HashMap<Player, ItemStack[]> armors;
	
	private InventoryHandler()
	{
		inventories = new HashMap<Player, ItemStack[]>();
		armors = new HashMap<Player, ItemStack[]>();
	}
	
	public void saveInventory(Player p)
	{
		ItemStack[] tmpInv = new ItemStack[p.getInventory().getSize()];
		ItemStack[] tmpArmor = new ItemStack[p.getInventory().getArmorContents().length];
		
		tmpArmor = p.getInventory().getArmorContents();
		tmpInv = p.getInventory().getContents();
		inventories.put(p, tmpInv);
		armors.put(p, tmpArmor);
	}
	
	public ItemStack[] loadInventory(Player p)
	{
		return (ItemStack[]) inventories.get(p);
	}
	
	public ItemStack[] loadArmor(Player p)
	{
		return (ItemStack[]) armors.get(p);
	}
	
	public void removeInventory(Player p) 
	{
		inventories.remove(p);
		armors.remove(p);
	}
	
	public boolean hasInventorySaved(Player p)
	{
		return inventories.containsKey(p);
	}
	
	public boolean hasArmorSaved(Player p)
	{
		return armors.containsKey(p);
	}
	
	public static InventoryHandler getInstance() 
	{
		return ih;
	}
}
