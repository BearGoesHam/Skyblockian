package me.craigcontreras.Skyblockian.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class TagCommand implements CommandExecutor, TextFormat
{
	//DATA FORMAT: UUID: <TAG>
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{

			Skyblockian.getCore().blacklisted_tags.add("owner");
			Skyblockian.getCore().blacklisted_tags.add("manager");
			Skyblockian.getCore().blacklisted_tags.add("developer");
			Skyblockian.getCore().blacklisted_tags.add("dev");
			Skyblockian.getCore().blacklisted_tags.add("administrator");
			Skyblockian.getCore().blacklisted_tags.add("admin");
			Skyblockian.getCore().blacklisted_tags.add("moderator");
			Skyblockian.getCore().blacklisted_tags.add("mod");
			Skyblockian.getCore().blacklisted_tags.add("helper");
			Skyblockian.getCore().blacklisted_tags.add("staff");

			Player p = (Player) sender;
			if(p.hasPermission("skyblockian.tag"))
			{
				if((args.length == 1) && (args[0].equalsIgnoreCase("reset")))
				{
					
					if(Skyblockian.getCore().tagConfig.contains(p.getUniqueId().toString()))
					{
						Skyblockian.getCore().tagConfig.set(p.getUniqueId().toString(), null);
						p.sendMessage(prefix + "You have reset your tag.");
					} else
					{
						p.sendMessage(prefix + "You do not have a current tag.");
					}
					
				} else
				{
						if(args.length >= 1)
						{
							String tag = "";
							int x = 1;
							for(String a : args)
							{
								if(x == 1)
								{
									x++;
								}
								tag = tag + " " + a;
								tag.trim();
							}
							if(!Skyblockian.getCore().blacklisted_tags.contains(tag.trim().toLowerCase()))
							{
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have set your tag to: " + tag.trim()));
								try
								{
									Skyblockian.getCore().tagConfig.set(p.getUniqueId().toString(), tag.toString());
									Skyblockian.getCore().saveYml(Skyblockian.getCore().tagFile, Skyblockian.getCore().tagConfig);
								} catch (Exception e)
								{
									e.printStackTrace();
									p.sendMessage(prefix + cmdError);
								}
							} else
							{
								p.sendMessage(prefix + "You can not use blacklisted words in your tag. please use something else.");
							}

						} else if(!Skyblockian.getCore().tagConfig.contains(p.getUniqueId().toString()))
						{
							p.sendMessage(prefix + "You do not have a tag set. use /tag <tag> to make one!");
						} else
						{
							p.sendMessage(prefix + "Your current tag: " + Skyblockian.getCore().tagConfig.getString(p.getUniqueId().toString()));
						}

				}
			} else
			{
				p.sendMessage(noPerm);
			}
		
		} else
		{
			sender.sendMessage(cmdError);
		}
		
		
		return false;
	}
	

}
