package com.skyblockian.Skyblockian;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.skyblockian.Skyblockian.commands.*;
import com.skyblockian.Skyblockian.commands.admin.*;
import com.skyblockian.Skyblockian.economy.CommandManager;
import com.skyblockian.Skyblockian.economy.SettingsManager;
import com.skyblockian.Skyblockian.economy.VaultIntegration;
import com.skyblockian.Skyblockian.enchantments.AddEnchantment;
import com.skyblockian.Skyblockian.enchantments.AnvilListener;
import com.skyblockian.Skyblockian.interfaces.TextFormat;
import com.skyblockian.Skyblockian.island.IslandManager;
import com.skyblockian.Skyblockian.island.IslandSelector;
import com.skyblockian.Skyblockian.island.KitSelector;
import com.skyblockian.Skyblockian.listeners.*;
import com.skyblockian.Skyblockian.nicknames.NickCmd;
import com.skyblockian.Skyblockian.permissions.PermissionListeners;
import com.skyblockian.Skyblockian.permissions.PermissionsCommand;
import com.skyblockian.Skyblockian.permissions.UserSettings;
import com.skyblockian.Skyblockian.permissions.managers.PermissionsManager;
import com.skyblockian.Skyblockian.tpa.TPAManager;
import com.skyblockian.Skyblockian.world.SkyblockGen;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import com.skyblockian.Skyblockian.enchantments.listeners.ExplosionEnchantmentListener;
import com.skyblockian.Skyblockian.enchantments.listeners.HomingEnchantmentListener;
import com.skyblockian.Skyblockian.enchantments.listeners.LifestealEnchantmentListener;
import com.skyblockian.Skyblockian.enchantments.listeners.PoisonEnchantmentListener;
import com.skyblockian.Skyblockian.enchantments.listeners.WitheringEnchantmentListener;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.economy.Economy;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_12_R1.PlayerConnection;

