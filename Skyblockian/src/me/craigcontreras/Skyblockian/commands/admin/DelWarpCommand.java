package me.craigcontreras.Skyblockian.commands.admin;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelWarpCommand
    extends AdminCommands
    implements TextFormat
{
    public DelWarpCommand() { super("", "", ""); }

    public void run(CommandSender sender, String[] args)
    {
        Player p = (Player)sender;

        if (sender.hasPermission("skyblockian.admin.delwarp"))
        {
            if (args.length >= 1)
            {
                String name = args[0];
                if (Skyblockian.getCore().warpConfig.contains(name))
                {
                    Skyblockian.getCore().warpConfig.set(name, null);

                    try {
                        Skyblockian.getCore().warpConfig.save(Skyblockian.getCore().warpFile);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    p.sendMessage(prefix + "Removed the warp.");
                }
                else{
                    p.sendMessage(prefix + "This warp does not exist.");
                    return;
                }
            }
            else{
                p.sendMessage(argsError);
            }

            return;
        }
    }
}
