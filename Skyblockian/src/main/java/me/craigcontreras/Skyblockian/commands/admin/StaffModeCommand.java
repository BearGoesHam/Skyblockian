package me.craigcontreras.Skyblockian.commands.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class StaffModeCommand 
extends AdminCommands
implements TextFormat
{
	public static List<UUID> staffmode = new ArrayList<UUID>();
	
	public static boolean playerInStaffMode(Player p)
	{
		return staffmode.contains(p.getUniqueId());
	}
	
	public StaffModeCommand()
	{
		super("staff", "Enable/disable staff mode.", "");
	}
	
	public void run(CommandSender sender, String args[])
	{
		Player p = (Player)sender;
		
		if (sender instanceof Player)
		{
			if (!(playerInStaffMode(p)))
			{
				ItemStack rtp = new ItemStack(Material.WATCH);
				ItemMeta rtpmeta = rtp.getItemMeta();
				rtpmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bRandom Teleport"));
				rtp.setItemMeta(rtpmeta);
				
				ItemStack chest = new ItemStack(Material.CHEST);
				ItemMeta cmeta = chest.getItemMeta();
				cmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bOpen Chest Silently"));
				chest.setItemMeta(cmeta);
				
				ItemStack vanish = new ItemStack(Material.INK_SACK);
				ItemMeta vmeta = vanish.getItemMeta();
				vmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bVanish yourself"));
				vanish.setItemMeta(vmeta);
				
				ItemStack freeze = new ItemStack(Material.ICE);
				ItemMeta fmeta = freeze.getItemMeta();
				fmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bFreeze a Player"));
				freeze.setItemMeta(fmeta);
				
				ItemStack book = new ItemStack(Material.BOOK);
				ItemMeta bmeta = book.getItemMeta();
				bmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bInformation about a Player"));
				book.setItemMeta(bmeta);
				
				p.getInventory().setItem(0, rtp);
				p.getInventory().setItem(2, chest);
				p.getInventory().setItem(4, vanish);
				p.getInventory().setItem(6, freeze);
				p.getInventory().setItem(8, book);
				
				p.setGameMode(GameMode.CREATIVE);
				p.setAllowFlight(true);
				p.sendMessage(prefix + "You're now in staff mode.");
				staffmode.add(p.getUniqueId());

				Bukkit.broadcast(prefix + p.getName() + " has entered staff mode.", "skyblockian.admin");
			}
			else {
				p.sendMessage(prefix + "You're no longer in staff mode.");
				p.setGameMode(GameMode.SURVIVAL);
				p.setAllowFlight(false);
				p.getInventory().clear();
				staffmode.remove(p.getUniqueId());

				Bukkit.broadcast(prefix + p.getName() + " is no longer in staff mode.", "skyblockian.admin");
				SetSpawnCommand.teleportToSpawn(p);
			}
		}
		else {
			return;
		}
	}
}
