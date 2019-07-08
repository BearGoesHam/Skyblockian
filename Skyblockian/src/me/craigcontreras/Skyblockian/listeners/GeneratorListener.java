package me.craigcontreras.Skyblockian.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class GeneratorListener 
implements Listener, TextFormat
{
	public static List<Block> inUse = new ArrayList<Block>();
	public static List<ArmorStand> armorStands = new ArrayList<>();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();
		
		if (inUse.contains(b) && b.getType().equals(Material.IRON_BLOCK))
		{
			ItemStack item = new ItemStack(Material.IRON_INGOT);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bItem type:"));
			meta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&71x iron ingot")));
			item.setItemMeta(meta);
			
			ItemStack rate = new ItemStack(Material.WATCH);
			ItemMeta rmeta = rate.getItemMeta();
			rmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bSpawn rate:"));
			rmeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&71x iron ingot every 15 seconds")));
			rate.setItemMeta(rmeta);
			
			Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bGenerator"));
			inv.setItem(0, item);
			inv.setItem(8, rate);
			
			p.openInventory(inv);
		}else if (inUse.contains(b) && b.getType().equals(Material.GOLD_BLOCK))
		{
			ItemStack item = new ItemStack(Material.GOLD_INGOT);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bItem type:"));
			meta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&71x gold ingot")));
			item.setItemMeta(meta);
			
			ItemStack rate = new ItemStack(Material.WATCH);
			ItemMeta rmeta = rate.getItemMeta();
			rmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bSpawn rate:"));
			rmeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&71x gold ingot every 15 seconds")));
			rate.setItemMeta(rmeta);
			
			Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bGenerator"));
			inv.setItem(0, item);
			inv.setItem(8, rate);
			
			p.openInventory(inv);
		}else if (inUse.contains(b) && b.getType().equals(Material.DIAMOND_BLOCK))
		{
			ItemStack item = new ItemStack(Material.DIAMOND);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bItem type:"));
			meta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&71x diamond")));
			item.setItemMeta(meta);
			
			ItemStack rate = new ItemStack(Material.WATCH);
			ItemMeta rmeta = rate.getItemMeta();
			rmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bSpawn rate:"));
			rmeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&71x diamond every 15 seconds")));
			rate.setItemMeta(rmeta);
			
			Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bGenerator"));
			inv.setItem(0, item);
			inv.setItem(8, rate);

			p.openInventory(inv);
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		if (e.getInventory().getName().equals(ChatColor.translateAlternateColorCodes('&', "&bGenerator")))	
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getBlock();
		World w = b.getWorld();
		float x = b.getX() + 0.5F;
		float y = b.getY() + 1.5F;
		float z = b.getZ() + 0.5F;
		
		Location loc = new Location(w, x, b.getY() - 0.9F, z);
			
		if (b.getType().equals(Material.IRON_BLOCK) && p.getInventory().getItemInMainHand().
        		getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
        				'&', "&bIron &fGenerator")))
		{
			inUse.add(b);
			
			ArmorStand as = (ArmorStand) b.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
			
			as.setArms(false);
			as.setGravity(false);
			as.setVisible(false);
			as.setCustomName(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', "&7Generator")));
			as.setCustomNameVisible(true);
			
			armorStands.add(as);
			
			int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Skyblockian.getCore(), new Runnable()
			{
				@Override
				public void run()
				{	
					w.dropItem(new Location(w, x, y, z), new ItemStack(Material.IRON_INGOT, 1));
				}
			}, 0, 15 * 20);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Skyblockian.getCore(), new Runnable()
			{
				@Override
				public void run()
				{
					b.setType(Material.AIR);
					p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 1.0F, 1.0F);
					p.sendMessage(prefix + "Your iron ingot generator broke!");
					as.setCustomNameVisible(false);
					as.remove();
					armorStands.remove(as);
					Bukkit.getScheduler().cancelTask(taskId);
				}
			}, 1800 * 20);
			
			p.sendMessage(prefix + "Placed an iron ingot generator.");
		}else if (b.getType().equals(Material.GOLD_BLOCK) && p.getInventory().getItemInMainHand().
        		getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
        				'&', "&bGold &fGenerator")))
		{
			inUse.add(b);
			
			ArmorStand as = (ArmorStand) b.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
			
			as.setArms(false);
			as.setGravity(false);
			as.setVisible(false);
			as.setCustomName(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', "&7Generator")));
			as.setCustomNameVisible(true);
			
			armorStands.add(as);
			
			int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Skyblockian.getCore(), new Runnable()
			{
				@Override
				public void run()
				{	
					w.dropItem(new Location(w, x, y, z), new ItemStack(Material.GOLD_INGOT, 1));
				}
			}, 0, 15 * 20);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Skyblockian.getCore(), new Runnable()
			{
				@Override
				public void run()
				{
					b.setType(Material.AIR);
					p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 1.0F, 1.0F);
					p.sendMessage(prefix + "Your gold ingot generator broke!");
					as.setCustomNameVisible(false);
					as.remove();
					armorStands.remove(as);
					Bukkit.getScheduler().cancelTask(taskId);
				}
			}, 1800 * 20);
			
			p.sendMessage(prefix + "Placed a gold ingot generator.");
		}else if (b.getType().equals(Material.DIAMOND_BLOCK) && p.getInventory().getItemInMainHand().
        		getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
        				'&', "&bDiamond &fGenerator")))
		{
			inUse.add(b);
			
			ArmorStand as = (ArmorStand) b.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
			
			as.setArms(false);
			as.setGravity(false);
			as.setVisible(false);
			as.setCustomName(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', "&7Generator")));
			as.setCustomNameVisible(true);
			
			armorStands.add(as);
			
			int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Skyblockian.getCore(), new Runnable()
			{
				@Override
				public void run()
				{	
					w.dropItem(new Location(w, x, y, z), new ItemStack(Material.DIAMOND, 1));
				}
			}, 0, 15 * 20);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Skyblockian.getCore(), new Runnable()
			{
				@Override
				public void run()
				{
					b.setType(Material.AIR);
					p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 1.0F, 1.0F);
					p.sendMessage(prefix + "Your diamond generator broke!");
					as.setCustomNameVisible(false);
					as.remove();
					armorStands.remove(as);
					Bukkit.getScheduler().cancelTask(taskId);
				}
			}, 1800 * 20);
			
			p.sendMessage(prefix + "Placed a diamond generator.");
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getBlock();
		
		if (inUse.contains(b))
		{
			e.setCancelled(true);
			p.sendMessage(prefix + 
					"Can not break this generator. It will break on its own after 30 minutes of placement.");
		}
	}
	
	public static void clear()
	{
		for (ArmorStand as : armorStands)
		{
			as.remove();
		}
		
		for (Block b : inUse)
		{
			b.setType(Material.AIR);
		}
		
		armorStands.clear();
		inUse.clear();
		Skyblockian.getCore().debug(Level.INFO, "Cleared all blocks and armor stands active from the generators");
	}
}