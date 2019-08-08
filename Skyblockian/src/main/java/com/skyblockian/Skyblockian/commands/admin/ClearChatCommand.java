package com.skyblockian.Skyblockian.commands.admin;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.skyblockian.Skyblockian.commands.AdminCommands;

public class ClearChatCommand 
extends AdminCommands
implements TextFormat
{
    public ClearChatCommand() 
    {
		super("cc", "Clear the chat.", "No specified args.");
		// TODO Auto-generated constructor stub
	}
    
	public void run(CommandSender sender, String[] args)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage("");
			return;
		}
		
		if (sender.hasPermission("skyblockian.admin"))
		{
			clearChat();
			sender.sendMessage(prefix + "You have successfully cleared the chat.");
		}
		else{
			sender.sendMessage(noPerm);
		}
	}
    
	public void clearChat() 
    {
        for (Player pl : Bukkit.getOnlinePlayers()) 
        {
            for (int i=0; i < 100; i++) 
            {
                pl.sendMessage(" ");
            }
        }
    }
}
