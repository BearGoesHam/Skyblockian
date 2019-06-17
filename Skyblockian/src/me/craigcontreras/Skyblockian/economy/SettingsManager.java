package me.craigcontreras.Skyblockian.economy;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.craigcontreras.Skyblockian.Skyblockian;

public class SettingsManager 
{
	private static SettingsManager sm = new SettingsManager();
	private SettingsManager() {}
	
	public static SettingsManager getEcoManager()
	{
		return sm;
	}
	
	private FileConfiguration config;
	private File cFile;
	
	public void setup(Skyblockian p)
	{
		if (!(p.getDataFolder().exists())) p.getDataFolder().mkdir();
		
		cFile = new File(p.getDataFolder(), "economy.yml");
		
		if (!(cFile.exists()))
		{
			try { cFile.createNewFile(); }
			catch (Exception e) { e.printStackTrace(); }
		}
		
		config = YamlConfiguration.loadConfiguration(cFile);
	}
	
	public double getBalance(String p)
	{
		String name = Bukkit.getPlayer(p).getUniqueId().toString();
		return config.getDouble("money." + name);
	}
	
	public void addBalance(String p, double amount)
	{
		String name = Bukkit.getPlayer(p).getUniqueId().toString();
		setBalance(name, getBalance(name) + amount);
	}
	
	public boolean removeBalance(String p, double amount)
	{
		String name = Bukkit.getPlayer(p).getUniqueId().toString();
		
		if (getBalance(name) - amount < 0) return false;
		setBalance(name, getBalance(name) - amount);
		return true;
	}
	
	public void setBalance(String p, double amount)
	{
		String name = Bukkit.getPlayer(p).getUniqueId().toString();
		
		config.set("money." + name, amount);
		save();
	}
	
	public ArrayList<String> getValues()
	{
		Map<String, Object> map = config.getValues(true);
		ArrayList<String> lines = new ArrayList<>();
		
		for (Entry<String, Object> e : map.entrySet())
		{
			lines.add(e.getValue() + " " + e.getKey());
		}
		
		return lines;
	}
	
	private void save()
	{
		try { config.save(cFile); }
		catch (Exception e) { e.printStackTrace(); }
	}
}
