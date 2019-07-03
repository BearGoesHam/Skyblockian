package me.craigcontreras.Skyblockian.commands;

import me.craigcontreras.Skyblockian.Skyblockian;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class OnlineCommand implements CommandExecutor, TextFormat
{


	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		
		if(sender instanceof Player)
		{
			Player p = (Player) sender;
			for(Player players : Bukkit.getServer().getOnlinePlayers())
			{
				Skyblockian.getCore().onlinePlayers.add(players.getName().toString());

			}
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "Players Online: " + Skyblockian.getCore().onlinePlayers.size()));

		}
		
		
		return false;
	}
	

}
