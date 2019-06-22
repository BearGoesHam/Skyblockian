package me.craigcontreras.Skyblockian.listeners;

import java.util.Random;

import me.craigcontreras.Skyblockian.economy.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitScheduler;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.commands.admin.StaffModeCommand;
import me.craigcontreras.Skyblockian.economy.VaultIntegration;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class PlayerDeath 
implements Listener, TextFormat
{
	private VaultIntegration vault = new VaultIntegration();
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e)
	{
		Player p = (Player) e.getEntity();
		Player k = (Player) p.getKiller();
		e.setDeathMessage(null);
		p.getInventory().clear();
		
		BukkitScheduler scheduler = Skyblockian.getCore().getServer().getScheduler();
		scheduler.scheduleSyncDelayedTask(Skyblockian.getCore(), new Runnable()
		{
			@Override
			public void run()
			{
				p.spigot().respawn();
			}
		}, 15L);
		
		Random r = new Random();
		
		int money = r.nextInt(20);
		
		vault.depositPlayer(k.getName(), money);
		k.sendMessage(prefix + "You have earned $" + money + " from killing " + p.getName() + ".");
		
		if (StaffModeCommand.staffmode.contains(p.getUniqueId()))
		{
			e.getDrops().clear();
			StaffModeCommand.staffmode.remove(p.getUniqueId());
			p.sendMessage(prefix + "You have died, removing you from staff mode.");
		}
		else {
			return;
		}

			if (Skyblockian.getCore().Bounties.containsKey(p))
			{
				k.sendMessage(TextFormat.prefix + " You have killed &b" + p.getName() + " &7and claimed their &b" + Skyblockian.getCore().Bounties.get(p) + " &7bounty!");
				SettingsManager.getEcoManager().addBalance(k.getName(), Skyblockian.getCore().Bounties.get(p));
				for (Player players : Bukkit.getServer().getOnlinePlayers())
				{
					players.sendMessage(TextFormat.prefix + "&b" + k.getName() + " &7has claimed &b" + p.getName() + " &7's bounty of &b" + Skyblockian.getCore().Bounties.get(p) + "&7!");
				}
				Skyblockian.getCore().Bounties.remove(p);
			}
			p.sendMessage(k.getName() + " has claimed your bounty!");

	}
}
