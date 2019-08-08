package com.skyblockian.Skyblockian.listeners;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class PerkListeners 
implements Listener
{
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getBlock();
		Material drop = Material.AIR;
		int amount = 1;
		ItemStack hand = p.getInventory().getItemInMainHand();
		
		if (p.hasPermission("skyblockian.perk.autosmelter") || p.hasPermission("skyblockian.admin"))
		{
			switch (b.getType())
			{
			case GOLD_ORE:
				drop = Material.GOLD_INGOT;
				break;
			case IRON_ORE:
				drop = Material.IRON_INGOT;
				break;
			default:
				drop = Material.AIR;
			}
			
			if (!(drop.equals(Material.AIR)) && (!(b.getDrops(hand).isEmpty())))
			{
				if (hand.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS))
				{
					Random r = new Random();
					amount = r.nextInt(hand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1) + 1;
				}
				
				b.breakNaturally(new ItemStack(Material.AIR));
				b.getWorld().dropItemNaturally(b.getLocation().add(0.5D, 0.5D, 0.5D), new ItemStack(drop, amount));			
			}
		}
	}
}