package me.craigcontreras.Skyblockian.commands.admin;

import me.craigcontreras.Skyblockian.Skyblockian;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class WarpManager
{

    String name;
    World world;
    double x;
    double y;
    double z;
    float yaw;
    float pitch;

    File warps = new File(Skyblockian.getCore().getDataFolder(), "/warps.yml");
    FileConfiguration warpsConfig = YamlConfiguration.loadConfiguration(warps);

    public FileConfiguration getWarpsConfig() {return warpsConfig; }

    public void setWarp(String name, World world, double x, double y, double z, float yaw, float pitch)
    {
        warpsConfig.set(name, ".world" + world.toString());
        warpsConfig.set(name, ".X" + x);
        warpsConfig.set(name, ".Y" + y);
        warpsConfig.set(name, ".Z" + z);
        warpsConfig.set(name, ".yaw" + yaw);
        warpsConfig.set(name, ".pitch" + pitch);

        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;

        saveYml(warps, warpsConfig);
    }

 /*   public void getWarp(String name)
    {
        Location warpLoc = new Location(this.world,this.x,this.y,this.z,this.yaw, this.pitch);

        if(warpsConfig.contains(name))
        {
            return warpLoc;
        }
    }
*/


    public void saveYml(File file, FileConfiguration config)
    {
        try
        {
            config.save(file);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
