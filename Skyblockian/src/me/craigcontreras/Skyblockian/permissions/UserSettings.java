package me.craigcontreras.Skyblockian.permissions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.craigcontreras.Skyblockian.Skyblockian;

public class UserSettings 
{
	private Map<FileConfiguration, File> FileConfigurationFileMap = new HashMap<>();
	
	private static UserSettings settings = new UserSettings();
	private UserSettings() {}
	
	public static UserSettings getSettings()
	{
		return settings;
	}
	
	public boolean isDebugEnabled()
	{
		return getFile("config").getBoolean("debug-mode");
	}
	
	public FileConfiguration getFile(String fileName)
	{
		File tempFile = new File(Skyblockian.getCore().getDataFolder(), fileName + ".yml");
		if (!(tempFile.exists()))
		{
			createFile(fileName);
		}
		
		FileConfiguration temp = YamlConfiguration.loadConfiguration(tempFile);
		save(tempFile, temp);
		FileConfigurationFileMap.put(temp, tempFile);
		return temp;
	}
	
	public void createFile(String fileName)
	{
		File tempFile = new File(Skyblockian.getCore().getDataFolder(), fileName + ".yml");
		
		if (!(tempFile.exists()))
		{
			try
			{
				Skyblockian.getCore().getDataFolder().mkdir();
				tempFile.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		FileConfiguration temp = YamlConfiguration.loadConfiguration(tempFile);
		if (fileName.equalsIgnoreCase("config"))
		{
			//permissions.yml
			temp.set("debug-mode", "false");
		}
		FileConfigurationFileMap.put(temp, tempFile);
		save(tempFile, temp);
	}
	
	public void save(FileConfiguration fc)
	{
		save(FileConfigurationFileMap.get(fc), fc);
	}
	
	public void save(File file, FileConfiguration fc)
	{
		try
		{
			fc.save(file);
		}
		catch (IOException e)
		{
			System.out.println("An error has occurred whilst attempting to save the permissions.yml file");
			e.printStackTrace();
			return;
		}
	}
	
	public void disable()
	{
		FileConfigurationFileMap.clear();
	}
}
