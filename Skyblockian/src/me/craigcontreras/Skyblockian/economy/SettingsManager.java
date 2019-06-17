package me.craigcontreras.Skyblockian.economy;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

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
		return config.getDouble("money." + p.toLowerCase());
	}

	public void addBalance(String p, double amount)
	{
		setBalance(p, getBalance(p) + amount);
	}

	public boolean removeBalance(String p, double amount)
	{
		if (getBalance(p) - amount < 0) return false;
		setBalance(p, getBalance(p) - amount);
		return true;
	}

	public void setBalance(String p, double amount)
	{
		config.set("money." + p.toLowerCase(), amount);
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