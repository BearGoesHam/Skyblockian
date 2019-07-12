package me.craigcontreras.Skyblockian.economy;

import java.util.ArrayList;
import java.util.Arrays;

import me.craigcontreras.Skyblockian.economy.commands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class CommandManager
implements CommandExecutor, TextFormat
{
	private ArrayList<EcoCommands> cmds = new ArrayList<EcoCommands>();
	
	public CommandManager()
	{
		cmds.add(new AddCommand());
		cmds.add(new RemoveCommand());
		cmds.add(new PayCommand());
		cmds.add(new ResetCommand());
		cmds.add(new TopCommand());
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (args.length == 0)
		{
			if (sender instanceof Player)
			{
				Player p = (Player)sender;
				p.sendMessage(prefix + "Your balance is $" + SettingsManager.getEcoManager().getBalance(p.getName()) + ".");
			}
			
			if (sender.hasPermission("skyblockian.admin"))
			{
				for (EcoCommands c : cmds)
				{
					sender.sendMessage(prefix + "/eco " + c.getName() + ": " + c.getArgs() + " - " + c.getDescription());
				}
			}
			
			return true;
		}
		
		ArrayList<String> a = new ArrayList<String> (Arrays.asList(args));
		a.remove(0);
		
		for (EcoCommands c : cmds)
		{
			if (c.getName().equalsIgnoreCase(args[0]))
			{
				try { c.run(sender, a.toArray(new String[a.size()])); }
				catch (Exception e) { sender.sendMessage(prefix + "An error has occurred."); e.printStackTrace(); }
				return true;
			}
		}
		
		sender.sendMessage(prefix + "There is no such command.");
		
		return true;
	}
}
