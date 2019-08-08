package com.skyblockian.Skyblockian.listeners;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.List;

public class PreprocessCommandListener
    implements Listener, TextFormat
{
    @EventHandler
    public void onPreprocessCommand(PlayerCommandPreprocessEvent e)
    {
        String message = e.getMessage();
        Player p = e.getPlayer();

        if (p.hasPermission("skyblockian.admin"))
        {
            return;
        }
        else {
            List<String> commands = new ArrayList<>();

            commands.add("/plugins");
            commands.add("/pl");
            commands.add("/bukkit:plugins");
            commands.add("/bukkit:pl");
            commands.add("/?");
            commands.add("/icanhasbukkit");
            commands.add("/bukkit:icanhasbukkit");
            commands.add("/bukkit:?");
            commands.add("/version");
            commands.add("/bukkit:version");
            commands.add("/bukkit:help");
            commands.add("/minecraft:help");

            if (commands.contains(message))
            {
                e.setCancelled(true);
                p.sendMessage(prefix + "These commands are blocked.");
            }
        }
    }
}
