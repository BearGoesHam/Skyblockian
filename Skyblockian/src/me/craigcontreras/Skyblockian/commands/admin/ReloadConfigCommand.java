package me.craigcontreras.Skyblockian.commands.admin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

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
