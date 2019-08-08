package com.skyblockian.Skyblockian.listeners;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import com.skyblockian.Skyblockian.Skyblockian;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.File;

public class LevelUpListener
    implements Listener, TextFormat
{
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        Player p = e.getPlayer();

        File f = new File(Skyblockian.getCore().getDataFolder() + File.separator + "playerdata" + File.separator +
                p.getUniqueId() + ".yml");
        FileConfiguration con = YamlConfiguration.loadConfiguration(f);

        if (con.getInt("level") == 0)
        {
            con.set("level", 1);
            con.set("xp", 0);

            try {
                con.save(f);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        Skyblockian.getCore().levelUpManager.addXP(p, 1);

        Skyblockian.getCore().levelUpManager.levelUp(p);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e)
    {
        Player p = e.getPlayer();

        File f = new File(Skyblockian.getCore().getDataFolder() + File.separator + "playerdata" + File.separator +
                p.getUniqueId() + ".yml");
        FileConfiguration con = YamlConfiguration.loadConfiguration(f);

        if (con.getInt("level") == 0)
        {
            con.set("level", 1);
            con.set("xp", 0);

            try{
                con.save(f);
            }catch (Exception ex) { ex.printStackTrace(); }
        }

        Skyblockian.getCore().levelUpManager.addXP(p, 1);

        Skyblockian.getCore().levelUpManager.levelUp(p);
    }

    @EventHandler
    public void onEntityDeath(PlayerDeathEvent e)
    {
        Player p = (Player) e.getEntity();

        if (p instanceof Player)
        {
            File f = new File(Skyblockian.getCore().getDataFolder() + File.separator + "playerdata" + File.separator +
                    p.getUniqueId() + ".yml");
            FileConfiguration con = YamlConfiguration.loadConfiguration(f);

            if (con.getInt("level") == 0)
            {
                con.set("level", 1);
                con.set("xp", 0);

                try{
                    con.save(f);
                }catch (Exception ex) { ex.printStackTrace(); }
            }

            Skyblockian.getCore().levelUpManager.removeXP(p, 10);

            Skyblockian.getCore().levelUpManager.levelUp(p);
        }

        Player k = p.getKiller();

        if (k instanceof Player)
        {
            File f = new File(Skyblockian.getCore().getDataFolder() + File.separator + "playerdata" + File.separator +
                    k.getUniqueId() + ".yml");
            FileConfiguration con = YamlConfiguration.loadConfiguration(f);

            if (con.getInt("level") == 0)
            {
                con.set("level", 1);
                con.set("xp", 0);

                try{
                    con.save(f);
                }catch (Exception ex) { ex.printStackTrace(); }
            }

            Skyblockian.getCore().levelUpManager.addXP(k, 10);

            Skyblockian.getCore().levelUpManager.levelUp(k);
        }
    }
}
