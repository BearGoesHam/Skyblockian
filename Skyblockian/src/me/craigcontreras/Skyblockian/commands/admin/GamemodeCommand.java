package me.craigcontreras.Skyblockian.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class GamemodeCommand 
extends AdminCommands
implements TextFormat
{	
	public GamemodeCommand()
	{
		super("gm", "Change somebody else's gamemode or yourself.", "<gamemode type> <player>");
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
			if (args[0].equalsIgnoreCase("c"))
			{
				if (args.length == 2)
				{
					Player target = Bukkit.getPlayer(args[1]);
					
					if (target == null)
					{
						sender.sendMessage(prefix + "This player is not online.");
						return;
					}
					
					target.setGameMode(GameMode.CREATIVE);
					
					target.sendMessage(prefix + "Your gamemode has been set to creative.");
					sender.sendMessage(prefix + "You set " + target.getName() + "'s gamemode to creative.");
				}else if (args.length < 2)
				{
					Player p = (Player)sender;
					
					p.setGameMode(GameMode.CREATIVE);
					
					p.sendMessage(prefix + "You set your gamemode to creative.");
				}
			}else if (args[0].equalsIgnoreCase("s"))
			{
				if (args.length == 2)
				{
					Player target = Bukkit.getPlayer(args[1]);
					
					if (target == null)
					{
						sender.sendMessage(prefix + "This player is not online.");
						return;
					}
					
					target.setGameMode(GameMode.SURVIVAL);
					
					target.sendMessage(prefix + "Your gamemode has been set to survival.");
					sender.sendMessage(prefix + "You set " + target.getName() + "'s gamemode to survival.");
				}else if (args.length < 2)
				{
					Player p = (Player)sender;
					
					p.setGameMode(GameMode.SURVIVAL);
					
					p.sendMessage(prefix + "You set your gamemode to survival.");
				}
			}else if (args[0].equalsIgnoreCase("a"))
			{
				if (args.length == 2)
				{
					Player target = Bukkit.getPlayer(args[1]);
					
					if (target == null)
					{
						sender.sendMessage(prefix + "This player is not online.");
						return;
					}
					
					target.setGameMode(GameMode.ADVENTURE);
					
					target.sendMessage(prefix + "Your gamemode has been set to adventure.");
					sender.sendMessage(prefix + "You set " + target.getName() + "'s gamemode to adventure.");
				}else if (args.length < 2)
				{
					Player p = (Player)sender;
					
					p.setGameMode(GameMode.ADVENTURE);
					
					p.sendMessage(prefix + "You set your gamemode to adventure.");
				}
			}else if (args[0].equalsIgnoreCase("sp"))
			{
				if (args.length == 2)
				{
					Player target = Bukkit.getPlayer(args[1]);
					
					if (target == null)
					{
						sender.sendMessage(prefix + "This player is not online.");
						return;
					}
					
					target.setGameMode(GameMode.SPECTATOR);
					
					target.sendMessage(prefix + "Your gamemode has been set to spectator.");
					sender.sendMessage(prefix + "You set " + target.getName() + "'s gamemode to spectator.");
				}else if (args.length < 2)
				{
					Player p = (Player)sender;
					
					p.setGameMode(GameMode.SPECTATOR);
					
					p.sendMessage(prefix + "You set your gamemode to spectator.");
				}
			}
			else {
				sender.sendMessage(prefix + "Invalid type of gamemode type.");
			}
		}
	}
}