package me.craigcontreras.Skyblockian.commands;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class MessageManager 
{
	public static HashMap<Player, Player> conversations = new HashMap<>();
	
	public static void setReplyTarget(Player messager, Player receiver)
	{
		conversations.put(messager, receiver);
		conversations.put(receiver, messager);
	}
	
	public static Player getReplyTarget(Player messager)
	{
		return conversations.get(messager);
	}
}
