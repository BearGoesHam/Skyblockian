package com.skyblockian.Skyblockian.economy.commands;

import com.skyblockian.Skyblockian.economy.EcoCommands;
import com.skyblockian.Skyblockian.economy.SettingsManager;
import com.skyblockian.Skyblockian.economy.VaultIntegration;
import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetCommand
    extends EcoCommands
    implements TextFormat
{
    private VaultIntegration vault = new VaultIntegration();

    public ResetCommand() { super("reset", "Reset a player's balance.", "<player>"); }

    public void run(CommandSender sender, String[] args)
    {
        if (!(sender.hasPermission("skyblockian.admin")))
        {
            sender.sendMessage(noPerm);
            return;
        }
        else{
            if (args.length < 1)
            {
                sender.sendMessage(argsError);
                return;
            }

            Player t = Bukkit.getPlayer(args[0]);

            if (t == null)
            {
                sender.sendMessage(playerError + args[0] + ".");
                return;
            }

            SettingsManager.getEcoManager().setBalance(t.getName(), 0);
            sender.sendMessage(prefix + t.getName() + "'s bank account has been reset to $0.00.");
            t.sendMessage(prefix + "Your bank account has been reset.");

            Bukkit.broadcast(prefix + sender.getName() + " has reset " + t.getName() + "'s bank account.", "skyblockian.admin");
        }
    }
}
