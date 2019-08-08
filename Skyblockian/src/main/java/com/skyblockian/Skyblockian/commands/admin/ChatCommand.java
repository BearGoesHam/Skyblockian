package com.skyblockian.Skyblockian.commands.admin;

import com.skyblockian.Skyblockian.commands.AdminCommands;
import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ChatCommand
    extends AdminCommands
    implements TextFormat
{
    public ChatCommand() { super("chat", "Make another player speak in chat.", "<player> <message>"); }

    public void run(CommandSender sender, String[] args)
    {
        Player p = (Player)sender;
        Player t = Bukkit.getPlayer(args[0]);

        if (sender instanceof Player)
        {
            if (args.length >= 1)
            {
                if (t == null)
                {
                    p.sendMessage(playerError + args[0] + ".");
                    return;
                }
                else{
                    ArrayList<String> argsa = new ArrayList<>();
                    for (int i = 1; i != args.length; i++)
                    {
                        argsa.add(args[i]);
                    }

                    StringBuilder sb = new StringBuilder();
                    for (String str : argsa)
                    {
                        sb.append(str + " ");
                    }

                    t.chat(sb.toString());
                    Bukkit.broadcast(prefix + p.getName() + " sudo'd " + t.getName() + " to say: " + sb.toString(),
                            "skyblockian.admin");
                }
            }
            else{
                p.sendMessage(argsError);
                return;
            }
        }
        else{
            return;
        }

        return;
    }
}
