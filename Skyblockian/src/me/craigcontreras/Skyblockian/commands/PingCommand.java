package me.craigcontreras.Skyblockian.commands;

import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingCommand
    implements CommandExecutor, TextFormat
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        Player p = (Player)sender;

        if (sender instanceof Player)
        {
            if (args.length == 0)
            {
                int ping = ((CraftPlayer)p).getHandle().ping;
                p.sendMessage(prefix + "Your ping is " + ping + " ms.");
            }

            if (args.length == 1)
            {
                Player t = Bukkit.getPlayer(args[0]);

                if (t == null)
                {
                    p.sendMessage(playerError + args[0] + ".");
                    return true;
                }

                int ping = ((CraftPlayer) t).getHandle().ping;
                p.sendMessage(prefix + t.getName() + "'s ping is " + ping + " ms.");
            }

            if (args.length >= 2)
            {
                p.sendMessage(argsError);
                return true;
            }
        }
        else{
            return true;
        }

        return false;
    }
}
