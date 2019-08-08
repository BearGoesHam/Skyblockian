package com.skyblockian.Skyblockian.listeners;

import com.skyblockian.Skyblockian.Skyblockian;
import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.Arrays;

public class LevelUpManager
    implements TextFormat
{
    public Inventory upgrades;
    public ItemStack damage;
    public ItemStack health;

    public void levelUp(Player p)
    {
        File f = new File(Skyblockian.getCore().getDataFolder() + File.separator + "playerdata" + File.separator +
                p.getUniqueId() + ".yml");
        FileConfiguration con = YamlConfiguration.loadConfiguration(f);

        int currentLevel = con.getInt("level");
        int xp = Math.multiplyExact(con.getInt("level"), 100);
        int nextLevel = currentLevel + 1;

        if (nextLevel == 5)
        {
            p.sendMessage(prefix + "You've unlocked the upgrades menu! (/upgrades)");
        }

        if (con.getInt("xp") == xp)
        {
            con.set("level", currentLevel + 1);
            con.set("xp", 0);
            p.sendMessage(prefix + "Leveled up to " + nextLevel + ".");
            p.sendTitle(ChatColor.translateAlternateColorCodes('&', "&a&lLEVEL UP!"),
                    ChatColor.translateAlternateColorCodes('&', "&7[&9" +
                            (nextLevel - 1) + "&7] &8>> &7[&9" + nextLevel + "&7]"), 2, 15 * 20, 2);
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

            try{
                con.save(f);
            }catch (Exception ex) { ex.printStackTrace(); }
        }
        else{
            return;
        }
    }

    public boolean isLevel(Player p, int level)
    {
        File f = new File(Skyblockian.getCore().getDataFolder() + File.separator + "playerdata" + File.separator +
                p.getUniqueId() + ".yml");
        FileConfiguration con = YamlConfiguration.loadConfiguration(f);

        return (con.getInt("level") >= level);
    }

    public boolean hasXP(Player p, int xp)
    {
        File f = new File(Skyblockian.getCore().getDataFolder() + File.separator + "playerdata" + File.separator +
                p.getUniqueId() + ".yml");
        FileConfiguration con = YamlConfiguration.loadConfiguration(f);

        return (con.getInt("xp") >= xp);
    }

    public Integer getDamageLevel(Player p)
    {
        File f = new File(Skyblockian.getCore().getDataFolder() + File.separator + "playerdata" + File.separator +
                p.getUniqueId() + ".yml");
        FileConfiguration con = YamlConfiguration.loadConfiguration(f);

        return (con.getInt("damage-upgrade"));
    }

    public Integer setDamageLevel(Player p, int level)
    {
        File f = new File(Skyblockian.getCore().getDataFolder() + File.separator + "playerdata" + File.separator +
                p.getUniqueId() + ".yml");
        FileConfiguration con = YamlConfiguration.loadConfiguration(f);

        con.set("damage-upgrade", level);

        try{
            con.save(f);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return level;
    }

    public Integer getHealthLevel(Player p)
    {
        File f = new File(Skyblockian.getCore().getDataFolder() + File.separator + "playerdata" + File.separator +
                p.getUniqueId() + ".yml");
        FileConfiguration con = YamlConfiguration.loadConfiguration(f);

        return (con.getInt("health-upgrade"));
    }

    public Integer setHealthLevel(Player p, int level)
    {
        File f = new File(Skyblockian.getCore().getDataFolder() + File.separator + "playerdata" + File.separator +
                p.getUniqueId() + ".yml");
        FileConfiguration con = YamlConfiguration.loadConfiguration(f);

        con.set("health-upgrade", level);

        try{
            con.save(f);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return level;
    }

    public Integer addXP(Player p, int xp)
    {
        File f = new File(Skyblockian.getCore().getDataFolder() + File.separator + "playerdata" + File.separator +
                p.getUniqueId() + ".yml");
        FileConfiguration con = YamlConfiguration.loadConfiguration(f);

        con.set("xp", con.getInt("xp") + xp);

        try{
            con.save(f);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return xp;
    }

    public Integer removeXP(Player p, int xp)
    {
        File f = new File(Skyblockian.getCore().getDataFolder() + File.separator + "playerdata" + File.separator +
                p.getUniqueId() + ".yml");
        FileConfiguration con = YamlConfiguration.loadConfiguration(f);

        if (hasXP(p, xp))
        {
            con.set("xp", con.getInt("xp") - xp);
            p.sendMessage(prefix + "-" + xp + " experience");

            try{
                con.save(f);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return xp;
    }

    public void createInventory(Player p)
    {
        upgrades = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bUpgrades"));

        damage = new ItemStack(Material.IRON_SWORD);
        ItemMeta dmeta = damage.getItemMeta();
        dmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bDamage Upgrade"));

        health = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta hmeta = health.getItemMeta();
        hmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bMax Health Upgrade"));

        if (this.getHealthLevel(p) == 3)
        {
            hmeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Upgrade your max health!"),
                    ChatColor.translateAlternateColorCodes('&', "&7Level: &9") +
                            this.getHealthLevel(p),
                    ChatColor.translateAlternateColorCodes('&', "&7Price: N/A")));
        }
        else{
            hmeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Upgrade your max health!"),
                    ChatColor.translateAlternateColorCodes('&', "&7Level: &9") +
                            this.getHealthLevel(p),
                    ChatColor.translateAlternateColorCodes('&', "&7Price: " +
                            Math.multiplyExact(900, this.getDamageLevel(p) + 1) + " experience")));
        }

        if (this.getDamageLevel(p) == 3)
        {
            dmeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Upgrade your damage!"),
                    ChatColor.translateAlternateColorCodes('&', "&7Level: &9") +
                            this.getDamageLevel(p),
                    ChatColor.translateAlternateColorCodes('&', "&7Price: N/A")));
        }
        else{
            dmeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Upgrade your damage!"),
                    ChatColor.translateAlternateColorCodes('&', "&7Level: &9") +
                            this.getDamageLevel(p),
                    ChatColor.translateAlternateColorCodes('&', "&7Price: " +
                            Math.multiplyExact(400, this.getDamageLevel(p) + 1) + " experience")));
        }

        damage.setItemMeta(dmeta);
        health.setItemMeta(hmeta);

        upgrades.setItem(4, damage);
        upgrades.setItem(0, health);

        p.openInventory(upgrades);
    }
}
