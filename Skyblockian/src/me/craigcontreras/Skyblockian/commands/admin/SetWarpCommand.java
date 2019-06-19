package me.craigcontreras.Skyblockian.commands.admin;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand extends AdminCommands implements TextFormat
{

    public SetWarpCommand() { super("setwarp", "Set a warp", "<name>"); }

    public void run(CommandSender sender, String[] args)
    {
        if(sender instanceof Player)
        {
            Player p = (Player) sender;
            if(p.hasPermission("skyblockian.admin"))
            {
                if(args.length == 1)
                {
                    String name = args[0];
                    if(name != null)
                    {

                        World world = p.getWorld();
                        double x = p.getLocation().getX();
                        double y = p.getLocation().getY();
                        double z = p.getLocation().getZ();
                        float yaw = p.getLocation().getYaw();
                        float pitch = p.getLocation().getPitch();

                        Skyblockian.getWarpManager().setWarp(name, world, x,y,z,yaw,pitch);
                        p.sendMessage(TextFormat.SuccessfulWarpSet);
                    } else
                    {
                        p.sendMessage(TextFormat.cmdError);
                    }
                } else
                {
                    p.sendMessage(TextFormat.argsError);
                }
            } else
            {
                p.sendMessage(TextFormat.noPerm);
            }
        } else
        {
            sender.sendMessage(TextFormat.cmdError);
        }
    }


}
