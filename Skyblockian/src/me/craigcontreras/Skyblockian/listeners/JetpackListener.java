package me.craigcontreras.Skyblockian.listeners;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import net.minecraft.server.v1_12_R1.EnumParticle;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class JetpackListener
    implements Listener, TextFormat
{
    Map<Player, Long> cooldown = new HashMap<>();
    List<Player> fallDamage = new ArrayList<>();

    @EventHandler
    public void onBucketEmpty(BlockPlaceEvent e)
    {
        Player p = e.getPlayer();
        ItemStack item = p.getItemInHand();

        if (item == null) return;

        if (item.getType() == Material.LAVA_BUCKET &&
                item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
                        '&', "&6Engine")))
        {
            p.sendMessage(prefix + "You cannot place the engine of a jetpack!");
            e.setCancelled(true);
            return;
        }
        else{
            return;
        }
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent e)
    {
        Player p = e.getPlayer();
        ItemStack item = e.getItem();

        if (item.getType() == null) return;

        if (item.getType() == Material.GOLDEN_APPLE && item.getItemMeta().getDisplayName().equals(
                ChatColor.translateAlternateColorCodes('&', "&6Energy Source")))
        {
            p.sendMessage(prefix + "You cannot eat the energy source of a jetpack!");
            e.setCancelled(true);
            return;
        }
        else{
            return;
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        ItemStack item = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();

        if (inv instanceof AnvilInventory)
        {
            if (item == null) return;

            if (item.getType() == Material.DIAMOND_CHESTPLATE &&
                    item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
                            '&', "&6Jetpack")))
            {
                p.sendMessage(prefix + "You cannot put a jetpack in an anvil!");
                e.setCancelled(true);
                return;
            }else if (item.getType() == Material.LAVA_BUCKET &&
                    item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
                            '&', "&6Engine")))
            {
                p.sendMessage(prefix + "You cannot put the engine of a jetpack in an anvil!");
                e.setCancelled(true);
                return;
            }else if (item.getType() == Material.GOLDEN_APPLE &&
                    item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
                            '&', "&6Energy Source")))
            {
                p.sendMessage(prefix + "You cannot put the energy source of a jetpack in an anvil!");
                e.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onEnchantEvent(EnchantItemEvent e)
    {
        Player p = e.getEnchanter();
        ItemStack item = e.getItem();

        if (item.getType() == null) return;

        if (item.getType() == Material.DIAMOND_CHESTPLATE && item.getItemMeta().getDisplayName().equals(
                ChatColor.translateAlternateColorCodes('&', "&6Jetpack")))
        {
            p.sendMessage(prefix + "You cannot enchant a jetpack!");
            e.setCancelled(true);
            return;
        }
        else {
            return;
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e)
    {
        Player p = e.getPlayer();

        if (p.getGameMode() == GameMode.CREATIVE)
        {
            return;
        }

        if (p.getWorld().getName().equals("pvp"))
        {
            if (cooldown.containsKey(p))
            {
                if (p.getAllowFlight())
                {
                    p.setAllowFlight(false);
                    p.setFlying(false);
                }
            }

            return;
        }

        if (p.getInventory().getChestplate() == null)
        {
            if (cooldown.containsKey(p))
            {
                p.setAllowFlight(false);
                p.setFlying(false);
            }

            return;
        }

        if (p.getInventory().getChestplate().getType() == Material.DIAMOND_CHESTPLATE &&
                p.getInventory().getChestplate().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
                        '&', "&6Jetpack")))
        {
            if (!(p.isOnGround()))
            {
                Particles particles = new Particles(EnumParticle.FLAME, p.getLocation(), 0, 0, 0, 0.02f, 30);
                particles.sendToAll();
            }
        }
        else{
            return;
        }

        if (p.getInventory().getChestplate().getType() == Material.DIAMOND_CHESTPLATE &&
                p.getInventory().getChestplate().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
                        '&', "&6Jetpack")))
        {
            if (p.isOnGround())
            {
                if (!(p.getAllowFlight()))
                {
                    p.setAllowFlight(true);
                }

                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 4 * 20, 1));
            }
        }
        else {
            if (p.getAllowFlight())
            {
                p.setAllowFlight(false);
                p.setFlying(false);
            }
        }
    }

    @EventHandler
    public void onPlayerDoubleJump(PlayerToggleFlightEvent e)
    {
        Player p = e.getPlayer();

        if (p.getWorld().getName().equals("pvp")) return;

        if (p.getInventory().getChestplate() == null) return;

        if (p.getInventory().getChestplate().getType() == Material.DIAMOND_CHESTPLATE &&
                p.getInventory().getChestplate().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
                        '&', "&6Jetpack")))
        {
            if (p.getGameMode() != GameMode.CREATIVE)
            {
                e.setCancelled(true);

                int cooldownTime = 60;

                if (cooldown.containsKey(p))
                {
                    long timeLeft = cooldown.get(p) / 1000 + cooldownTime - System.currentTimeMillis() / 1000;

                    if (timeLeft > 0)
                    {
                        p.sendMessage(prefix + "Your jetpack is on cooldown for " + timeLeft + " seconds.");
                        return;
                    }

                    cooldown.remove(p);
                }

                cooldown.put(p, System.currentTimeMillis());
                p.sendMessage(prefix + "You can now fly for 15 seconds thanks to your jetpack!");
                p.setAllowFlight(true);
                p.setFlying(true);
                new JetpackRunnable(this, p, 15).runTaskTimer(Skyblockian.getCore(), 0, 20);
            }
        }
        else{
            p.setAllowFlight(false);
            p.setFlying(false);
            return;
        }
    }

    @EventHandler
    public void onFall(EntityDamageByEntityEvent e)
    {
        if (fallDamage.contains(e.getEntity()))
        {
            e.setDamage(e.getDamage() * 2);
            fallDamage.remove(e.getEntity());
        }
    }

    @EventHandler
    public void onPrepareItemCraftEvent(PrepareItemCraftEvent e)
    {
        if (e.getInventory().getMatrix().length < 0)
        {
            return;
        }

        ItemStack engine = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta emeta = engine.getItemMeta();
        emeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Engine"));
        emeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Used to craft"),
                ChatColor.translateAlternateColorCodes('&', "&7jetpacks.")));
        engine.setItemMeta(emeta);

        ItemStack energysource = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta esmeta = energysource.getItemMeta();
        esmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Energy Source"));
        esmeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Used to craft"),
                ChatColor.translateAlternateColorCodes('&', "&7jetpacks.")));
        energysource.setItemMeta(esmeta);

        ItemStack result = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Jetpack"));
        result.setItemMeta(meta);

        //012
        //###
        //345
        //###
        //678
        //###

        checkCraft(result, e.getInventory(), new HashMap<Integer, ItemStack>(){{
            put(0, new ItemStack(Material.DIAMOND));
            put(2, new ItemStack(Material.DIAMOND));
            put(3, new ItemStack(Material.DIAMOND));
            put(4, energysource);
            put(5, new ItemStack(Material.DIAMOND));
            put(6, engine);
            put(7, engine);
            put(8, engine);
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

    public boolean addToFallDamage(Player p)
    {
        return fallDamage.add(p);
    }
}