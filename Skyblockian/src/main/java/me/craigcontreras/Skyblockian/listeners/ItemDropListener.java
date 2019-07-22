package me.craigcontreras.Skyblockian.listeners;

import me.craigcontreras.Skyblockian.Skyblockian;
import net.minecraft.server.v1_12_R1.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

public class ItemDropListener
    implements Listener
{
    @EventHandler
    public void onItemDrop(ItemSpawnEvent e)
    {
        Bukkit.getScheduler().runTaskLater(Skyblockian.getCore(), new Runnable()
        {
           public void run()
           {
               if (e.getEntity().isValid())
               {
                   e.getEntity().remove();
                   Particles particles = new Particles(EnumParticle.SMOKE_NORMAL, e.getEntity().getLocation(),
                           0, 0, 0, 2.0F, 30);
                   particles.sendToAll();;
                   e.getEntity().getLocation().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_ITEM_PICKUP, 1.0f, 1.0f);
               }
           }
        }, 120L * 20);
    }
}
