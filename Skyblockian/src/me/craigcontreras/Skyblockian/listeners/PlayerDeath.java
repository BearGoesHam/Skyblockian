package me.craigcontreras.Skyblockian.listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
		
		if (!(p.getWorld().equals(Skyblockian.getCore().world)))
		{
			p.getInventory().clear();
		}
		else {
			InventoryHandler ih = InventoryHandler.getInstance();
			ih.saveInventory(p);
			e.getDrops().clear();
		}
		
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
		double money = r.nextInt(20);
			
		vault.bankDeposit(k.getName(), Math.round(money));
		k.sendMessage(prefix + "You have earned $" + money + " from killing " + p.getName() + ".");
		
		if (Skyblockian.getCore().getBountyConfig().contains(p.getUniqueId().toString() + ".bounty"))
		{
			k.sendMessage(ChatColor.translateAlternateColorCodes('&', TextFormat.prefix + 
					"You have killed &b" + p.getName() + " &7and claimed their &b$" + 
					Skyblockian.getCore().getBountyConfig().get(p.getUniqueId().toString() + ".bounty") + " &7bounty!"));
			vault.depositPlayer(k.getName(), Skyblockian.getCore().getBountyConfig().getDouble(p.getUniqueId().toString() + ".bounty"));
			
			for (Player players : Bukkit.getServer().getOnlinePlayers())
			{
				players.sendMessage(ChatColor.translateAlternateColorCodes('&', TextFormat.prefix + 
						"&b" + k.getName() + " &7has claimed &b" + p.getName() + "&7's bounty of &b$" 
						+ Skyblockian.getCore().getBountyConfig().get(p.getUniqueId().toString() + ".bounty") + "&7!"));
			}
				
			Skyblockian.getCore().getBountyConfig().set(p.getUniqueId().toString() + ".bounty", null);
			
			try {
				Skyblockian.getCore().getBountyConfig().save(Skyblockian.getCore().bounties);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
				
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + k.getName() + " has claimed your bounty!"));
		}
		else {
			return;
		}
				
		if (StaffModeCommand.staffmode.contains(p.getUniqueId()))
		{
			e.getDrops().clear();
			StaffModeCommand.staffmode.remove(p.getUniqueId());
			p.sendMessage(prefix + "You have died, removing you from staff mode.");
		}
		else {
			return;
		}
	}
}
