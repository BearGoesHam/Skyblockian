package com.skyblockian.Skyblockian.economy.commands;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.skyblockian.Skyblockian.economy.EcoCommands;
import com.skyblockian.Skyblockian.economy.SettingsManager;
import com.skyblockian.Skyblockian.interfaces.TextFormat;

public class AddCommand 
extends EcoCommands
implements TextFormat
{
	public AddCommand()
	{
		super("add", "Add money to balance.", "<player> <amount>");
	}
	
	public void run(CommandSender sender, String[] args)
	{
		if (!(sender.hasPermission("skyblockian.admin")))
		{
			sender.sendMessage(noPerm);
			return;
		}
		
		if (args.length < 2)
		{
			sender.sendMessage(argsError);
			return;
		}
		
		String name = args[0];
		double amount;
		
		try { amount = Double.parseDouble(args[1]); }
		catch (Exception e)
		{
			sender.sendMessage(prefix + "Invalid balance.");
			return;
		}
		
		SettingsManager.getEcoManager().addBalance(name, amount);
		sender.sendMessage(prefix + "Added $" + amount + " to " + name + "'s bank. They now have $" + SettingsManager.getEcoManager().getBalance(name) + ".");

		Bukkit.broadcast(prefix + sender.getName() + " has added $" + amount + " to " + name + "'s bank account.", "skyblockian.admin");
	}
}
