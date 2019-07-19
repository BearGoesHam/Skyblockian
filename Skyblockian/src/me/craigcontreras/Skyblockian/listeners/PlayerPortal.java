package me.craigcontreras.Skyblockian.listeners;

import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

import java.util.Random;

public class PlayerPortal
    implements Listener, TextFormat
{
    @EventHandler
    public void onPortal(PlayerPortalEvent e)
    {
        e.setCancelled(true);

        Player p = e.getPlayer();
        World w = Bukkit.getWorld("world_skyblock_nether");
        Random r = new Random();
        int x = r.nextInt(1000);
        int z = r.nextInt(1000);
        double doubleX = (double) x;
        double doubleZ = (double) z;

        Location loc = new Location(w, doubleX, 70.0D, doubleZ);
        p.teleport(loc);
        p.sendMessage(prefix + "Teleported to a random location in the nether.");
        p.sendMessage(prefix + "Coordinates: " + Math.round(doubleX) + ", " + "70, " + Math.round(doubleZ));
    }
}