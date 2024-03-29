package com.skyblockian.Skyblockian.commands;

import java.util.ArrayList;
import java.util.Arrays;

import com.skyblockian.Skyblockian.commands.admin.*;
import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandManagerAdmin 
implements CommandExecutor, TextFormat
{
	private ArrayList<AdminCommands> cmds = new ArrayList<AdminCommands>();
	
	public CommandManagerAdmin()
	{
		cmds.add(new FlyCommand());
		cmds.add(new HealCommand());
		cmds.add(new FeedCommand());
		cmds.add(new GamemodeCommand());
		cmds.add(new ClearChatCommand());
		cmds.add(new VanishCommand());
		cmds.add(new StaffChatCommand());
		cmds.add(new SetWarpCommand());
		cmds.add(new SetSpawnCommand());
		cmds.add(new AllSayCommand());
		cmds.add(new HitDelayCommand());
		cmds.add(new StaffModeCommand());
		cmds.add(new FreezeCommand());
		cmds.add(new TeleportAllCommand());
		cmds.add(new ReloadConfigCommand());
		cmds.add(new BroadcastCommand());
		cmds.add(new ReachLimitCommand());
		cmds.add(new SudoCommand());
		cmds.add(new ItemCommand());
		cmds.add(new DelWarpCommand());
		cmds.add(new SetWarpCommand());
		cmds.add(new SmiteCommand());
		cmds.add(new InvseeCommand());
		cmds.add(new EnderchestSeeCommand());
		cmds.add(new ChatCommand());
		cmds.add(new MuteChatCommand());
		cmds.add(new DemoCommand());
		cmds.add(new GiveMinerCommand());
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (args.length == 0)
		{
			if (sender.hasPermission("skyblockian.admin"))
			{
				for (AdminCommands c : cmds)
				{
					sender.sendMessage(prefix + "/admin " + c.getName() + ": " + c.getArgs() + " - " + c.getDescription());
				}
			}
			else {
				sender.sendMessage(noPerm);
			}
			return true;
		}
		
		ArrayList<String> a = new ArrayList<String> (Arrays.asList(args));
		a.remove(0);
		
		for (AdminCommands c : cmds)
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