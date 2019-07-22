package me.craigcontreras.Skyblockian.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class YeetCommand 
implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("yeet"))
		{
			if(sender instanceof Player)
			{
				Player p = (Player) sender;
				if(p.hasPermission("command.yeet"))
				{
					for(Player players : Bukkit.getServer().getOnlinePlayers())
					{
						players.chat("yeet");
					}
				}
			}
		}
		
		return false;
	}
}
