package me.craigcontreras.Skyblockian.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import me.craigcontreras.Skyblockian.economy.SettingsManager;
import me.craigcontreras.Skyblockian.permissions.managers.PermissionsManager;

public class ScoreboardManager 
{
	private static ScoreboardManager scoreboardman = new ScoreboardManager();
	private ScoreboardManager() {}
	
	public static ScoreboardManager getScoreMan()
	{
		return scoreboardman;
	}
	
	public void setupScoreboard(Player p)
	{
		Scoreboard b = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = b.registerNewObjective("scoreboard", "dummy");
		
		obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bSky&fblockian      "));
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		p.setScoreboard(b);
		updateScoreboard(p);
	}
	
	public void updateScoreboard(Player p)
	{	
		Scoreboard b = p.getScoreboard();
		Objective obj = b.getObjective("scoreboard");
		
		Score underline = obj.getScore(ChatColor.translateAlternateColorCodes('&', "&7&l&m------------"));
		underline.setScore(10);

		Score name = obj.getScore(ChatColor.translateAlternateColorCodes('&', "&fName: &b" + p.getName()));
		name.setScore(9);
		
		Score money = obj.getScore(ChatColor.translateAlternateColorCodes('&', "&fMoney: &b$" + SettingsManager.getEcoManager().getBalance(p.getName())));
		money.setScore(8);
		
		Score group = obj.getScore(ChatColor.translateAlternateColorCodes('&', "&fGroup: &b" + PermissionsManager.getPManager().getGroup(p).toString()));
		group.setScore(7);
	}
}
