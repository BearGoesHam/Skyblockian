package me.craigcontreras.Skyblockian.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhatsNewCommand
    implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player p = (Player)sender;
            changeLogs(p);
        }
        else{
            sender.sendMessage("");
            return true;
        }

        return false;
    }

    public void changeLogs(Player p)
    {
        p.sendMessage(" ");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m--&r &bWhat's new &f&l&m--"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- New /whatsnew command that outlines the new features"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- Nether has been enabled (teleports you to a random location in Nether)"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- Craftable packed ice (Recipe: fill the crafting table with normal ice)"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m--&r &bWhat's new &f&l&m--"));
        p.sendMessage(" ");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m--&r &bWhat's next &f&l&m--"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- /profile command that shows your statistics"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m--&r &bWhat's next &f&l&m--"));
        p.sendMessage(" ");
    }
}
