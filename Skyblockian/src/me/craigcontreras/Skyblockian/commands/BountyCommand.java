package me.craigcontreras.Skyblockian.commands;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.economy.SettingsManager;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BountyCommand implements CommandExecutor, TextFormat
{


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(sender instanceof Player)
        {

            Player p = (Player) sender;
            if(args.length == 1)
            {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null)
                {

                    if(Skyblockian.getCore().Bounties.containsKey(target))
                    {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', TextFormat.prefix + "Player &7" + target.getName() + " &bis worth &7" + Skyblockian.getCore().Bounties.get(target)));
                    } else
                    {
                        p.sendMessage(TextFormat.prefix + "Player does not have a current bounty.");
                    }
                } else
                {
                    p.sendMessage(TextFormat.playerError);
                }
            } else if(args.length == 3 && args[0].equalsIgnoreCase("add"))
            {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null)
                {
                    double amount = Double.parseDouble(args[2]);
                    if (Skyblockian.getCore().Bounties.containsKey(target))
                    {
                        Skyblockian.getCore().Bounties.put(target, Skyblockian.getCore().Bounties.get(target) + amount);
                        SettingsManager.getEcoManager().removeBalance(p.getName(), amount);
                        p.sendMessage(TextFormat.prefix + "you have added &b" + amount + " &7to&b " + target.getName() + "&7's bounty!");
                    } else
                    {
                        Skyblockian.getCore().Bounties.put(target, amount);
                        SettingsManager.getEcoManager().removeBalance(p.getName(), amount);
                        p.sendMessage(TextFormat.prefix + "You have set &b" + target.getName() + "&7's bounty to &b" + amount + "&7!");

                    }
                } else {
                    p.sendMessage(TextFormat.playerError);
                }
            }

            if(args.length == 0)
            {
                p.sendMessage(TextFormat.prefix + "Incorrect Usage. /bounty <player> | /bounty add <player> <name>");
            }
            if(args.length == 2)
            {
                p.sendMessage(TextFormat.prefix + "Invalid amount.");
            }


        } else
        {
            sender.sendMessage("Error while executing command.");
        }


        return false;
    }


}
