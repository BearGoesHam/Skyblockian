package com.skyblockian.Skyblockian.commands;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatColorManager 
{



	private static ChatColorManager instance = new ChatColorManager();

	public Map<UUID, String> chatcolors = new HashMap<UUID, String>();

	
	public static ChatColorManager getChatColorManager()
	{
		return instance;
	}
	
	public String getChatColorType(Player player)
	{
		//return Skyblockian.getCore().colorConfig.getString(player.getUniqueId().toString());
	return this.chatcolors.get(player.getUniqueId()).toString();
	}
	
	
	
	

}
