package me.craigcontreras.Skyblockian.nicknames;

import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class NickCmd 
implements CommandExecutor, TextFormat
{
	public static ArrayList<UUID> nicked = new ArrayList<UUID>();
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			GameProfile prof = ((CraftPlayer)p).getProfile();
			
			if (p.hasPermission("skyblockian.admin"))
			{
				if (nicked.contains(p.getUniqueId()))
				{
					p.sendMessage(prefix + "You're already nicknamed.");
				}
				else {
					if (args.length > 0)
					{
						String message = args[0].toString();
						int msglength = message.length();
						Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
						Matcher m = pattern.matcher(message);
						boolean b = m.find();
						
						if (message.contains(" "))
						{
							p.sendMessage(prefix + "You can't use spaces in your nickname.");
							return false;
						}else if (b)
						{
							p.sendMessage(prefix + "You can't use special characters in your nickname.");
							return false;
						}else if (msglength > 16)
						{
							p.sendMessage(prefix + "Your nickname can't be more than 16 characters in length.");
							return false;
						}
						else {
							OfflinePlayer op = Bukkit.getOfflinePlayer(message.toString());
							
							ProfileChanger.setSkin(p, prof, op.getUniqueId());
							ProfileChanger.setProfileName(p, message.toString());
							
							p.sendMessage(prefix + "You've set your skin and nickname to " + op.getName() + ".");
						}
					}
					
					if (args.length == 0)
					{
						p.sendMessage(prefix + "Error while executing the command. Maybe try using /nick <name>.");
						return false;
					}
				}
			}
			else {
				p.sendMessage(noPerm);
				return false;
			}
		}
		return true;
	}
}
