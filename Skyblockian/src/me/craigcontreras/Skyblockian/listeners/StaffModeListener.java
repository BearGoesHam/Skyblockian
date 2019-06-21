package me.craigcontreras.Skyblockian.listeners;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.commands.admin.StaffModeCommand;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

@SuppressWarnings("deprecation")
public class StaffModeListener 
implements Listener, TextFormat
{
	@EventHandler
	public void onInteract(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		ItemStack inHand = p.getItemInHand();
		Inventory chest = Bukkit.createInventory(null, 54, "Chest");
		Action a = e.getAction();
		
		if (StaffModeCommand.playerInStaffMode(p))
		{
			if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK))
			{
				if (inHand.getType().equals(Material.WATCH) && 
						inHand.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
								'&', "&bRandom Teleport")))
				{
					if (Skyblockian.getCore().toTeleportTo.size() > 0)
					{
						Random r = new Random();
						int index = r.nextInt(Skyblockian.getCore().toTeleportTo.size());
						p.teleport(Skyblockian.getCore().toTeleportTo.get(index).getLocation());
						p.sendMessage(prefix + "You have teleported to " + Skyblockian.getCore().toTeleportTo.get(index).getName() + ".");
					}
					else {
						p.sendMessage(prefix + "There is nobody to teleport to.");
					}
				}else if (inHand.getType().equals(Material.INK_SACK)
						&& inHand.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
								'&', "&bVanish yourself")))
				{
					Bukkit.dispatchCommand(p, "a vanish");
				}
			}
			
			if (a.equals(Action.RIGHT_CLICK_BLOCK))
			{
				if (e.getClickedBlock().getType().equals(Material.CHEST) || e.getClickedBlock().getType().equals(Material.TRAPPED_CHEST))
				{
					Chest c = (Chest) e.getClickedBlock().getState();
					p.sendMessage(prefix + "You have opened the chest silently.");
					chest.setContents(c.getInventory().getContents());
					p.openInventory(chest);
					e.setCancelled(true);
				}
			}
			
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e)
	{
		Player p = e.getPlayer();
		if (StaffModeCommand.playerInStaffMode(p))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		if (StaffModeCommand.playerInStaffMode(p)) 
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInventoryDrop(PlayerDropItemEvent e)
	{
		Player p = e.getPlayer();
		if (StaffModeCommand.playerInStaffMode(p))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInventoryPickup(PlayerPickupItemEvent e)
	{
		Player p = e.getPlayer();
		if (StaffModeCommand.playerInStaffMode(p))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInventoryMove(InventoryClickEvent e)
	{
		Player p = (Player) e.getWhoClicked();
		if (StaffModeCommand.playerInStaffMode(p))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerAttack(EntityDamageByEntityEvent e)
	{
		if (e.getDamager() instanceof Player)
		{
			if (StaffModeCommand.playerInStaffMode((Player) e.getDamager()))
			{
				Player p = (Player) e.getDamager();
				e.setCancelled(true);
				p.sendMessage(prefix + "To PvP, please get out of staff mode.");
			}
		}
	}

	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e)
	{
		Player p = e.getPlayer();
		ItemStack inHand = p.getItemInHand();
		if (StaffModeCommand.playerInStaffMode(p))
		{
			if (inHand.getType().equals(Material.ICE)
					&& inHand.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
							'&', "&bFreeze a Player")))
			{
				if (e.getRightClicked() instanceof Player)
				{
					Player clicked = (Player) e.getRightClicked();
					Bukkit.dispatchCommand(p, "a freeze " + clicked.getName());
				}
			}else if (inHand.getType().equals(Material.BOOK)
					&& inHand.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
							'&', "&bInformation about a Player")))
			{
				if (e.getRightClicked() instanceof Player)
				{
					Player clicked = (Player) e.getRightClicked();
					Inventory info = Bukkit.createInventory(null, 54, clicked.getName());
					info.setContents(clicked.getInventory().getContents());
					
					for (int i = 36; i < 45; i++)
					{
						info.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0, (byte) 5));
					}
					
					ItemStack health = new ItemStack(Material.GOLDEN_APPLE);
					ItemMeta hmeta = health.getItemMeta();
					hmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bHealth: &r" + Math.round(clicked.getHealth()) + "HP"));
					health.setItemMeta(hmeta);
					
					ItemStack hunger = new ItemStack(Material.COOKED_BEEF);
					ItemMeta humeta = hunger.getItemMeta();
					humeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bHunger: &r" + clicked.getFoodLevel()));
					hunger.setItemMeta(humeta);
					
					ItemStack potion = new ItemStack(Material.POTION, 1);
					ItemMeta pmeta = potion.getItemMeta();
					ArrayList<String> effects = new ArrayList<String>();
					effects.add(" ");
					
					ItemStack gamemode = new ItemStack(Material.DIAMOND_BLOCK);
					ItemMeta gmeta = gamemode.getItemMeta();
					gmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bGamemode: &r" + clicked.getGameMode().toString().toLowerCase()));
					gamemode.setItemMeta(gmeta);
					
					for (PotionEffect effect : clicked.getActivePotionEffects())
					{
						if (clicked.getActivePotionEffects() != null)
						{
							effects.add(ChatColor.translateAlternateColorCodes('&', "&f" + effect.getType().getName() + " " + effect.getDuration()));
						}
						else {
							effects.add(ChatColor.translateAlternateColorCodes('&', "&fNone"));
						}
					}
					
					pmeta.setLore(effects);
					pmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bActive Potion Effects"));
					potion.setItemMeta(pmeta);
										
					info.setItem(45, health);
					info.setItem(46, hunger);
					info.setItem(47, potion);
					info.setItem(48, gamemode);
					
					p.openInventory(info);
				}
			}
			
			e.setCancelled(true);
		}
	}
}
