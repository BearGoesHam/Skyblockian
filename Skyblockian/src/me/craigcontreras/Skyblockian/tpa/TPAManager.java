package me.craigcontreras.Skyblockian.tpa;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class TPAManager 
implements TextFormat
{
	public static TPAManager that;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, TPA> players = new HashMap();
	
	public TPAManager()
	{
		that = this;
	}
	
	public static TPAManager getTPAMan()
	{
		return that;
	}
	
	public void acceptRequest(Player p)
	{
		if (this.players.containsKey(p.getName()))
		{
			TPA tpa = (TPA)this.players.get(p.getName());
			long timeLeft = tpa.time.longValue() / 1000L + 30L - System.currentTimeMillis() / 1000L;
			if (timeLeft <= 0L)
			{
				p.sendMessage(prefix + "Your teleportation request has expired.");
			}
			else {
				p.sendMessage(prefix + "You have accepted " + tpa.p.getName() + "'s teleportation request.");
				tpa.p.sendMessage(prefix + p.getName() + " has accepted your teleportation request.");
				tpa.p.teleport(p);
				this.players.remove(p.getName());
			}
		}
		else {
			p.sendMessage(prefix + "You don't have any teleportation request.");
		}
	}
	
	public void denyRequest(Player p)
	{
		if (this.players.containsKey(p.getName()))
		{
			TPA tpa = (TPA)this.players.get(p.getName());
			tpa.p.sendMessage(prefix + "Your teleportation request has been denied.");
			p.sendMessage(prefix + p.getName() + "'s teleportation request has been denied.");
			this.players.remove(p.getName());
		}
	}
	
	public void request(Player p, Player target)
	{
		if (this.players.containsKey(target.getName()))
		{
			TPA tpa = (TPA)this.players.get(target.getName());
			if (tpa.p.getName().equals(p.getName()))
			{
				long timeLeft = tpa.time.longValue() / 1000L + 30L - System.currentTimeMillis() / 1000L;
				if (timeLeft > 0L)
				{
					p.sendMessage(prefix + "You have already sent a teleportation request to this player.");
					return;
				}
				this.players.remove(p.getName());
			}
		}
		
		this.players.put(target.getName(), new TPA(p));
		p.sendMessage(prefix + "A teleportation request has been sent to " + target.getName() + '.');
		target.sendMessage(prefix + p.getName() + " has requested a teleport request. Use /island tpa accept to accept the request. Type /island tpa deny to deny the request. This request will expire in 42 seconds. This player will have full access to your island if you do accept it.");
	}
}
