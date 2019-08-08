package com.skyblockian.Skyblockian.listeners;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.skyblockian.Skyblockian.Skyblockian;
import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class PreventArrowListener
    implements Listener, TextFormat
{
    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e)
    {
        Player p = (Player) e.getEntity().getShooter();

        for (String dregion : Skyblockian.getCore().getConfig().getStringList("disabledregions"))
        {
            for (final ProtectedRegion reg : WGBukkit.getRegionManager(p.getWorld())
                    .getApplicableRegions(p.getLocation()))
            {
                if (reg.getId().equalsIgnoreCase(dregion))
                {
                    p.sendMessage(prefix + "You are in a protected region! You cannot shoot an arrow.");
                    e.setCancelled(true);
                    return;
                }
                else {
                    return;
                }
            }
        }
    }
}
