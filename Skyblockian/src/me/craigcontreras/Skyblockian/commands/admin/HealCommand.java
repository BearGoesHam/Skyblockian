package me.craigcontreras.Skyblockian.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class HealCommand 
extends AdminCommands
implements TextFormat
{	
	public HealCommand() { super("heal", "Heal somebody or yourself.", "<player>"); }
	
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
				
				target.setHealth(20.0D);
				target.setFoodLevel(20);
				
				target.sendMessage(prefix + "You've been healed.");
				sender.sendMessage(prefix + "You healed " + target.getName() + ".");
			}else if (args.length == 0)
			{
				Player p = (Player)sender;
				
				p.setHealth(20.0D);
				p.setFoodLevel(20);
				
				p.sendMessage(prefix + "You healed yourself.");
			}else if (args.length >= 2)
			{
				Player p = (Player)sender;
				p.sendMessage(argsError);
				return;
			}
		}
	}
}
