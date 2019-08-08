package com.skyblockian.Skyblockian.listeners;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLogin
    implements Listener, TextFormat
{
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e)
    {
        Player p = e.getPlayer();

        if (e.getResult().equals(PlayerLoginEvent.Result.KICK_FULL))
        {
            if (p.hasPermission("skyblockian.admin"))
            {
                e.allow();
            }
        }else if (e.getResult().equals(PlayerLoginEvent.Result.KICK_WHITELIST))
        {
            Bukkit.broadcast("skyblockian.admin", prefix + p.getName() + " tried to join the server, but it is whitelisted.");
        }
    }
}
