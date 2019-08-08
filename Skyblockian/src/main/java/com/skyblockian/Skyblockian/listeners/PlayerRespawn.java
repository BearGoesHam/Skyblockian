package com.skyblockian.Skyblockian.listeners;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.skyblockian.Skyblockian.island.Island;
import com.skyblockian.Skyblockian.island.IslandManager;

public class PlayerRespawn 
implements Listener
{
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e)
	{
		Player p = e.getPlayer();
		InventoryHandler ih = InventoryHandler.getInstance();

		if (IslandManager.getIM().hasIsland(p))
		{
			Island i = IslandManager.getIM().getIsland(p);
			
			if ((i.getSpawnLoc().getBlock().getRelative(BlockFace.DOWN).isLiquid()) || 
			        (i.getSpawnLoc().getBlock().getType().equals(Material.LAVA)) || 
			        (i.getSpawnLoc().getBlock().getType().equals(Material.STATIONARY_LAVA))) 
			{
				e.setRespawnLocation(i.getSpawnLoc().clone().subtract(0.0D, 0.8D, 1.0D));
			} 
			else {
				e.setRespawnLocation(i.getSpawnLoc());
			}
		}
		
		if ((ih.hasInventorySaved(p)) && (ih.hasArmorSaved(p)))
		{
			p.getInventory().setContents(ih.loadInventory(p));
			p.getInventory().setArmorContents(ih.loadArmor(p));
			ih.removeInventory(p);
		}
		else {
			return;
		}
	}
}
