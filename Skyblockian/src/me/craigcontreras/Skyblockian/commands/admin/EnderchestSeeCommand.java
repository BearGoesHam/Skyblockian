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
        Player t = Bukkit.getPlayer(args[0]);

        if (sender instanceof Player)
        {
            if (args.length == 1)
            {
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
        }
        else{
            return;
        }

        return;
    }
}
