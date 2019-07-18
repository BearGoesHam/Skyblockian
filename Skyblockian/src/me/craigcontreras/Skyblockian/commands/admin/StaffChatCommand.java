package me.craigcontreras.Skyblockian.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

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
				}else if (args.length >= 2)
				{
					p.sendMessage(argsError);
					return;
				}
			}
			else {
				p.sendMessage(noPerm);
			}
		}
	}
}