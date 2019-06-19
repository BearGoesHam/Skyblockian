package me.craigcontreras.Skyblockian.listeners;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.craigcontreras.Skyblockian.economy.SettingsManager;
import me.craigcontreras.Skyblockian.economy.VaultIntegration;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import me.craigcontreras.Skyblockian.permissions.managers.PermissionsManager;

public class ShopInventoryListener 
implements Listener, TextFormat
{
	private VaultIntegration vault = new VaultIntegration();
	
	@EventHandler
	public void onInventorySelect(InventoryClickEvent e)
	{
		Player p = (Player) e.getWhoClicked();
		ItemStack i = (ItemStack) e.getCurrentItem();
		
		if (e.getCurrentItem() == null)
		{
			return;
		}
		
		if (!(e.getCurrentItem().hasItemMeta()))
		{
			return;
		}
		
		if (e.getInventory().getName().equals(ChatColor.translateAlternateColorCodes('&', "&bFarmstuffs")))
		{
			if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Sapling"))
					&& i.getType().equals(Material.SAPLING)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Price: $50.00"))))
			{
				e.setCancelled(true);
				
				if (SettingsManager.getEcoManager().getBalance(p.getName()) < 50.0D)
				{
					p.sendMessage(prefix + "Insufficient funds.");
					p.closeInventory();
				}
				else {
					vault.withdrawPlayer(p.getName(), 50.0D);
					p.getInventory().addItem(new ItemStack(Material.SAPLING, 1));
					p.sendMessage(prefix + "You have bought a sapling.");
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Wheat Seed"))
					&& i.getType().equals(Material.SEEDS)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Price: $5.00"))))
			{
				e.setCancelled(true);
				
				if (SettingsManager.getEcoManager().getBalance(p.getName()) < 5.0D)
				{
					p.sendMessage(prefix + "Insufficient funds.");
					p.closeInventory();
				}
				else {
					vault.withdrawPlayer(p.getName(), 50.0D);
					p.getInventory().addItem(new ItemStack(Material.SEEDS, 1));
					p.sendMessage(prefix + "You have bought a seed.");
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Pumpkin Seed"))
					&& i.getType().equals(Material.PUMPKIN_SEEDS)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Price: $5.00"))))
			{
				e.setCancelled(true);
				
				if (SettingsManager.getEcoManager().getBalance(p.getName()) < 5.0D)
				{
					p.sendMessage(prefix + "Insufficient funds.");
					p.closeInventory();
				}
				else {
					vault.withdrawPlayer(p.getName(), 50.0D);
					p.getInventory().addItem(new ItemStack(Material.PUMPKIN_SEEDS, 1));
					p.sendMessage(prefix + "You have bought a pumpkin seed.");
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Melon Seed"))
					&& i.getType().equals(Material.MELON_SEEDS)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Price: $5.00"))))
			{
				e.setCancelled(true);
				
				if (SettingsManager.getEcoManager().getBalance(p.getName()) < 5.0D)
				{
					p.sendMessage(prefix + "Insufficient funds.");
					p.closeInventory();
				}
				else {
					vault.withdrawPlayer(p.getName(), 50.0D);
					p.getInventory().addItem(new ItemStack(Material.MELON_SEEDS, 1));
					p.sendMessage(prefix + "You have bought a melon seed.");
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Beetroot Seed"))
					&& i.getType().equals(Material.BEETROOT_SEEDS)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Price: $5.00"))))
			{
				e.setCancelled(true);
				
				if (SettingsManager.getEcoManager().getBalance(p.getName()) < 5.0D)
				{
					p.sendMessage(prefix + "Insufficient funds.");
					p.closeInventory();
				}
				else {
					vault.withdrawPlayer(p.getName(), 50.0D);
					p.getInventory().addItem(new ItemStack(Material.BEETROOT_SEEDS, 1));
					p.sendMessage(prefix + "You have bought a beetroot seed.");
				}
			}
		}
		
		if (e.getInventory().getName().contains(ChatColor.translateAlternateColorCodes('&', "&bIngots/Ores")))
		{			
			if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
				'&', "&b1x Iron Ingot"))
				&& i.getType().equals(Material.IRON_INGOT)
				&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
						ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
						ChatColor.translateAlternateColorCodes('&', "&7Price: $350.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 350.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 350.0D);
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
						p.sendMessage(prefix + "You have bought an iron ingot.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.IRON_INGOT))
					{
						vault.depositPlayer(p.getName(), 350.D);
						p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 1));
						p.sendMessage(prefix + "You've successfully sold an iron ingot.");
					}
					else {
						p.sendMessage(prefix + "You don't have a iron ingot.");
						p.closeInventory();
						return;
					}
				}				
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
				'&', "&b1x Gold Ingot"))
				&& i.getType().equals(Material.GOLD_INGOT)
				&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
						ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
						ChatColor.translateAlternateColorCodes('&', "&7Price: $700.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 700.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 700.0D);
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1));
						p.sendMessage(prefix + "You have bought an iron ingot.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.GOLD_INGOT))
					{
						vault.depositPlayer(p.getName(), 700.D);
						p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 1));
						p.sendMessage(prefix + "You've successfully sold an iron ingot.");
					}
					else {
						p.sendMessage(prefix + "You don't have a gold ingot.");
						p.closeInventory();
						return;
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Diamond"))
					&& i.getType().equals(Material.DIAMOND) && 
					i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"), 
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"), 
							ChatColor.translateAlternateColorCodes('&', "&7Price: $2500.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 2500.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 2500.0D);
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
						p.sendMessage(prefix + "You have bought a diamond.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.DIAMOND))
					{
						vault.depositPlayer(p.getName(), 2500.D);
						p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 1));
						p.sendMessage(prefix + "You've successfully sold a diamond.");
					}
					else {
						p.sendMessage(prefix + "You don't have a diamond.");
						p.closeInventory();
						return;
					}
				}
			}
		}
		
		if (e.getInventory().getName().contains(ChatColor.translateAlternateColorCodes('&', "&bSpawners")))
		{
			if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Zombie Spawner"))
					&& i.getType().equals(Material.MOB_SPAWNER)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Price: $9000.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 9000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 9000.0D);
						ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
						ItemMeta spawnermeta = spawner.getItemMeta();
						spawnermeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bZombie &fSpawner"));
						spawner.setItemMeta(spawnermeta);
						p.getInventory().addItem(spawner);
						p.sendMessage(prefix + "You have bought a Zombie mob spawner.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Skeleton Spawner"))
					&& i.getType().equals(Material.MOB_SPAWNER)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Price: $9500.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 9500.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 9500.0D);
						ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
						ItemMeta spawnermeta = spawner.getItemMeta();
						spawnermeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bSkeleton &fSpawner"));
						spawner.setItemMeta(spawnermeta);
						p.getInventory().addItem(spawner);
						p.sendMessage(prefix + "You have bought a Skeleton mob spawner.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Pig Spawner"))
					&& i.getType().equals(Material.MOB_SPAWNER)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Price: $4000.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 4000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 4000.0D);
						ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
						ItemMeta spawnermeta = spawner.getItemMeta();
						spawnermeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bPig &fSpawner"));
						spawner.setItemMeta(spawnermeta);
						p.getInventory().addItem(spawner);
						p.sendMessage(prefix + "You have bought a Pig mob spawner.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Cow Spawner"))
					&& i.getType().equals(Material.MOB_SPAWNER)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Price: $4000.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 4000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 4000.0D);
						ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
						ItemMeta spawnermeta = spawner.getItemMeta();
						spawnermeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bCow &fSpawner"));
						spawner.setItemMeta(spawnermeta);
						p.getInventory().addItem(spawner);
						p.sendMessage(prefix + "You have bought a Cow mob spawner.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Chicken Spawner"))
					&& i.getType().equals(Material.MOB_SPAWNER)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Price: $4000.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 4000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 4000.0D);
						ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
						ItemMeta spawnermeta = spawner.getItemMeta();
						spawnermeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bChicken &fSpawner"));
						spawner.setItemMeta(spawnermeta);
						p.getInventory().addItem(spawner);
						p.sendMessage(prefix + "You have bought a Chicken mob spawner.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Iron Golem Spawner"))
					&& i.getType().equals(Material.MOB_SPAWNER)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Price: $10000.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 10000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 10000.0D);
						ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
						ItemMeta spawnermeta = spawner.getItemMeta();
						spawnermeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bIron Golem &fSpawner"));
						spawner.setItemMeta(spawnermeta);
						p.getInventory().addItem(spawner);
						p.sendMessage(prefix + "You have bought an Iron Golem mob spawner.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Blaze Spawner"))
					&& i.getType().equals(Material.MOB_SPAWNER)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Price: $10000.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 10000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 10000.0D);
						ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
						ItemMeta spawnermeta = spawner.getItemMeta();
						spawnermeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bBlaze &fSpawner"));
						spawner.setItemMeta(spawnermeta);
						p.getInventory().addItem(spawner);
						p.sendMessage(prefix + "You have bought a Blaze mob spawner.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Creeper Spawner"))
					&& i.getType().equals(Material.MOB_SPAWNER)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Price: $9000.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 9000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 9000.0D);
						ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
						ItemMeta spawnermeta = spawner.getItemMeta();
						spawnermeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bCreeper &fSpawner"));
						spawner.setItemMeta(spawnermeta);
						p.getInventory().addItem(spawner);
						p.sendMessage(prefix + "You have bought a Creeper mob spawner.");
					}
				}
			}
		}
		
		if (e.getInventory().getName().contains(ChatColor.translateAlternateColorCodes('&', "&bShop")))
		{
			if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bSpawners"))
					&& i.getType().equals(Material.MOB_SPAWNER))
			{
				e.setCancelled(true);
				
				Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bSpawners"));
				
				inv.setItem(0, ItemManager.getIManager().createAnItem(Material.MOB_SPAWNER, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Zombie Spawner"), 
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $9000.00"))));
				
				inv.setItem(1, ItemManager.getIManager().createAnItem(Material.MOB_SPAWNER, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Skeleton Spawner"), 
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $9500.00"))));
				
				inv.setItem(2, ItemManager.getIManager().createAnItem(Material.MOB_SPAWNER, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Pig Spawner"), 
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $4000.00"))));
				
				inv.setItem(3, ItemManager.getIManager().createAnItem(Material.MOB_SPAWNER, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Cow Spawner"), 
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $4000.00"))));
				
				inv.setItem(4, ItemManager.getIManager().createAnItem(Material.MOB_SPAWNER, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Chicken Spawner"), 
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $4000.00"))));
				
				inv.setItem(5, ItemManager.getIManager().createAnItem(Material.MOB_SPAWNER, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Iron Golem Spawner"), 
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $10000.00"))));
				
				inv.setItem(6, ItemManager.getIManager().createAnItem(Material.MOB_SPAWNER, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Blaze Spawner"), 
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $10000.00"))));
				
				inv.setItem(7, ItemManager.getIManager().createAnItem(Material.MOB_SPAWNER, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Creeper Spawner"), 
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $9000.00"))));

				p.openInventory(inv);
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bIngots/Ores"))
					&& i.getType().equals(Material.IRON_INGOT))
			{
				e.setCancelled(true);
				
				Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bIngots/Ores"));
				
				inv.setItem(0, ItemManager.getIManager().createItem(Material.IRON_INGOT, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Iron Ingot"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $350.00")));
								
				inv.setItem(4, ItemManager.getIManager().createItem(Material.GOLD_INGOT, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Gold Ingot"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $700.00")));
				
				inv.setItem(8, ItemManager.getIManager().createItem(Material.DIAMOND, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Diamond"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $2500.00")));
				
				p.openInventory(inv);
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bFarmstuffs"))
					&& i.getType().equals(Material.WHEAT))
			{
				e.setCancelled(true);
				
				Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bFarmstuffs"));
				
				inv.setItem(0, ItemManager.getIManager().createAnItem(Material.SAPLING, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Sapling"), 
						Arrays.asList(ChatColor.translateAlternateColorCodes(
								'&', "&7Price: $50.00"))));
								
				inv.setItem(1, ItemManager.getIManager().createAnItem(Material.SEEDS, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Wheat Seed"), 
						Arrays.asList(ChatColor.translateAlternateColorCodes(
								'&', "&7Price: $5.00"))));
				
				inv.setItem(2, ItemManager.getIManager().createAnItem(Material.PUMPKIN_SEEDS, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Pumpkin Seed"), 
						Arrays.asList(ChatColor.translateAlternateColorCodes(
								'&', "&7Price: $5.00"))));
				
				inv.setItem(3, ItemManager.getIManager().createAnItem(Material.MELON_SEEDS, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Melon Seed"), 
						Arrays.asList(ChatColor.translateAlternateColorCodes(
								'&', "&7Price: $5.00"))));
				
				inv.setItem(4, ItemManager.getIManager().createAnItem(Material.BEETROOT_SEEDS, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Beetroot Seed"), 
						Arrays.asList(ChatColor.translateAlternateColorCodes(
								'&', "&7Price: $5.00"))));
				
				p.openInventory(inv);
			}
		}else if (e.getInventory().getName().contains(ChatColor.translateAlternateColorCodes('&', "&bBuyable Perks")))
		{
			if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bAuto-Smelter"))
					&& i.getType().equals(Material.DIAMOND_PICKAXE)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
							ChatColor.translateAlternateColorCodes('&', "&cEverything will be auto-smelted"),
							ChatColor.translateAlternateColorCodes('&', "&con drop from mining."),
							ChatColor.translateAlternateColorCodes('&', "&cLost when you log off."),
							ChatColor.translateAlternateColorCodes('&', "&7Price: $2200.00"))))
			{
				e.setCancelled(true);
				
				if (SettingsManager.getEcoManager().getBalance(p.getName()) < 2200.0D)
				{	
					p.sendMessage(prefix + "Insufficient funds.");
					p.closeInventory();
				}
				else {
					vault.withdrawPlayer(p.getName(), 2200.0D);
					PermissionsManager.getPManager().insertPermission(p, "skyblockian.perk.autosmelter");
					p.sendMessage(prefix + "You have bought a perk: Auto-Smelter.");
					p.closeInventory();
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bAuto-Smelter"))
					&& i.getType().equals(Material.DIAMOND_PICKAXE)
					&& i.getItemMeta().getLore().contains(ChatColor.translateAlternateColorCodes('&', "&aPURCHASED")))
			{
				e.setCancelled(true);
				p.closeInventory();	
				p.sendMessage(prefix + "You already purchased this perk.");
			}
		}
	}
}
