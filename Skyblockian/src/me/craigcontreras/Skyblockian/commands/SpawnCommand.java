package me.craigcontreras.Skyblockian.commands;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor, TextFormat
{

    World worldname = Bukkit.getWorld(Skyblockian.getCore().getConfig().getString("spawn.world"));
    double x = Double.parseDouble(Skyblockian.getCore().getConfig().getString("spawn.x"));
    double y = Double.parseDouble(Skyblockian.getCore().getConfig().getString("spawn.y"));
    double z = Double.parseDouble(Skyblockian.getCore().getConfig().getString("spawn.z"));


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(sender instanceof Player)
        {
            Player p = (Player) sender;
            if(Skyblockian.getCore().getConfig().get("spawn") != null)
            {
                Location spawn = new Location(worldname, x,y,z);

                p.teleport(spawn);

                p.sendMessage(welcomeSpawn);
            } else
            {
                p.sendMessage(noSpawnSet);
            }
        } else
        {
            sender.sendMessage(cmdError);
        }

        return false;
    }


}
