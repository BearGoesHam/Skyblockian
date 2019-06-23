package me.craigcontreras.Skyblockian.permissions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import me.craigcontreras.Skyblockian.permissions.managers.PermissionsManager;

public class PermissionsCommand 
implements CommandExecutor, TextFormat
{
	@SuppressWarnings("deprecation")
	public boolean  onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (!sender.hasPermission("*") && args.length != 0)
		{
			sender.sendMessage(noPerm);
			return true;
		}else if (!sender.hasPermission("*") && args.length == 0)
		{
			sender.sendMessage(prefix + "This server is running off Skyblockian Permission System, version 1.0.");
		}else if (sender.hasPermission("*") && args.length == 0)
		{
			sender.sendMessage(prefix + "This server is running off Skyblockian Permission System, version 1.0.");
			sender.sendMessage(prefix + "/permissions info <player>");
			sender.sendMessage(prefix + "/permissions add <player> <permission>");
			sender.sendMessage(prefix + "/permissions remove <player> <permission>");
			sender.sendMessage(prefix + "/permissions set <player> <group>");
			sender.sendMessage(prefix + "/permissions prefix <player> <string>");
			sender.sendMessage(prefix + "/permissions suffix <player> <string>");
			sender.sendMessage(prefix + "/permissions group add <permission> <group>");
			sender.sendMessage(prefix + "/permissions group remove <permission> <group>");
			sender.sendMessage(prefix + "/permissions group prefix <group> <string>");
			sender.sendMessage(prefix + "/permissions group suffix <group> <string>");
			sender.sendMessage(prefix + "/permissions group create <name>");
			return true;
		}
		
		if (sender.hasPermission("*") && args.length != 0)
		{
			if (args[0].equalsIgnoreCase("info"))
			{
				if (isNull(args, 1))
				{
					sender.sendMessage(prefix + "/permissions info <player>");
					return true;
				}
				
				Player target = Bukkit.getPlayer(args[1]);
				
				UUID uuid = null;
				
				if (target == null) 
				{
					OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(args[1]);
					uuid = offlineTarget.getUniqueId();
				}
				else {
					uuid = target.getUniqueId();
				}
				
				List<String> permissions = PermissionsManager.getPManager().getPermissions(uuid);
				
				if (permissions == null) 
				{
					sender.sendMessage(prefix + "This player does not have any permissions or has not played on this server.");
					return true;
				}
				
				sender.sendMessage(prefix + args[1] + "'s information:");
				sender.sendMessage(prefix + "Group: " + PermissionsManager.getPManager().getGroup(uuid));
				sender.sendMessage(prefix + "User Prefix: " + PermissionsManager.getPManager().getPrefix(target));
				sender.sendMessage(prefix + "Group Prefix: " + PermissionsManager.getPManager().getGroupPrefix(target));
				sender.sendMessage(prefix + "User Suffix: " + PermissionsManager.getPManager().getSuffix(target));
				sender.sendMessage(prefix + "Group Suffix: " + PermissionsManager.getPManager().getGroupSuffix(target));
				sender.sendMessage(prefix + "Permissions: " + convert(permissions));			
			}
			else if (args[0].equalsIgnoreCase("add"))
			{
				if (isNull(args, 1) || isNull(args, 2))
				{
					sender.sendMessage(prefix + "/permissions add <player> <permission>");
					return true;
				}
				
				Player target = Bukkit.getPlayer(args[1]);
				
				UUID uuid = null;
				
				if (target == null) 
				{
					OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(args[1]);
					uuid = offlineTarget.getUniqueId();
				}
				else {
					uuid = target.getUniqueId();
				}
				
				String permission = args[2];
				FileConfiguration fc = UserSettings.getSettings().getFile("permissions");
				List<String> permissions;

				if (fc.getStringList("data." + uuid + ".permissions") != null)
				{
					permissions = UserSettings.getSettings().getFile("permissions").getStringList("data." + uuid + ".permissions");
				}
				else {
					fc.set("data." + uuid + ".permissions", new ArrayList<>());
					permissions = new ArrayList<>();
				}
				
				permissions.add(permission);
				
				fc.set("data." + uuid + ".permissions", permissions);
				UserSettings.getSettings().save(fc);
				sender.sendMessage(prefix + "Added permission " + permission + " to " + args[1] + ".");
				
				if (target != null)
				{
					PermissionsManager.getPManager().reload(target);
				}
			}else if (args[0].equalsIgnoreCase("set"))
			{
				if (isNull(args, 1) || isNull(args, 2))
				{
					sender.sendMessage(prefix + "/permissions set <player> <group>");
					return true;
				}
				
				Player target = Bukkit.getPlayer(args[1]);
				
				PermissionsManager.getPManager().removePermissions(target);
				
				UUID uuid;
				
				if (target == null) 
				{
					OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(args[1]);
					uuid = offlineTarget.getUniqueId();
				}
				else {
					uuid = target.getUniqueId();
				}
				
				String group = args[2];
				FileConfiguration fc = UserSettings.getSettings().getFile("permissions");
							
				fc.set("data." + uuid + ".group", group);
				UserSettings.getSettings().save(fc);
				sender.sendMessage(prefix + "Set group to " + group + " for " + args[1] + ".");
				
				if (target != null)
				{
					PermissionsManager.getPManager().reload(target);
				}
			}else if (args[0].equalsIgnoreCase("remove"))
			{
				if (isNull(args, 1) || isNull(args, 2))
				{
					sender.sendMessage(prefix + "/permissions remove <player> <permission>");
					return true;
				}
				
				Player target = Bukkit.getPlayer(args[1]);
				
				UUID uuid = null;
				
				if (target == null) 
				{
					OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(args[1]);
					uuid = offlineTarget.getUniqueId();
				}
				else {
					uuid = target.getUniqueId();
				}
				
				String permission = args[2];
				FileConfiguration fc = UserSettings.getSettings().getFile("permissions");
				List<String> permissions;

				permissions = UserSettings.getSettings().getFile("permissions").getStringList("data." + uuid + ".permissions");
				
				permissions.remove(permission);
								
				fc.set("data." + uuid + ".permissions", permissions);
				UserSettings.getSettings().save(fc);
				sender.sendMessage(prefix + "Removed permission " + permission + " from " + args[1] + ".");
			
				if (target != null)
				{
					PermissionsManager.getPManager().removePermission(target, permission);
					PermissionsManager.getPManager().reload(target);
				}
			}else if (args[0].equalsIgnoreCase("prefix"))
			{
				if (isNull(args, 1) || isNull(args, 2))
				{
					sender.sendMessage(prefix + "/permissions prefix <player> <string>");
					return true;
				}
				
				Player target = Bukkit.getPlayer(args[1]);
				
				UUID uuid = null;
				
				if (target == null) 
				{
					OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(args[1]);
					uuid = offlineTarget.getUniqueId();
				}
				else {
					uuid = target.getUniqueId();
				}
				
				String uPrefix = args[2];
				
				FileConfiguration fc = UserSettings.getSettings().getFile("permissions");
				
				fc.set("data." + uuid + ".prefix", uPrefix);
				UserSettings.getSettings().save(fc);
				sender.sendMessage(prefix + "Set " + target.getName() + "'s prefix to " + uPrefix + ".");
			}else if (args[0].equalsIgnoreCase("suffix"))
			{
				if (isNull(args, 1) || isNull(args, 2))
				{
					sender.sendMessage(prefix + "/permissions suffix <player> <string>");
					return true;
				}
				
				Player target = Bukkit.getPlayer(args[1]);
				
				UUID uuid = null;
				
				if (target == null) 
				{
					OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(args[1]);
					uuid = offlineTarget.getUniqueId();
				}
				else {
					uuid = target.getUniqueId();
				}
				
				String uSuffix = args[2];
				
				FileConfiguration fc = UserSettings.getSettings().getFile("permissions");
				
				fc.set("data." + uuid + ".suffix", uSuffix);
				UserSettings.getSettings().save(fc);
				sender.sendMessage(prefix + "Set " + target.getName() + "'s suffix to " + uSuffix + ".");
			}else if (args[0].equalsIgnoreCase("group"))
			{
				if (isNull(args, 1))
				{
					sender.sendMessage(prefix + "/permissions group <arg>");
					return true;
				}
				
				if (args[1].equalsIgnoreCase("add"))
				{
					if (isNull(args, 2) || isNull(args, 3))
					{
						sender.sendMessage(prefix + "/permissions group add <permission> <group>");
						return true;
					}
										
					String permission = args[2];
					String group = args[3];
					
					if (PermissionsManager.getPManager().getGroupPermissions(group) == null)
					{
						FileConfiguration fk = UserSettings.getSettings().getFile("groups");
						fk.set(group + ".permissions", new ArrayList<>());
						UserSettings.getSettings().save(fk);
					}
					
					List<String> permissions = PermissionsManager.getPManager().getGroupPermissions(group);
					permissions.add(permission);
					
					FileConfiguration fk = UserSettings.getSettings().getFile("groups");
					fk.set(group + ".permissions", permissions);
					UserSettings.getSettings().save(fk);
					
					for (Player pl : Bukkit.getOnlinePlayers())
					{
						if (PermissionsManager.getPManager().getGroup(pl).equals(group))
						{
							PermissionsManager.getPManager().insertPermission(pl, permission);							
							PermissionsManager.getPManager().reload(pl);
						}
					}
					
					sender.sendMessage(prefix + "Added permission " + permission + " to " + group + ".");
				}else if (args[1].equalsIgnoreCase("remove"))
				{
					if (isNull(args, 2) || isNull(args, 3))
					{
						sender.sendMessage(prefix + "/permissions group remove <permission> <group>");
						return true;
					}
					
					String permission = args[2];
					String group = args[3];
								
					if (PermissionsManager.getPManager().getGroupPermissions(group) == null)
					{
						FileConfiguration fk = UserSettings.getSettings().getFile("groups");
						fk.set(group + ".permissions", new ArrayList<>());
						UserSettings.getSettings().save(fk);
					}
					
					List<String> permissions = PermissionsManager.getPManager().getGroupPermissions(group);
					permissions.remove(permission);
					
					FileConfiguration fk = UserSettings.getSettings().getFile("groups");
					fk.set(group + ".permissions", permissions);
					UserSettings.getSettings().save(fk);
										
					for (Player pl : Bukkit.getOnlinePlayers())
					{
						if (PermissionsManager.getPManager().getGroup(pl).equals(group))
						{
							PermissionsManager.getPManager().removePermission(pl, permission);
							PermissionsManager.getPManager().reload(pl);
						}
					}
					
					sender.sendMessage(prefix + "Removed permission " + permission + " from " + group + ".");
				}else if (args[1].equalsIgnoreCase("create"))			
				{
					if (isNull(args, 2))
					{
						sender.sendMessage(prefix + "/permissions group create <name>");
						return true;
					}
					
					String name = args[2];
					
					if (PermissionsManager.getPManager().getGroupPermissions(name) != null)
					{
						FileConfiguration fk = UserSettings.getSettings().getFile("groups");
						fk.set(name + ".permissions", new ArrayList<>());
						UserSettings.getSettings().save(fk);
						sender.sendMessage(prefix + "Created group " + name + ".");
					}
					else
					{
						sender.sendMessage(prefix + "That group already exists.");
						return true;
					}
				}else if (args[1].equalsIgnoreCase("prefix"))
				{
					if (isNull(args, 2) || isNull(args, 3))
					{
						sender.sendMessage(prefix + "/permissions group prefix <group> <string>");
						return true;
					}
					
					String group = args[2];
					String gPrefix = args[3];
					
					FileConfiguration fk = UserSettings.getSettings().getFile("groups");
					fk.set(group + ".prefix", gPrefix);
					UserSettings.getSettings().save(fk);
					
					sender.sendMessage(prefix + "Set " + group + "'s prefix to " + gPrefix + ".");
				}else if (args[1].equalsIgnoreCase("suffix"))
				{
					if (isNull(args, 2) || isNull(args, 3))
					{
						sender.sendMessage(prefix + "/permissions suffix <group> <string>");
						return true;
					}
					
					String group = args[2];
					String gSuffix = args[3];
					
					FileConfiguration fc = UserSettings.getSettings().getFile("groups");
					
					fc.set(group + ".suffix", gSuffix);
					UserSettings.getSettings().save(fc);
					sender.sendMessage(prefix + "Set " + group + "'s suffix to " + gSuffix + ".");
				}
			}
			else {
				sender.sendMessage(prefix + "There is no such command.");
			}
		}
		
		return false;
	}
	
	@SuppressWarnings("unused")
	public boolean isNull(String[] args, int index)
	{
		try
		{
			String temp = args[index];
			return false;
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return true;
		}
	}
	
	public String convert(List<String> list)
	{
		String toReturn = "";
		for (int i = 0; i < list.size(); i++)
		{
			if (toReturn.equals(""))
			{
				toReturn = toReturn + list.get(i);
			}
			else {
				toReturn = toReturn + ", " + list.get(i);
			}
		}
		
		return toReturn;
	}
}
