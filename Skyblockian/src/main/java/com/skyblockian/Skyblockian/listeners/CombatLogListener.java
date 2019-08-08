package com.skyblockian.Skyblockian.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.skyblockian.Skyblockian.Skyblockian;

public class CombatLogListener
implements Listener, TextFormat
{
	private ArrayList<UUID> inCombatLog = new ArrayList<>();

	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e)
	{
		Player p = (Player) e.getEntity();
		Player k = (Player) e.getDamager();

		if (!(e.getDamager() instanceof Player) && (!(e.getEntity() instanceof Player))) return;

		if (p.getWorld().getName().equals("spawn") || (k.getWorld().getName().equals("spawn"))) return;

		if (!Skyblockian.getCore().ifInRegion(p) && !Skyblockian.getCore().ifInRegion(k))
		{
			k.sendMessage(prefix + "You're in a protected region! You cannot combat log!");
			e.setCancelled(true);
		}
		else{
			if ((!(inCombatLog.contains(p.getUniqueId())) && (!(inCombatLog.contains(k.getUniqueId())))))
			{
				inCombatLog.add(p.getUniqueId());
				inCombatLog.add(k.getUniqueId());
				p.sendMessage(prefix + "You are now in combat with " + k.getName() + ".");
				k.sendMessage(prefix + "You are now in combat with " + p.getName() + ".");

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Skyblockian.getCore(), new Runnable()
				{
					public void run()
					{
						if (inCombatLog.contains(p.getUniqueId()) && (inCombatLog.contains(k.getUniqueId())))
						{
							inCombatLog.remove(p.getUniqueId());
							inCombatLog.remove(k.getUniqueId());
							p.sendMessage(prefix + "You are no longer in combat with " + k.getName() + ".");
							k.sendMessage(prefix + "You are no longer in combat with " + p.getName() + ".");
						}
					}
				}, 600L);
			}
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e)
	{
		Player p = (Player) e.getEntity();
		Player k = p.getKiller();

		if (inCombatLog.contains(p.getUniqueId()) && (inCombatLog.contains(k.getUniqueId())))
		{
			inCombatLog.remove(p.getUniqueId());
			inCombatLog.remove(k.getUniqueId());

			p.sendMessage(prefix + "You are no longer in combat.");
			k.sendMessage(prefix + "You are no longer in combat.");
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		Player d = (Player) p.getLastDamageCause();

		if (d == null) return;

		if (inCombatLog.contains(p.getUniqueId()) && (inCombatLog.contains(p.getUniqueId())))
		{
			inCombatLog.remove(p.getUniqueId());
			inCombatLog.remove(d.getUniqueId());

			Bukkit.broadcastMessage(prefix + p.getName() + " has logged out whilst in combat! and has been banned for 5 minutes");
			p.damage(20.0D);
			d.sendMessage(prefix + p.getName() + "You are no longer in combat as " + p.getName() + " is no longer online.");

			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban " + p.getName() + " 5m Combat Log");
		}
	}

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

			if (inCombatLog.contains(p.getUniqueId()))
			{
				commands.add("/spawn");
				commands.add("/is home");
				commands.add("/warp pvp");
			}

			if (commands.contains(message))
			{
				e.setCancelled(true);
				p.sendMessage(prefix + "You are currently combat logged.");
			}
		}
	}
}
