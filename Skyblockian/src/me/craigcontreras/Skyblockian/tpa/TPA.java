package me.craigcontreras.Skyblockian.tpa;

import org.bukkit.entity.Player;

public class TPA 
{
	public Player p;
	public Long time;
	
	public TPA(Player p)
	{
		this.p = p;
		this.time = Long.valueOf(System.currentTimeMillis());
	}
}
