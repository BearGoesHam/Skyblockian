package com.skyblockian.Skyblockian.economy.commands;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.skyblockian.Skyblockian.economy.EcoCommands;
import com.skyblockian.Skyblockian.economy.SettingsManager;
import com.skyblockian.Skyblockian.economy.VaultIntegration;

public class PayCommand 
extends EcoCommands
implements TextFormat
{
	private VaultIntegration vault = new VaultIntegration();
	
	public PayCommand()
	{
		super("pay", "Pay somebody money.", "<player> <amount>");
	}
	
	public void run(CommandSender sender, String[] args)
	{
		if (args.length < 2)
		{
			sender.sendMessage(argsError);
			return;
		}
		
		Player target = Bukkit.getPlayer(args[0]);
		double amount;
		
		try { amount = Double.parseDouble(args[1]); }
		catch (Exception e)
		{
			sender.sendMessage(prefix + "Invalid balance.");
			return;
		}
		
		if (target == null)
		{
			sender.sendMessage(prefix + "This player does not exist.");
			return;
		}
		
		if (SettingsManager.getEcoManager().getBalance(sender.getName()) < amount)
		{
			sender.sendMessage(prefix + "Insufficient funds.");
		}
		else {
			vault.withdrawPlayer(sender.getName(), amount);
			vault.depositPlayer(target.getName(), amount);
			
			target.sendMessage(prefix + "You have been paid $" + amount + " by " + sender.getName() + ".");
			target.sendMessage(prefix + "You have successfully paid " + target.getName() + ".");
		}
	}
}
