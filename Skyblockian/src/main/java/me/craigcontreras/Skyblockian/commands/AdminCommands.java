package me.craigcontreras.Skyblockian.commands;

import org.bukkit.command.CommandSender;

public abstract class AdminCommands 
{
	private String name, desc, args;
	
	public AdminCommands(String name, String desc, String args)
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
