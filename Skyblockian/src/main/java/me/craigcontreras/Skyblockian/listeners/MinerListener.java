package me.craigcontreras.Skyblockian.listeners;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import net.minecraft.server.v1_12_R1.ItemSkull;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class MinerListener
    implements Listener, TextFormat
{
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        Location loc = e.getBlock().getLocation();

        if (Skyblockian.getCore().minerManager.getLocations().contains(loc))
        {
            Skyblockian.getCore().minersConfig.set(Skyblockian.getCore().minerManager.removeMiner(Skyblockian.getCore().minerManager.getMiner(loc)), false);

            try{
                Skyblockian.getCore().minersConfig.save(Skyblockian.getCore().minersFile);
            }catch (IOException ex)
            {
                ex.printStackTrace();
            }

            ItemStack i = new ItemStack(Material.DISPENSER, 1);
            ItemMeta meta = i.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fMiner"));
            i.setItemMeta(meta);

            e.setCancelled(true);
            e.getBlock().setType(Material.AIR);
            loc.getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e)
    {
        for (Block b : e.blockList())
        {
            if (Skyblockian.getCore().minerManager.getLocations().contains(b.getLocation()))
            {
                Skyblockian.getCore().minersConfig.set(Skyblockian.getCore().minerManager.removeMiner(
                        Skyblockian.getCore().minerManager.getMiner(b.getLocation())), Boolean.valueOf(false));
                try{
                    Skyblockian.getCore().minersConfig.save(Skyblockian.getCore().minersFile);
                }catch (IOException ex)
                {
                    ex.printStackTrace();
                }

                ItemStack i = new ItemStack(Material.DISPENSER, 1);
                ItemMeta meta = i.getItemMeta();
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fMiner"));
                i.setItemMeta(meta);

                b.getLocation().getWorld().dropItemNaturally(b.getLocation(), i);
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e)
    {
        Player p = e.getPlayer();

        if (!(p.getItemInHand().hasItemMeta())) return;

        if (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&fMiner")))
        {
            Skyblockian.getCore().minerManager.getLocations().add(e.getBlock().getLocation());
            Skyblockian.getCore().minersConfig.set(Skyblockian.getCore().minerManager.registerNewMiner(e.getBlock().getLocation(), true), Boolean.valueOf(true));
            p.sendMessage(prefix + "You have placed a miner. Place a block in front of the miner and it'll mine for you. Place a chest behind it to store the contents.");

            try{
                Skyblockian.getCore().minersConfig.save(Skyblockian.getCore().minersFile);
            }catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onPrepareItemCraftEvent(PrepareItemCraftEvent e)
    {
        if (e.getInventory().getMatrix().length < 0)
        {
            return;
        }

        ItemStack reinforcedstone = new ItemStack(Material.STONE, 1);
        ItemMeta rismeta = reinforcedstone.getItemMeta();
        rismeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fReinforced Stone"));
        reinforcedstone.setItemMeta(rismeta);

        ItemStack miner = new ItemStack(Material.DISPENSER);
        ItemMeta meta = miner.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fMiner"));
        miner.setItemMeta(meta);

        //012
        //###
        //345
        //###
        //678
        //###

        checkCraft(miner, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, reinforcedstone);
            put(1, reinforcedstone);
            put(2, reinforcedstone);
            put(3, reinforcedstone);
            put(4, new ItemStack(Material.DIAMOND_PICKAXE));
            put(5, reinforcedstone);
            put(6, reinforcedstone);
            put(7, new ItemStack(Material.REDSTONE));
            put(8, reinforcedstone);
        }});

        checkCraft(reinforcedstone, e.getInventory(), new HashMap<Integer, ItemStack>() {{
            put(0, new ItemStack(Material.STONE));
            put(1, new ItemStack(Material.STONE));
            put(2, new ItemStack(Material.STONE));
            put(3, new ItemStack(Material.STONE));
            put(4, new ItemStack(Material.STONE));
            put(5, new ItemStack(Material.STONE));
            put(6, new ItemStack(Material.STONE));
            put(7, new ItemStack(Material.STONE));
            put(8, new ItemStack(Material.STONE));
        }});
    }

    public void checkCraft(ItemStack result, CraftingInventory inv, HashMap<Integer, ItemStack> ingredients)
    {
        ItemStack[] matrix = inv.getMatrix();

        for (int i = 0; i < 9; i++)
        {
            if (ingredients.containsKey(i))
            {
                if (matrix[i] == null || !matrix[i].equals(ingredients.get(i)))
                {
                    return;
                }
            }
            else{
                if (matrix[i] != null)
                {
                    return;
                }
            }
        }

        inv.setResult(result);
    }
}