public class Skyblockian
		extends JavaPlugin
		implements TextFormat
{
	String worldName = "world_skyblock";
	public World world;
	public WorldEditPlugin worldEdit;
	private static Skyblockian skyBlockian;
	public LevelUpManager levelUpManager;
	public MinerManager minerManager;

	public ArrayList<Player> toTeleportTo = new ArrayList<Player>();
	public List<String> onlinePlayers = new ArrayList<String>();

	public File bounties = new File(this.getDataFolder() + "bounties.yml");
	public FileConfiguration bountyConfig = YamlConfiguration.loadConfiguration(bounties);

	public File tagFile = new File(this.getDataFolder(), "tagdata.yml");
	public FileConfiguration tagConfig = YamlConfiguration.loadConfiguration(tagFile);

	public File colorData = new File(this.getDataFolder(), "colordata.yml");
	public FileConfiguration colorConfig = YamlConfiguration.loadConfiguration(colorData);

	public File warpFile = new File(this.getDataFolder(), "warps.yml");
	public FileConfiguration warpConfig = new YamlConfiguration().loadConfiguration(warpFile);

	public File lockersFile = new File(this.getDataFolder(), "lockers.yml");
	public FileConfiguration lockersConfig = new YamlConfiguration().loadConfiguration(lockersFile);

	public File codesFile = new File(this.getDataFolder(), "codes.yml");
	public FileConfiguration codesConfig = new YamlConfiguration().loadConfiguration(codesFile);

	public File minersFile = new File(this.getDataFolder(), "miners.yml");
	public FileConfiguration minersConfig = new YamlConfiguration().loadConfiguration(minersFile);

	public List<String> blacklisted_tags = new ArrayList<String>();

	public FileConfiguration getBountyConfig()
	{
		return bountyConfig;
	}

	public void onEnable()
	{
		skyBlockian = this;
		this.worldEdit = ((WorldEditPlugin)Bukkit.getServer().getPluginManager().getPlugin("WorldEdit"));
		if (this.worldEdit == null)
		{
			sendMessage("You must have WorldEdit installed on your server.");
		}
		else {
			SignChangeListener scl = new SignChangeListener();

			scl.setup();
			makeWorld();
			new IslandManager();
			new TPAManager();
			new MessageManager();
			levelUpManager = new LevelUpManager();
			minerManager = new MinerManager();
			registerPermissions();
			registerCommands();
			registerListeners();
			autoBroadcast();
			initiateCrafting();

			HitDelayCommand.setup(false);

			this.minerManager.registerMiners();

			try{
				this.minerManager.createFile();
			}catch (IOException ex)
			{
				ex.printStackTrace();
			}

			new MinerRunnable().runTaskTimer(this, 0L, this.minersConfig.getLong("DELAY"));

			//economy
			SettingsManager.getEcoManager().setup(this);

			if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null)
			{
				Bukkit.getServer().getServicesManager().register(Economy.class, new VaultIntegration(), this, ServicePriority.Highest);
			}

			//permissions
			debug(Level.INFO, "debug-mode is enabled in the config.yml, debug messages will appear in console");

			this.getConfig().options().copyDefaults();
			this.saveConfig();

			for (Player pl : Bukkit.getOnlinePlayers())
			{
				PermissionsManager.getPManager().reload(pl);
				sendTabHF(pl,
						ChatColor.translateAlternateColorCodes('&', "&bSky&fblockian"),
						ChatColor.translateAlternateColorCodes('&', "&bIP: &fus.skyblockian.com"));
			}

	        new BukkitRunnable()
	        {
	            public void run()
	            {
	                for (Player pl : Bukkit.getOnlinePlayers())
	                {
	                    ScoreboardManager.getScoreMan().setupScoreboard(pl);
	                    toTeleportTo.add(pl);
	                }
	            }
	        }.runTaskTimer(this, 20, 20);
	    }
	}

	public void onDisable()
	{
		IslandManager.getIM().onDisable();

		for (Player pl : Bukkit.getOnlinePlayers())
		{
			PermissionsManager.getPManager().clear(pl);
		}

		VanishCommand.vanish.clear();
		toTeleportTo.clear();

		StaffModeCommand.staffmode.clear();
		FreezeCommand.frozen.clear();
		MessageManager.conversations.clear();
		GeneratorListener.clear();

		PermissionsManager.getPManager().disable();
		UserSettings.getSettings().disable();

		if (MuteChatCommand.mute)
		{
			MuteChatCommand.mute = false;
		}
	}

	private void registerPermissions()
	{
		PluginManager pm = Bukkit.getPluginManager();
		Permission p = new Permission("skyblockian.admin");
		p.setDefault(PermissionDefault.OP);
		pm.addPermission(p);
	}

	private void registerCommands()
	{
		//economy executor
		CommandManager cm = new CommandManager();
		CommandManagerAdmin cma = new CommandManagerAdmin();

		getCommand("island").setExecutor(new IslandCommand());
		getCommand("nick").setExecutor(new NickCmd());
		getCommand("help").setExecutor(new HelpCommand());
		getCommand("permissions").setExecutor(new PermissionsCommand());
		getCommand("eco").setExecutor(cm);
		getCommand("admin").setExecutor(cma);
		getCommand("yeet").setExecutor(new YeetCommand());
		getCommand("spawn").setExecutor(new SpawnCommand());
		getCommand("bounty").setExecutor(new BountyCommand());
		getCommand("message").setExecutor(new MessageCommand());
		getCommand("reply").setExecutor(new MessageCommand());
		getCommand("tag").setExecutor(new TagCommand());
		getCommand("online").setExecutor(new OnlineCommand());
		getCommand("chatcolor").setExecutor(new ChatColorCommand());
		getCommand("warp").setExecutor(new WarpCommand());
		getCommand("rules").setExecutor(new RulesCommand());
		getCommand("media").setExecutor(new MediaCommand());
		getCommand("ping").setExecutor(new PingCommand());
		getCommand("whatsnew").setExecutor(new WhatsNewCommand());
		getCommand("warps").setExecutor(new WarpsCommand());
		getCommand("upgrades").setExecutor(new UpgradesCommand());
	}

	private void registerListeners()
	{
		new PingResponseListener().addPingResponsePacketListener();
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerQuit(), this);
		pm.registerEvents(new PlayerMove(), this);
		pm.registerEvents(new PlayerRespawn(), this);
		pm.registerEvents(new PlayerAsyncChat(), this);
		pm.registerEvents(new PlayerLogin(), this);
		pm.registerEvents(new IslandSelector(), this);
		pm.registerEvents(new OreGeneration(), this);
		pm.registerEvents(new KitSelector(), this);
		pm.registerEvents(new PermissionListeners(), this);
		pm.registerEvents(new ShopInventoryListener(), this);
		pm.registerEvents(new PerkListeners(), this);
		pm.registerEvents(new PlayerDeath(), this);
		pm.registerEvents(new SpawnerPlace(), this);
		pm.registerEvents(new StaffModeListener(), this);
		pm.registerEvents(new AddEnchantment(), this);
		pm.registerEvents(new ExplosionEnchantmentListener(), this);
		pm.registerEvents(new HomingEnchantmentListener(), this);
		pm.registerEvents(new LimitedReachListener(), this);
		pm.registerEvents(new CriticalDamageListener(), this);
		pm.registerEvents(new PoisonEnchantmentListener(), this);
		pm.registerEvents(new WitheringEnchantmentListener(), this);
		pm.registerEvents(new LifestealEnchantmentListener(), this);
		pm.registerEvents(new AnvilListener(), this);
		pm.registerEvents(new CombatLogListener(), this);
		pm.registerEvents(new GeneratorListener(), this);
		pm.registerEvents(new FishRewardListener(), this);
		pm.registerEvents(new ChatColorListener(), this);
		pm.registerEvents(new PreprocessCommandListener(), this);
		pm.registerEvents(new WarpsCommand(), this);
		pm.registerEvents(new PlayerPortal(), this);
		pm.registerEvents(new JetpackListener(), this);
		pm.registerEvents(new SignChangeListener(), this);
		pm.registerEvents(new ItemDropListener(), this);
		pm.registerEvents(new PreventArrowListener(), this);
		pm.registerEvents(new AuthenticationListener(), this);
		pm.registerEvents(new LevelUpListener(), this);
		pm.registerEvents(new UpgradesMenuListener(), this);
		pm.registerEvents(new MinerListener(), this);
