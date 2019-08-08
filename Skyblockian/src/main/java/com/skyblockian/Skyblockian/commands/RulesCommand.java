package com.skyblockian.Skyblockian.commands;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RulesCommand
    implements CommandExecutor, TextFormat
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        Player p = (Player)sender;

        if (sender instanceof Player)
        {
            p.sendMessage(prefix + "You can read the rules on the website here:");
            p.sendMessage(linkColor + "PLACEHOLDER");
        }
        else{
            return true;
        }

        return false;
    }
}
