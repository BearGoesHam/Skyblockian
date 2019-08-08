package com.skyblockian.Skyblockian.listeners;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.skyblockian.Skyblockian.Skyblockian;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AuthenticationListener
    implements Listener, TextFormat
{
    private List<UUID> authLocked = new ArrayList<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();

        if (p.hasPermission("skyblockian.admin"))
        {
            if (!(Skyblockian.getCore().codesConfig.contains("authcodes." + p.getUniqueId())))
            {
                GoogleAuthenticator gauth = new GoogleAuthenticator();
                GoogleAuthenticatorKey key = gauth.createCredentials();

                p.sendMessage(prefix + "Your Google Auth code is: " + key.getKey() + ". Do not share this code with anybody else.");
                p.sendMessage(prefix + "You must write this code in the Google Authenticator App before leaving the server.");

                Skyblockian.getCore().codesConfig.set("authcodes." + p.getUniqueId(), key.getKey());

                try{
                    Skyblockian.getCore().codesConfig.save(Skyblockian.getCore().codesFile);
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            else{
                p.sendMessage(prefix + "Please input your authentication code.");
                authLocked.add(p.getUniqueId());
            }
        }
        else{
            return;
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e)
    {
        Player p = e.getPlayer();
        String message = e.getMessage().replace(" ", "");

        if (authLocked.contains(p.getUniqueId()))
        {
            try{
                Integer code = Integer.parseInt(message);

                if (playerInputCode(p, code))
                {
                    authLocked.remove(p.getUniqueId());
                    p.sendMessage(prefix + "You have been logged in.");
                }
                else{
                    p.sendMessage(prefix + "Incorrect or expired code. A code will only contain numbers.");
                    Bukkit.broadcast(prefix + p.getName() + " has failed the authentication process.", "skyblockian.admin");
                }
            }catch (Exception ex)
            {
                p.sendMessage(prefix + "Incorrect or expired code. A code will only contain numbers.");
                Bukkit.broadcast(prefix + p.getName() + " has failed the authentication process.", "skyblockian.admin");
                ex.printStackTrace();
            }

            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e)
    {
        Player p = e.getPlayer();

        if (authLocked.contains(p.getUniqueId()))
        {
            e.setCancelled(true);
            p.sendMessage(prefix + "Please input your authentication code.");
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        Player p = e.getPlayer();

        if (authLocked.contains(p.getUniqueId()))
        {
            e.setCancelled(true);
            p.sendMessage(prefix + "Please input your authentication code.");
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e)
    {
        Player p = e.getPlayer();

        if (authLocked.contains(p.getUniqueId()))
        {
            e.setCancelled(true);
            p.sendMessage(prefix + "Please input your authentication code.");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();

        if (authLocked.contains(p.getUniqueId()))
        {
            authLocked.remove(p.getUniqueId());
        }
        else{
            return;
        }
    }

    private boolean playerInputCode(Player p, int code)
    {
        String secretkey = Skyblockian.getCore().codesConfig.getString("authcodes." + p.getUniqueId());

        GoogleAuthenticator gauth = new GoogleAuthenticator();
        boolean codeValid = gauth.authorize(secretkey, code);

        if (codeValid)
        {
            authLocked.remove(p.getUniqueId());
            return codeValid;
        }

        return codeValid;
    }
}
