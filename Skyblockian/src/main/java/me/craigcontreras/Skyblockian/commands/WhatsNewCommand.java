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
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- Craftable jetpacks using 3 engines, an energy source, and 4 diamonds."));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- Energy sources can found through treasure chests from fishing."));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- Engines can found through treasure chests from fishing."));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- Added WorldGuard support. (no longer can be combat logged and no longer can use custom enchantments in disabled regions."));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- Added levels. You can gain XP from doing basic tasks."));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- Added upgrades based on levels & experience (Unlocks at level 5)."));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m--&r &bWhat's new &f&l&m--"));
        p.sendMessage(" ");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m--&r &bWhat's next &f&l&m--"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- /profile command that shows your statistics"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m--&r &bWhat's next &f&l&m--"));
        p.sendMessage(" ");
    }
}
