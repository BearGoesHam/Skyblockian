package me.craigcontreras.Skyblockian.island;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.Skyblockian;

public class IslandManager 
{
	private static IslandManager im;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Map<UUID, Island> islands = new HashMap();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Map<UUID, Island2> island2 = new HashMap();
	
	private static double x = -50000.0D;
	private static double z = -50000.0D;
	private static double offset = 1000.0D;
	private static Location nextLoc = new Location(Skyblockian.getCore().world, x, 72.0D, z);
	public Block block1;
	public Block block2;
	File file;
	FileConfiguration config;
	public static File islandFile;
	public static FileConfiguration islandConfig;
	public static int totalIslands = 0;
	
	public IslandManager()
	{
		im = this;
		registerFile();
		registerIslandFile();
		loadNextLocation();
		for (Player ps : Bukkit.getOnlinePlayers()) 
		{
			loadPlayer(ps);
		}
	}
		
	public void onDisable()
	{
		this.config.set("total-islands", Integer.valueOf(totalIslands));
		ConfigurationSection s = this.config.getConfigurationSection("nextLocation");
		s.set("x", Double.valueOf(nextLoc.getX()));
		s.set("z", Double.valueOf(nextLoc.getZ()));
		
		for (Player ps : Bukkit.getOnlinePlayers())
		{
			unloadPlayer(ps);
		}
	}
	
	public static void createIsland(Player p)
	{
		totalIslands += 1;
		
		double x = nextLoc.getX() + offset;
		double z = nextLoc.getZ();
		
		if (x > Math.abs(x))
		{
			z += offset;
			nextLoc.setX(x);
			x = nextLoc.getX() + offset;
			nextLoc.setZ(z);
		}
		
		Math.abs(z);
		
		Location loc = new Location(Skyblockian.getCore().world, x, 72.0D, z);
		Location playerLoc = loc.clone().add(1.5D, 3.0D, 5.5D);
		playerLoc.setYaw(-180.0F);
		p.teleport(playerLoc);
		Island il = new Island(p, loc, true);
		islands.put(p.getUniqueId(), il);
		nextLoc = il.getLoc();
		ConfigurationSection s = islandConfig.createSection(p.getUniqueId().toString());
		s.set("name", p.getName());
		s.set("x", Double.valueOf(il.getLoc().getX()));
		s.set("z", Double.valueOf(il.getLoc().getZ()));
		saveIslands();
	}
	
	public static void createIsland2(Player p)
	{
		totalIslands += 1;
		
		double x = nextLoc.getX() + offset;
		double z = nextLoc.getZ();
		
		if (x > Math.abs(x))
		{
			z += offset;
			nextLoc.setX(x);;
			x = nextLoc.getX() + offset;
			nextLoc.setZ(z);
		}
		
		Math.abs(z);
		
		Location loc = new Location(Skyblockian.getCore().world, x, 72.0D, z);
		Location playerLoc = loc.clone().add(1.5D, 3.0D, 5.5D);
		playerLoc.setYaw(-180.0F);
		p.teleport(playerLoc);
		Island2 il = new Island2(p, loc, true);
		island2.put(p.getUniqueId(), il);
		nextLoc = il.getLoc();
		ConfigurationSection s = islandConfig.createSection(p.getUniqueId().toString());
		s.set("name", p.getName());
		s.set("x", Double.valueOf(il.getLoc().getX()));
		s.set("z", Double.valueOf(il.getLoc().getZ()));
		saveIslands();
	}
	
	public static void deleteIsland(Player p)
	{
		p.teleport(((World)Bukkit.getWorlds().get(0)).getSpawnLocation());
				
		if (islands.containsKey(p.getUniqueId()))
		{
			islands.remove(p.getUniqueId());
		}else if (island2.containsKey(p.getUniqueId()))
		{
			island2.remove(p.getUniqueId());
		}
		
		islandConfig.set(p.getUniqueId().toString(), null);
		saveIslands();
		
		p.getInventory().clear();
	}
	
	public static IslandManager getIM()
	{
		return im;
	}
	
	private void registerFile()
	{
		this.file = new File(Skyblockian.getCore().getDataFolder(), "IslandManager.yml");
		this.config = YamlConfiguration.loadConfiguration(this.file);
	}
	
	private void saveFile()
	{
		try
		{
			this.config.save(this.file);
		}
		catch (IOException localIOException) {}
	}
	
	private void registerIslandFile()
	{
		islandFile = new File(Skyblockian.getCore().getDataFolder(), "islands.yml");
		islandConfig = YamlConfiguration.loadConfiguration(islandFile);
	}
	
	private static void saveIslands()
	{
		try
		{
			islandConfig.save(islandFile);
		}
		catch (IOException localIOException) {}
	}
	
	private void loadNextLocation()
	{
		if (this.config.contains("nextLocation"))
		{
			ConfigurationSection s = this.config.getConfigurationSection("nextLocation");
			double x = s.getDouble("x");
			double z = s.getDouble("z");
			nextLoc = new Location(Skyblockian.getCore().world, x, 72.0D, z);
		}
		else {
			ConfigurationSection s= this.config.createSection("nextLocation");
			s.set("x", Double.valueOf(nextLoc.getX()));
			s.set("z", Double.valueOf(nextLoc.getZ()));
			saveFile();
		}
	}
	
	public void loadPlayer(Player p)
	{
		if (islandConfig.contains(p.getUniqueId().toString()))
		{
			ConfigurationSection s = islandConfig.getConfigurationSection(p.getUniqueId().toString());
			double x = s.getDouble("x");
			double z = s.getDouble("z");
			
			Island i = new Island(p, new Location(Skyblockian.getCore().world, x, 72.0D, z), false);
			islands.put(p.getUniqueId(), i);
			Island2 i2 = new Island2(p, new Location(Skyblockian.getCore().world, x, 72.0D, z), false);
			island2.put(p.getUniqueId(), i2);
		}
	}
	
	public void unloadPlayer(Player p)
	{
		if (hasIsland(p))
		{
			if (islands.containsKey(p.getUniqueId()))
			{
				islands.remove(p.getUniqueId());
			}
		}else if (hasIsland2(p))
		{
			if (island2.containsKey(p.getUniqueId()))
			{
				island2.remove(p.getUniqueId());
			}
		}
	}
	
	public Island getIsland(Player p)
	{
		if (hasIsland(p))
		{
			return islands.get(p.getUniqueId());
		}
		return null;
	}
	
	public Island2 getIsland2(Player p)
	{
		if (hasIsland2(p))
		{
			return island2.get(p.getUniqueId());
		}
		return null;
	}
	
	public void sendHome(Player p)
	{
		if (hasIsland(p))
		{
			p.teleport(getIsland(p).getSpawnLoc());
		}else if (hasIsland2(p))
		{
			p.teleport(getIsland2(p).getSpawnLoc());
		}
	}
	
	public boolean hasIsland(Player p)
	{
		return islands.containsKey(p.getUniqueId());
	}
	
	public boolean hasIsland2(Player p)
	{
		return island2.containsKey(p.getUniqueId());
	}
	
	public int getTotalIslands()
	{
		return totalIslands;
	}
	
	public int getTotalIslandsInUse()
	{
		return islandConfig.getKeys(false).size();
	}
}
