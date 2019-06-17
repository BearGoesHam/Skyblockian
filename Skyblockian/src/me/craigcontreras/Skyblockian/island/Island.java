package me.craigcontreras.Skyblockian.island;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.LocalPlayer;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

import me.craigcontreras.Skyblockian.Skyblockian;

@SuppressWarnings("deprecation")
public class Island 
{
	private String o;
	public Location l;
	private Location spawn;
	int r = 420;
	
	public Island(Player o, Location l, boolean gen)
	{
		this.o = o.getName();
		this.l = l;
		this.spawn = l.clone().add(1.5D, 3.0D, 5.5D);
		this.spawn.setYaw(-180.0F);
		
		if (gen)
		{
			gen();
		}
	}
	
	ItemStack[] items = {};
	
	private void gen()
	{
		LocalSession ls = Skyblockian.getCore().worldEdit.getSession(Bukkit.getPlayerExact(this.o));
		LocalPlayer lp = Skyblockian.getCore().worldEdit.wrapPlayer(Bukkit.getPlayer(this.o));
		
		try
		{
			SchematicFormat sf =
					SchematicFormat.getFormat(new File(Skyblockian.getCore().getDataFolder(), "island.schematic"));
			CuboidClipboard cc = sf.load(new File(Skyblockian.getCore().getDataFolder(), "island.schematic"));
			cc.paste(ls.createEditSession(lp), BukkitUtil.toVector(this.l), true);
		}
		catch (DataException|IOException ex)
		{
			ex.printStackTrace();
		}
		catch (MaxChangedBlocksException e)
		{
			e.printStackTrace();
		}
		
		Location chestLoc = this.l.clone().add(1.0D, 3.0D, 0.0D);
		if (chestLoc.getBlock().getType() != Material.CHEST)
		{
			chestLoc.getBlock().setType(Material.CHEST);
		}
		
		Chest chest = (Chest)chestLoc.getBlock().getState();
		ItemStack[] arrayOfItemStack;
		int j = (arrayOfItemStack = this.items).length;
		for (int i = 0; i < j; i++)
		{
			ItemStack item = arrayOfItemStack[i];
			chest.getInventory().addItem(new ItemStack[] { item });
		}
	}
	
	public boolean isAt(Location location)
	{
		if (location == null)
		{
			return false;
		}
		
		int x = Math.abs(location.getBlockX() - this.l.getBlockX());
		int z = Math.abs(location.getBlockZ() - this.l.getBlockZ());
		return (x < this.r) && (z < this.r);
	}
	
	public Player getPlayer()
	{
		return Bukkit.getPlayerExact(this.o);
	}
	
	public Location getLoc()
	{
		return this.l;
	}
	
	public Location getSpawnLoc()
	{
		return this.spawn;
	}
}
