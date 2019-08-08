package com.skyblockian.Skyblockian.commands.admin;

import com.skyblockian.Skyblockian.Skyblockian;
import com.skyblockian.Skyblockian.commands.AdminCommands;
import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemCommand
extends AdminCommands
implements TextFormat
{
	public ItemCommand()
	{
		super("i", "Give yourself an item.", "<item> <amount>");
	}

	public void run(CommandSender sender, String[] args)
	{
		Player p = (Player)sender;

		if (sender instanceof Player)
		{
			if (args.length == 0)
			{
				p.sendMessage(argsError);
				return;
			}

			if (args.length == 1)
			{
				ItemStack mat = new ItemStack(Material.valueOf(args[0].toUpperCase()));

				if (Material.getMaterial(args[0].toUpperCase()) == null)
				{
					p.sendMessage(prefix + "The material you have specified does not exist.");
					return;
				}
				else {
					p.getInventory().addItem(mat);
					p.sendMessage(prefix + "Giving 1 of " + args[0] + " to yourself.");

					Bukkit.broadcast(prefix + p.getName() + " has given themselves a(n) " + args[0] + ".",
							"skyblockian.admin");
				}
			}else if (args.length == 2)
			{
				if (Material.getMaterial(args[0].toUpperCase()) == null)
				{
					p.sendMessage(prefix + "The material you have specified does not exist.");
					return;
				}

				if (!(Skyblockian.getCore().isNumeric(args[1])))
				{
					p.sendMessage(prefix + "You must specify an integer.");
					return;
				}
				else{
					int num = Integer.parseInt(args[1]);
					ItemStack mat = new ItemStack(Material.valueOf(args[0].toUpperCase()), num);
					p.getInventory().addItem(mat);
					p.sendMessage(prefix + "Giving " + num + " of " + args[0] + " to yourself.");

					Bukkit.broadcast(prefix + p.getName() + " has given themselves " + num + "x " + args[0] + ".",
							"skyblockian.admin");
				}
			}else if (args.length >= 3)
			{
				p.sendMessage(argsError);
				return;
			}
		}
	}
}
