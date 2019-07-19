package me.craigcontreras.Skyblockian.listeners;

import me.craigcontreras.Skyblockian.commands.ChatColorManager;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.mojang.authlib.GameProfile;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.permissions.managers.PermissionsManager;

public class PlayerAsyncChat
		implements Listener
{
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e)
	{
		Player p = e.getPlayer();
		GameProfile profile = ((CraftPlayer)p).getProfile();
		String name = profile.getName();

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
					e.setFormat(ChatColor.translateAlternateColorCodes('&', PermissionsManager.getPManager().getGroupPrefix(p) +
							Skyblockian.getCore().tagConfig.getString(p.getUniqueId().toString()) + " " + name + " "
							+ PermissionsManager.getPManager().getSuffix(p) + "&7> " + ChatColorManager.getChatColorManager().chatcolors.get(p.getUniqueId()) +  e.getMessage()));
				} else
				{
					e.setFormat(ChatColor.translateAlternateColorCodes('&', PermissionsManager.getPManager().getGroupPrefix(p) +
							Skyblockian.getCore().tagConfig.getString(p.getUniqueId().toString()) + " " + name + " "
							+ PermissionsManager.getPManager().getSuffix(p) + "&7> " + e.getMessage()));
				}
			} else
			{
				e.setFormat(ChatColor.translateAlternateColorCodes('&', PermissionsManager.getPManager().getGroupPrefix(p) + " " + name + " "
						+ PermissionsManager.getPManager().getSuffix(p) + "&7> &r" + e.getMessage()));
			}
		}

		if (PermissionsManager.getPManager().getGroup(p) == null)
		{
			if(Skyblockian.getCore().tagConfig.contains(p.getUniqueId().toString()))
			{
				if(ChatColorManager.getChatColorManager().chatcolors.containsKey(p.getUniqueId()))
				{
					e.setFormat(ChatColor.translateAlternateColorCodes('&', PermissionsManager.getPManager().getPrefix(p) +
							Skyblockian.getCore().tagConfig.getString(p.getUniqueId().toString()) + " " + name + " "
							+ PermissionsManager.getPManager().getSuffix(p) + "&7> " + ChatColorManager.getChatColorManager().chatcolors.get(p.getUniqueId()) + e.getMessage()));
				} else
				{
					e.setFormat(ChatColor.translateAlternateColorCodes('&', PermissionsManager.getPManager().getPrefix(p) +
							Skyblockian.getCore().tagConfig.getString(p.getUniqueId().toString()) + " " + name + " "
							+ PermissionsManager.getPManager().getSuffix(p) + "&7> &r" + e.getMessage()));
				}
			} else
			{
				e.setFormat(ChatColor.translateAlternateColorCodes('&', PermissionsManager.getPManager().getPrefix(p) + " " + name + " "
						+ PermissionsManager.getPManager().getSuffix(p) + "&7> &r" + e.getMessage()));
			}
		}
		else {
			if(Skyblockian.getCore().tagConfig.contains(p.getUniqueId().toString()))
			{
				if(ChatColorManager.getChatColorManager().chatcolors.containsKey(p.getUniqueId()))
				{
					e.setFormat(ChatColor.translateAlternateColorCodes('&', PermissionsManager.getPManager().getGroupPrefix(p) +
							Skyblockian.getCore().tagConfig.getString(p.getUniqueId().toString()) + " " + name  + " "
							+ PermissionsManager.getPManager().getGroupSuffix(p) + "&7> " + ChatColorManager.getChatColorManager().chatcolors.get(p.getUniqueId()) + e.getMessage()));
				} else
				{
					e.setFormat(ChatColor.translateAlternateColorCodes('&', PermissionsManager.getPManager().getGroupPrefix(p) +
							Skyblockian.getCore().tagConfig.getString(p.getUniqueId().toString()) + " " + name  + " "
							+ PermissionsManager.getPManager().getGroupSuffix(p) + "&7> &r" + e.getMessage()));
				}
			} else
			{
				if(ChatColorManager.getChatColorManager().chatcolors.containsKey(p.getUniqueId()))
				{
					e.setFormat(ChatColor.translateAlternateColorCodes('&', PermissionsManager.getPManager().getGroupPrefix(p) + " " + name + " "
							+ PermissionsManager.getPManager().getGroupSuffix(p) + "&7> " + ChatColorManager.getChatColorManager().chatcolors.get(p.getUniqueId()) + e.getMessage()));
				} else
				{
					e.setFormat(ChatColor.translateAlternateColorCodes('&', PermissionsManager.getPManager().getGroupPrefix(p) + " " + name + " "
							+ PermissionsManager.getPManager().getGroupSuffix(p) + "&7> " + e.getMessage()));
				}
			}

		}


	}
}