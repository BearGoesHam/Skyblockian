package me.craigcontreras.Skyblockian.listeners;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

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
		
		if (e.getInventory().getName().contains(ChatColor.translateAlternateColorCodes('&', "&bShop")))
		{
			if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
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
			}
		}else if (e.getInventory().getName().contains(ChatColor.translateAlternateColorCodes('&', "&bBuyable Perks")))
		{
			if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bAuto-Smelter"))
					&& i.getType().equals(Material.DIAMOND_PICKAXE)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
							ChatColor.translateAlternateColorCodes('&', "&cEverything will be auto-smelted"),
							ChatColor.translateAlternateColorCodes('&', "&con drop from mining."),
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
