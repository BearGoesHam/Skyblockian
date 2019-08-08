package com.skyblockian.Skyblockian.listeners;

import com.skyblockian.Skyblockian.Skyblockian;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinerManager
{
    public ArrayList<Miner> miners = new ArrayList();
    public ArrayList<Location> locations = new ArrayList();

    public void registerMiners()
    {
        for (String loc : Skyblockian.getCore().minersConfig.getKeys(false))
        {
            if (!(loc.equalsIgnoreCase("UNHARVESTABLE_BLOCKS")) && (!(loc.equalsIgnoreCase("DELAY"))))
            {
                String[] strings = loc.split(":");
                World w = Bukkit.getWorld(strings[0]);

                if (w == null)
                {
                    throw new IllegalArgumentException("World does not exist");
                }

                Location location = new Location(w, Double.parseDouble(strings[1]), Double.parseDouble(strings[2]), Double.parseDouble(strings[3]));
                miners.add(new Miner(Skyblockian.getCore().minersConfig.getBoolean(loc), location));
                locations.add(location);
            }
        }
    }

    public String registerNewMiner(Location loc, boolean enabled)
    {
        miners.add(new Miner(enabled, loc));
        String w = loc.getWorld().getName();
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        int z = (int) loc.getZ();

        return w + ":" + x + ":" + y + ":" + z;
    }

    public Miner getMiner(Location loc)
    {
        for (Miner miner : miners)
        {
            if (miner.getLocation().equals(loc))
            {
                return miner;
            }
        }

        return null;
    }

    public String removeMiner(Miner miner)
    {
        if (miners.contains(miner))
        {
            miners.remove(miner);
            Location loc = miner.getLocation();
            String w = loc.getWorld().getName();
            int x = (int) loc.getX();
            int y = (int) loc.getY();
            int z = (int) loc.getZ();

            return w + ":" + x + ":" + y + ":" + z;
        }

        return null;
    }

    public void createFile() throws IOException
    {
        File miner = new File(Skyblockian.getCore().getDataFolder(), "miners.yml");
        FileConfiguration miners = YamlConfiguration.loadConfiguration(miner);

        ArrayList<String> blocks = new ArrayList<>();
        blocks.addAll(Arrays.asList(new String[] { "BEDROCK", "AIR" }));
        miners.addDefault("DELAY", Integer.valueOf(100));
        miners.options().copyDefaults(true);
        miners.save(miner);
    }

    public List<Location> getLocations()
    {
        return locations;
    }

    public List<Miner> getMiners()
    {
        return miners;
    }
}
