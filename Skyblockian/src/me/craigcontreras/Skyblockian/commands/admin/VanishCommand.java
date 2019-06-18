package me.craigcontreras.Skyblockian.commands.admin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class VanishCommand
extends AdminCommands
implements TextFormat
{
	public VanishCommand() 
	{
		super("vanish", "Make yourself invisible from players.", "<player>");
	}
	
	public static Set<UUID> vanish = new HashSet<UUID>();
	
	@SuppressWarnings("deprecation")
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
								
				if (!(vanish.contains(target.getUniqueId())))
				{
					for (Player pl : Bukkit.getOnlinePlayers())
					{
						pl.hidePlayer(target);
						target.sendMessage(prefix + "You've been vanished.");
					}
					
					sender.sendMessage(prefix + "You vanished " + target.getName() + " from the server.");
					vanish.add(target.getUniqueId());
					return;
				}
				
				if (vanish.contains(target.getUniqueId()))
				{					
					for (Player pl : Bukkit.getOnlinePlayers())
					{
						pl.showPlayer(target);
						target.sendMessage(prefix + "You've been unvanished.");
					}
					
					sender.sendMessage(prefix + "You unvanished " + target.getName() + ".");
					vanish.remove(target.getUniqueId());
					return;
				}
			}else if (args.length == 0)
			{
				Player p = (Player)sender;
				
				if (!(vanish.contains(p.getUniqueId())))
				{
					for (Player pl : Bukkit.getOnlinePlayers())
					{
						pl.hidePlayer(p);
					}
					
					p.sendMessage(prefix + "You've been vanished.");
					vanish.add(p.getUniqueId());
					return;
				}
				
				if (vanish.contains(p.getUniqueId()))
				{
					for (Player pl : Bukkit.getOnlinePlayers())
					{
						pl.showPlayer(p);
					}
					
					p.sendMessage(prefix + "You've been unvanished.");
					vanish.remove(p.getUniqueId());
				}
			}
		}
	}
}
