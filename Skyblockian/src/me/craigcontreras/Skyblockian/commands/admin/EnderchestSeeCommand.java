package me.craigcontreras.Skyblockian.commands.admin;

import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EnderchestSeeCommand
    extends AdminCommands
    implements TextFormat
{
    public EnderchestSeeCommand() { super("esee", "Look at another player's enderchset.", "<player>"); }

    public void run(CommandSender sender, String[] args)
    {
        Player p = (Player)sender;

        if (sender instanceof Player)
        {
            if (args.length == 0)
            {
                Inventory inv = p.getEnderChest();
                p.openInventory(inv);
                p.sendMessage(prefix + "Opening your own enderchest...");
            }

            if (args.length == 1)
            {
                Player t = Bukkit.getPlayer(args[0]);

                if (t == null)
                {
                    p.sendMessage(playerError + args[0] + ".");
                    return;
                }
                else{
                    Inventory inv = t.getEnderChest();
                    p.openInventory(inv);
                    p.sendMessage(prefix + "Opening " + t.getName() + "'s enderchest...");
                }
            }

            if (args.length >= 2)
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
