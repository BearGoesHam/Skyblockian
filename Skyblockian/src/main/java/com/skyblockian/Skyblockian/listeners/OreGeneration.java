package com.skyblockian.Skyblockian.listeners;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import com.skyblockian.Skyblockian.Skyblockian;

public class OreGeneration
implements Listener
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onFromTo(BlockFromToEvent e)
	{
		int id = e.getBlock().getTypeId();
		if ((id >= 8) && (id <= 11))
		{
			Block b = e.getToBlock();
			int toid = b.getTypeId();
			
			if ((toid == 0)
					&& (generatesCobble(id, b)))
			{
				String world = Skyblockian.getCore().world.getName();
				if (world.contains(e.getBlock().getLocation().getWorld().getName()))
				{
					Random r = new Random();
					int chance = 0;
					for (int counter = 1; counter <= 1; counter++)
					{
						chance = 1 + r.nextInt(100);
					}
					
					double coal = Skyblockian.getCore().getConfig().getDouble("chances.coal");
					double iron = Skyblockian.getCore().getConfig().getDouble("chances.iron");
					double gold = Skyblockian.getCore().getConfig().getDouble("chances.gold");
					double redstone = Skyblockian.getCore().getConfig().getDouble("chances.redstone");
					double lapis = Skyblockian.getCore().getConfig().getDouble("chances.lapis");
					double diamond = Skyblockian.getCore().getConfig().getDouble("chances.diamond");
					double obsidian = Skyblockian.getCore().getConfig().getDouble("chances.obsidian");
					
					if ((chance > 0) && (chance <= coal))
					{
						b.setType(Material.COAL_ORE);
					}else if ((chance > coal) && (chance <= iron))
					{
						b.setType(Material.IRON_ORE);
					}else if ((chance > iron) && (chance <= gold))
					{
						b.setType(Material.GOLD_ORE);
					}else if ((chance > iron) && (chance <= redstone))
					{
						b.setType(Material.REDSTONE_ORE);
					}else if ((chance > redstone) && (chance <= lapis))
					{
						b.setType(Material.LAPIS_ORE);
					}else if ((chance > lapis) && (chance <= diamond))
					{
						b.setType(Material.DIAMOND_ORE);
					}else if ((chance > obsidian) && (chance <= 100))
					{
						b.setType(Material.COBBLESTONE);
					}else if ((chance > diamond) && (chance <= obsidian))
					{
						b.setType(Material.OBSIDIAN);
					}
				}
			}
		}
	}
	
	private final BlockFace[] faces = 
		{ BlockFace.SELF, BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, 
				BlockFace.SOUTH, BlockFace.WEST, BlockFace.EAST };
	
	@SuppressWarnings("deprecation")
	public boolean generatesCobble(int id, Block b) 
	{
		int mirrorID1 = (id == 8) || (id == 9) ? 10 : 8;
		int mirrorID2 = (id == 8) || (id == 9) ? 11 : 9;
		
		BlockFace[] arrayOfBlockFace;
		int j = (arrayOfBlockFace = this.faces).length;
		for (int i = 0; i < j; i++)
		{
			BlockFace face = arrayOfBlockFace[i];
			Block relative = b.getRelative(face, 1);
			if ((relative.getTypeId() == mirrorID1) || (relative.getTypeId() == mirrorID2))
			{
				return true;
			}
		}
		
		return false;
	}
}
