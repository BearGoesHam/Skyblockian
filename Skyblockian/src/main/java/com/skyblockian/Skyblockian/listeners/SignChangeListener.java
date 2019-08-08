package com.skyblockian.Skyblockian.listeners;

import com.skyblockian.Skyblockian.Skyblockian;
import com.skyblockian.Skyblockian.economy.SettingsManager;
import com.skyblockian.Skyblockian.economy.VaultIntegration;
import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.*;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class SignChangeListener
    implements Listener, TextFormat
{
    private VaultIntegration vault = new VaultIntegration();

    public List<Sign> signs = new ArrayList<>();

    public void setup()
    {
        for (String str : Skyblockian.getCore().getConfig().getConfigurationSection("signs").getKeys(false))
        {
            ConfigurationSection sec = Skyblockian.getCore().getConfig().getConfigurationSection("signs." + str);
            Location loc = locationFromConfig(sec.getConfigurationSection("location"), false);
            Sign s = (Sign) loc.getBlock().getState();
            signs.add(s);
        }
    }

    @EventHandler
    public void onSignChange(SignChangeEvent e)
    {
        Player p = e.getPlayer();

        if (e.getLine(0).equalsIgnoreCase("Black Market"))
        {
            if (p.hasPermission("skyblockian.admin"))
            {
                ConfigurationSection section = Skyblockian.getCore().getConfig().createSection("signs." +
                        Skyblockian.getCore().getConfig().getConfigurationSection("signs").getKeys(true).size() + 1);
                ConfigurationSection loc = section.createSection("location");
                loc.set("world", e.getBlock().getLocation().getWorld().getName());
                loc.set("x", e.getBlock().getLocation().getX());
                loc.set("y", e.getBlock().getLocation().getY());
                loc.set("z", e.getBlock().getLocation().getZ());

                try{
                    Skyblockian.getCore().saveConfig();
                }catch (Exception ex) { ex.printStackTrace(); }

                p.sendMessage(prefix + "You've created a black market sign!");
                signs.add((Sign) e.getBlock().getState());
                e.setLine(1, ChatColor.translateAlternateColorCodes('&', "&8&oRight click"));
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();

        if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;

        if (e.getClickedBlock().getState() instanceof Sign)
        {
            Sign s = (Sign) e.getClickedBlock().getState();
            System.out.println(s);

            if (s.getLine(0).equalsIgnoreCase("Black Market"))
            {
                Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bBlack Market"));

                ItemStack potions = new ItemStack(Material.POTION);
                ItemMeta pmeta = potions.getItemMeta();
                pmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bPotions"));
                potions.setItemMeta(pmeta);

                p.sendMessage(prefix + "Opening black market...");
                inv.setItem(0, potions);
                p.openInventory(inv);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        Player p = e.getPlayer();

        if (e.getBlock().getState() instanceof Sign)
        {
            Sign s = (Sign) e.getBlock().getState();

            if (s.getLine(0).equalsIgnoreCase("Black Market"))
            {
                e.setCancelled(true);
                Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bBlack Market"));

                ItemStack potions = new ItemStack(Material.POTION);
                ItemMeta pmeta = potions.getItemMeta();
                pmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bPotions"));
                potions.setItemMeta(pmeta);

                p.sendMessage(prefix + "Opening black market...");
                inv.setItem(0, potions);
                p.openInventory(inv);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if (item == null) return;

        if (e.getInventory().getName().equals(ChatColor.translateAlternateColorCodes('&', "&bBlack Market")))
        {
            if (item.getType() == Material.POTION &&
                    item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
                            '&', "&bPotions")))
            {
                Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes(
                        '&', "&bPotions"));

                inv.setItem(0, ItemManager.getIManager().potionItem(
                        PotionEffectType.SPEED, "&bSpeed II potion", "&7Price: $1500.00"));
                inv.setItem(1, ItemManager.getIManager().potionItem(
                        PotionEffectType.INCREASE_DAMAGE, "&bStrength II potion", "&7Price: $1500.00"));
                inv.setItem(2, ItemManager.getIManager().potionItem(
                        PotionEffectType.FAST_DIGGING, "&bHaste II potion", "&7Price: $1500.00"));
                inv.setItem(3, ItemManager.getIManager().potionItem(
                        PotionEffectType.NIGHT_VISION, "&bNight Vision potion", "&7Price: $1500.00"));
                inv.setItem(4, ItemManager.getIManager().potionItem(
                        PotionEffectType.HEAL, "&bHealth II potion", "&7Price: $1500.00"));
                inv.setItem(5, ItemManager.getIManager().potionItem(
                        PotionEffectType.POISON, "&bPoison II potion", "&7Price: $1500.00"));
                inv.setItem(6, ItemManager.getIManager().potionItem(
                        PotionEffectType.SLOW, "&bSlowness II potion", "&7Price: $1500.00"));

                p.openInventory(inv);
                e.setCancelled(true);
                return;
            }
        }else if (e.getInventory().getName().equals(ChatColor.translateAlternateColorCodes('&', "&bPotions")))
        {
            if (item.equals(ItemManager.getIManager().potionItem(PotionEffectType.SPEED, "&bSpeed II potion", "&7Price: $1500.00")))
            {
                if (SettingsManager.getEcoManager().getBalance(p.getName()) < 1500.0D)
                {
                    p.sendMessage(prefix + "Insufficient funds.");
                    e.setCancelled(true);
                    return;
                }
                else{
                    e.setCancelled(true);

                    ItemStack i = new ItemStack(Material.SPLASH_POTION);
                    PotionMeta meta = (PotionMeta) i.getItemMeta();
                    PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 90 * 20, 1, true, true, Color.AQUA);
                    meta.addCustomEffect(speed, true);
                    meta.setDisplayName(ChatColor.WHITE + "Splash Potion of Swiftness");
                    i.setItemMeta(meta);

                    p.getInventory().addItem(i);
                    p.sendMessage(prefix + "You have bought 1x Speed II potion.");
                    vault.withdrawPlayer(p.getName(), 1500.0D);
                }
            }else if (item.equals(ItemManager.getIManager().potionItem(PotionEffectType.INCREASE_DAMAGE, "&bStrength II potion",
                    "&7Price: $1500.00")))
            {
                if (SettingsManager.getEcoManager().getBalance(p.getName()) < 1500.0D)
                {
                    p.sendMessage(prefix + "Insufficient funds.");
                    e.setCancelled(true);
                    return;
                }
                else{
                    e.setCancelled(true);

                    ItemStack i = new ItemStack(Material.SPLASH_POTION);
                    PotionMeta meta = (PotionMeta) i.getItemMeta();
                    PotionEffect haste = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 90 * 20, 1, true, true, Color.RED);
                    meta.addCustomEffect(haste, true);
                    meta.setDisplayName(ChatColor.WHITE + "Splash Potion of Strength");
                    i.setItemMeta(meta);

                    p.getInventory().addItem(i);
                    p.sendMessage(prefix + "You have bought 1x Strength II potion.");
                    vault.withdrawPlayer(p.getName(), 1500.0D);
                }
            }else if (item.equals(ItemManager.getIManager().potionItem(PotionEffectType.FAST_DIGGING, "&bHaste II potion",
                    "&7Price: $1500.00")))
            {
                if (SettingsManager.getEcoManager().getBalance(p.getName()) < 1500.0D)
                {
                    p.sendMessage(prefix + "Insufficient funds.");
                    e.setCancelled(true);
                    return;
                }
                else{
                    e.setCancelled(true);

                    ItemStack i = new ItemStack(Material.SPLASH_POTION);
                    PotionMeta meta = (PotionMeta) i.getItemMeta();
                    PotionEffect haste = new PotionEffect(PotionEffectType.FAST_DIGGING, 90 * 20, 1, true, true, Color.YELLOW);
                    meta.addCustomEffect(haste, true);
                    meta.setDisplayName(ChatColor.WHITE + "Splash Potion of Haste");
                    i.setItemMeta(meta);

                    p.getInventory().addItem(i);
                    p.sendMessage(prefix + "You have bought 1x Haste II potion.");
                    vault.withdrawPlayer(p.getName(), 1500.0D);
                }
            }else if (item.equals(ItemManager.getIManager().potionItem(PotionEffectType.NIGHT_VISION, "&bNight Vision potion",
                    "&7Price: $1500.00")))
            {
                if (SettingsManager.getEcoManager().getBalance(p.getName()) < 1500.0D)
                {
                    p.sendMessage(prefix + "Insufficient funds.");
                    e.setCancelled(true);
                    return;
                }
                else{
                    e.setCancelled(true);

                    ItemStack i = new ItemStack(Material.SPLASH_POTION);
                    PotionMeta meta = (PotionMeta) i.getItemMeta();
                    PotionEffect haste = new PotionEffect(PotionEffectType.NIGHT_VISION, 360 * 20, 0, true, true, Color.PURPLE);
                    meta.addCustomEffect(haste, true);
                    meta.setDisplayName(ChatColor.WHITE + "Splash Potion of Night Vision");
                    i.setItemMeta(meta);

                    p.getInventory().addItem(i);
                    p.sendMessage(prefix + "You have bought 1x Night Vision potion.");
                    vault.withdrawPlayer(p.getName(), 1500.0D);
                }
            }else if (item.equals(ItemManager.getIManager().potionItem(PotionEffectType.HEAL, "&bHealth II potion",
                    "&7Price: $1500.00")))
            {
                if (SettingsManager.getEcoManager().getBalance(p.getName()) < 1500.0D)
                {
                    p.sendMessage(prefix + "Insufficient funds.");
                    e.setCancelled(true);
                    return;
                }
                else{
                    e.setCancelled(true);

                    ItemStack i = new ItemStack(Material.SPLASH_POTION);
                    PotionMeta meta = (PotionMeta) i.getItemMeta();
                    PotionEffect haste = new PotionEffect(PotionEffectType.HEAL, 10, 1, true, true, Color.FUCHSIA);
                    meta.addCustomEffect(haste, true);
                    meta.setDisplayName(ChatColor.WHITE + "Splash Potion of Healing");
                    i.setItemMeta(meta);

                    p.getInventory().addItem(i);
                    p.sendMessage(prefix + "You have bought 1x Instant Health II potion.");
                    vault.withdrawPlayer(p.getName(), 1500.0D);
                }
            }else if (item.equals(ItemManager.getIManager().potionItem(PotionEffectType.POISON, "&bPoison II potion",
                    "&7Price: $1500.00")))
            {
                if (SettingsManager.getEcoManager().getBalance(p.getName()) < 1500.0D)
                {
                    p.sendMessage(prefix + "Insufficient funds.");
                    e.setCancelled(true);
                    return;
                }
                else{
                    e.setCancelled(true);

                    ItemStack i = new ItemStack(Material.SPLASH_POTION);
                    PotionMeta meta = (PotionMeta) i.getItemMeta();
                    PotionEffect haste = new PotionEffect(PotionEffectType.POISON, 90 * 20, 1, true, true, Color.GREEN);
                    meta.addCustomEffect(haste, true);
                    meta.setDisplayName(ChatColor.WHITE + "Splash Potion of Poison");
                    i.setItemMeta(meta);

                    p.getInventory().addItem(i);
                    p.sendMessage(prefix + "You have bought 1x Poison II potion.");
                    vault.withdrawPlayer(p.getName(), 1500.0D);
                }
            }else if (item.equals(ItemManager.getIManager().potionItem(PotionEffectType.SLOW, "&bSlowness II potion",
                    "&7Price: $1500.00")))
            {
                if (SettingsManager.getEcoManager().getBalance(p.getName()) < 1500.0D)
                {
                    p.sendMessage(prefix + "Insufficient funds.");
                    e.setCancelled(true);
                    return;
                }
                else{
                    e.setCancelled(true);

                    ItemStack i = new ItemStack(Material.SPLASH_POTION);
                    PotionMeta meta = (PotionMeta) i.getItemMeta();
                    PotionEffect haste = new PotionEffect(PotionEffectType.SLOW, 90 * 20, 1, true, true, Color.GRAY);
                    meta.addCustomEffect(haste, true);
                    meta.setDisplayName(ChatColor.WHITE + "Splash Potion of Slowness");
                    i.setItemMeta(meta);

                    p.getInventory().addItem(i);
                    p.sendMessage(prefix + "You have bought 1x Slowness II potion.");
                    vault.withdrawPlayer(p.getName(), 1500.0D);
                }
            }
        }
    }

    public Location locationFromConfig(ConfigurationSection sec, boolean pitchAndYaw)
    {
        try{
            Location loc = new Location(
                    Bukkit.getWorld(sec.getString("world")),
                    sec.getDouble("x"),
                    sec.getDouble("y"),
                    sec.getDouble("z")
            );

            if (pitchAndYaw)
            {
                loc.setPitch((float) sec.getDouble("pitch"));
                loc.setYaw((float) sec.getDouble("yaw"));
            }

            return loc;
        }catch (Exception e) { return null; }
    }
}
