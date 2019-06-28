package me.craigcontreras.Skyblockian;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import me.craigcontreras.Skyblockian.commands.BountyCommand;
import me.craigcontreras.Skyblockian.commands.CommandManagerAdmin;
import me.craigcontreras.Skyblockian.commands.HelpCommand;
import me.craigcontreras.Skyblockian.commands.IslandCommand;
import me.craigcontreras.Skyblockian.commands.SpawnCommand;
import me.craigcontreras.Skyblockian.commands.admin.FreezeCommand;
import me.craigcontreras.Skyblockian.commands.admin.HitDelayCommand;
import me.craigcontreras.Skyblockian.commands.admin.StaffModeCommand;
import me.craigcontreras.Skyblockian.commands.admin.VanishCommand;
import me.craigcontreras.Skyblockian.commands.admin.WarpManager;
import me.craigcontreras.Skyblockian.commands.admin.YeetCommand;
import me.craigcontreras.Skyblockian.economy.CommandManager;
import me.craigcontreras.Skyblockian.economy.SettingsManager;
import me.craigcontreras.Skyblockian.economy.VaultIntegration;
import me.craigcontreras.Skyblockian.enchantments.AddEnchantment;
import me.craigcontreras.Skyblockian.enchantments.listeners.ExplosionEnchantmentListener;
import me.craigcontreras.Skyblockian.enchantments.listeners.HomingEnchantmentListener;
import me.craigcontreras.Skyblockian.enchantments.listeners.PoisonEnchantmentListener;
import me.craigcontreras.Skyblockian.island.IslandManager;
import me.craigcontreras.Skyblockian.island.IslandSelector;
import me.craigcontreras.Skyblockian.island.KitSelector;
import me.craigcontreras.Skyblockian.listeners.CriticalDamageListener;
import me.craigcontreras.Skyblockian.listeners.LimitedReachListener;
import me.craigcontreras.Skyblockian.listeners.OreGeneration;
import me.craigcontreras.Skyblockian.listeners.PerkListeners;
import me.craigcontreras.Skyblockian.listeners.PlayerAsyncChat;
import me.craigcontreras.Skyblockian.listeners.PlayerDeath;
import me.craigcontreras.Skyblockian.listeners.PlayerJoin;
import me.craigcontreras.Skyblockian.listeners.PlayerMove;
import me.craigcontreras.Skyblockian.listeners.PlayerQuit;
import me.craigcontreras.Skyblockian.listeners.PlayerRespawn;
import me.craigcontreras.Skyblockian.listeners.ScoreboardManager;
import me.craigcontreras.Skyblockian.listeners.ShopInventoryListener;
import me.craigcontreras.Skyblockian.listeners.SpawnerPlace;
import me.craigcontreras.Skyblockian.listeners.StaffModeListener;
import me.craigcontreras.Skyblockian.nicknames.NickCmd;
import me.craigcontreras.Skyblockian.permissions.PermissionListeners;
import me.craigcontreras.Skyblockian.permissions.PermissionsCommand;
import me.craigcontreras.Skyblockian.permissions.UserSettings;
import me.craigcontreras.Skyblockian.permissions.managers.PermissionsManager;
import me.craigcontreras.Skyblockian.tpa.TPAManager;
import me.craigcontreras.Skyblockian.world.SkyblockGen;
import net.milkbowl.vault.economy.Economy;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_12_R1.PlayerConnection;

public class Skyblockian 
extends JavaPlugin
{
	String worldName = "world_skyblock";
	public World world;
	public WorldEditPlugin worldEdit;
	private static Skyblockian skyBlockian;
	private static WarpManager WarpManager;
		
	public ArrayList<Player> toTeleportTo = new ArrayList<Player>();

	public File bounties = new File(this.getDataFolder() + "/bounties.yml");
	public FileConfiguration bountyConfig = YamlConfiguration.loadConfiguration(bounties);
	
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
			makeWorld();
			new IslandManager();
			new TPAManager();
			registerPermissions();
			registerCommands();
			registerListeners();
			
			HitDelayCommand.setup(false);
			
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
		
		PermissionsManager.getPManager().disable();
		UserSettings.getSettings().disable();
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
	}
	
	private void registerListeners()
	{
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerQuit(), this);
		pm.registerEvents(new PlayerMove(), this);
		pm.registerEvents(new PlayerRespawn(), this);
		pm.registerEvents(new PlayerAsyncChat(), this);
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

	public static WarpManager getWarpManager() { return WarpManager; }
		
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
}