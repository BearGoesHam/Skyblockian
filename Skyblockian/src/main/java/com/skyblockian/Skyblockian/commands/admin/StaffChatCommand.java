package com.skyblockian.Skyblockian.commands.admin;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.skyblockian.Skyblockian.Skyblockian;
import com.skyblockian.Skyblockian.commands.AdminCommands;

public class StaffChatCommand
extends AdminCommands
implements TextFormat
{
	public StaffChatCommand()
	{
		super("sc", "Send a message in the staff chat.", "<message>");
	}
	
	public void run(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			
			if (p.hasPermission("skyblockian.admin"))
			{
				if (args.length == 0)
				{
					p.sendMessage(argsError);
					return;
				}

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
					
					msg = msg.trim();
					for (Player players : Bukkit.getServer().getOnlinePlayers())
					{
						if (players.hasPermission("skyblockian.admin"))
						{
							String format = ChatColor.translateAlternateColorCodes('&', 
                            		Skyblockian.getCore().getConfig().getString("staffchat-format").replace(
                            				"$player_name$", p.getName()).replace("$message$", msg));
                            players.sendMessage(format);
						}
					}
				}
			}
			else {
				p.sendMessage(noPerm);
			}
		}
	}
}