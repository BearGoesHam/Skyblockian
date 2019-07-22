package me.craigcontreras.Skyblockian.commands.admin;

import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteChatCommand
    extends AdminCommands
    implements TextFormat
{
    public static boolean mute = false;

    public MuteChatCommand() { super("mutechat", "Mute the global chat.", ""); }

    public void run(CommandSender sender, String[] args)
    {
        Player p = (Player)sender;

        if (sender instanceof Player)
        {
            if (mute)
            {
                Bukkit.broadcastMessage(prefix + "The chat is no longer muted.");
                mute = false;
            }
            else{
                Bukkit.broadcastMessage(prefix + "The chat has been muted.");
                mute = true;
            }
        }
        else{
            return;
        }

        return;
    }
}
