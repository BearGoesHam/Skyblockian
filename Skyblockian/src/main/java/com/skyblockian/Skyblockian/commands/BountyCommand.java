package com.skyblockian.Skyblockian.commands;

import java.io.IOException;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.skyblockian.Skyblockian.Skyblockian;
import com.skyblockian.Skyblockian.economy.SettingsManager;

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
                    if(Skyblockian.getCore().getBountyConfig().contains(target.getUniqueId().toString() + ".bounty"))
                    {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', TextFormat.prefix + "Player &7" + target.getName() +
                        		" &bis worth &7$" + Skyblockian.getCore().getBountyConfig().get(target.getUniqueId().toString() + ".bounty") + "."));
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
                
                if (!(Skyblockian.getCore().isNumeric(args[2])))
                {
                	p.sendMessage(prefix + "Invalid amount.");
                	return true;
                }
                
                if (target != null)
                {
                    if(!target.getName().equals(p.getName()))
                    {
                        double amount = Double.parseDouble(args[2]);
                        double max_bounty = Double.parseDouble(Skyblockian.getCore().getConfig().getString("bounty-max"));
                        if(amount <= max_bounty)
                        {
                            if(Skyblockian.getCore().getBountyConfig().contains(target.getUniqueId().toString() + ".bounty"))
                            {
                                SettingsManager.getEcoManager().removeBalance(p.getName(), amount);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', TextFormat.prefix + "You have added $"
                                        + amount + " &7to&b " + target.getName() + "&7's bounty!"));
                                Bukkit.broadcastMessage(prefix + p.getName() + " has added $" + amount + " to " + target.getName() + "'s bounty.");
                                Skyblockian.getCore().getBountyConfig().set(target.getUniqueId().toString() + ".bounty", 
                                		Skyblockian.getCore().getBountyConfig().getDouble(target.getUniqueId().toString() + ".bounty") + amount);                                
                                try {
									Skyblockian.getCore().getBountyConfig().save(Skyblockian.getCore().bounties);
								} catch (IOException e) {
									e.printStackTrace();
								}
                            } else
                            {
                                SettingsManager.getEcoManager().removeBalance(p.getName(), amount);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', TextFormat.prefix +
                                        "You have set &b" + target.getName() + "&7's bounty to &b$" + amount + "&7!"));
                                Bukkit.broadcastMessage(prefix + p.getName() + " has placed $" + amount + " on " + target.getName() + " in the form of a bounty.");
                                Skyblockian.getCore().getBountyConfig().set(target.getUniqueId().toString() + ".bounty", amount);
                                try {
									Skyblockian.getCore().getBountyConfig().save(Skyblockian.getCore().bounties);
								} catch (IOException e) {
									e.printStackTrace();
								}
                            }
                        } else
                        {
                            p.sendMessage(TextFormat.prefix +
                                    "The amount of money you have specified is above the maximum amount that has been set. Please lower the amount.");
                        }
                    } else
                    {
                        p.sendMessage(TextFormat.prefix +
                                "You can not set a bounty on yourself.");
                    }
                } else {
                    p.sendMessage(prefix + "Unable to find player.");
                }
            }

            if(args.length == 0)
            {
                p.sendMessage(TextFormat.prefix + "Incorrect usage. /bounty <player> | /bounty add <player> <money amount>");
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
