package me.craigcontreras.Skyblockian.listeners;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.TileEntityChest;
import net.minecraft.server.v1_12_R1.World;

public class FishRewardListener 
implements Listener, TextFormat
{	
	@EventHandler
	public void onPlayerFish(PlayerFishEvent e)
	{
		Player p = e.getPlayer();
		Item item = (Item) e.getCaught();	
		Location loc = p.getLocation().clone().add(1.0D, 0.0D, 0.0D);
		
		Random r = new Random();
		
		if (e.getCaught() instanceof Item)
		{
			if (item.getItemStack().getType().equals(Material.RAW_FISH))
			{
				if (Skyblockian.getCore().randomize(1, 2) == 1)
				{
					ItemStack sword = new ItemStack(Material.IRON_SWORD);
					sword.addEnchantment(Enchantment.DAMAGE_ALL, 3);
					sword.addEnchantment(Enchantment.DURABILITY, 1);
					sword.addEnchantment(Enchantment.FIRE_ASPECT, 1);
					sword.addEnchantment(Enchantment.KNOCKBACK, 1);
					sword.setDurability((short) 56);
					
					ItemStack[] items = { new ItemStack(Material.DIAMOND_HELMET), 
							new ItemStack(Material.IRON_LEGGINGS), 
							new ItemStack(Material.CHAINMAIL_CHESTPLATE), 
							new ItemStack(Material.LEATHER_BOOTS),
							sword };
					
					int piece = r.nextInt(items.length);
					int amount = r.nextInt(4);
					
					ItemStack type = item.getItemStack();
					type.setType(Material.AIR);
					
					p.sendMessage(prefix + "You've caught a treasure chest!");
					item.setItemStack(type);
					
					//treasure chest spawning
					loc.getBlock().setType(Material.CHEST);
					Chest c = (Chest) loc.getBlock().getState();
					//get inventory of chest
					Inventory inv = c.getInventory();
					//items/treasure in the chest
					ItemStack item1 = new ItemStack(Material.GOLDEN_APPLE, amount);
					ItemStack item2 = new ItemStack(Material.GOLD_INGOT, 13);
					ItemStack item3 = new ItemStack(Material.IRON_INGOT, 5);
					ItemStack item4 = new ItemStack(Material.GOLD_NUGGET, 14);
					inv.addItem(item1);
					inv.addItem(item2);
					inv.addItem(item3);
					inv.addItem(items[piece]);
					inv.addItem(item4);
					//sending packets to show as if chest was opened already
					playChestAction(c, true);
				}
			}
		}
	}
	
    public void playChestAction(Chest chest, boolean open)
    {
        Location location = chest.getLocation();
        World world = ((CraftWorld) location.getWorld()).getHandle();
        BlockPosition position = new BlockPosition(location.getX(), location.getY(), location.getZ());
        TileEntityChest tileChest = (TileEntityChest) world.getTileEntity(position);
        world.playBlockAction(position, tileChest.getBlock(), 1, open ? 1 : 0);
    }
}