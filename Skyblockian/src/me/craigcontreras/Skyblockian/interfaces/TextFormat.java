package me.craigcontreras.Skyblockian.interfaces;

import org.bukkit.ChatColor;

public abstract interface TextFormat 
{
	public static final String name = ChatColor.translateAlternateColorCodes('&', "&bSkyblockian");
	public static final String textColor = ChatColor.translateAlternateColorCodes('&', "&7");
	public static final String numberColor = ChatColor.translateAlternateColorCodes('&', "&8");
	public static final String infoColor = ChatColor.translateAlternateColorCodes('&', "&a");
	public static final String linkColor = ChatColor.translateAlternateColorCodes('&', "&9");
	public static final String prefix = ChatColor.translateAlternateColorCodes('&', "&bSkyblockian: &7");
	public static final String author = ChatColor.translateAlternateColorCodes('&', "&cDeveloped by craigcontreras");
	public static final ChatColor whiteColor = ChatColor.WHITE;
	
	public static final String helpMessage = prefix + "/island <create, delete, home, tpa, shop, perks>";
	public static final String ahelpMessage = prefix + "/island <create, delete, home, tpa, shop, info, tp, perks>";
	public static final String cmdError = prefix + "Error while executing the command. Maybe try using /island ";
	public static final String argsError = prefix + "Invalid arguments.";
	public static final String noPerm = prefix + "You do not have enough permissions to perform this command.";
	public static final String playerError = prefix + "Unable to find the player ";
	public static final String successfulWarpSet = prefix + "Successfully set warp.";
	public static final String invalidWarp = prefix + "Invalid warp Name.";
	public static final String successfulWarp = prefix + "You have warped to ";
	public static final String successfulSpawnSet = prefix + "You have set the server's spawn point.";
	public static final String welcomeSpawn = prefix + "You have been teleported to spawn.";

}
