package me.craigcontreras.Skyblockian.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

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
