package me.craigcontreras.Skyblockian.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class FishRewardListener 
implements Listener, TextFormat
{
	@EventHandler
	public void onPlayerFish(PlayerFishEvent e)
	{
		Player p = e.getPlayer();
		Item item = (Item) e.getCaught();	
		Location loc = p.getLocation().clone().add(0.0D, 0.8D, 0.0D);
		
		if (e.getCaught() instanceof Item)
		{
			if (item.getItemStack().getType().equals(Material.RAW_FISH))
			{
				if (Skyblockian.getCore().randomize(1, 2) == 1)
				{	
					ItemStack type = item.getItemStack();
					type.setType(Material.AIR);
					
					p.sendMessage(prefix + "You've caught a treasure chest!");
					item.setItemStack(type);
					
					//treasure chest spawning
					Chest c = (Chest) loc.getBlock().getState();
					//get inventory of chest
					Inventory inv = c.getInventory();
					//items/treasure in the chest
					ItemStack item1 = new ItemStack(Material.GOLDEN_APPLE);
					inv.setItem(0, item1);
				}
			}
		}
	}
}