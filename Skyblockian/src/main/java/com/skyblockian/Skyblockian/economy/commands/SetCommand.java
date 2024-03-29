package com.skyblockian.Skyblockian.economy.commands;

import com.skyblockian.Skyblockian.economy.EcoCommands;
import com.skyblockian.Skyblockian.economy.SettingsManager;
import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class SetCommand
    extends EcoCommands
    implements TextFormat
{
    public SetCommand() { super("set", "Set another player's balance to the amount specified.", "<player> <amount>"); }

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

        SettingsManager.getEcoManager().setBalance(name, amount);
        sender.sendMessage(prefix + name + "'s bank account is now set at $" + amount + ".");
        Bukkit.broadcast(prefix + sender.getName() + " has set " + name + "'s bank account to $" + amount + ".", "skyblockian.admin");
    }
}
