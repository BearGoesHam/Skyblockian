package com.skyblockian.Skyblockian.commands.admin;

import java.util.ArrayList;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.skyblockian.Skyblockian.commands.AdminCommands;

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
						Bukkit.broadcast(prefix + p.getName() + " has sudo'd " + t.getName() + " to execute the command: " + sb.toString(),
								"skyblockian.admin");
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
						Bukkit.broadcast(prefix + p.getName() + " has sudo'd " + t.getName() + " to execute the command: /" + sb.toString(),
								"skyblockian.admin");
					}
				}
				else {
					p.sendMessage(playerError + args[0] + ".");
					return;
				}
			}
			else {
				p.sendMessage(argsError);
				return;
			}
		}
		else {
			return;
		}
	}
}
