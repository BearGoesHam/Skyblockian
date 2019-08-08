package com.skyblockian.Skyblockian.commands;

import java.util.Arrays;

import com.skyblockian.Skyblockian.commands.admin.SetSpawnCommand;
import com.skyblockian.Skyblockian.interfaces.TextFormat;
import com.skyblockian.Skyblockian.listeners.ItemManager;
import com.skyblockian.Skyblockian.Skyblockian;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.skyblockian.Skyblockian.commands.admin.SetSpawnCommand;
import com.skyblockian.Skyblockian.interfaces.TextFormat;
import com.skyblockian.Skyblockian.island.Island;
import com.skyblockian.Skyblockian.island.IslandManager;
import com.skyblockian.Skyblockian.listeners.ItemManager;
import com.skyblockian.Skyblockian.tpa.TPAManager;

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
					if (Skyblockian.getCore().randomize(1, 100) == 1)
					{
						p.sendMessage(prefix + "No snitch niggas. No bitch niggas. No twitch niggas, and no fake switch niggas.");
					}

					if (!im.hasIsland(p))
					{
						Inventory islands = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bAvailable Island Types"));
						
						islands.setItem(0, ItemManager.getIManager().createAnItem(Material.GRASS,
								ChatColor.translateAlternateColorCodes('&', "&bOriginal"), 
								Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Difficulty: &aEasy"))));
																		
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
						SetSpawnCommand.teleportToSpawn(p);
					}
					else {
						p.sendMessage(cmdError + "create");
					}
				}else if (args[0].equalsIgnoreCase("home"))
				{
					if (im.hasIsland(p))
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
					Inventory inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', "&bShop"));
					
					ItemStack spawners = new ItemStack(Material.MOB_SPAWNER);
					ItemMeta spawnermeta = spawners.getItemMeta();
					spawnermeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bSpawners"));
					spawners.setItemMeta(spawnermeta);
					
					ItemStack enchants = new ItemStack(Material.ENCHANTED_BOOK);
					ItemMeta emeta = enchants.getItemMeta();
					emeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bEnchantment Books"));
					enchants.setItemMeta(emeta);
					
					ItemStack ingotsores = new ItemStack(Material.IRON_INGOT);
					ItemMeta iometa = ingotsores.getItemMeta();
					iometa.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bIngots/Ores"));
					ingotsores.setItemMeta(iometa);
					
					ItemStack farmstuffs = new ItemStack(Material.WHEAT);
					ItemMeta fsmeta = farmstuffs.getItemMeta();
					fsmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bFarmstuffs"));
					farmstuffs.setItemMeta(fsmeta);
					
					ItemStack blocks = new ItemStack(Material.GRASS);
					ItemMeta bmeta = blocks.getItemMeta();
					bmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bBlocks"));
					blocks.setItemMeta(bmeta);
					
					ItemStack mobdrops = new ItemStack(Material.BLAZE_ROD);
					ItemMeta mdmeta = mobdrops.getItemMeta();
					mdmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bMob Drops"));
					mobdrops.setItemMeta(mdmeta);
					
					inv.setItem(0, ingotsores);
					inv.setItem(2, spawners);
					inv.setItem(4, farmstuffs);
					inv.setItem(6, blocks);
					inv.setItem(8, mobdrops);
					inv.setItem(10, enchants);
					
					p.openInventory(inv);
					p.sendMessage(prefix + "Opening shops...");
				}else if (args[0].equalsIgnoreCase("perks"))
				{
					Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bBuyable Perks"));
					
					inv.setItem(0, ItemManager.getIManager().createAnItem(Material.DIAMOND_PICKAXE, 
							ChatColor.translateAlternateColorCodes('&', "&bAuto-Smelter"), 
							Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&cEverything will be auto-smelted"),
									ChatColor.translateAlternateColorCodes('&', "&con drop from mining."),
									ChatColor.translateAlternateColorCodes('&', "&cLost when you log off."),
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

							Bukkit.broadcast(prefix + sender.getName() + " has teleported to " + target.getName() + "'s island.",
									"skyblockian.admin");
						}
						else {
							p.sendMessage(prefix + "That player does not exist.");
						}
					}	
					else {
						p.sendMessage(noPerm);

					}
				}else if (args[0].equalsIgnoreCase("help"))
				{
					if (p.hasPermission("skyblockian.admin"))
					{
						p.sendMessage(ahelpMessage);
					}
					else {
						p.sendMessage(helpMessage);
					}
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
