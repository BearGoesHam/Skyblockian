package com.skyblockian.Skyblockian.economy;

import java.util.List;

import org.bukkit.OfflinePlayer;

import com.skyblockian.Skyblockian.Skyblockian;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

public class VaultIntegration 
implements Economy
{	
	@Override
	public EconomyResponse bankBalance(String arg0) 
	{
		return null;
	}

	@Override
	public EconomyResponse bankDeposit(String arg0, double arg1) 
	{
		return null;
	}

	@Override
	public EconomyResponse bankHas(String arg0, double arg1) 
	{
		return null;
	}

	@Override
	public EconomyResponse bankWithdraw(String arg0, double arg1) 
	{
		return null;
	}

	@Override
	public EconomyResponse createBank(String arg0, String arg1) 
	{
		return null;
	}

	@Override
	public EconomyResponse createBank(String arg0, OfflinePlayer arg1) 
	{
		return null;
	}

	@Override
	public boolean createPlayerAccount(String name) 
	{
		SettingsManager.getEcoManager().setBalance(name, 0);
		return true;
	}

	@Override
	public boolean createPlayerAccount(String name, String world) 
	{
		return createPlayerAccount(name);
	}

	@Override
	public String currencyNamePlural() 
	{
		return "dollars";
	}

	@Override
	public String currencyNameSingular() 
	{
		return "dollar";
	}

	@Override
	public EconomyResponse deleteBank(String arg0) 
	{
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(String name, double amount) 
	{
		SettingsManager.getEcoManager().addBalance(name, amount);
		return new EconomyResponse(amount, SettingsManager.getEcoManager().getBalance(name), ResponseType.SUCCESS, "");
	}

	@Override
	public EconomyResponse depositPlayer(String name, String world, double amount) 
	{
		return depositPlayer(name, amount);
	}

	@Override
	public String format(double amount) 
	{
		return "$" + String.valueOf(amount);
	}

	@Override
	public int fractionalDigits() 
	{
		return 2;
	}

	@Override
	public double getBalance(String name) 
	{
		return SettingsManager.getEcoManager().getBalance(name);
	}

	@Override
	public double getBalance(String name, String world) 
	{
		return getBalance(name);
	}

	@Override
	public List<String> getBanks() 
	{
		return null;
	}

	@Override
	public String getName() 
	{
		return "Skyblockian";
	}

	@Override
	public boolean has(String player, double amount) 
	{
		return SettingsManager.getEcoManager().getBalance(player) >= amount;
	}

	@Override
	public boolean has(String player, String world, double amount) 
	{
		return has(player, amount);
	}

	@Override
	public boolean hasAccount(String arg0) 
	{
		return false;
	}

	@Override
	public boolean hasAccount(String arg0, String arg1) 
	{
		return false;
	}

	@Override
	public boolean hasBankSupport() 
	{
		return false;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, String arg1) 
	{
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, String arg1) 
	{
		return null;
	}

	@Override
	public boolean isEnabled() 
	{
		return Skyblockian.getCore().isEnabled();
	}

	@Override
	public EconomyResponse withdrawPlayer(String player, double amount) 
	{
		return new EconomyResponse(amount, SettingsManager.getEcoManager().getBalance(player) - amount, SettingsManager.getEcoManager().removeBalance(player, amount) ? ResponseType.SUCCESS : ResponseType.FAILURE, "Insufficient funds.");
	}

	@Override
	public EconomyResponse withdrawPlayer(String player, String world, double amount) 
	{
		return withdrawPlayer(player, amount);
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getBalance(OfflinePlayer arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBalance(OfflinePlayer arg0, String arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean has(OfflinePlayer arg0, double arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean has(OfflinePlayer arg0, String arg1, double arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		// TODO Auto-generated method stub
		return null;
	}
}
