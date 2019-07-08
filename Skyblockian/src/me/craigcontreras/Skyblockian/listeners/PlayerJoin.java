package me.craigcontreras.Skyblockian.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.commands.admin.SetSpawnCommand;
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
			PermissionsManager.getPManager().reload(p);
			SetSpawnCommand.teleportToSpawn(p);
		}
		
		Skyblockian.getCore().onlinePlayers.add(p.getName());
		createBossBar(p);
		
		IslandManager.getIM().loadPlayer(p);
		
		if (IslandManager.getIM().hasIsland(p))
		{
			p.sendMessage(prefix + "Teleporting to your personal island...");
			IslandManager.getIM().sendHome(p);
		}
		
		ScoreboardManager.getScoreMan().setupScoreboard(p);
		
		Skyblockian.getCore().sendTabHF(p, 
				ChatColor.translateAlternateColorCodes('&', "&bSky&fblockian"),
				ChatColor.translateAlternateColorCodes('&', "&bIP: &fus.skyblockian.com"));
		
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
		
		p.setMaximumNoDamageTicks(Integer.parseInt(Skyblockian.getCore().getConfig().getString("hit-delay")));
	}
	
	public void createBossBar(final Player p)
	{
		new BukkitRunnable()
		{
			String message = ChatColor.translateAlternateColorCodes('&', "&7Welcome to &bSky&fblockian&7!");
			BossBar b = Bukkit.createBossBar(this.message, BarColor.BLUE, BarStyle.SEGMENTED_6, new BarFlag[0]);
			BarColor[] c = BarColor.values();
			int i = 0;
	      
			@Override
			public void run()
			{
				if (i >= c.length)
				{
					i = 0;
				}
				
				b.setColor(c[i]);
				b.addPlayer(p);
				b.setVisible(true);
				i++;
			}
		}.runTaskTimer(Skyblockian.getCore(), 0L, 6L);
	}
}
