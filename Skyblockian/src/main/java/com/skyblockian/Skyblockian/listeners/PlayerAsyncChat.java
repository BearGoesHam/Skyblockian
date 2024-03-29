package com.skyblockian.Skyblockian.listeners;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import com.skyblockian.Skyblockian.permissions.managers.PermissionsManager;
import com.skyblockian.Skyblockian.commands.ChatColorManager;
import com.skyblockian.Skyblockian.commands.admin.MuteChatCommand;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.mojang.authlib.GameProfile;

import com.skyblockian.Skyblockian.Skyblockian;

import java.io.File;

public class PlayerAsyncChat
		implements Listener, TextFormat
{
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e)
	{
		Player p = e.getPlayer();
		GameProfile profile = ((CraftPlayer)p).getProfile();
		String name = profile.getName();

		File f = new File(Skyblockian.getCore().getDataFolder() + File.separator + "playerdata" + File.separator +
				p.getUniqueId() + ".yml");
		FileConfiguration con = YamlConfiguration.loadConfiguration(f);

		if (PermissionsManager.getPManager().getGroupSuffix(p) == null)
		{
			if(Skyblockian.getCore().tagConfig.contains(p.getUniqueId().toString()))
			{
				/*e.setFormat(ChatColor.translateAlternateColorCodes('&', PermissionsManager.getPManager().getGroupPrefix(p) +
						Skyblockian.getCore().tagConfig.getString(p.getUniqueId().toString()) + " " + name + " "
						+ PermissionsManager.getPManager().getSuffix(p) + "&7> &r" + e.getMessage()));
						*/
				if(ChatColorManager.getChatColorManager().chatcolors.containsKey(p.getUniqueId()))
				{
					e.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[&9" + con.getInt("level") + "&7] " + PermissionsManager.getPManager().getGroupPrefix(p) +
							Skyblockian.getCore().tagConfig.getString(p.getUniqueId().toString()) + " " + name + " "
							+ PermissionsManager.getPManager().getSuffix(p) + "&7> " + ChatColorManager.getChatColorManager().chatcolors.get(p.getUniqueId()) +  e.getMessage()));
				} else
				{
					e.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[&9" + con.getInt("level") + "&7] " + PermissionsManager.getPManager().getGroupPrefix(p) +
							Skyblockian.getCore().tagConfig.getString(p.getUniqueId().toString()) + " " + name + " "
							+ PermissionsManager.getPManager().getSuffix(p) + "&7> &r" + e.getMessage()));
				}
			} else
			{
				e.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[&9" + con.getInt("level") + "&7] " + PermissionsManager.getPManager().getGroupPrefix(p) + " " + name + " "
						+ PermissionsManager.getPManager().getSuffix(p) + "&7> &r" + e.getMessage()));
			}
		}

		if (PermissionsManager.getPManager().getGroup(p) == null)
		{
			if(Skyblockian.getCore().tagConfig.contains(p.getUniqueId().toString()))
			{
				if(ChatColorManager.getChatColorManager().chatcolors.containsKey(p.getUniqueId()))
				{
					e.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[&9" + con.getInt("level") + "&7] " + PermissionsManager.getPManager().getPrefix(p) +
							Skyblockian.getCore().tagConfig.getString(p.getUniqueId().toString()) + " " + name + " "
							+ PermissionsManager.getPManager().getSuffix(p) + "&7> " + ChatColorManager.getChatColorManager().chatcolors.get(p.getUniqueId()) + e.getMessage()));
				} else
				{
					e.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[&9" + con.getInt("level") + "&7] " + PermissionsManager.getPManager().getPrefix(p) +
							Skyblockian.getCore().tagConfig.getString(p.getUniqueId().toString()) + " " + name + " "
							+ PermissionsManager.getPManager().getSuffix(p) + "&7> &r" + e.getMessage()));
				}
			} else
			{
				e.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[&9" + con.getInt("level") + "&7] " + PermissionsManager.getPManager().getPrefix(p) + " " + name + " "
						+ PermissionsManager.getPManager().getSuffix(p) + "&7> &r" + e.getMessage()));
			}
		}
		else {
			if(Skyblockian.getCore().tagConfig.contains(p.getUniqueId().toString()))
			{
				if(ChatColorManager.getChatColorManager().chatcolors.containsKey(p.getUniqueId()))
				{
					e.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[&9" + con.getInt("level") + "&7] " + PermissionsManager.getPManager().getGroupPrefix(p) +
							Skyblockian.getCore().tagConfig.getString(p.getUniqueId().toString()) + " " + name  + " "
							+ PermissionsManager.getPManager().getGroupSuffix(p) + "&7> " + ChatColorManager.getChatColorManager().chatcolors.get(p.getUniqueId()) + e.getMessage()));
				} else
				{
					e.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[&9" + con.getInt("level") + "&7] " + PermissionsManager.getPManager().getGroupPrefix(p) +
							Skyblockian.getCore().tagConfig.getString(p.getUniqueId().toString()) + " " + name  + " "
							+ PermissionsManager.getPManager().getGroupSuffix(p) + "&7> &r" + e.getMessage()));
				}
			} else
			{
				if(ChatColorManager.getChatColorManager().chatcolors.containsKey(p.getUniqueId()))
				{
					e.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[&9" + con.getInt("level") + "&7] " + PermissionsManager.getPManager().getGroupPrefix(p) + " " + name + " "
							+ PermissionsManager.getPManager().getGroupSuffix(p) + "&7> " + ChatColorManager.getChatColorManager().chatcolors.get(p.getUniqueId()) + e.getMessage()));
				} else
				{
					e.setFormat(ChatColor.translateAlternateColorCodes('&', "&7[&9" + con.getInt("level") + "&7] " + PermissionsManager.getPManager().getGroupPrefix(p) + " " + name + " "
							+ PermissionsManager.getPManager().getGroupSuffix(p) + "&7> &r" + e.getMessage()));
				}
			}
		}

		if (MuteChatCommand.mute)
		{
			if (p.hasPermission("skyblockian.admin"))
			{
				return;
			}
			else{
				p.sendMessage(prefix + "The chat is muted.");
				e.setCancelled(true);
			}
		}
		else{
			e.setCancelled(false);
			return;
		}
	}
}