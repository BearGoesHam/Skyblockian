package com.skyblockian.Skyblockian.listeners;

import com.skyblockian.Skyblockian.Skyblockian;
import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LockListener
    implements Listener, TextFormat
{
    @EventHandler
    public void onPlayerPlaceChest(BlockPlaceEvent e)
    {
        if (e.getBlockPlaced().getType().equals(Material.CHEST) || e.getBlock().getType().equals(Material.TRAPPED_CHEST))
        {
            Player p = e.getPlayer();
            p.openInventory(openLocker(p, e.getBlockPlaced().getLocation()));
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        Player p = e.getPlayer();

        int x = e.getBlock().getX();
        int y = e.getBlock().getY();
        int z = e.getBlock().getZ();

        String configLoc = "" + x + "/" + y + "/" + z;

        if (Skyblockian.getCore().lockersConfig.contains(configLoc))
        {
            if (Skyblockian.getCore().randomize(1, 15) == 1)
            {
                e.setCancelled(false);
                Skyblockian.getCore().lockersConfig.set(configLoc, null);

                try{
                    Skyblockian.getCore().lockersConfig.save(Skyblockian.getCore().lockersFile);
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }

                p.sendMessage(prefix + "You broke through the lock!");
                return;
            }
            else{
                e.setCancelled(true);
                p.sendMessage(prefix + "This lock is too strong! Try again and it might get weaker!");
                return;
            }
        }
    }

    @EventHandler
    public void onPlayerClickChest(PlayerInteractEvent e)
    {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            if (e.getClickedBlock().getType().equals(Material.CHEST) || e.getClickedBlock().getType().equals(Material.TRAPPED_CHEST))
            {
                e.setCancelled(true);
                e.getPlayer().openInventory(openLocker(e.getPlayer(), e.getClickedBlock().getLocation()));
            }
        }
    }

    @EventHandler
    public void onClickItems(InventoryClickEvent e)
    {
        if (e.getInventory().getName().startsWith(ChatColor.AQUA + "Locker"))
        {
            e.setCancelled(true);

            if (e.getRawSlot() == e.getSlot() && e.getCurrentItem() != null && !e.getCurrentItem().getType().equals(Material.AIR))
            {
                Player p = (Player) e.getWhoClicked();
                ItemStack item = e.getCurrentItem();

                if (item.getType().equals(Material.STAINED_GLASS_PANE))
                {
                    if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "BACK"))
                    {
                        Inventory inv = e.getInventory();

                        if (inv.firstEmpty() != 0)
                        {
                            inv.setItem(inv.firstEmpty() - 1, new ItemStack(Material.AIR));
                        }
                    }else if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "ACCEPT"))
                    {
                        String code = "";

                        for (int i = 0; i < 9; i++)
                        {
                            if (e.getInventory().getItem(i) != null)
                            {
                                ItemStack numberItem = e.getInventory().getItem(i);
                                String number = ChatColor.stripColor(numberItem.getItemMeta().getDisplayName());
                                code = code + number;
                            }
                        }

                        String configCode = ChatColor.stripColor(e.getInventory().getTitle()).replace("Locker/", "");

                        if (Skyblockian.getCore().lockersConfig.contains(configCode))
                        {
                            if (Skyblockian.getCore().lockersConfig.getString(configCode).equals(code))
                            {
                                int x = Integer.parseInt(configCode.split("/")[0]);
                                int y = Integer.parseInt(configCode.split("/")[1]);
                                int z = Integer.parseInt(configCode.split("/")[2]);
                                Location loc = e.getWhoClicked().getLocation();
                                loc.setX(x);
                                loc.setY(y);
                                loc.setZ(z);
                                Inventory chestInv =
                                        ((Chest)Bukkit.getWorld(loc.getWorld().getName()).getBlockAt(loc).getState()).getBlockInventory();
                                e.getWhoClicked().openInventory(chestInv);
                                p.sendMessage(prefix + "Unlocked chest.");
                            }
                            else{
                                p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 30 * 20, 0));
                                p.sendMessage(prefix + "Code is incorrect.");
                                p.closeInventory();
                                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 1.0f, 1.0f);
                            }

                            return;
                        }

                        Skyblockian.getCore().lockersConfig.set(configCode, code);
                        p.sendMessage(prefix + "Code has been set.");
                        p.closeInventory();

                        try{
                            Skyblockian.getCore().lockersConfig.save(Skyblockian.getCore().lockersFile);
                        }catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }else if (e.getRawSlot() > 8)
                {
                    Inventory inv = e.getInventory();

                    if (inv.firstEmpty() <= 8)
                    {
                        inv.setItem(inv.firstEmpty(), item);
                    }
                }
            }
        }
    }

    public Inventory openLocker(Player p, Location loc)
    {
        Inventory locker = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&',
                "&bLocker/" + loc.getBlockX() + "/" + loc.getBlockY() + "/" + loc.getBlockZ()));

        for (int i = 0; i < 10; i++)
        {
            ItemStack number = new ItemStack(Material.WOOL, 1, (byte)1);
            ItemMeta numbermeta = number.getItemMeta();
            numbermeta.setDisplayName(ChatColor.GRAY + "" + i);
            number.setItemMeta(numbermeta);

            int slot = 0;

            if (i == 0)
            {
                slot = 40;
            }else if (i <= 3)
            {
                slot = 11 + i;
            }else if (i <= 6)
            {
                slot = 20 + (i - 3);
            }else if (i <= 9)
            {
                slot = 29 + (i - 6);
            }

            locker.setItem(slot, number);
        }

        ItemStack back = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)14);
        ItemMeta backmeta  = back.getItemMeta();
        backmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cBACK"));
        back.setItemMeta(backmeta);
        locker.setItem(36, back);

        ItemStack confirm = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)5);
        ItemMeta confirmmeta  = confirm.getItemMeta();
        confirmmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aACCEPT"));
        confirm.setItemMeta(confirmmeta);
        locker.setItem(44, confirm);

        return locker;
    }
}
