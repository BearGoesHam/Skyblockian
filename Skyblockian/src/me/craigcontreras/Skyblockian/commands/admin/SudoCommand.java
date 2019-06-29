package me.craigcontreras.Skyblockian.commands.admin;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class SudoCommand 
extends AdminCommands
implements TextFormat
{
	public SudoCommand()
	{
		super("sudo", "Superuser-do other players.", "<player> <command>");
	}
	
	public void run(CommandSender sender, String[] args)
	{
		Player p = (Player)sender;
		
		if (sender instanceof Player)
		{
			if (args.length >= 2)
			{
				Player t = Bukkit.getPlayer(args[0]);
				
				if (t != null)
				{
					if (args[1].startsWith("/"))
					{
						ArrayList<String> argsa = new ArrayList<>();
						
						for (int i = 1; i != args.length; i++)
						{
							argsa.add(args[i]);
						}
						
						StringBuilder sb = new StringBuilder();
						
						for (String str : argsa)
						{
							sb.append(str + " ");
						}
						
						t.chat(sb.toString());
						p.sendMessage(prefix + "You have made " + t.getName() + " execute the command: " + sb.toString());
					}
					else {
						ArrayList<String> argsa = new ArrayList<>();
						
						for (int i = 1; i != args.length; i++)
						{
							argsa.add(args[i]);
						}
						
						StringBuilder sb = new StringBuilder();
						
						for (String str : argsa)
						{
							sb.append(str + " ");
						}
						
						t.chat("/" + sb.toString());
						p.sendMessage(prefix + "You have made " + t.getName() + " execute the command: " + sb.toString());
					}
				}
				else {
					p.sendMessage(playerError);
					return;
				}
			}
			else {
				p.sendMessage(argsError);
			}
		}
		else {
			return;
		}
	}
}
