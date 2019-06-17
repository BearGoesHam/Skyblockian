package me.craigcontreras.Skyblockian.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import me.craigcontreras.Skyblockian.island.Island;
import me.craigcontreras.Skyblockian.island.Island2;
import me.craigcontreras.Skyblockian.island.IslandManager;
import me.craigcontreras.Skyblockian.listeners.ItemManager;
import me.craigcontreras.Skyblockian.tpa.TPAManager;

public class IslandCommand 
implements CommandExecutor, TextFormat
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if ((sender instanceof Player))
		{
			Player p = (Player)sender;
			if (args.length >= 1)
			{
				IslandManager im = IslandManager.getIM();
				
				if (args[0].equalsIgnoreCase("create"))
				{
					if (!im.hasIsland(p) && !im.hasIsland2(p))
					{
						Inventory islands = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bAvailable Island Types"));
						
						islands.setItem(0, ItemManager.getIManager().createAnItem(Material.GRASS, 
								ChatColor.translateAlternateColorCodes('&', "&bOriginal"), 
								Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Difficulty: &aEasy"))));
												
						islands.setItem(8, ItemManager.getIManager().createAnItem(Material.BARRIER, 
								ChatColor.translateAlternateColorCodes('&', "&cMore soon."),
								Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Developers are lazy."))));
						
						p.openInventory(islands);
						
						p.sendMessage(prefix + "Creating a personal island for you...");
					}
					else {
						p.sendMessage(cmdError + "delete");
					}
				}else if (args[0].equalsIgnoreCase("delete"))
				{
					if (im.hasIsland(p))
					{
						p.sendMessage(prefix + "Deleting your island...");
						IslandManager.deleteIsland(p);
					}
					else if (im.hasIsland2(p))
					{
						p.sendMessage(prefix + "Deleting your island...");
						IslandManager.deleteIsland(p);
					}
					else {
						p.sendMessage(cmdError + "create");
					}
				}else if (args[0].equalsIgnoreCase("home"))
				{
					if (im.hasIsland(p) || im.hasIsland2(p))
					{
						p.sendMessage(prefix + "Teleporting to your personal island...");
						im.sendHome(p);
					}
					else {
						p.sendMessage(cmdError + "create");
					}
				}else if (args[0].equalsIgnoreCase("tpa"))
				{
					if (args.length == 1)
					{
						p.sendMessage(cmdError + "tpa <teleport, accept, deny>");
						return false;
					}
					
					if (args[1].equalsIgnoreCase("accept"))
					{
						TPAManager.getTPAMan().acceptRequest(p);
						return false;
					}
					
					if (args[1].equalsIgnoreCase("deny"))
					{
						TPAManager.getTPAMan().denyRequest(p);
						return false;
					}
					
					Player target = Bukkit.getPlayer(args[1]);
					
					if (target == null)
					{
						p.sendMessage(playerError + args[1]);
					}
					else {
						TPAManager.getTPAMan().request(p, target);
					}
				}else if (args[0].equalsIgnoreCase("shop"))
				{
					Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bShop"));
													
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
					p.sendMessage(prefix + "Opening shops...");
				}else if (args[0].equalsIgnoreCase("perks"))
				{
					Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bBuyable Perks"));
					
					inv.setItem(0, ItemManager.getIManager().createAnItem(Material.DIAMOND_PICKAXE, 
							ChatColor.translateAlternateColorCodes('&', "&bAuto-Smelter"), 
							Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cEverything will be auto-smelted"),
									ChatColor.translateAlternateColorCodes('&', "&con drop from mining."),
									ChatColor.translateAlternateColorCodes('&', "&7Price: $2200.00"))));

					if (p.hasPermission("skyblockian.perk.autosmelter") || p.hasPermission("skyblockian.admin"))
					{
						inv.setItem(0, ItemManager.getIManager().createAnItem(Material.DIAMOND_PICKAXE, 
								ChatColor.translateAlternateColorCodes('&', "&bAuto-Smelter"), 
								Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cEverything will be auto-smelted"),
										ChatColor.translateAlternateColorCodes('&', "&con drop from mining."),
										ChatColor.translateAlternateColorCodes('&', "&aPURCHASED"))));
					}
					
					p.openInventory(inv);
					p.sendMessage(prefix + "Opening perk shop...");
				}else if (args[0].equalsIgnoreCase("info"))
				{
					if (p.hasPermission("skyblockian.admin"))
					{
						p.sendMessage(prefix);
						p.sendMessage(author);
						p.sendMessage(textColor + "Total islands: " + im.getTotalIslands());
						p.sendMessage(textColor + "Current islands in use: " + im.getTotalIslandsInUse());
					}
					else {
						p.sendMessage(noPerm);
					}
				}else if (args[0].equalsIgnoreCase("tp"))
				{
					if (p.hasPermission("skyblockian.admin"))
					{
						if (args.length < 2)
						{
							p.sendMessage(cmdError + "tp <player>");
							return false;
						}
						
						Player target = Bukkit.getPlayer(args[1]);
						if (target == null)
						{
							p.sendMessage(playerError + args[1].toString() + ".");
							return false;
						}
						
						if (im.hasIsland(target))
						{
							Island i = im.getIsland(target);
							p.sendMessage(prefix + "Teleporting to player...");
							p.teleport(i.getLoc());
						}else if (im.hasIsland2(target))
						{
							Island2 i = im.getIsland2(target);
							p.sendMessage(prefix + "Teleporting to player...");
							p.teleport(i.getLoc());
						}
						else {
							p.sendMessage(prefix + "That player does not exist.");
						}
					}	
					else {
						p.sendMessage(noPerm);

					}
				}
				else {
					p.sendMessage(prefix + "There is no such command.");
				}
			}
			else if (p.hasPermission("skyblockian.admin"))
			{
				p.sendMessage(ahelpMessage);
			}
			else {
				p.sendMessage(helpMessage);
			}
		}
		return false;
	}
}
