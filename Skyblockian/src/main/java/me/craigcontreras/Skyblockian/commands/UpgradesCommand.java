package me.craigcontreras.Skyblockian.commands;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UpgradesCommand
    implements CommandExecutor, TextFormat
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player p = (Player)sender;

            if (Skyblockian.getCore().levelUpManager.isLevel(p, 5))
            {
                Skyblockian.getCore().levelUpManager.createInventory(p);
                p.sendMessage(prefix + "Opening upgrades...");
            }
            else{
                p.sendMessage("Unknown command. Type \"/help\" for help.");
                return true;
            }
        }
        else{
            return true;
        }

        return false;
    }
}
