package me.craigcontreras.Skyblockian.commands;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class WarpCommand
    implements CommandExecutor, TextFormat
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            if (args.length >= 1)
            {
                Player p = (Player)sender;
                String name = args[0];

                if (Skyblockian.getCore().warpConfig.contains(name))
                {
                    ConfigurationSection warp = Skyblockian.getCore().warpConfig.getConfigurationSection(name);
                    if (Bukkit.getWorld(warp.getString("world")) == null)
                    {
                        p.sendMessage(prefix + "The world does not exist.");
                        return true;
                    }

                    World w = Bukkit.getWorld(warp.getString("world"));
                    double x = warp.getDouble("x");
                    double y = warp.getDouble("y");
                    double z = warp.getDouble("z");
                    float yaw = (float) warp.getDouble("yaw");
                    float pitch = (float) warp.getDouble("pitch");
                    Location loc = new Location(w, x, y, z, yaw, pitch);

                    p.teleport(loc);
                    p.sendMessage(prefix + "You werw warped to " + name + ".");
                }
                else{
                    p.sendMessage(prefix + "This warp does not exist.");
                    return true;
                }
            }
        }
        else{
            return true;
        }

        return false;
    }
}
