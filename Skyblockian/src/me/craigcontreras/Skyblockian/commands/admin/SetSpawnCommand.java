package me.craigcontreras.Skyblockian.commands.admin;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand extends AdminCommands implements TextFormat
{

    public SetSpawnCommand() { super("setspawn", "sets the spawn for the server", "" ); }

    public void run(CommandSender sender, String[] args)
    {
        if(sender instanceof Player)
        {
            Player p = (Player) sender;
            if(p.hasPermission("skyblockian.admin"))
            {
                World world = p.getWorld();
                double x = p.getLocation().getX();
                double y = p.getLocation().getY();
                double z = p.getLocation().getZ();

                Skyblockian.getCore().getConfig().set("spawn", ".world" + world.toString());
                Skyblockian.getCore().getConfig().set("spawn", ".x" + x);
                Skyblockian.getCore().getConfig().set("spawn", ".y" + y);
                Skyblockian.getCore().getConfig().set("spawn", ".z" + z);

                p.sendMessage(TextFormat.successfulSpawnSet);
            } else
            {
                p.sendMessage(TextFormat.noPerm);
            }
        } else
        {
            sender.sendMessage(TextFormat.cmdError);
        }
    }


}
