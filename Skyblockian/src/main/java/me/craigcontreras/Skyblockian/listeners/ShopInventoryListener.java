package me.craigcontreras.Skyblockian.listeners;

import java.util.Arrays;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
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
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.ROTTEN_FLESH, 64))
					{
						vault.depositPlayer(p.getName(), 3.0D * 64);
						p.getInventory().removeItem(new ItemStack(Material.ROTTEN_FLESH, 64));
						p.sendMessage(prefix + "You've successfully sold 64x rotten flesh.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x rotten flesh.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 3.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
						return;
					}
					else{
						vault.withdrawPlayer(p.getName(), 3.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.ROTTEN_FLESH, 64));
						p.sendMessage(prefix + "You have bought 64x rotten flesh.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Bone"))
					&& i.getType().equals(Material.BONE)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
					ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.BONE, 64))
					{
						vault.depositPlayer(p.getName(), 5.0D * 64);
						p.getInventory().removeItem(new ItemStack(Material.BONE, 64));
						p.sendMessage(prefix + "You've successfully sold 64x bone.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x bone.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 5.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 5.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.BONE, 64));
						p.sendMessage(prefix + "You have bought 64x bone.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Grilled Porkchop"))
					&& i.getType().equals(Material.GRILLED_PORK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
					ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.GRILLED_PORK, 64))
					{
						vault.depositPlayer(p.getName(), 25.0D * 64);
						p.getInventory().removeItem(new ItemStack(Material.GRILLED_PORK, 64));
						p.sendMessage(prefix + "You've successfully sold 64x grilled porkchop.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x grilled porkchop.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 25.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 25.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.GRILLED_PORK, 64));
						p.sendMessage(prefix + "You have bought 64x bone.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Steak"))
					&& i.getType().equals(Material.COOKED_BEEF)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
					ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.COOKED_BEEF, 64))
					{
						vault.depositPlayer(p.getName(), 40.0D * 64);
						p.getInventory().removeItem(new ItemStack(Material.COOKED_BEEF, 64));
						p.sendMessage(prefix + "You've successfully sold 64x steak.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x steak.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 40.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 40.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 64));
						p.sendMessage(prefix + "You have bought 64x steak.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Cooked Chicken"))
					&& i.getType().equals(Material.COOKED_CHICKEN)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
					ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.COOKED_CHICKEN, 64))
					{
						vault.depositPlayer(p.getName(), 20.0D * 64);
						p.getInventory().removeItem(new ItemStack(Material.COOKED_CHICKEN, 64));
						p.sendMessage(prefix + "You've successfully sold 64x cooked chicken.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x cooked chicken.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 20.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 20.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.COOKED_CHICKEN, 64));
						p.sendMessage(prefix + "You have bought 64x cooked chicken.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Red Rose"))
					&& i.getType().equals(Material.RED_ROSE)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
					ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.RED_ROSE, 64))
					{
						vault.depositPlayer(p.getName(), 8.0D * 64);
						p.getInventory().removeItem(new ItemStack(Material.RED_ROSE, 64));
						p.sendMessage(prefix + "You've successfully sold 64x red rose.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x red rose.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 8.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 8.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.RED_ROSE, 64));
						p.sendMessage(prefix + "You have bought 64x red rose.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Blaze Rod"))
					&& i.getType().equals(Material.BLAZE_ROD)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
					ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.BLAZE_ROD, 64))
					{
						vault.depositPlayer(p.getName(), 80.0D * 64);
						p.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD, 64));
						p.sendMessage(prefix + "You've successfully sold 64x blaze rod.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x blaze rod.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 80.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 80.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 64));
						p.sendMessage(prefix + "You have bought a blaze rod.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Gunpowder"))
					&& i.getType().equals(Material.SULPHUR)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
					ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.SULPHUR, 64))
					{
						vault.depositPlayer(p.getName(), 75.0D * 64);
						p.getInventory().removeItem(new ItemStack(Material.SULPHUR, 64));
						p.sendMessage(prefix + "You've successfully sold 64x gunpowder.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x gunpowder.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 75.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 75.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.SULPHUR, 64));
						p.sendMessage(prefix + "You have bought 64x gunpowder.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Spider Eye"))
					&& i.getType().equals(Material.SPIDER_EYE)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
					ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
					ChatColor.translateAlternateColorCodes('&', "&7Price: $15.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 15.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 15.0D);
						p.getInventory().addItem(new ItemStack(Material.SPIDER_EYE, 1));
						p.sendMessage(prefix + "You have bought a spider eye.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.SPIDER_EYE))
					{
						vault.depositPlayer(p.getName(), 15.0D);
						p.getInventory().removeItem(new ItemStack(Material.SPIDER_EYE, 1));
						p.sendMessage(prefix + "You've successfully sold a spider eye.");
					}
					else {
						p.sendMessage(prefix + "You don't have a spider eye.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.SPIDER_EYE, 64))
					{
						vault.depositPlayer(p.getName(), 15.0D * 64);
						p.getInventory().removeItem(new ItemStack(Material.SPIDER_EYE, 64));
						p.sendMessage(prefix + "You've successfully sold 64x spider eye.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x spider eye.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 15.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 15.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.SPIDER_EYE, 64));
						p.sendMessage(prefix + "You have bought 64x spider eye.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x String"))
					&& i.getType().equals(Material.STRING)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
					ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
					ChatColor.translateAlternateColorCodes('&', "&7Price: $10.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 10.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 10.0D);
						p.getInventory().addItem(new ItemStack(Material.STRING, 1));
						p.sendMessage(prefix + "You have bought string.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.STRING))
					{
						vault.depositPlayer(p.getName(), 10.0D);
						p.getInventory().removeItem(new ItemStack(Material.STRING, 1));
						p.sendMessage(prefix + "You've successfully sold string.");
					}
					else {
						p.sendMessage(prefix + "You don't have string.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.STRING, 64))
					{
						vault.depositPlayer(p.getName(), 10.0D * 64);
						p.getInventory().removeItem(new ItemStack(Material.STRING, 64));
						p.sendMessage(prefix + "You've successfully sold 64x string.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x string.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 10.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 10.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.STRING, 64));
						p.sendMessage(prefix + "You have bought 64x string.");
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
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.WOOD, 64))
					{
						vault.depositPlayer(p.getName(), 25.D * 64);
						p.getInventory().removeItem(new ItemStack(Material.WOOD, 64));
						p.sendMessage(prefix + "You've successfully sold 64x wood.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x wood.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 25.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 25.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.WOOD, 64));
						p.sendMessage(prefix + "You have bought 64x wood.");
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
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.COBBLESTONE, 64))
					{
						vault.depositPlayer(p.getName(), 1.0D * 64);
						p.getInventory().removeItem(new ItemStack(Material.COBBLESTONE, 64));
						p.sendMessage(prefix + "You've successfully sold 64x cobblestone.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x cobblestone.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 1.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 1.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 64));
						p.sendMessage(prefix + "You have bought 64x cobblestone.");
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
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Obsidian"))
					&& i.getType().equals(Material.OBSIDIAN)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
					'&', "&7Price: $1000.00"))))
			{
				e.setCancelled(true);

				if (SettingsManager.getEcoManager().getBalance(p.getName()) < 1000.0D)
				{
					p.sendMessage(prefix + "Insufficient funds.");
					p.closeInventory();
				}
				else {
					vault.withdrawPlayer(p.getName(), 1000.0D);
					p.getInventory().addItem(new ItemStack(Material.OBSIDIAN, 1));
					p.sendMessage(prefix + "You have bought a obsidian.");
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
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.SUGAR_CANE, 64))
					{
						vault.depositPlayer(p.getName(), 200.D * 64);
						p.getInventory().removeItem(new ItemStack(Material.SUGAR_CANE, 64));
						p.sendMessage(prefix + "You've successfully sold 64x sugar cane.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x sugar cane.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 200.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 200.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.SUGAR_CANE, 64));
						p.sendMessage(prefix + "You have bought 64x sugar cane.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Potato"))
					&& i.getType().equals(Material.POTATO_ITEM)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
					ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.POTATO_ITEM, 64))
					{
						vault.depositPlayer(p.getName(), 300.D * 64);
						p.getInventory().removeItem(new ItemStack(Material.POTATO_ITEM, 64));
						p.sendMessage(prefix + "You've successfully sold 64x potato.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x potato.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 300.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 300.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.POTATO_ITEM, 64));
						p.sendMessage(prefix + "You have bought 64x potato.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Carrot"))
					&& i.getType().equals(Material.CARROT_ITEM)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
					ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.CARROT_ITEM, 64))
					{
						vault.depositPlayer(p.getName(), 300.D * 64);
						p.getInventory().removeItem(new ItemStack(Material.CARROT_ITEM, 64));
						p.sendMessage(prefix + "You've successfully sold 64x carrot.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x carrot.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 300.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 300.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.CARROT_ITEM, 64));
						p.sendMessage(prefix + "You have bought 64x carrot.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Wheat"))
					&& i.getType().equals(Material.WHEAT)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
					ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.WHEAT, 64))
					{
						vault.depositPlayer(p.getName(), 150.D * 64);
						p.getInventory().removeItem(new ItemStack(Material.WHEAT, 64));
						p.sendMessage(prefix + "You've successfully sold 64x wheat.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x wheat.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 150.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 150.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.WHEAT, 64));
						p.sendMessage(prefix + "You have bought 64x wheat.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Melon"))
					&& i.getType().equals(Material.MELON)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
					ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
					ChatColor.translateAlternateColorCodes('&', "&7Price: $10.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 10.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 10.0D);
						p.getInventory().addItem(new ItemStack(Material.MELON, 1));
						p.sendMessage(prefix + "You have bought a melon.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.MELON))
					{
						vault.depositPlayer(p.getName(), 10.0D);
						p.getInventory().removeItem(new ItemStack(Material.MELON, 1));
						p.sendMessage(prefix + "You've successfully sold a melon.");
					}
					else {
						p.sendMessage(prefix + "You don't have a melon.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.MELON, 64))
					{
						vault.depositPlayer(p.getName(), 10.0D * 64);
						p.getInventory().removeItem(new ItemStack(Material.MELON, 64));
						p.sendMessage(prefix + "You've successfully sold 64x melon.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x melon.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 10.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 10.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.MELON, 64));
						p.sendMessage(prefix + "You have bought 64x melon.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Pumpkin"))
					&& i.getType().equals(Material.PUMPKIN)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
					ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
						p.getInventory().addItem(new ItemStack(Material.PUMPKIN, 1));
						p.sendMessage(prefix + "You have bought a pumpkin.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.PUMPKIN))
					{
						vault.depositPlayer(p.getName(), 8.0D);
						p.getInventory().removeItem(new ItemStack(Material.PUMPKIN, 1));
						p.sendMessage(prefix + "You've successfully sold a pumpkin.");
					}
					else {
						p.sendMessage(prefix + "You don't have a pumpkin.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.PUMPKIN, 64))
					{
						vault.depositPlayer(p.getName(), 8.0D * 64);
						p.getInventory().removeItem(new ItemStack(Material.PUMPKIN, 64));
						p.sendMessage(prefix + "You've successfully sold 64x pumpkin.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x pumpkin.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 8.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 8.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.PUMPKIN, 64));
						p.sendMessage(prefix + "You have bought 64x pumpkin.");
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
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.IRON_INGOT, 64))
					{
						vault.depositPlayer(p.getName(), 350.D * 64);
						p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 64));
						p.sendMessage(prefix + "You've successfully sold 64x iron ingot.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x iron ingot.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 350.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 350.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 64));
						p.sendMessage(prefix + "You have bought 64x iron ingot.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Gold Ingot"))
					&& i.getType().equals(Material.GOLD_INGOT)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
					ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
					ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
					ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
						p.sendMessage(prefix + "You have bought a gold ingot.");
					}
				}else if (e.getClick() == ClickType.RIGHT)
				{
					if (p.getInventory().contains(Material.GOLD_INGOT))
					{
						vault.depositPlayer(p.getName(), 700.D);
						p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 1));
						p.sendMessage(prefix + "You've successfully sold a gold ingot.");
					}
					else {
						p.sendMessage(prefix + "You don't have a gold ingot.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.GOLD_INGOT, 64))
					{
						vault.depositPlayer(p.getName(), 700.D * 64);
						p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 64));
						p.sendMessage(prefix + "You've successfully sold 64x gold ingot.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x gold ingot.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 700.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 700.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 64));
						p.sendMessage(prefix + "You have bought 64x gold ingot.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Diamond"))
					&& i.getType().equals(Material.DIAMOND) &&
					i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
							ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
							ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.DIAMOND, 64))
					{
						vault.depositPlayer(p.getName(), 2500.D * 64);
						p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 64));
						p.sendMessage(prefix + "You've successfully sold 64x diamond.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x diamond.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 2500.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 2500.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.DIAMOND, 64));
						p.sendMessage(prefix + "You have bought 64x diamond.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Coal"))
					&& i.getType().equals(Material.COAL) &&
					i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cLeft click to buy"),
							ChatColor.translateAlternateColorCodes('&', "&aRight click to sell"),
							ChatColor.translateAlternateColorCodes('&', "&eMiddle click to sell a stack"),
							ChatColor.translateAlternateColorCodes('&', "&9Shift click to buy a stack"),
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
				}else if (e.getClick() == ClickType.MIDDLE)
				{
					if (p.getInventory().contains(Material.COAL, 64))
					{
						vault.depositPlayer(p.getName(), 50.D * 64);
						p.getInventory().removeItem(new ItemStack(Material.COAL, 64));
						p.sendMessage(prefix + "You've successfully sold 64x coal.");
					}
					else {
						p.sendMessage(prefix + "You don't have 64x coal.");
						p.closeInventory();
						return;
					}
				}else if (e.getClick() == ClickType.SHIFT_LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 50.0D * 64)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 50.0D * 64);
						p.getInventory().addItem(new ItemStack(Material.COAL, 64));
						p.sendMessage(prefix + "You have bought 64x coal.");
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
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&b1x Spider Spawner"))
					&& i.getType().equals(Material.MOB_SPAWNER)
					&& i.getItemMeta().getLore().equals(Arrays.asList(ChatColor.translateAlternateColorCodes(
					'&', "&7Price: $6000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 6000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 6000.0D);
						ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
						ItemMeta spawnermeta = spawner.getItemMeta();
						spawnermeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bSpider &fSpawner"));
						spawner.setItemMeta(spawnermeta);
						p.getInventory().addItem(spawner);
						p.sendMessage(prefix + "You have bought a Spider mob spawner.");
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

				inv.setItem(8, ItemManager.getIManager().createAnItem(Material.MOB_SPAWNER,
						ChatColor.translateAlternateColorCodes('&', "&b1x Spider Spawner"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $6000.00"))));

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

				Inventory inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', "&bFarmstuffs"));

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

				inv.setItem(9, ItemManager.getIManager().createItem(Material.MELON,
						ChatColor.translateAlternateColorCodes('&', "&b1x Melon"),
						ChatColor.translateAlternateColorCodes('&', "&7Price: $10.00")));

				inv.setItem(10, ItemManager.getIManager().createItem(Material.PUMPKIN,
						ChatColor.translateAlternateColorCodes('&', "&b1x Pumpkin"),
						ChatColor.translateAlternateColorCodes('&', "&7Price: $8.00")));

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

				inv.setItem(1, ItemManager.getIManager().createAnItem(Material.OBSIDIAN,
						ChatColor.translateAlternateColorCodes('&', "&b1x Obsidian"),
						Arrays.asList( ChatColor.translateAlternateColorCodes('&', "&7Price: $1000.00"))));

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

				Inventory inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', "&bMob Drops"));

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

				inv.setItem(8, ItemManager.getIManager().createItem(Material.SPIDER_EYE,
						ChatColor.translateAlternateColorCodes('&', "&b1x Spider Eye"),
						ChatColor.translateAlternateColorCodes('&', "&7Price: $15.00")));

				inv.setItem(9, ItemManager.getIManager().createItem(Material.STRING,
						ChatColor.translateAlternateColorCodes('&', "&b1x String"),
						ChatColor.translateAlternateColorCodes('&', "&7Price: $10.00")));

				p.openInventory(inv);
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bEnchantment Books"))
					&& i.getType().equals(Material.ENCHANTED_BOOK))
			{
				e.setCancelled(true);

				Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bEnchantment Books"));

				inv.setItem(0, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bGeneral"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7General enchantments"))));

				inv.setItem(2, ItemManager.getIManager().createAnItem(Material.BOW,
						ChatColor.translateAlternateColorCodes('&', "&bBow"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Bow enchantments"))));

				inv.setItem(4, ItemManager.getIManager().createAnItem(Material.DIAMOND_SWORD,
						ChatColor.translateAlternateColorCodes('&', "&bSword"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Sword enchantments"))));

				inv.setItem(6, ItemManager.getIManager().createAnItem(Material.DIAMOND_CHESTPLATE,
						ChatColor.translateAlternateColorCodes('&', "&bArmor"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Armor enchantments"))));

				inv.setItem(8, ItemManager.getIManager().createAnItem(Material.FISHING_ROD,
						ChatColor.translateAlternateColorCodes('&', "&bFishing Rod"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Fishing Rod enchantments"))));

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
		}else if (e.getInventory().getName().equals(ChatColor.translateAlternateColorCodes('&', "&bEnchantment Books")))
		{
			if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bGeneral"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7General enchantments"))))
			{
				e.setCancelled(true);
				Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bGeneral"));

				inv.setItem(0, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bUnbreaking III"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $30000.00"))));

				inv.setItem(1, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bEfficiency V"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $50000.00"))));

				inv.setItem(2, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bFortune III"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $30000.00"))));

				inv.setItem(3, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bSilk Touch"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $50000.00"))));

				inv.setItem(4, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bMending"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $100000.00"))));

				p.openInventory(inv);
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bSword"))
					&& i.getType().equals(Material.DIAMOND_SWORD)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Sword enchantments"))))
			{
				e.setCancelled(true);
				Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bSword"));

				inv.setItem(0, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bSharpness V"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $50000.00"))));

				inv.setItem(1, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bBane of Arthropods V"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $50000.00"))));

				inv.setItem(2, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bKnockback II"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $20000.00"))));

				inv.setItem(3, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bFire Aspect II"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $50000.00"))));

				inv.setItem(4, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bLooting III"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $35000.00"))));

				inv.setItem(5, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bLooting III"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $35000.00"))));

				inv.setItem(6, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bSweeping Edge III"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $35000.00"))));

				p.openInventory(inv);
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bBow"))
					&& i.getType().equals(Material.BOW)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Bow enchantments"))))
			{
				e.setCancelled(true);
				Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bBow"));

				inv.setItem(0, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bPower V"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $50000.00"))));

				inv.setItem(1, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bPunch II"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $20000.00"))));

				inv.setItem(2, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bFlame"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $50000.00"))));

				inv.setItem(3, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bInfinity"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $100000.00"))));

				p.openInventory(inv);
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bArmor"))
					&& i.getType().equals(Material.DIAMOND_CHESTPLATE)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Armor enchantments"))))
			{
				e.setCancelled(true);
				Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bArmor"));

				inv.setItem(0, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bProtection IV"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $40000.00"))));

				inv.setItem(1, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bFeather Falling IV"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $40000.00"))));

				inv.setItem(2, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bRespiration III"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $30000.00"))));

				inv.setItem(3, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bAqua Affinity"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $10000.00"))));

				inv.setItem(4, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bThorns III"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $75000.00"))));

				inv.setItem(5, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bDepth Strider III"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $30000.00"))));

				inv.setItem(6, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bFrost Walker II"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $25000.00"))));

				p.openInventory(inv);
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bFishing Rod"))
					&& i.getType().equals(Material.FISHING_ROD)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Fishing Rod enchantments"))))
			{
				e.setCancelled(true);
				Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bFishing Rod"));

				inv.setItem(0, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bLuck of the Sea III"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $30000.00"))));

				inv.setItem(1, ItemManager.getIManager().createAnItem(Material.ENCHANTED_BOOK,
						ChatColor.translateAlternateColorCodes('&', "&bLure III"),
						Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Price: $30000.00"))));

				p.openInventory(inv);
			}
		}else if (e.getInventory().getName().equals(ChatColor.translateAlternateColorCodes('&', "&bGeneral")))
		{
			if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bUnbreaking III"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $30000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 30000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 30000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.DURABILITY, 3, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought an Unbreaking III enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bEfficiency V"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $50000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 50000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 50000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.DIG_SPEED, 5, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought an Efficiency V enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bFortune III"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $30000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 30000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 30000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Fortune III enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bSilk Touch"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $50000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 50000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 50000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.SILK_TOUCH, 1, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Silk Touch enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bMending"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $100000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 100000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 100000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.MENDING, 1, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Mending enchantment book.");
					}
				}
			}
		}else if (e.getInventory().getName().equals(ChatColor.translateAlternateColorCodes('&', "&bSword")))
		{
			if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bSharpness V"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $50000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 50000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 50000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.DAMAGE_ALL, 5, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Sharpness V enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bBane of Arthropods V"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $50000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 50000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 50000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.DAMAGE_ARTHROPODS, 5, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Bane of Arthropods V enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bKnockback II"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $20000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 20000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 20000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.KNOCKBACK, 2, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Knockback II enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bFire Aspect II"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $50000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 50000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 50000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.FIRE_ASPECT, 2, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Fire Aspect II enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bLooting III"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $35000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 35000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 35000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.LOOT_BONUS_MOBS, 3, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Looting III enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bSweeping Edge III"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $35000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 35000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 35000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.SWEEPING_EDGE, 3, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Sweeping Edge III enchantment book.");
					}
				}
			}
		}else if (e.getInventory().getName().equals(ChatColor.translateAlternateColorCodes('&', "&bBow")))
		{
			if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bPower V"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $50000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 50000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 50000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.ARROW_DAMAGE, 5, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Power V enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bPunch II"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $20000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 20000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 20000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.ARROW_KNOCKBACK, 2, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Punch II enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bFlame"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $50000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 50000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 50000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.ARROW_FIRE, 1, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Flame enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bInfinity"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $100000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 100000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 100000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.ARROW_INFINITE, 1, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought an Infinity enchantment book.");
					}
				}
			}
		}else if (e.getInventory().getName().equals(ChatColor.translateAlternateColorCodes('&', "&bArmor")))
		{
			if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bProtection IV"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $40000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 40000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 40000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Protection IV enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bFeather Falling IV"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $40000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 40000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 40000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.PROTECTION_FALL, 4, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Feather Falling IV enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bRespiration III"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $30000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 30000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 30000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.OXYGEN, 3, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Respiration III enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bAqua Affinity"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $10000.00"))))
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
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.WATER_WORKER, 1, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought an Aqua Affinity enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bThorns III"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $75000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 75000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 75000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.THORNS, 3, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Thorns III enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bDepth Strider III"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $30000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 30000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 30000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.DEPTH_STRIDER, 3, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Depth Strider III enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bFrost Walker II"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $25000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 25000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 25000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.FROST_WALKER, 2, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Frost Walker II enchantment book.");
					}
				}
			}
		}else if (e.getInventory().getName().equals(ChatColor.translateAlternateColorCodes('&', "&bFishing Rod")))
		{
			if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bLuck of the Sea III"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $30000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 30000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 30000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.LUCK, 3, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Luck of the Sea III enchantment book.");
					}
				}
			}else if (i.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
					'&', "&bLure III"))
					&& i.getType().equals(Material.ENCHANTED_BOOK)
					&& i.getItemMeta().getLore().equals(Arrays.asList(
					ChatColor.translateAlternateColorCodes('&', "&7Price: $30000.00"))))
			{
				e.setCancelled(true);

				if (e.getClick() == ClickType.LEFT)
				{
					if (SettingsManager.getEcoManager().getBalance(p.getName()) < 30000.0D)
					{
						p.sendMessage(prefix + "Insufficient funds.");
						p.closeInventory();
					}
					else {
						vault.withdrawPlayer(p.getName(), 30000.0D);
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
						meta.addStoredEnchant(Enchantment.LURE, 3, true);
						book.setItemMeta(meta);
						p.getInventory().addItem(book);
						p.sendMessage(prefix + "You have bought a Lure III enchantment book.");
					}
				}
			}
		}
	}
}