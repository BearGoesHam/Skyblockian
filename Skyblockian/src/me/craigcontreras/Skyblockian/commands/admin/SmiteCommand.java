package me.craigcontreras.Skyblockian.commands.admin;

import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SmiteCommand
    extends AdminCommands
    implements TextFormat
{
    public SmiteCommand() { super("smite", "Smite another player.", "<player>"); }

    public void run(CommandSender sender, String[] args)
    {
        Player p = (Player)sender;

        if (sender instanceof Player)
        {
            if (args.length == 0)
            {
                p.sendMessage(argsError);
                return;
            }

            if (args.length == 1)
            {
                Player t = Bukkit.getPlayer(args[0]);
                World w = t.getWorld();
                Location loc = t.getLocation();

                if (t == null)
                {
                    p.sendMessage(playerError + args[0] + ".");
                    return;
                }
                else{
                    p.sendMessage(prefix + t.getName() + " was struck with lightning.");
                    w.strikeLightning(loc);

                    Bukkit.broadcast(prefix + p.getName() + " has struck " + t.getName() + " with lightning.", "skyblockian.admin");
                }
            }else if (args.length >= 2)
            {
                p.sendMessage(argsError);
                return;
            }
        }
        else{
            return;
        }

        return;
    }
}
