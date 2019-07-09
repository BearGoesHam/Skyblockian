package me.craigcontreras.Skyblockian.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.craigcontreras.Skyblockian.interfaces.TextFormat;

import java.util.ArrayList;
import java.util.List;

public class ChatColorCommand implements CommandExecutor, TextFormat
{
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
	
		Inventory ColorMenu = Bukkit.createInventory(null,  18, ChatColor.translateAlternateColorCodes('&', "&bChatColor"));
		
		if(sender instanceof Player)
		{
			Player p = (Player) sender;
			if(p.hasPermission("skyblockian.chatcolor"))
            {
				p.sendMessage(prefix + "Opened Chat Color GUI.");


				//WHITE
				ItemStack WhiteChatColor = new ItemStack(Material.STAINED_GLASS_PANE);
				ItemMeta WhiteMeta = WhiteChatColor.getItemMeta();
				WhiteMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&fWhite"));
				List<String> whiteLore = new ArrayList<String>();
				whiteLore.add("Change your chatcolor to white");
				WhiteMeta.setLore(whiteLore);
				WhiteChatColor.setItemMeta(WhiteMeta);

				//LIGHT RED
				ItemStack RedChatColor = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
				ItemMeta RedMeta = RedChatColor.getItemMeta();
				List<String> redLore = new ArrayList<>();
				RedMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cLight Red"));
				redLore.add("Change your Color to Red");
				RedMeta.setLore(redLore);
				RedChatColor.setItemMeta(RedMeta);

				//YELLOW
				ItemStack YellowChatColor = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 4);
				ItemMeta YellowMeta = YellowChatColor.getItemMeta();
				List<String> YellowLore = new ArrayList<>();
				YellowMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eYellow"));
				YellowLore.add("Change your Color to Yellow");
				YellowMeta.setLore(YellowLore);
				YellowChatColor.setItemMeta(YellowMeta);

				//GOLD
				ItemStack GoldChatColor = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 1);
				ItemMeta GoldMeta = GoldChatColor.getItemMeta();
				List<String> GoldLore = new ArrayList<>();
				GoldMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Gold"));
				GoldLore.add("Change your Color to Gold");
				GoldMeta.setLore(GoldLore);
				GoldChatColor.setItemMeta(GoldMeta);

				//PURPLE
				ItemStack PurpleChatColor = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 2);
				ItemMeta PurpleMeta = PurpleChatColor.getItemMeta();
				List<String> PurpleLore = new ArrayList<>();
				PurpleMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5Magenta"));
				PurpleLore.add("Change your Color to Purple");
				PurpleMeta.setLore(PurpleLore);
				PurpleChatColor.setItemMeta(PurpleMeta);

				//DARK GREEN
				ItemStack DGreenChatColor = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 13);
				ItemMeta DGreenMeta = DGreenChatColor.getItemMeta();
				List<String> DGreenLore = new ArrayList<>();
				DGreenMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&2Dark Green"));
				DGreenLore.add("Change your Color to Dark Green");
				DGreenMeta.setLore(DGreenLore);
				DGreenChatColor.setItemMeta(DGreenMeta);

				ItemStack LGreenChatColor = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
				ItemMeta LGreenMeta = PurpleChatColor.getItemMeta();
				List<String> LGreenLore = new ArrayList<>();
				LGreenMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aGreen"));
				LGreenLore.add("Change your Color to Light Green");
				LGreenMeta.setLore(LGreenLore);
				LGreenChatColor.setItemMeta(LGreenMeta);

				//
				ItemStack BlueChatColor = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11);
				ItemMeta BlueMeta = BlueChatColor.getItemMeta();
				List<String> BlueLore = new ArrayList<>();
				BlueMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&1Blue"));
				BlueLore.add("Change your Color to Blue");
				BlueMeta.setLore(BlueLore);
				BlueChatColor.setItemMeta(BlueMeta);

				//DARK RED
				ItemStack DRedChatColor = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
				ItemMeta DRedMeta = DRedChatColor.getItemMeta();
				List<String> DRedLore = new ArrayList<>();
				DRedMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4Red"));
				DRedLore.add("Change your Color to Dark Red");
				DRedMeta.setLore(BlueLore);
				DRedChatColor.setItemMeta(DRedMeta);

				ItemStack RESET = new ItemStack(Material.PAPER);

				if(ChatColorManager.getChatColorManager().chatcolors.containsKey(p.getUniqueId()))
				{
					ItemMeta RESETMETA = RESET.getItemMeta();
					List<String> RESETLORE = new ArrayList<String>();
					RESETMETA.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fReset your ChatColor"));
					RESETLORE.add(ChatColor.translateAlternateColorCodes('&', "&7Your current ChatColor: " + ChatColorManager.getChatColorManager().getChatColorType(p) + "this"));
					RESETLORE.add(ChatColor.translateAlternateColorCodes('&', "&7Clicking this will reset your ChatColor"));
					RESETMETA.setLore(RESETLORE);
					RESET.setItemMeta(RESETMETA);
				} else
				{
					ItemMeta RESETMETA = RESET.getItemMeta();
					List<String> RESETLORE = new ArrayList<String>();
					RESETMETA.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fReset your ChatColor"));
					RESETLORE.add(ChatColor.translateAlternateColorCodes('&', "You do not currently have a ChatColor enabled."));
					RESETLORE.add(ChatColor.translateAlternateColorCodes('&', "&7Clicking this will reset your ChatColor"));
					RESETMETA.setLore(RESETLORE);
					RESET.setItemMeta(RESETMETA);
				}

                ColorMenu.setItem(0, WhiteChatColor);
                ColorMenu.setItem(1, RedChatColor);
                ColorMenu.setItem(2, YellowChatColor);
                ColorMenu.setItem(3, GoldChatColor);
                ColorMenu.setItem(4, PurpleChatColor);
                ColorMenu.setItem(5, DGreenChatColor);
                ColorMenu.setItem(6, LGreenChatColor);
                ColorMenu.setItem(7, BlueChatColor);
                ColorMenu.setItem(8, DRedChatColor);
                ColorMenu.setItem(17, RESET);
				p.openInventory(ColorMenu);
				
				
				
				
				}
		}
		
		
		return false;
	}

}
