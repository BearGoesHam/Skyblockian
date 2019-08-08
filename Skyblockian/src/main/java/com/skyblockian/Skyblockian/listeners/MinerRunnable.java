package com.skyblockian.Skyblockian.listeners;

import com.skyblockian.Skyblockian.Skyblockian;
import com.skyblockian.Skyblockian.Skyblockian;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.Iterator;

public class MinerRunnable
    extends BukkitRunnable
{
    public void run()
    {
        Iterator i = Skyblockian.getCore().minerManager.getMiners().iterator();

        while (i.hasNext())
        {
            Miner miners = (Miner) i.next();

            if (miners.getEnabled())
            {
                Block b = miners.getLocation().getBlock();
                b.setData((byte) (b.getData() & -9));
                Block front = b.getRelative(getFace(b));
                Block behind = b.getRelative(getFace(b).getOppositeFace());

                if (!(Skyblockian.getCore().minersConfig.getStringList("UNHARVESTABLE_BLOCKS").contains(front.getType().toString().toUpperCase())))
                {
                    if (b.getState().getType() != Material.DISPENSER)
                    {
                        Skyblockian.getCore().minersConfig.set(Skyblockian.getCore().minerManager.removeMiner(miners), false);

                        try
                        {
                            Skyblockian.getCore().minersConfig.save(Skyblockian.getCore().minersFile);
                        }catch (IOException ex)
                        {
                            ex.printStackTrace();
                        }
                    }

                    if (behind.getState().getType().equals(Material.CHEST))
                    {
                        Chest c = (Chest) behind.getState();

                        if (c.getInventory().firstEmpty() == -1)
                        {
                            miners.getLocation().getWorld().dropItemNaturally(miners.getLocation(), new ItemStack(front.getType(), 1, front.getData()));
                        }
                        else{
                            c.getInventory().addItem(new ItemStack[] { new ItemStack(front.getType(), 1, front.getData()) });
                        }

                        front.setType(Material.AIR);
                    }
                    else{
                        miners.getLocation().getWorld().dropItemNaturally(miners.getLocation(), new ItemStack(front.getType(), 1, front.getData()));
                        front.setType(Material.AIR);
                    }
                }
            }
        }
    }

    public BlockFace getFace(Block b)
    {
        if (b.getData() == 0)
        {
            return BlockFace.DOWN;
        }else if (b.getData() == 1)
        {
            return BlockFace.UP;
        }else if (b.getData() == 2)
        {
            return BlockFace.NORTH;
        }else if (b.getData() == 3)
        {
            return BlockFace.SOUTH;
        }else if (b.getData() == 4)
        {
            return BlockFace.WEST;
        }else if (b.getData() == 5)
        {
            return BlockFace.EAST;
        }

        return BlockFace.SELF;
    }
}
