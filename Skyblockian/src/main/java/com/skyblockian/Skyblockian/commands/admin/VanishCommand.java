package com.skyblockian.Skyblockian.commands.admin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.skyblockian.Skyblockian.commands.AdminCommands;

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
						if (pl.hasPermission("skyblockian.admin"))
						{
							pl.showPlayer(target);
						}
						else{
							pl.hidePlayer(target);
						}
					}

					target.sendMessage(prefix + "You've been vanished.");
					target.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&aYou're vanished!")));

					sender.sendMessage(prefix + "You vanished " + target.getName() + " from the server.");
					vanish.add(target.getUniqueId());

					Bukkit.broadcast(prefix + target.getName() + " has been vanished.", "skyblockian.admin");
					return;
				}
				
				if (vanish.contains(target.getUniqueId()))
				{					
					for (Player pl : Bukkit.getOnlinePlayers())
					{
						pl.showPlayer(target);
					}
					
					target.sendMessage(prefix + "You've been unvanished.");
					
					sender.sendMessage(prefix + "You unvanished " + target.getName() + ".");
					vanish.remove(target.getUniqueId());

					Bukkit.broadcast(prefix + target.getName() + " has been unvanished.", "skyblockian.admin");
					return;
				}
			}else if (args.length == 0)
			{
				Player p = (Player)sender;
				
				if (!(vanish.contains(p.getUniqueId())))
				{
					for (Player pl : Bukkit.getOnlinePlayers())
					{
						if (pl.hasPermission("skyblockian.admin"))
						{
							pl.showPlayer(p);
						}
						else{
							pl.hidePlayer(p);
						}
					}
					
					p.sendMessage(prefix + "You've been vanished.");
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&aYou're vanished!")));
					vanish.add(p.getUniqueId());

					Bukkit.broadcast(prefix + p.getName() + " has been vanished.", "skyblockian.admin");
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

					Bukkit.broadcast(prefix + p.getName() + " has been unvanished.", "skyblockian.admin");
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
