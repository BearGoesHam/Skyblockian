package me.craigcontreras.Skyblockian.commands.admin;

import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvseeCommand
    extends AdminCommands
    implements TextFormat
{
    public InvseeCommand() { super("invsee", "Look into another player's inventory.", "<player>"); }

    public void run(CommandSender sender, String[] args)
    {
        Player p = (Player)sender;
        Player t = Bukkit.getPlayer(args[0]);

        if (sender instanceof Player)
        {
            if (args.length == 1)
            {
                if (t != null)
                {
                    Inventory inv = t.getInventory();
                    p.openInventory(inv);
                    p.sendMessage(prefix + "Opening " + t.getName() + "'s inventory...");
                }
                else{
                    p.sendMessage(playerError + args[0] + ".");
                }
            }
            else{
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
