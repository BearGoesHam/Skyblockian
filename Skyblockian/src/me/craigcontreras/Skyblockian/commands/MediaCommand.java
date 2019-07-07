package me.craigcontreras.Skyblockian.commands;

import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MediaCommand implements CommandExecutor, TextFormat
{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(sender instanceof Player)
        {
            Player p = (Player) sender;
            TextComponent twitter1 = new TextComponent("Skyblockian Twitter: ");
            TextComponent twitter2 = new TextComponent("click here");

            twitter1.setColor(net.md_5.bungee.api.ChatColor.AQUA);
            twitter2.setColor(net.md_5.bungee.api.ChatColor.AQUA);
            twitter2.setBold(true);

            twitter2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://twitter.com/skyblockian"));
            twitter2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("twitter.com/skyblockian").create()));

            twitter1.addExtra(twitter2);

            TextComponent youtube1 = new TextComponent("Our YouTube Channel: ");
            TextComponent youtube2 = new TextComponent("click here");

            youtube1.setColor(net.md_5.bungee.api.ChatColor.RED);
            youtube2.setColor(net.md_5.bungee.api.ChatColor.WHITE);
            youtube2.setBold(true);
            youtube2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("youtube.com/skyblockian").create()));
            youtube2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.youtube.com/channel/UCdKH-rc9OjS1PywL-1Pb0hQ/featured"));
            youtube1.addExtra(youtube2);

            TextComponent discord1 = new TextComponent("Join our public discord: ");
            TextComponent discord2 = new TextComponent("here");
            discord1.setColor(net.md_5.bungee.api.ChatColor.DARK_AQUA);
            discord2.setColor(net.md_5.bungee.api.ChatColor.DARK_AQUA);
            discord2.setBold(true);
            discord2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("https://discord.gg/gU96pYr").create()));
            discord2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/gU96pYr"));
            discord1.addExtra(discord2);

            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7---------------------------------------------"));
            p.spigot().sendMessage(twitter1);
            p.spigot().sendMessage(youtube1);
            p.spigot().sendMessage(discord1);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7---------------------------------------------"));
        }

        return false;
    }

}
