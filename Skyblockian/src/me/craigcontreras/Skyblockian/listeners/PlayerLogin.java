package me.craigcontreras.Skyblockian.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLogin
    implements Listener
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
        }
    }
}
