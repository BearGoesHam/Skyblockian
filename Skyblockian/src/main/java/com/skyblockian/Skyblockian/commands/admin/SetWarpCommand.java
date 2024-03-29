package com.skyblockian.Skyblockian.commands.admin;

import com.skyblockian.Skyblockian.Skyblockian;
import com.skyblockian.Skyblockian.commands.AdminCommands;
import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class SetWarpCommand
extends AdminCommands
    implements TextFormat
{
    public SetWarpCommand() { super("setwarp", "Create a warp at your location.", "<name>");  }

    public void run(CommandSender sender, String[] args)
    {
        Player p = (Player)sender;

        if (sender instanceof Player && sender.hasPermission("skyblockian.admin.setwarp"))
        {
            if (args.length >= 1)
            {
                Location loc = p.getLocation();
                String name = args[0].toLowerCase();

                if (Skyblockian.getCore().warpConfig.contains(name))
                {
                    p.sendMessage(prefix + "This warp already exists. Try another name.");
                    return;
                }
                else{
                    ConfigurationSection warp = Skyblockian.getCore().warpConfig.createSection(name);
                    warp.set("world", loc.getWorld().getName());
                    warp.set("x", loc.getX());
                    warp.set("y", loc.getY());
                    warp.set("z", loc.getZ());
                    warp.set("yaw", loc.getYaw());
                    warp.set("pitch", loc.getPitch());

                    try{
                        Skyblockian.getCore().warpConfig.save(Skyblockian.getCore().warpFile);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    p.sendMessage(prefix + name + " has been created.");

                    Bukkit.broadcast(prefix + p.getName() + " has created a warp: " + name + ".", "skyblockian.admin");
                }
            }
            else{
                p.sendMessage(argsError);
            }
        }
        else{
            p.sendMessage(noPerm);
        }
    }
}
