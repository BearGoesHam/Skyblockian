package com.skyblockian.Skyblockian.listeners;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import com.skyblockian.Skyblockian.commands.ChatColorManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ChatColorListener implements Listener, TextFormat
{
    @EventHandler
    public void onInteract(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();

        if(e.getInventory().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&bChatColor")))
        {
            if(e.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE))
            {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&fWhite")))
                {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have changed your color to &fwhite&7."));
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    ChatColorManager.getChatColorManager().chatcolors.put(p.getUniqueId(), "&f");
                    p.closeInventory();
                }
                
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&cLight Red")))
                {
                    e.setCancelled(true);
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    ChatColorManager.getChatColorManager().chatcolors.put(p.getUniqueId(), "&c");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have changed your color to &cred&7."));
                    p.closeInventory();
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&eYellow")))
                {
                    e.setCancelled(true);
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    ChatColorManager.getChatColorManager().chatcolors.put(p.getUniqueId(), "&e");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have set your color to &eyellow&7."));
                    p.closeInventory();
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6Gold")))
                {
                    e.setCancelled(true);
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    ChatColorManager.getChatColorManager().chatcolors.put(p.getUniqueId(), "&6");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have changed your ChatColor to &6Gold"));
                    p.closeInventory();
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&5Magenta")))
                {
                    e.setCancelled(true);
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    ChatColorManager.getChatColorManager().chatcolors.put(p.getUniqueId(), "&5");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have changed your color to &5purple&7."));
                    p.closeInventory();
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&2Dark Green")))
                {
                    e.setCancelled(true);
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    ChatColorManager.getChatColorManager().chatcolors.put(p.getUniqueId(), "&2");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have changed your color to &2dark green&7."));
                    p.closeInventory();
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aGreen")))
                {
                    e.setCancelled(true);
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    ChatColorManager.getChatColorManager().chatcolors.put(p.getUniqueId(), "&a");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have changed your color to &alight green&7."));
                    p.closeInventory();
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&1Blue")))
                {
                    e.setCancelled(true);
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    ChatColorManager.getChatColorManager().chatcolors.put(p.getUniqueId(), "&1");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have changed your color to &1blue&7."));
                    p.closeInventory();
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&4Red")))
                {
                    e.setCancelled(true);
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    ChatColorManager.getChatColorManager().chatcolors.put(p.getUniqueId(), "&4");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have color your color to &4red&7."));
                    p.closeInventory();
                }
                e.setCancelled(true);
            } else if(e.getCurrentItem().getType().equals(Material.PAPER))
            {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&fReset your ChatColor")))
                {
                    e.setCancelled(true);
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have reset your color."));
                    p.closeInventory();
                }
            }
            else
            {
                e.setCancelled(true);
            }


            e.setCancelled(true);
        }


    }

}
