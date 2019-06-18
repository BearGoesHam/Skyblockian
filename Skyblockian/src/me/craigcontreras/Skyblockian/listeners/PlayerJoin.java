package me.craigcontreras.Skyblockian.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.commands.admin.VanishCommand;
import me.craigcontreras.Skyblockian.economy.VaultIntegration;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import me.craigcontreras.Skyblockian.island.IslandManager;
import me.craigcontreras.Skyblockian.permissions.managers.PermissionsManager;

public class PlayerJoin
implements Listener, TextFormat
{
	private VaultIntegration vault = new VaultIntegration();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		boolean hasPlayed = p.hasPlayedBefore();
		
		if (hasPlayed)
		{
			p.sendMessage(prefix + "Welcome back to Skyblockian.");
		}
		else {
			Bukkit.broadcastMessage(prefix + "Please welcome " + p.getName() + " to Skyblockian.");
			vault.depositPlayer(p.getName(), 1000.0D);
			PermissionsManager.getPManager().setGroup(p, "Member");
		}
		
		IslandManager.getIM().loadPlayer(p);
		
		if (IslandManager.getIM().hasIsland(p) || IslandManager.getIM().hasIsland2(p))
		{
			p.sendMessage(prefix + "Teleporting to your personal island...");
			IslandManager.getIM().sendHome(p);
		}
				
		ScoreboardManager.getScoreMan().setupScoreboard(p);
		
		Skyblockian.getCore().sendTabHF(p, 
				ChatColor.translateAlternateColorCodes('&', "&bSky&fblockian"),
				ChatColor.translateAlternateColorCodes('&', "&bIP: &fskyblockian.com"));
		
		for (UUID in : VanishCommand.vanish)
		{
			if (in == null) return;
			Player inal = Bukkit.getPlayer(in);
			
			for (Player pl : Bukkit.getOnlinePlayers())
			{
				pl.hidePlayer(inal);
			}
		}
		
		e.setJoinMessage(null);
	}
}
