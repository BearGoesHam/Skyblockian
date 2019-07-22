package me.craigcontreras.Skyblockian.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class MessageCommand 
implements CommandExecutor, TextFormat
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{		
		if (cmd.getName().equalsIgnoreCase("message"))
		{
			if (sender instanceof Player)
			{
				Player p = (Player)sender;
				
				if (args.length > 0)
				{
					Player t = Bukkit.getPlayer(args[0]);
				
					if (t == null)
					{
						p.sendMessage(playerError + args[0].toString() + ".");
						return true;
					}
					else {
						MessageManager.setReplyTarget(p, t);
						
						String message = "";
												
						for (int i = 1; i < args.length; i++)
						{
							message = message + " " + args[i];
						}
						
						p.sendMessage(prefix + "You >> " + t.getName() + ":" + message);
						t.sendMessage(prefix + "You << " + p.getName() + ":" + message);
						return true;
					}
				}
				else {
					p.sendMessage(argsError);
				}
			}
		}
		
		if (cmd.getName().equalsIgnoreCase("reply"))
		{
			if (sender instanceof Player)
			{
				Player p = (Player)sender;
				Player t = MessageManager.getReplyTarget(p);
				
				if (MessageManager.getReplyTarget(p) == null)
				{
					p.sendMessage(prefix + "You do not have any current conversations.");
					return true;
				}
				
				String message = "";
				
				for (int i = 0; i < args.length; i++)
				{
					message = message + " " + args[i];
				}
				
				p.sendMessage(prefix + "You >> " + t.getName() + ":" + message);
				t.sendMessage(prefix + "You << " + p.getName() + ":" + message);
			}
			
			return true;
		}
		
		return false;
	}
}
