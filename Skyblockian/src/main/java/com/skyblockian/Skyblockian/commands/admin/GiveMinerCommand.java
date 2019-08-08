package com.skyblockian.Skyblockian.commands.admin;

import com.skyblockian.Skyblockian.commands.AdminCommands;
import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveMinerCommand
    extends AdminCommands
    implements TextFormat
{
    public GiveMinerCommand() { super("giveminer", "Give yourself a miner.", ""); }

    public void run(CommandSender sender, String[] args)
    {
        if (sender instanceof Player)
        {
            Player p = (Player)sender;

            ItemStack i = new ItemStack(Material.DISPENSER);
            ItemMeta meta = i.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fMiner"));
            i.setItemMeta(meta);

            p.getInventory().addItem(i);
            p.sendMessage(prefix + "You gave yourself a miner.");
        }
        else{
            sender.sendMessage(" ");
            return;
        }
    }
}
