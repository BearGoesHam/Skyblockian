package me.craigcontreras.Skyblockian.commands.admin;

import me.craigcontreras.Skyblockian.commands.AdminCommands;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class DemoCommand
    extends AdminCommands
    implements TextFormat
{
    public DemoCommand() { super("demo", "Show the demo menu to a player.", "<player>"); }

    public void run(CommandSender sender, String[] args)
    {
        if (sender instanceof Player)
        {
            Player p = (Player)sender;

            if (args.length == 0)
            {
                p.sendMessage(argsError);
                return;
            }

            if (args.length >= 2)
            {
                p.sendMessage(argsError);
                return;
            }

            if (args.length == 1)
            {
                Player t = Bukkit.getPlayer(args[0]);

                if (t == null)
                {
                    p.sendMessage(playerError + args[0] + ".");
                    return;
                }
                else{
                    try{
                        String path = Bukkit.getServer().getClass().getPackage().getName();
                        String version = path.substring(path.lastIndexOf(".") + 1, path.length());

                        Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer");
                        Class<?> PacketPlayOutGameStateChange = Class.forName("net.minecraft.server." + version +
                                ".PacketPlayOutGameStateChange");
                        Class<?> Packet = Class.forName("net.minecraft.server." + version + ".Packet");
                        Constructor<?> playOutConstructor = PacketPlayOutGameStateChange.getConstructor(new Class[]
                                { Integer.TYPE, Float.TYPE });
                        Object packet = playOutConstructor.newInstance(new Object[] { Integer.valueOf(5), Integer.valueOf(0) });
                        Object craftPlayerObject = craftPlayer.cast(t);
                        Method getHandleMethod = craftPlayer.getMethod("getHandle", new Class[0]);
                        Object handle = getHandleMethod.invoke(craftPlayerObject, new Object[0]);
                        Object pc = handle.getClass().getField("playerConnection").get(handle);
                        Method sendPacketMethod = pc.getClass().getMethod("sendPacket", new Class[] { Packet });
                        sendPacketMethod.invoke(pc, new Object[] { packet });
                    }catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }

                    p.sendMessage(prefix + t.getName() + " has received the demo menu.");
                }
            }
        }
        else{
            return;
        }

        return;
    }
}
