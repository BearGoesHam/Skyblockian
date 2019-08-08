package com.skyblockian.Skyblockian.economy.commands;

import com.skyblockian.Skyblockian.economy.EcoCommands;
import com.skyblockian.Skyblockian.economy.SettingsManager;
import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TopCommand
    extends EcoCommands
    implements TextFormat
{
    public TopCommand() { super("top", "Look at the top players.", ""); }

    public void run(CommandSender sender, String[] args)
    {
        Player p = (Player)sender;

        getTop(p);
    }

    public void getTop(Player p)
    {
        ArrayList<String> data = new ArrayList<>();

        p.sendMessage(prefix + "TOP NET WORTH OF PLAYERS:");

        for (String key : SettingsManager.getEcoManager().getConfig().getConfigurationSection("money").getKeys(false))
        {
            data.add(key + " " + SettingsManager.getEcoManager().getConfig().getInt("money." + key));
        }

        Collections.sort(data, new Comparator<String>()
        {
            @Override
            public int compare(String a, String b)
            {
                int aVal = Integer.parseInt(a.split(" ")[1]);
                int bVal = Integer.parseInt(b.split(" ")[1]);
                return Integer.compare(aVal, bVal);
            }
        });

        int i = data.size();

        if (i >= 10)
        {
            while (i >= data.size() - 10)
            {
                p.sendMessage(ChatColor.GRAY + data.get(i).replace(" ", ": " + ChatColor.AQUA + "$").toUpperCase());
                i--;
            }
        }
        else{
            while (i - 1 >= 0)
            {
                p.sendMessage(ChatColor.GRAY + data.get(i - 1).replace(" ", ": " + ChatColor.AQUA + "$").toUpperCase());
                i--;
            }
        }

        return;
    }
}
