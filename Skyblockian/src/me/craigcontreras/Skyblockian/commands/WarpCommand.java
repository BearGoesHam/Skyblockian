package me.craigcontreras.Skyblockian.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class WarpCommand implements CommandExecutor, TextFormat
{
    // /setwarp sets X,Y,Z,YAW pos

    String WARP_NAME;

    String worldName = Skyblockian.getWarpManager().getWarpsConfig().get(WARP_NAME + ".world").toString();
    World world = Bukkit.getWorld(worldName);
    double x = Double.parseDouble(Skyblockian.getWarpManager().getWarpsConfig().get(WARP_NAME + ".X").toString());
    double y = Double.parseDouble(Skyblockian.getWarpManager().getWarpsConfig().get(WARP_NAME + ".Y").toString());
    double z = Double.parseDouble(Skyblockian.getWarpManager().getWarpsConfig().get(WARP_NAME + ".Z").toString());
    float yaw = Float.parseFloat(Skyblockian.getWarpManager().getWarpsConfig().get(WARP_NAME + ".yaw").toString());
    float pitch = Float.parseFloat(Skyblockian.getWarpManager().getWarpsConfig().get(WARP_NAME + ".pitch").toString());


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(sender instanceof Player)
        {
            Player p = (Player) sender;

            if(args.length == 1)
            {
                String warpName = args[0];

                this.WARP_NAME = warpName;

                if(Skyblockian.getWarpManager().getWarpsConfig().contains(warpName))
                {
                    Location warpLoc = new Location(this.world, this.x, this.y, this.z, this.yaw, this.pitch);

                    p.teleport(warpLoc);

                    p.sendMessage(TextFormat.successfulWarp + this.WARP_NAME);
                } else
                {
                    p.sendMessage(TextFormat.invalidWarp);
                }
            } else
            {
                p.sendMessage(TextFormat.argsError);
            }
        } else
        {
            sender.sendMessage(TextFormat.cmdError);
        }


        return false;
    }

}