//		pm.registerEvents(new LockListener(), this);
	}

	private void makeWorld()
	{
		if (Bukkit.getWorld(this.worldName) == null)
		{
			sendMessage("Generating Skyblock world");
			WorldCreator wc = new WorldCreator(this.worldName);
			wc.generateStructures(false);
			wc.generator(new SkyblockGen());
			Bukkit.getServer().createWorld(wc);
		}

		sendMessage("World has been loaded");
		this.world = Bukkit.getWorld(this.worldName);
		this.world.setDifficulty(Difficulty.EASY);
	}


	public static Skyblockian getCore()
	{
		return skyBlockian;
	}

	public void sendMessage(String message)
	{
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bSkyblockian: &7" + message));
	}

	public void debug(Level level, String message)
	{
		if (UserSettings.getSettings().isDebugEnabled())
		{
			getLogger().log(level, message);
		}
	}

	public void sendTabHF(Player player, String header, String footer)
	{
	    CraftPlayer craftplayer = (CraftPlayer) player;
	    PlayerConnection connection = craftplayer.getHandle().playerConnection;
	    IChatBaseComponent headerJSON = ChatSerializer.a("{\"text\": \"" + header +"\"}");
	    IChatBaseComponent footerJSON = ChatSerializer.a("{\"text\": \"" + footer +"\"}");
	    PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

	    try
	    {
	        Field headerField = packet.getClass().getDeclaredField("a");
	        headerField.setAccessible(true);
	        headerField.set(packet, headerJSON);
	        headerField.setAccessible(!headerField.isAccessible());

	        Field footerField = packet.getClass().getDeclaredField("b");
	        footerField.setAccessible(true);
	        footerField.set(packet, footerJSON);
	        footerField.setAccessible(!footerField.isAccessible());
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }

	    connection.sendPacket(packet);
	}

	@SuppressWarnings("unused")
	public boolean isNumeric(String string)
	{
		try
		{
			Double d;
			d = Double.valueOf(Double.parseDouble(string));
	    }
		catch (NumberFormatException nfe)
	    {
			Double d;
			return false;
	    }
	    return true;
	}

	public void saveYml(File file, FileConfiguration configuration)
	{
		try
		{
			configuration.save(file);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public int randomize(int lower, int upper)
	{
		Random r = new Random();
		return r.nextInt(upper - lower + 1) + lower;
	}

	@SuppressWarnings("deprecation")
	private void initiateCrafting()
	{
		ItemStack iron = new ItemStack(Material.IRON_BLOCK);
	    ItemMeta imeta = iron.getItemMeta();
	    imeta.setDisplayName(org.bukkit.ChatColor.translateAlternateColorCodes('&', "&bIron &fGenerator"));
	    iron.setItemMeta(imeta);

	    ShapedRecipe irecipe = new ShapedRecipe(iron);
	    irecipe.shape(
	      "!#!", 
	      "$@$", 
	      "!#!");
	    irecipe.setIngredient('!', Material.IRON_INGOT);
	    irecipe.setIngredient('$', Material.GOLDEN_APPLE);
	    irecipe.setIngredient('#', Material.REDSTONE_BLOCK);
	    irecipe.setIngredient('@', Material.CHEST);
	    
	    ItemStack gold = new ItemStack(Material.GOLD_BLOCK);
	    ItemMeta gmeta = gold.getItemMeta();
	    gmeta.setDisplayName(org.bukkit.ChatColor.translateAlternateColorCodes('&', "&bGold &fGenerator"));
	    gold.setItemMeta(gmeta);
	    
	    ShapedRecipe grecipe = new ShapedRecipe(gold);
	    grecipe.shape(
	      "!#!", 
	      "$@$", 
	      "!#!");
	    grecipe.setIngredient('!', Material.GOLD_INGOT);
	    grecipe.setIngredient('$', Material.GOLDEN_APPLE);
	    grecipe.setIngredient('#', Material.REDSTONE_BLOCK);
	    grecipe.setIngredient('@', Material.CHEST);
	    
	    ItemStack diamond = new ItemStack(Material.DIAMOND_BLOCK);
	    ItemMeta dmeta = diamond.getItemMeta();
	    dmeta.setDisplayName(org.bukkit.ChatColor.translateAlternateColorCodes('&', "&bDiamond &fGenerator"));
	    diamond.setItemMeta(dmeta);
	    
	    ShapedRecipe drecipe = new ShapedRecipe(diamond);
	    drecipe.shape(
	      "!#!", 
	      "$@$", 
	      "!#!");
	    drecipe.setIngredient('!', Material.DIAMOND);
	    drecipe.setIngredient('$', Material.GOLDEN_APPLE);
	    drecipe.setIngredient('#', Material.REDSTONE_BLOCK);
	    drecipe.setIngredient('@', Material.CHEST);
	    
	    Bukkit.addRecipe(irecipe);
	    Bukkit.addRecipe(grecipe);
	    Bukkit.addRecipe(drecipe);
	}
	  
	public void autoBroadcast()
	{
		new BukkitRunnable()
		{
			public void run()
			{
				for(Player players : Bukkit.getServer().getOnlinePlayers())
				{
					TextComponent twitter1 = new TextComponent("Skyblockian twitter: ");
					TextComponent twitter2 = new TextComponent("click here");

					twitter1.setColor(net.md_5.bungee.api.ChatColor.AQUA);
					twitter2.setColor(net.md_5.bungee.api.ChatColor.AQUA);
					twitter2.setBold(true);

					twitter2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://twitter.com/skyblockian"));
					twitter2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("twitter.com/skyblockian").create()));

					twitter1.addExtra(twitter2);

					TextComponent youtube1 = new TextComponent("Our YouTube channel: ");
					TextComponent youtube2 = new TextComponent("click here");

					youtube1.setColor(net.md_5.bungee.api.ChatColor.RED);
					youtube2.setColor(net.md_5.bungee.api.ChatColor.WHITE);
					youtube2.setBold(true);
					youtube2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("youtube.com/skyblockian").create()));
					youtube2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.youtube.com/channel/UCdKH-rc9OjS1PywL-1Pb0hQ/featured"));
					youtube1.addExtra(youtube2);

					TextComponent discord1 = new TextComponent("Join our public Discord: ");
					TextComponent discord2 = new TextComponent("here");
					discord1.setColor(net.md_5.bungee.api.ChatColor.DARK_AQUA);
					discord2.setColor(net.md_5.bungee.api.ChatColor.DARK_AQUA);
					discord2.setBold(true);
					discord2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("https://discord.gg/gU96pYr").create()));
					discord2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/gU96pYr"));
					discord1.addExtra(discord2);

					players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&l&m---------------------------------------------"));
					players.spigot().sendMessage(twitter1);
					players.spigot().sendMessage(youtube1);
					players.spigot().sendMessage(discord1);
					players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&l&m---------------------------------------------"));
				}
			}
		}.runTaskTimer(this, 0L, 6000L);
	}

	public boolean ifInRegion(Player p)
	{
		for (String dregion : Skyblockian.getCore().getConfig().getStringList("disabledregions"))
		{
			for (final ProtectedRegion reg : WGBukkit.getRegionManager(p.getWorld())
					.getApplicableRegions(p.getLocation()))
			{
				if (reg.getId().equalsIgnoreCase(dregion))
				{
					return false;
				}
			}
		}

		return true;
	}
}