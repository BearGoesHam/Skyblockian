package me.craigcontreras.Skyblockian.listeners;

import org.bukkit.Location;

public class Miner
{
    private boolean enabled;
    private Location location;

    public Miner(boolean enabled, Location location)
    {
        this.enabled = enabled;
        this.location = location;
    }

    public boolean getEnabled()
    {
        return this.enabled;
    }

    public Location getLocation()
    {
        return this.location;
    }
}
