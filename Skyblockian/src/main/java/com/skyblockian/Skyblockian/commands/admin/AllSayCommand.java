package com.skyblockian.Skyblockian.commands.admin;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.skyblockian.Skyblockian.commands.AdminCommands;

public class AllSayCommand extends AdminCommands implements TextFormat
{
    public AllSayCommand()
    {
        super("allsay", "Command to make all players say something", "<message>");
    }

    public void run(CommandSender sender, String[] args)
    {
        if (sender instanceof Player)
        {
            Player p = (Player) sender;
            if (p.hasPermission("skyblockian.admin"))
            {
                if (args.length >= 1)
                {
                    String msg = "";
                    int x = 1;

                    for (String a : args)
                    {
                        if (x == 1)
                        {
                            x++;
                        }

                        msg = msg + " " + a;


                    }
                    for(Player players : Bukkit.getServer().getOnlinePlayers())
                    {
                        players.chat(ChatColor.translateAlternateColorCodes('&', msg));
                    }
                }
                else{
                    p.sendMessage(argsError);
                    return;
                }
            } else
            {
                p.sendMessage(TextFormat.noPerm);
            }
        } else
        {
            sender.sendMessage(TextFormat.cmdError);
        }
    }
}