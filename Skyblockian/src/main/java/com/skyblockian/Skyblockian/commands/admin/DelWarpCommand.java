package com.skyblockian.Skyblockian.commands.admin;

import com.skyblockian.Skyblockian.Skyblockian;
import com.skyblockian.Skyblockian.commands.AdminCommands;
import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelWarpCommand
    extends AdminCommands
    implements TextFormat
{
    public DelWarpCommand() { super("delwarp", "Delete an already-existing warp.", "<name>"); }

    public void run(CommandSender sender, String[] args)
    {
        Player p = (Player)sender;

        if (sender.hasPermission("skyblockian.admin.delwarp"))
        {
            if (args.length >= 1)
            {
                String name = args[0].toLowerCase();
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

                    Bukkit.broadcast(prefix + p.getName() + " has removed warp: " + name + ".", "skyblockian.admin");
                }
                else{
                    p.sendMessage(prefix + "This warp does not exist.");
                    return;
                }
            }
            else{
                p.sendMessage(argsError);
                return;
            }

            return;
        }
    }
}
