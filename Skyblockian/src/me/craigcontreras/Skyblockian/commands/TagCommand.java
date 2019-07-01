package me.craigcontreras.Skyblockian.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class TagCommand implements CommandExecutor, TextFormat
{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("skyblockian.tag"))
			{
				p.sendMessage(prefix + "This works!");
			}
		}
		
		return false;
	}

}
