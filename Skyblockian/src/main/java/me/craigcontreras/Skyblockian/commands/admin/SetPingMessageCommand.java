package me.craigcontreras.Skyblockian.commands.admin;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class SetPingMessageCommand 
extends AdminCommands
implements TextFormat
{
	public SetPingMessageCommand()
	{
		super("setping", "Set the ping message on the server list.", "<message>");
	}
	
	public void run(CommandSender sender, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			
			if (args.length == 0)
			{
				p.sendMessage(argsError);
				return;
			}
			
			if (p.hasPermission("skyblockian.admin.setping"))
			{
				String message = args[0].toString();
				
				try {
					Skyblockian.getCore().getConfig().set("ping-message", message);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have set the message to " + args[0] + "."));
					Skyblockian.getCore().saveConfig();
					Skyblockian.getCore().reloadConfig();
				}catch (Exception e)
				{
					e.printStackTrace();
					p.sendMessage(prefix + "An exception has occurred.");
				}
			}
			else {
				p.sendMessage(noPerm);
			}
		}
		else {
			return;
		}
	}
}
