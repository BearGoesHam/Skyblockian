package com.skyblockian.Skyblockian.commands.admin;

import java.util.ArrayList;
import java.util.UUID;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.skyblockian.Skyblockian.commands.AdminCommands;

public class FreezeCommand
extends AdminCommands
implements TextFormat
{
	public static ArrayList<UUID> frozen = new ArrayList<UUID>();
	
	public FreezeCommand()
	{
		super("freeze", "Freeze a player.", "<player>");
	}
	
	public void run(CommandSender sender, String args[])
	{
		Player p = (Player)sender;
		Player target = Bukkit.getPlayer(args[0]);
		
		if (sender instanceof Player)
		{
			if (args.length == 1)
			{
				if (target == null)
				{
					p.sendMessage(playerError + args[0].toString() + ".");
				}
				else {
					if (!(frozen.contains(target.getUniqueId())))
					{
						p.sendMessage(prefix + "You have frozen " + target.getName() + ".");
						target.sendMessage(prefix + "You have been frozen.");
						frozen.add(target.getUniqueId());

						Bukkit.broadcast(prefix + p.getName() + " frozen " + target.getName() + ".",
								"skyblockian.admin");
					}
					else {
						p.sendMessage(prefix + "You have unfrozen " + target.getName() + ".");
						target.sendMessage(prefix + "You have been unfrozen.");
						frozen.remove(target.getUniqueId());
					}
				}

			}else if (args.length >= 2)
			{
				p.sendMessage(argsError);
				return;
			}
		}
		else {
			return;
		}
	}
}
