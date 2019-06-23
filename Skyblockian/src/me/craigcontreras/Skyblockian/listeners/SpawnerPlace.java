package me.craigcontreras.Skyblockian.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class SpawnerPlace 
implements Listener, TextFormat
{
	@EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) 
	{
        Player p = e.getPlayer();
 
        if (e.getBlock().getType().equals(Material.MOB_SPAWNER) && p.getInventory().getItemInMainHand().
        		getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
        				'&', "&bZombie &fSpawner"))) 
        {
            setSpawnerZombie(e.getBlockPlaced());
 
            p.sendMessage(prefix + "You have placed/created a Zombie spawner.");
        }else if (e.getBlock().getType().equals(Material.MOB_SPAWNER) && p.getInventory().getItemInMainHand().
        		getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
        				'&', "&bSkeleton &fSpawner"))) 
        {
            setSpawnerSkeleton(e.getBlockPlaced());
 
            p.sendMessage(prefix + "You have placed/created a Skeleton spawner.");
        }else if (e.getBlock().getType().equals(Material.MOB_SPAWNER) && p.getInventory().getItemInMainHand().
        		getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
        				'&', "&bPig &fSpawner"))) 
        {
            setSpawnerPig(e.getBlockPlaced());
 
            p.sendMessage(prefix + "You have placed/created a Pig spawner.");
        }else if (e.getBlock().getType().equals(Material.MOB_SPAWNER) && p.getInventory().getItemInMainHand().
        		getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
        				'&', "&bCow &fSpawner"))) 
        {
            setSpawnerCow(e.getBlockPlaced());
 
            p.sendMessage(prefix + "You have placed/created a Cow spawner.");
        }else if (e.getBlock().getType().equals(Material.MOB_SPAWNER) && p.getInventory().getItemInMainHand().
        		getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
        				'&', "&bChicken &fSpawner"))) 
        {
            setSpawnerChicken(e.getBlockPlaced());
 
            p.sendMessage(prefix + "You have placed/created a Chicken spawner.");
        }else if (e.getBlock().getType().equals(Material.MOB_SPAWNER) && p.getInventory().getItemInMainHand().
        		getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
        				'&', "&bIron Golem &fSpawner"))) 
        {
            setSpawnerIG(e.getBlockPlaced());
 
            p.sendMessage(prefix + "You have placed/created an Iron Golem spawner.");
        }else if (e.getBlock().getType().equals(Material.MOB_SPAWNER) && p.getInventory().getItemInMainHand().
        		getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
        				'&', "&bBlaze &fSpawner"))) 
        {
            setSpawnerBlaze(e.getBlockPlaced());
 
            p.sendMessage(prefix + "You have placed/created a Blaze spawner.");
        }else if (e.getBlock().getType().equals(Material.MOB_SPAWNER) && p.getInventory().getItemInMainHand().
        		getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
        				'&', "&bCreeper &fSpawner"))) 
        {
            setSpawnerCreeper(e.getBlockPlaced());
 
            p.sendMessage(prefix + "You have placed/created a Creeper spawner.");
        }else if (e.getBlock().getType().equals(Material.MOB_SPAWNER) && p.getInventory().getItemInMainHand().
        		getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes(
        				'&', "&bSpider &fSpawner"))) 
        {
            setSpawnerSpider(e.getBlockPlaced());
 
            p.sendMessage(prefix + "You have placed/created a Spider spawner.");
        }
    }
	
    public void setSpawnerZombie(Block block) 
	{
        BlockState blockState = block.getState();
        CreatureSpawner spawner = ((CreatureSpawner)blockState);
        spawner.setSpawnedType(EntityType.ZOMBIE);
        blockState.update();
    }    
    
    public void setSpawnerSkeleton(Block block) 
	{
        BlockState blockState = block.getState();
        CreatureSpawner spawner = ((CreatureSpawner)blockState);
        spawner.setSpawnedType(EntityType.SKELETON);
        blockState.update();
    }
    
    public void setSpawnerPig(Block block) 
	{
        BlockState blockState = block.getState();
        CreatureSpawner spawner = ((CreatureSpawner)blockState);
        spawner.setSpawnedType(EntityType.PIG);
        blockState.update();
    }
    
    public void setSpawnerCow(Block block) 
	{
        BlockState blockState = block.getState();
        CreatureSpawner spawner = ((CreatureSpawner)blockState);
        spawner.setSpawnedType(EntityType.COW);
        blockState.update();
    }
    
    public void setSpawnerChicken(Block block) 
	{
        BlockState blockState = block.getState();
        CreatureSpawner spawner = ((CreatureSpawner)blockState);
        spawner.setSpawnedType(EntityType.CHICKEN);
        blockState.update();
    }
    
    public void setSpawnerIG(Block block) 
	{
        BlockState blockState = block.getState();
        CreatureSpawner spawner = ((CreatureSpawner)blockState);
        spawner.setSpawnedType(EntityType.IRON_GOLEM);
        blockState.update();
	}
    
    public void setSpawnerBlaze(Block block) 
	{
        BlockState blockState = block.getState();
        CreatureSpawner spawner = ((CreatureSpawner)blockState);
        spawner.setSpawnedType(EntityType.BLAZE);
        blockState.update();
	}
    
    public void setSpawnerCreeper(Block block) 
	{
        BlockState blockState = block.getState();
        CreatureSpawner spawner = ((CreatureSpawner)blockState);
        spawner.setSpawnedType(EntityType.CREEPER);
        blockState.update();
	}
    
    public void setSpawnerSpider(Block block) 
	{
        BlockState blockState = block.getState();
        CreatureSpawner spawner = ((CreatureSpawner)blockState);
        spawner.setSpawnedType(EntityType.SPIDER);
        blockState.update();
	}
}
