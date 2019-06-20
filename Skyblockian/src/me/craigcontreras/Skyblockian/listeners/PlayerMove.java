package me.craigcontreras.Skyblockian.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.commands.admin.FreezeCommand;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import me.craigcontreras.Skyblockian.island.Island;
import me.craigcontreras.Skyblockian.island.Island2;
import me.craigcontreras.Skyblockian.island.IslandManager;

public class PlayerMove 
implements Listener, TextFormat
{
	@EventHandler
	public void onMove(PlayerMoveEvent e)
	{
		Player p = e.getPlayer();
		if (p.getWorld().getName().equals(
				Skyblockian.getCore().world.getName()))
		{
			Island i = IslandManager.getIM().getIsland(p);
			Island2 i2 = IslandManager.getIM().getIsland2(p);
			
			if ((i != null)
					&& (i.isAt(e.getFrom())) && 
					(!i.isAt(e.getTo())))
			{
				e.setCancelled(true);
				p.sendMessage(prefix + "You may not leave the bounds of your island.");
			}else if ((i2 != null)
					&& (i2.isAt(e.getFrom())) && 
					(!i2.isAt(e.getTo())))
			{
				e.setCancelled(true);
				p.sendMessage(prefix + "You may not leave the bounds of your island.");
			}
		}
		
		if (FreezeCommand.frozen.contains(p.getUniqueId()))
		{
			e.setCancelled(true);
			p.sendMessage(prefix + "You have been frozen.");
			p.sendMessage(prefix + "To be unfrozen, please go on the Discord (https://discord.gg/7aGRssC) and enter a voice chat.");
			p.sendMessage(prefix + "You'll be moved into another lobby to speak to the staff member that has froze you.");
			p.sendMessage(prefix + "You'll most likely be screenshared.");
			p.sendMessage(prefix + "If you log out now, you'll be automatically banned.");
			p.sendMessage(prefix + "If you do not follow set instructions, you'll be banned.");
		}
		else {
			return;
		}
	}
}
