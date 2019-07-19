package me.craigcontreras.Skyblockian.commands.admin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import me.craigcontreras.Skyblockian.listeners.LimitedReachListener;

public class ReachLimitCommand 
extends AdminCommands
implements TextFormat
{
	public ReachLimitCommand()
	{
		super("setreach", "Set the global reach limit across the entire server.", "<reach limit in blocks>");
	}
	
	public void run(CommandSender sender, String[] args)
	{
		Player p = (Player)sender;
		
		if (sender instanceof Player)
		{
			if (p.hasPermission("skyblockian.admin.setreach"))
			{
				if (args.length == 0)
				{
					p.sendMessage(argsError);
					return;
				}
				
				if (args.length == 1)
				{
					if (Skyblockian.getCore().isNumeric(args[0].toString()))
					{
						LimitedReachListener.reachLimit = Double.valueOf(args[0]);
						p.sendMessage(prefix + "You have set the reach limit to " + args[0] + ".");
					}
					else {
						p.sendMessage(prefix + "Invalid number.");
						return;
					}
				}else if (args.length >= 2)
				{
					p.sendMessage(argsError);
					return;
				}
			}
			else {
				p.sendMessage(noPerm);
				return;
			}
		}
		else {
			return;
		}
	}
}
