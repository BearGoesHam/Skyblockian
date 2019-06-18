package me.craigcontreras.Skyblockian.commands.admin;

import me.craigcontreras.Skyblockian.Skyblockian;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand implements CommandExecutor
{

    public static String INVALID_ARGS = ChatColor.translateAlternateColorCodes('&', "&aSkyblockian: &7Invalid Message.");
    public static String INVALID_PERM = ChatColor.translateAlternateColorCodes('&', "&aSkyblockian: &7Invalid Permissions.");

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(cmd.getName().equalsIgnoreCase("staffchat"))
        {
            if(sender instanceof Player)
            {
                Player p = (Player) sender;
                if(p.hasPermission("command.staffchat"))
                {
                    if(args.length >= 1)
                    {
                        String msg = "";
                        int x = 1;
                        for (String a : args) {
                            if (x == 1) {
                                x++;
                                continue;
                            }
                            msg = msg + " " + a;
                        }
                        msg = msg.trim();
                        for(Player players : Bukkit.getServer().getOnlinePlayers())
                        {
                            if(players.hasPermission("command.staffchat"))
                            {
                                String format = ChatColor.translateAlternateColorCodes('&', Skyblockian.getCore().getConfig().getString("staffchat-format").replace("$player_name$", p.getName()).replace("$message$", msg));
                            }
                        }
                    } else
                    {
                        p.sendMessage(INVALID_ARGS);
                    }

                } else
                {
                    p.sendMessage(INVALID_PERM);
                }
            }
        }


        return false;
    }

}
