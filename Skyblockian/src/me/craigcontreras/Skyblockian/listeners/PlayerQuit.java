package me.craigcontreras.Skyblockian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.commands.admin.FreezeCommand;
import me.craigcontreras.Skyblockian.commands.admin.SetSpawnCommand;
import me.craigcontreras.Skyblockian.commands.admin.StaffModeCommand;
import me.craigcontreras.Skyblockian.commands.admin.VanishCommand;
import me.craigcontreras.Skyblockian.island.IslandManager;

import java.io.File;
import java.util.Date;

public class PlayerQuit
implements Listener
{
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e)
	{
		IslandManager.getIM().unloadPlayer(e.getPlayer());
		
		if (VanishCommand.vanish.contains(e.getPlayer().getUniqueId()))
		{
			SetSpawnCommand.teleportToSpawn(e.getPlayer());
			VanishCommand.vanish.remove(e.getPlayer().getUniqueId());
		}
		
		if (StaffModeCommand.staffmode.contains(e.getPlayer().getUniqueId()))
		{
			e.getPlayer().getInventory().clear();
			SetSpawnCommand.teleportToSpawn(e.getPlayer());
			StaffModeCommand.staffmode.remove(e.getPlayer().getUniqueId());
		}
		
		if (FreezeCommand.frozen.contains(e.getPlayer().getUniqueId()))
		{
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + e.getPlayer().getName() + " Left when frozen");
			FreezeCommand.frozen.remove(e.getPlayer().getUniqueId());
		}
		
		if (Skyblockian.getCore().toTeleportTo.contains(e.getPlayer()))
		{
			Skyblockian.getCore().toTeleportTo.remove(e.getPlayer());
		}

		Skyblockian.getCore().onlinePlayers.remove(e.getPlayer().getName());
		
		e.setQuitMessage(null);
	}
}
