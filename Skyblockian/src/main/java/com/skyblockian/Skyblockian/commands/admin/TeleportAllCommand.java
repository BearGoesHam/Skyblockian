package com.skyblockian.Skyblockian.commands.admin;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.skyblockian.Skyblockian.commands.AdminCommands;
import com.skyblockian.Skyblockian.interfaces.TextFormat;

public class TeleportAllCommand 
extends AdminCommands
implements TextFormat
{
	public TeleportAllCommand()
	{
		super("tpall", "Teleport all players to your location.", "");
	}
	
	public void run(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			
			for (Player pl : Bukkit.getOnlinePlayers())
			{
				pl.teleport(p.getLocation());
			}
			
			p.sendMessage(prefix + "Teleporting all players to your location...");

			Bukkit.broadcast(prefix + p.getName() + " has teleported all players to their location.", "skyblockian.admin");
		}
		else {
			return;
		}
	}
}
