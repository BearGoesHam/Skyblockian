package me.craigcontreras.Skyblockian.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class HitDelayCommand 
extends AdminCommands
implements TextFormat
{
	public HitDelayCommand() 
	{
		super("setdelay", "Set the hit delay for all players in PvP/PvE.", "<ticks>");
	}
	
	public static void setup(boolean deepSearch)
	{
		if (deepSearch)
		{
			Skyblockian.getCore().reloadConfig();
		}
		try {
			for (Player pl : Bukkit.getOnlinePlayers())
			{
				pl.setMaximumNoDamageTicks(Integer.parseInt(Skyblockian.getCore().getConfig().getString("hit-delay")));
			}
		}
		catch (Exception e)
		{
			Bukkit.getConsoleSender().sendMessage(prefix + "The hit delay has been reset.");
			Skyblockian.getCore().getConfig().set("hit-delay", Integer.valueOf(7));
			Skyblockian.getCore().saveConfig();
			
			for (Player pl : Bukkit.getOnlinePlayers())
			{
				pl.setMaximumNoDamageTicks(Integer.parseInt(Skyblockian.getCore().getConfig().getString("hit-delay")));
			}
		}
	}
	
	public void run(CommandSender sender, String[] args)
	{
		if (sender.hasPermission("skyblockian.admin.setdelay"))
		{
			if (args.length >= 0)
			{
				if (args.length == 1)
				{
					try
					{
						Integer.parseInt(args[0]);
					}
					catch (Exception e)
					{
						sender.sendMessage(prefix + args[0] + " is not a valid number.");
						return;
					}
					
					int i = Integer.parseInt(args[0]);
					Skyblockian.getCore().getConfig().set("hit-delay", Integer.valueOf(i));
					Skyblockian.getCore().saveConfig();
					
					sender.sendMessage(prefix + "The hit delay has been set to " + i + " ticks.");
					setup(true);
				}
			}
		}
		else {
			sender.sendMessage(noPerm);
		}
	}
}
