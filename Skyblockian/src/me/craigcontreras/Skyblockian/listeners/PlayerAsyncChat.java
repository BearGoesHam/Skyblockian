package me.craigcontreras.Skyblockian.listeners;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.mojang.authlib.GameProfile;

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
		
		if (PermissionsManager.getPManager().getGroup(p) == null)
		{
			e.setFormat(ChatColor.translateAlternateColorCodes('&', PermissionsManager.getPManager().getPrefix(p) + " " + name + " "
					+ PermissionsManager.getPManager().getSuffix(p) + "&7> &r" + e.getMessage()));
		}
		else {
			e.setFormat(ChatColor.translateAlternateColorCodes('&', PermissionsManager.getPManager().getGroupPrefix(p) + " " + name  + " "
					+ PermissionsManager.getPManager().getGroupSuffix(p) + "&7> &r" + e.getMessage()));
		}
	}
}
