package com.skyblockian.XoTicPrison;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class XoTicPrison
    extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        log(Level.INFO, "Prison core enabled");
    }

    @Override
    public void onDisable()
    {
        log(Level.INFO, "Prison core disabled");
    }

    public void log(Level level, String message)
    {
        getLogger().log(level, message);
    }
}
