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
		
		if (e.getInventory().getName().equals(ChatColor.translateAlternateColorCodes('&', "&bMob Drops")))
		{
			if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Rotten Flesh"))
					&& i.getType().equals(Material.ROTTEN_FLESH)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
							ChatColor.translateAlternateColorCodes('&', "&7Price: $3.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 3.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 3.0D);
						p.getInventory().addItem(new ItemStack(Material.ROTTEN_FLESH, 1));
						p.sendMessage(prefix + "You have bought rotten flesh.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.ROTTEN_FLESH))
					{
						vault.depositPlayer(p.getName(), 3.0D);
						p.getInventory().removeItem(new ItemStack(Material.ROTTEN_FLESH, 1));
						p.sendMessage(prefix + "You've successfully sold rotten flesh.");
					}
					else {
						p.sendMessage(prefix + "You don't have rotten flesh.");
						p.closeInventory();
						return;
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Bone"))
					&& i.getType().equals(Material.BONE)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
							ChatColor.translateAlternateColorCodes('&', "&7Price: $5.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 5.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 5.0D);
						p.getInventory().addItem(new ItemStack(Material.BONE, 1));
						p.sendMessage(prefix + "You have bought a bone.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.BONE))
					{
						vault.depositPlayer(p.getName(), 5.0D);
						p.getInventory().removeItem(new ItemStack(Material.BONE, 1));
						p.sendMessage(prefix + "You've successfully sold a bone.");
					}
					else {
						p.sendMessage(prefix + "You don't have a bone.");
						p.closeInventory();
						return;
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Grilled Porkchop"))
					&& i.getType().equals(Material.GRILLED_PORK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
							ChatColor.translateAlternateColorCodes('&', "&7Price: $25.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 25.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 25.0D);
						p.getInventory().addItem(new ItemStack(Material.GRILLED_PORK, 1));
						p.sendMessage(prefix + "You have bought a bone.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.GRILLED_PORK))
					{
						vault.depositPlayer(p.getName(), 25.0D);
						p.getInventory().removeItem(new ItemStack(Material.GRILLED_PORK, 1));
						p.sendMessage(prefix + "You've successfully sold a grilled porkchop.");
					}
					else {
						p.sendMessage(prefix + "You don't have a grilled porkchop.");
						p.closeInventory();
						return;
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Steak"))
					&& i.getType().equals(Material.COOKED_BEEF)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
							ChatColor.translateAlternateColorCodes('&', "&7Price: $40.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 40.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 40.0D);
						p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 1));
						p.sendMessage(prefix + "You have bought steak.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.COOKED_BEEF))
					{
						vault.depositPlayer(p.getName(), 40.0D);
						p.getInventory().removeItem(new ItemStack(Material.COOKED_BEEF, 1));
						p.sendMessage(prefix + "You've successfully sold steak.");
					}
					else {
						p.sendMessage(prefix + "You don't have steak.");
						p.closeInventory();
						return;
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Cooked Chicken"))
					&& i.getType().equals(Material.COOKED_CHICKEN)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
							ChatColor.translateAlternateColorCodes('&', "&7Price: $20.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 20.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 20.0D);
						p.getInventory().addItem(new ItemStack(Material.COOKED_CHICKEN, 1));
						p.sendMessage(prefix + "You have bought cooked chicken.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.COOKED_CHICKEN))
					{
						vault.depositPlayer(p.getName(), 20.0D);
						p.getInventory().removeItem(new ItemStack(Material.COOKED_CHICKEN, 1));
						p.sendMessage(prefix + "You've successfully sold cooked chicken.");
					}
					else {
						p.sendMessage(prefix + "You don't have cooked chicken.");
						p.closeInventory();
						return;
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Red Rose"))
					&& i.getType().equals(Material.RED_ROSE)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
							ChatColor.translateAlternateColorCodes('&', "&7Price: $8.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 8.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 8.0D);
						p.getInventory().addItem(new ItemStack(Material.RED_ROSE, 1));
						p.sendMessage(prefix + "You have bought a red rose.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.RED_ROSE))
					{
						vault.depositPlayer(p.getName(), 8.0D);
						p.getInventory().removeItem(new ItemStack(Material.RED_ROSE, 1));
						p.sendMessage(prefix + "You've successfully sold a red rose.");
					}
					else {
						p.sendMessage(prefix + "You don't have a red rose.");
						p.closeInventory();
						return;
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Blaze Rod"))
					&& i.getType().equals(Material.BLAZE_ROD)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
							ChatColor.translateAlternateColorCodes('&', "&7Price: $80.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 80.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 80.0D);
						p.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
						p.sendMessage(prefix + "You have bought a blaze rod.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.BLAZE_ROD))
					{
						vault.depositPlayer(p.getName(), 80.0D);
						p.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD, 1));
						p.sendMessage(prefix + "You've successfully sold a blaze rod.");
					}
					else {
						p.sendMessage(prefix + "You don't have a blaze rod.");
						p.closeInventory();
						return;
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Gunpowder"))
					&& i.getType().equals(Material.SULPHUR)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
							ChatColor.translateAlternateColorCodes('&', "&7Price: $75.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 75.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 75.0D);
						p.getInventory().addItem(new ItemStack(Material.SULPHUR, 1));
						p.sendMessage(prefix + "You have bought gunpowder.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.SULPHUR))
					{
						vault.depositPlayer(p.getName(), 75.0D);
						p.getInventory().removeItem(new ItemStack(Material.SULPHUR, 1));
						p.sendMessage(prefix + "You've successfully sold gunpowder.");
					}
					else {
						p.sendMessage(prefix + "You don't have gunpowder.");
						p.closeInventory();
						return;
					}
				}
			}
		}
		
		if (e.getInventory().getName().equals(ChatColor.translateAlternateColorCodes('&', "&bBlocks")))
		{
			if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Grass Block"))
					&& i.getType().equals(Material.GRASS)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Price: $500.00"))))
			{
				e.setCancelled(true);
				
				if (SettingsManager.getEcoManager().getBalance(p.getName()) < 500.0D)
				{
					p.sendMessage(prefix + "Insufficient funds.");
					p.closeInventory();
				}
				else {
					vault.withdrawPlayer(p.getName(), 500.0D);
					p.getInventory().addItem(new ItemStack(Material.GRASS, 1));
					p.sendMessage(prefix + "You have bought a grass block.");
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Wood"))
					&& i.getType().equals(Material.WOOD)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
							ChatColor.translateAlternateColorCodes('&', "&7Price: $25.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 25.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 25.0D);
						p.getInventory().addItem(new ItemStack(Material.WOOD, 1));
						p.sendMessage(prefix + "You have bought wood.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.WOOD))
					{
						vault.depositPlayer(p.getName(), 25.D);
						p.getInventory().removeItem(new ItemStack(Material.WOOD, 1));
						p.sendMessage(prefix + "You've successfully sold wood.");
					}
					else {
						p.sendMessage(prefix + "You don't have wood.");
						p.closeInventory();
						return;
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Ice"))
					&& i.getType().equals(Material.ICE)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Price: $250.00"))))
			{
				e.setCancelled(true);
				
				if (SettingsManager.getEcoManager().getBalance(p.getName()) < 250.0D)
				{
					p.sendMessage(prefix + "Insufficient funds.");
					p.closeInventory();
				}
				else {
					vault.withdrawPlayer(p.getName(), 250.0D);
					p.getInventory().addItem(new ItemStack(Material.ICE, 1));
					p.sendMessage(prefix + "You have bought ice.");
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Cobblestone"))
					&& i.getType().equals(Material.COBBLESTONE)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
							ChatColor.translateAlternateColorCodes('&', "&7Price: $1.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 1.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 1.0D);
						p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 1));
						p.sendMessage(prefix + "You have bought cobblestone.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.COBBLESTONE))
					{
						vault.depositPlayer(p.getName(), 1.0D);
						p.getInventory().removeItem(new ItemStack(Material.COBBLESTONE, 1));
						p.sendMessage(prefix + "You've successfully sold cobblestone.");
					}
					else {
						p.sendMessage(prefix + "You don't have cobblestone.");
						p.closeInventory();
						return;
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Shulker Box"))
					&& i.getType().equals(Material.BLACK_SHULKER_BOX)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
							'&', "&7Price: $10000.00"))))
			{
				e.setCancelled(true);
				
				if (SettingsManager.getEcoManager().getBalance(p.getName()) < 10000.0D)
				{
					p.sendMessage(prefix + "Insufficient funds.");
					p.closeInventory();
				}
				else {
					vault.withdrawPlayer(p.getName(), 10000.0D);
					p.getInventory().addItem(new ItemStack(Material.BLACK_SHULKER_BOX, 1));
					p.sendMessage(prefix + "You have bought a black shulker box.");
				}
			}
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
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Sugar Cane"))
					&& i.getType().equals(Material.SUGAR_CANE)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
							ChatColor.translateAlternateColorCodes('&', "&7Price: $200.00"))))
			{
				e.setCancelled(true);
					
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 200.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 200.0D);
						p.getInventory().addItem(new ItemStack(Material.SUGAR_CANE, 1));
						p.sendMessage(prefix + "You have bought sugar cane.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.SUGAR_CANE))
					{
						vault.depositPlayer(p.getName(), 200.D);
						p.getInventory().removeItem(new ItemStack(Material.SUGAR_CANE, 1));
						p.sendMessage(prefix + "You've successfully sold sugar cane.");
					}
					else {
						p.sendMessage(prefix + "You don't have sugar cane.");
						p.closeInventory();
						return;
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Potato"))
					&& i.getType().equals(Material.POTATO_ITEM)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
							ChatColor.translateAlternateColorCodes('&', "&7Price: $300.00"))))
			{
				e.setCancelled(true);
					
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 300.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 300.0D);
						p.getInventory().addItem(new ItemStack(Material.POTATO_ITEM, 1));
						p.sendMessage(prefix + "You have bought potato.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.POTATO_ITEM))
					{
						vault.depositPlayer(p.getName(), 300.D);
						p.getInventory().removeItem(new ItemStack(Material.POTATO_ITEM, 1));
						p.sendMessage(prefix + "You've successfully sold a potato.");
					}
					else {
						p.sendMessage(prefix + "You don't have a potato.");
						p.closeInventory();
						return;
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Carrot"))
					&& i.getType().equals(Material.CARROT_ITEM)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
							ChatColor.translateAlternateColorCodes('&', "&7Price: $300.00"))))
			{
				e.setCancelled(true);
					
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 300.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 300.0D);
						p.getInventory().addItem(new ItemStack(Material.CARROT_ITEM, 1));
						p.sendMessage(prefix + "You have bought a carrot.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.CARROT_ITEM))
					{
						vault.depositPlayer(p.getName(), 300.D);
						p.getInventory().removeItem(new ItemStack(Material.CARROT_ITEM, 1));
						p.sendMessage(prefix + "You've successfully sold a carrot.");
					}
					else {
						p.sendMessage(prefix + "You don't have a carrot.");
						p.closeInventory();
						return;
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Wheat"))
					&& i.getType().equals(Material.WHEAT)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
							ChatColor.translateAlternateColorCodes('&', "&7Price: $150.00"))))
			{
				e.setCancelled(true);
					
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 150.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 150.0D);
						p.getInventory().addItem(new ItemStack(Material.WHEAT, 1));
						p.sendMessage(prefix + "You have bought wheat.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.WHEAT))
					{
						vault.depositPlayer(p.getName(), 150.D);
						p.getInventory().removeItem(new ItemStack(Material.WHEAT, 1));
						p.sendMessage(prefix + "You've successfully sold wheat.");
					}
					else {
						p.sendMessage(prefix + "You don't have wheat.");
						p.closeInventory();
						return;
					}
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
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Coal"))
					&& i.getType().equals(Material.COAL) && 
					i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"), 
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"), 
							ChatColor.translateAlternateColorCodes('&', "&7Price: $50.00"))))
			{
				e.setCancelled(true);
				
				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 50.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 50.0D);
						p.getInventory().addItem(new ItemStack(Material.COAL, 1));
						p.sendMessage(prefix + "You have bought coal.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.COAL))
					{
						vault.depositPlayer(p.getName(), 50.D);
						p.getInventory().removeItem(new ItemStack(Material.COAL, 1));
						p.sendMessage(prefix + "You've successfully sold coal.");
					}
					else {
						p.sendMessage(prefix + "You don't have coal.");
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
				
				inv.setItem(2, ItemManager.getIManager().createItem(Material.COAL, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Coal"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $50.00")));
								
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
				
				inv.setItem(5, ItemManager.getIManager().createItem(Material.SUGAR_CANE, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Sugar Cane"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $200.00")));
					
				inv.setItem(6, ItemManager.getIManager().createItem(Material.POTATO_ITEM, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Potato"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $300.00")));
				
				inv.setItem(7, ItemManager.getIManager().createItem(Material.CARROT_ITEM, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Carrot"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $300.00")));
				
				inv.setItem(8, ItemManager.getIManager().createItem(Material.WHEAT, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Wheat"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $150.00")));
		
				p.openInventory(inv);
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bBlocks"))
					&& i.getType().equals(Material.GRASS))
			{
				e.setCancelled(true);
				
				Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bBlocks"));
				
				inv.setItem(0, ItemManager.getIManager().createAnItem(Material.GRASS, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Grass Block"), 
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $500.00"))));
				
				inv.setItem(2, ItemManager.getIManager().createItem(Material.WOOD, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Wood"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $25.00")));
				
				inv.setItem(4, ItemManager.getIManager().createAnItem(Material.ICE, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Ice"), 
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $250.00"))));
				
				inv.setItem(6, ItemManager.getIManager().createItem(Material.COBBLESTONE, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Cobblestone"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $1.00")));

				inv.setItem(8, ItemManager.getIManager().createAnItem(Material.BLACK_SHULKER_BOX, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Shulker Box"), 
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $10000.00"))));
				
				p.openInventory(inv);
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bMob Drops"))
					&& i.getType().equals(Material.BLAZE_ROD))
			{
				e.setCancelled(true);
				
				Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bMob Drops"));
				
				inv.setItem(0, ItemManager.getIManager().createItem(Material.ROTTEN_FLESH, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Rotten Flesh"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $3.00")));
				
				inv.setItem(1, ItemManager.getIManager().createItem(Material.BONE, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Bone"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $5.00")));
				
				inv.setItem(2, ItemManager.getIManager().createItem(Material.GRILLED_PORK, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Grilled Porkchop"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $25.00")));
				
				inv.setItem(3, ItemManager.getIManager().createItem(Material.COOKED_BEEF, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Steak"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $40.00")));

				inv.setItem(4, ItemManager.getIManager().createItem(Material.COOKED_CHICKEN, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Cooked Chicken"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $20.00")));

				inv.setItem(5, ItemManager.getIManager().createItem(Material.RED_ROSE, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Red Rose"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $8.00")));
				
				inv.setItem(6, ItemManager.getIManager().createItem(Material.BLAZE_ROD, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Blaze Rod"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $80.00")));

				inv.setItem(7, ItemManager.getIManager().createItem(Material.SULPHUR, 
						ChatColor.translateAlternateColorCodes('&', "&b1x Gunpowder"), 
						ChatColor.translateAlternateColorCodes('&', "&7Price: $75.00")));
				
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
