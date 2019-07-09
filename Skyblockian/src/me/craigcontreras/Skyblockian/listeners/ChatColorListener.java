package me.craigcontreras.Skyblockian.listeners;

import me.craigcontreras.Skyblockian.commands.ChatColorManager;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
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
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have changed your ChatColor to &fWhite"));
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    ChatColorManager.getChatColorManager().chatcolors.put(p.getUniqueId(), "&f");
                    p.closeInventory();
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&cLight Red")))
                {
                    e.setCancelled(true);
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    ChatColorManager.getChatColorManager().chatcolors.put(p.getUniqueId(), "&c");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have changed your ChatColor to &cRed"));
                    p.closeInventory();
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&eYellow")))
                {
                    e.setCancelled(true);
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    ChatColorManager.getChatColorManager().chatcolors.put(p.getUniqueId(), "&e");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have set your ChatColor to &eYellow"));
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
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have changed your ChatColor to &5Purple"));
                    p.closeInventory();
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&2Dark Green")))
                {
                    e.setCancelled(true);
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    ChatColorManager.getChatColorManager().chatcolors.put(p.getUniqueId(), "&2");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have changed your ChatColor to &2Dark Green"));
                    p.closeInventory();
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&aGreen")))
                {
                    e.setCancelled(true);
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    ChatColorManager.getChatColorManager().chatcolors.put(p.getUniqueId(), "&a");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have changed your ChatColor to &aLight Green"));
                    p.closeInventory();
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&1Blue")))
                {
                    e.setCancelled(true);
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    ChatColorManager.getChatColorManager().chatcolors.put(p.getUniqueId(), "&1");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have changed your ChatColor to &1Blue"));
                    p.closeInventory();
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&4Red")))
                {
                    e.setCancelled(true);
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    ChatColorManager.getChatColorManager().chatcolors.put(p.getUniqueId(), "&4");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have changed your ChatColor to &4Red"));
                    p.closeInventory();
                }
                e.setCancelled(true);
            } else if(e.getCurrentItem().getType().equals(Material.PAPER))
            {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&fReset your ChatColor")))
                {
                    e.setCancelled(true);
                    ChatColorManager.getChatColorManager().chatcolors.remove(p.getUniqueId());
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You have reset your ChatColor."));
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
