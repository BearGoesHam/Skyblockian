package me.craigcontreras.Skyblockian.listeners;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitScheduler;

import me.craigcontreras.Skyblockian.Skyblockian;
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
	}
}
