package me.craigcontreras.Skyblockian.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.craigcontreras.Skyblockian.commands.admin.SetSpawnCommand;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;

public class SpawnCommand 
implements CommandExecutor, TextFormat
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		Player p = (Player)sender;
		
		if (sender instanceof Player)
		{
			SetSpawnCommand.teleportToSpawn(p);
			p.sendMessage(welcomeSpawn);
		}
		else {
			return true;
		}
		return false;
	}
}
