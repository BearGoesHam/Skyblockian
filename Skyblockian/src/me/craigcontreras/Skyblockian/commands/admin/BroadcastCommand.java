package me.craigcontreras.Skyblockian.commands.admin;

import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand extends AdminCommands implements TextFormat
{

    public BroadcastCommand()
    {
        super("broadcast", "Broadcasts to the current server.", "<message>");
    }

    public void run(CommandSender sender, String[] args)
    {
    	if (sender.hasPermission("skyblockian.admin"))
    	{
    		if(args.length >= 1)
    		{
    			String msg = "";
    			int x = 1;

    			for (String a : args)
    			{
    				if (x == 1)
    				{
    					x++;
    				}
    				
    				msg = msg + " " + a;
    			}

    			msg = msg.trim();

    			for(Player players : Bukkit.getOnlinePlayers())
    			{
    				players.sendMessage(TextFormat.prefix + msg);
    			}

    		} else
    		{
    			sender.sendMessage(TextFormat.argsError);
    		}
    	} else
    	{
    		sender.sendMessage(TextFormat.noPerm);
    	}
    }
}
