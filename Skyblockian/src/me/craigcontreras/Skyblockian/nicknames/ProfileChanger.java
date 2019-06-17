package me.craigcontreras.Skyblockian.nicknames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.UUID;
import java.util.logging.Level;

import javax.net.ssl.HttpsURLConnection;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.util.UUIDTypeAdapter;

import me.craigcontreras.Skyblockian.Skyblockian;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;

public class ProfileChanger 
{
	@SuppressWarnings("deprecation")
	public static boolean setSkin(Player p, GameProfile profile, UUID uuid)
	{
		try
		{
			HttpsURLConnection connection = (HttpsURLConnection) new URL(String.format("https://sessionserver.mojang.com/session/minecraft/profile/" + getUUID(uuid) + "?unsigned=false", UUIDTypeAdapter.fromUUID(uuid))).openConnection();
			if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK)
			{	
				String reply = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
				String skin = reply.split("\"value\":\"")[1].split("\"")[0];
				String signature = reply.split("\"signature\":\"")[1].split("\"")[0];
				
				profile.getProperties().removeAll("textures");
				profile.getProperties().put("textures", new Property("textures", skin, signature));
				
				Bukkit.getScheduler().runTaskLater(Skyblockian.getCore(), new Runnable()
				{
					@Override
					public void run()
					{
						for (Player pl : Bukkit.getOnlinePlayers())
						{
							pl.hidePlayer(p);
						}
					}
				}, 1);
				
				Bukkit.getScheduler().runTaskLater(Skyblockian.getCore(), new Runnable()
				{
					@Override
					public void run()
					{
						for (Player pl : Bukkit.getOnlinePlayers())
						{
							pl.showPlayer(p);
						}
					}
				}, 20);
				
				return true;
			}
			else{
				Bukkit.getLogger().log(Level.SEVERE, "The connection to Mojang's auth servers could not be opened: " + connection.getResponseCode() + connection.getResponseMessage() + connection.getURL().toString());
				return true;
			}
		}catch (IOException e)
		{
			Bukkit.getLogger().log(Level.SEVERE, "Retrieving the skin textures did not work, maybe the sessionservers are down? ", e);
			return false;
		}
	}
	
	public static void setProfileName(Player p, String name)
	{
		for (Player pl : Bukkit.getOnlinePlayers())
		{
			((CraftPlayer)pl).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, ((CraftPlayer)p).getHandle()));
			GameProfile gp = ((CraftPlayer)p).getHandle().getProfile();
			try
			{
				Field nameField = GameProfile.class.getDeclaredField("name");
				nameField.setAccessible(true);
				
				Field modifiersField = Field.class.getDeclaredField("modifiers");
				modifiersField.setAccessible(true);
				modifiersField.setInt(nameField, nameField.getModifiers() & ~Modifier.FINAL);
				
				nameField.set(gp, name);				
			}catch (IllegalAccessException | NoSuchFieldException e)
			{
				Bukkit.getLogger().log(Level.SEVERE, "Could not change the profile name field: ", e);
			}
		}
	}
	
	public static String getUUID(UUID uuid)
	{
		return Bukkit.getOfflinePlayer(uuid).getUniqueId().toString().replace("-", "");
	}
}