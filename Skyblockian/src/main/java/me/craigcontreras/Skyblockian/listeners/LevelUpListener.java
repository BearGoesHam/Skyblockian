package me.craigcontreras.Skyblockian.listeners;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
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

        int currentLevel = con.getInt("level");
        int xp = Math.multiplyExact(con.getInt("level"), 100);
        int currentXP = con.getInt("xp");
        int nextLevel = currentLevel + 1;

        con.set("xp", currentXP + 1);

        try{
            con.save(f);
        }catch (Exception ex) { ex.printStackTrace(); }

        if (con.getInt("level") == 0)
        {
            con.set("level", 1);
            con.set("xp", 0);

            try{
                con.save(f);
            }catch (Exception ex) { ex.printStackTrace(); }
        }

        if (con.getInt("xp") == xp)
        {
            con.set("level", currentLevel + 1);
            p.sendMessage(prefix + "Leveled up to " + nextLevel + ".");

            try{
                con.save(f);
            }catch (Exception ex) { ex.printStackTrace(); }
        }
        else{
            return;
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e)
    {
        Player p = e.getPlayer();

        File f = new File(Skyblockian.getCore().getDataFolder() + File.separator + "playerdata" + File.separator +
                p.getUniqueId() + ".yml");
        FileConfiguration con = YamlConfiguration.loadConfiguration(f);

        int currentLevel = con.getInt("level");
        int xp = Math.multiplyExact(con.getInt("level"), 100);
        int currentXP = con.getInt("xp");
        int nextLevel = currentLevel + 1;

        con.set("xp", currentXP + 1);

        try{
            con.save(f);
        }catch (Exception ex) { ex.printStackTrace(); }

        if (con.getInt("level") == 0)
        {
            con.set("level", 1);
            con.set("xp", 0);

            try{
                con.save(f);
            }catch (Exception ex) { ex.printStackTrace(); }
        }

        if (con.getInt("xp") == xp)
        {
            con.set("level", currentLevel + 1);
            p.sendMessage(prefix + "Leveled up to " + nextLevel + ".");

            try{
                con.save(f);
            }catch (Exception ex) { ex.printStackTrace(); }
        }
        else{
            return;
        }
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

            int currentLevel = con.getInt("level");
            int xp = Math.multiplyExact(con.getInt("level"), 100);
            int currentXP = con.getInt("xp");
            int nextLevel = currentLevel + 1;

            con.set("xp", currentXP - 10);
            p.sendMessage(prefix + "-10 experience");

            try{
                con.save(f);
            }catch (Exception ex) { ex.printStackTrace(); }

            if (con.getInt("level") == 0)
            {
                con.set("level", 1);
                con.set("xp", 0);

                try{
                    con.save(f);
                }catch (Exception ex) { ex.printStackTrace(); }
            }

            if (con.getInt("xp") == xp)
            {
                con.set("level", currentLevel + 1);
                p.sendMessage(prefix + "Leveled up to " + nextLevel + ".");

                try{
                    con.save(f);
                }catch (Exception ex) { ex.printStackTrace(); }
            }
            else{
                return;
            }
        }

        Player k = p.getKiller();

        if (k instanceof Player)
        {
            File f = new File(Skyblockian.getCore().getDataFolder() + File.separator + "playerdata" + File.separator +
                    k.getUniqueId() + ".yml");
            FileConfiguration con = YamlConfiguration.loadConfiguration(f);

            int currentLevel = con.getInt("level");
            int xp = Math.multiplyExact(con.getInt("level"), 100);
            int currentXP = con.getInt("xp");
            int nextLevel = currentLevel + 1;

            con.set("xp", currentXP + 10);
            p.sendMessage(prefix + "+10 experience");

            try{
                con.save(f);
            }catch (Exception ex) { ex.printStackTrace(); }

            if (con.getInt("level") == 0)
            {
                con.set("level", 1);
                con.set("xp", 0);

                try{
                    con.save(f);
                }catch (Exception ex) { ex.printStackTrace(); }
            }

            if (con.getInt("xp") == xp)
            {
                con.set("level", currentLevel + 1);
                k.sendMessage(prefix + "Leveled up to " + nextLevel + ".");

                try{
                    con.save(f);
                }catch (Exception ex) { ex.printStackTrace(); }
            }
            else{
                return;
            }
        }
    }
}
