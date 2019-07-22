package me.craigcontreras.Skyblockian.permissions.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.permissions.UserSettings;

public class PermissionsManager 
{
	private static PermissionsManager pm = new PermissionsManager();
	private PermissionsManager() {}
	
	public static PermissionsManager getPManager()
	{
		return pm;
	}
	
	private Map<UUID, PermissionAttachment> permissionsDataMap = new HashMap<>();
	
	public PermissionAttachment getPermData(Player p)
	{
		UUID uuid = p.getUniqueId();
		
		if (permissionsDataMap.containsKey(uuid))
		{
			return permissionsDataMap.get(uuid);
		}
		else {
			PermissionAttachment permissionAttachment = p.addAttachment(Skyblockian.getCore());
			permissionsDataMap.put(uuid, permissionAttachment);
			return permissionsDataMap.get(uuid);
		}
	}
	
	public List<String> getPermissions(UUID uuid)
	{
		if (UserSettings.getSettings().getFile("permissions").getString("data." + uuid.toString()) == null)
		{
			return null;
		}
		else {
			return UserSettings.getSettings().getFile("permissions").getStringList("data." + uuid.toString() + ".permissions");
		}
	}
	
	public void insertPermission(Player p, String permission)
	{
		getPermData(p).setPermission(permission, true);
		
		Skyblockian.getCore().debug(Level.INFO, "Added permission " + permission + " to " + p.getName() + " during only this runtime.");
	}
	
	public void removePermission(Player p, String permission)
	{
		getPermData(p).setPermission(permission, false);
				
		Skyblockian.getCore().debug(Level.INFO, "Removed permission " + permission + " from " + p.getName() + " during only this runtime.");
	}
	
	public void removePermissions(Player p)
	{
		List<String> permissions;
		
		permissions = getGroupPermissions(getGroup(p));
		
		for (int x = 0; x < permissions.size(); x++)
		{
			removePermission(p, permissions.get(x));
		}
				
		Skyblockian.getCore().debug(Level.INFO, "Removed permissions " + permissions + " from " + p.getName() + " during only this runtime.");
	}
	
	public void clear(Player p)
	{
		permissionsDataMap.remove(p.getUniqueId());
		
		Skyblockian.getCore().debug(Level.INFO, "Cleared permissions for " + p.getName() + " for this runtime");
	}
	
	public void setGroup(Player p, String group)
	{
		FileConfiguration fk = UserSettings.getSettings().getFile("permissions");
		fk.set("data." + p.getUniqueId().toString() + ".group", group);
		UserSettings.getSettings().save(fk);
	}
	
	public String getGroup(UUID uuid)
	{
		FileConfiguration fk = UserSettings.getSettings().getFile("permissions");
		if (fk.getString("data." + uuid.toString() + ".group") == null)
		{
			return "None";
		}
		return fk.getString("data." + uuid.toString() + ".group"); 
	}
	
	public String getGroup(Player p)
	{
		FileConfiguration fk = UserSettings.getSettings().getFile("permissions");
		if (fk.getString("data." + p.getUniqueId().toString() + ".group") == null)
		{
			return "None";
		}
		return fk.getString("data." + p.getUniqueId().toString() + ".group"); 
	}
	
	public List<String> getGroupPermissions(String group)
	{
		FileConfiguration fk = UserSettings.getSettings().getFile("groups");
		
		return fk.getStringList(group + ".permissions"); //TODO define
	}
	
	public void reload(Player p)
	{
		//user specific permissions
		{
		List<String> permissions;
		
		if (UserSettings.getSettings().getFile("permissions").getStringList("data." + p.getUniqueId() + ".permissions") != null)
		{
			permissions = UserSettings.getSettings().getFile("permissions").getStringList("data." + p.getUniqueId() + ".permissions");
		}
		else {
			FileConfiguration fc = UserSettings.getSettings().getFile("permissions");
			fc.set("data." + p.getUniqueId().toString() + ".name", p.getName());
			fc.set("data." + p.getUniqueId().toString() + ".permissions", new ArrayList<>());
			UserSettings.getSettings().save(fc);
			return;
		}
		
		for (int x = 0; x < permissions.size(); x++)
		{
			insertPermission(p, permissions.get(x));
		}
		}
		//group permissions
		{
			List<String> permissions;
			if (getGroup(p) == null || getGroup(p).equals("noGroup"))
			{
				return;
			}
			
			permissions = getGroupPermissions(getGroup(p));
			
			if (permissions == null)
			{
				return;
			}
			
			for (int x = 0; x < permissions.size(); x++)
			{
				insertPermission(p, permissions.get(x));
			}
		}
	}
	
	public String getPrefix(Player p)
	{
		FileConfiguration fk = UserSettings.getSettings().getFile("permissions");
		if (fk.getString("data." + p.getUniqueId().toString() + ".prefix") == null)
		{
			return "";
		}
		return fk.getString("data." + p.getUniqueId().toString() + ".prefix"); 
	}
	
	public String getGroupPrefix(Player p)
	{
		FileConfiguration fk = UserSettings.getSettings().getFile("groups");
		if (fk.getString(getGroup(p) + ".prefix") == null)
		{
			return "";
		}
		return fk.getString(getGroup(p) + ".prefix"); 
	}
	
	public String getSuffix(Player p)
	{
		FileConfiguration fk = UserSettings.getSettings().getFile("permissions");
		if (fk.getString("data." + p.getUniqueId().toString() + ".suffix") == null)
		{
			return "";
		}
		return fk.getString("data." + p.getUniqueId().toString() + ".suffix"); 
	}
	
	public String getGroupSuffix(Player p)
	{
		FileConfiguration fk = UserSettings.getSettings().getFile("groups");
		if (fk.getString(getGroup(p) + ".suffix") == null)
		{
			return "";
		}
		return fk.getString(getGroup(p) + ".suffix"); 
	}
	
	public void refresh(Player p)
	{
		FileConfiguration fc = UserSettings.getSettings().getFile("permissions");
		fc.set("data." + p.getUniqueId().toString() + ".name", p.getName());
		UserSettings.getSettings().save(fc);
	}
	
	public void disable()
	{
		permissionsDataMap.clear();
	}
}
