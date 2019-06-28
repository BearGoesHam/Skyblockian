package me.craigcontreras.Skyblockian.enchantments.listeners;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class WitheringEnchantmentListener
implements Listener, TextFormat
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e)
	{
		Player k = (Player) e.getDamager();
		Player p = (Player) e.getEntity();
		
		ItemStack item = p.getItemInHand();
		
		if (k.getLocation().getWorld().equals(Skyblockian.getCore().world)) return;
		
		if (!(e.getEntity() instanceof Player)) return;
		
		if (item.hasItemMeta())
		{
			if (item.getItemMeta().hasLore()) 
			{
				for (int i = 0; i < item.getItemMeta().getLore().size(); i++) 
				{
					String[] fLore = ChatColor.stripColor(item.getItemMeta().getLore().get(i)).split(" ");
					String eLore = fLore[0];
			            
					if (eLore.contains("Withering")) 
					{
						if (Skyblockian.getCore().randomize(1, 100) == 1)
						{
							Random r = new Random();
							int time = r.nextInt(30) * 20;
							p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, time, 1));
						}
					}
			    }
			}			
		}
	}
}
