package com.skyblockian.Skyblockian.commands;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.skyblockian.Skyblockian.Skyblockian;

import java.util.Arrays;
import java.util.List;

public class OnlineCommand implements CommandExecutor, TextFormat
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "Players online: " + Skyblockian.getCore().onlinePlayers.size()));

			for (Player pl : Bukkit.getOnlinePlayers())
			{
				List<String> names = Arrays.asList(pl.getName());

				p.sendMessage(ChatColor.GRAY + convert(names));
			}
		}
		
		return false;
	}

	public String convert(List<String> list)
	{
		String toReturn = "";
		for (int i = 0; i < list.size(); i++)
		{
			if (toReturn.equals(""))
			{
				toReturn = toReturn + list.get(i);
			}
			else {
				toReturn = toReturn + ", " + list.get(i);
			}
		}

		return toReturn;
	}
}
