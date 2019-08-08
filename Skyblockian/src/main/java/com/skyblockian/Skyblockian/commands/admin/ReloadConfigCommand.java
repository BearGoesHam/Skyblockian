package com.skyblockian.Skyblockian.commands.admin;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.skyblockian.Skyblockian.Skyblockian;
import com.skyblockian.Skyblockian.commands.AdminCommands;

public class ReloadConfigCommand 
extends AdminCommands
implements TextFormat
{
	public ReloadConfigCommand()
	{
		super("reload", "Reload the config.", "");
	}
	
	public void run(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Skyblockian.getCore().reloadConfig();
			sender.sendMessage(prefix + "Reloaded config.");
		}
		else {
			return;
		}
	}
}
