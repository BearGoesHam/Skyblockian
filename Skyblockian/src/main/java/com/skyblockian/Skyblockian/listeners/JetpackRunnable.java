package com.skyblockian.Skyblockian.listeners;

import com.skyblockian.Skyblockian.interfaces.TextFormat;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class JetpackRunnable
    extends BukkitRunnable
    implements TextFormat
{
    private JetpackListener jl;
    private Player p;
    private int ticks;

    public JetpackRunnable(JetpackListener jl, Player p, int ticks)
    {
        this.jl = jl;
        this.p = p;
        this.ticks = ticks;
    }

    @Override
    public void run()
    {
        if (ticks <= 0)
        {
            p.sendMessage(prefix + "Your jetpack worn off.");
            p.setFlying(false);
            p.setAllowFlight(false);

            if (!(p.isOnGround()))
            {
                jl.addToFallDamage(p);
            }

            cancel();
            return;
        }

        p.sendMessage(prefix + ticks + " seconds of flight remains.");
        ticks--;
    }
}
