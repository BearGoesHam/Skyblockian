package me.craigcontreras.Skyblockian.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class SetSpawnCommand 
extends AdminCommands 
implements TextFormat
{
    public SetSpawnCommand()
    { 
    	super("setspawn", "Sets the spawn for the server.", ""); 
    }
    
    public static Location convertingLocation(String s)
    {
    	String[] split = s.split(",");
    	return new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), 
    			Double.parseDouble(split[2]), Double.parseDouble(split[3]), 
    			Float.parseFloat(split[4]), Float.parseFloat(split[5]));
    }
    
    public static String convertingString(Location loc)
    {
    	return String.valueOf(loc.getWorld().getName()) + "," + loc.getX() + 
    			"," + loc.getY() + "," + loc.getZ() + "," + loc.getYaw() + "," + loc.getPitch();
    }
    
    public static void teleportToSpawn(Player p)
    {
    	try
    	{
    		p.teleport(convertingLocation(Skyblockian.getCore().getConfig().getString("spawn-location")));
    		p.setFallDistance(0.0F);
    	}
    	catch (NullPointerException ex)
    	{
    		p.sendMessage(cmdError);
    	}
    }
    
    public void run(CommandSender sender, String[] args)
    {
    	Player p = (Player)sender;

        if (sender instanceof Player)
        {
        	if (p.hasPermission("skyblockian.admin.setspawn"))
        	{
            	Skyblockian.getCore().getConfig().set("spawn-location", convertingString(p.getLocation()));
            	Skyblockian.getCore().saveConfig();
            	p.sendMessage(successfulSpawnSet);

				Bukkit.broadcast(prefix + p.getName() + " has set the spawn to their location.", "skyblockian.admin");
			}
        }
        else {
        	p.sendMessage(noPerm);
        }
    }
}