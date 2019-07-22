package me.craigcontreras.Skyblockian.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class HelpCommand 
implements CommandExecutor, TextFormat
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			
			if (p.hasPermission("skyblockian.admin"))
			{
				p.sendMessage(ahelpMessage);
			}
			else {
				p.sendMessage(helpMessage);
			}
			return false;
		}
		else {
			sender.sendMessage("");
		}
		return false;
	}
}
