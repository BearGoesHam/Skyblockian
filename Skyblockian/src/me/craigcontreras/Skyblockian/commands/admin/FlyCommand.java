package me.craigcontreras.Skyblockian.commands.admin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class FlyCommand 
extends AdminCommands
implements TextFormat
{
	private List<String> fly = new ArrayList<>();
	
	public FlyCommand()
	{
		super("fly", "Enable fly on yourself or on another player.", "<player>");
	}
	
	public void run(CommandSender sender, String[] args)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage("");
			return;
		}
		
		if (sender.hasPermission("skyblockian.admin"))
		{
			if (args.length == 1)
			{
				Player target = Bukkit.getPlayer(args[0]);
				
				if (target == null)
				{
					sender.sendMessage(prefix + "This player is not online.");
					return;
				}
				
				if (fly.contains(target.getName()))
				{
					target.setAllowFlight(false);
					target.setFlying(false);
					
					target.sendMessage(prefix + "You're no longer flying.");
					sender.sendMessage(prefix + target.getName() + " is no longer flying.");
					fly.remove(target.getName());
				}
				else {
					target.setAllowFlight(true);
					target.setFlying(true);
					
					target.sendMessage(prefix + "You're now flying.");
					sender.sendMessage(prefix + target.getName() + " is now flying.");
					fly.add(target.getName());
				}			
			}else if (args.length == 0)
			{
				Player p = (Player)sender;

				if (!fly.contains(p.getName()))
				{
					p.setAllowFlight(true);
					p.setFlying(true);

					p.sendMessage(prefix + "You're now flying.");
					fly.add(p.getName());
				}
				else{
					p.setAllowFlight(false);
					p.setFlying(false);

					p.sendMessage(prefix + "You're no longer flying.");
					fly.remove(p.getName());
				}
			}else if (args.length >= 2)
			{
				Player p = (Player)sender;
				p.sendMessage(argsError);
				return;
			}
		}
	}
}
