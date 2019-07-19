package me.craigcontreras.Skyblockian.commands;

import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class WarpsCommand
    implements CommandExecutor,
        Listener, TextFormat
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player p = (Player)sender;
            Inventory inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&bWarps"));

            ItemStack pvp = new ItemStack(Material.STAINED_CLAY, 1, (byte)14);
            ItemMeta pvpmeta = pvp.getItemMeta();
            pvpmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bPvP"));
            pvpmeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Fight 'til the death! (or until combat log expires)")));
            pvp.setItemMeta(pvpmeta);

            p.sendMessage(prefix + "Opening warps...");
            inv.setItem(13, pvp);
            p.openInventory(inv);
        }

        return false;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();
        ItemStack item = e.getCurrentItem();

        if (inv.getName().equals(ChatColor.translateAlternateColorCodes('&', "&bWarps")))
        {
            if (item.getType().equals(Material.STAINED_CLAY) &&
                    (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
                            '&', "&bPvP"))) && (item.getItemMeta().getLore().equals(
                                    Arrays.asList(ChatColor.translateAlternateColorCodes(
                                            '&', "&7Fight 'til the death! (or until combat log expires)")))))
            {
                Bukkit.dispatchCommand(p, "warp pvp");
                p.closeInventory();
                e.setCancelled(true);
                return;
            }
        }
    }
}
