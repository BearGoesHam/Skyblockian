package com.skyblockian.Skyblockian.economy;

import org.bukkit.command.CommandSender;

public abstract class EcoCommands 
{
	private String name, desc, args;
	
	public EcoCommands(String name, String desc, String args)
	{
		this.name = name;
		this.desc = desc;
		this.args = args;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return desc;
	}
	
	public String getArgs()
	{
		return args;
	}
	
	public abstract void run(CommandSender sender, String[] args);
}
