package me.craigcontreras.Skyblockian.listeners;

import me.craigcontreras.Skyblockian.Skyblockian;
import me.craigcontreras.Skyblockian.interfaces.TextFormat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class UpgradesMenuListener
    implements Listener, TextFormat
{
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e)
    {
        if (e.getDamager() instanceof Player)
        {
            Player p = (Player) e.getDamager();

            e.setDamage(e.getDamage() + (Skyblockian.getCore().levelUpManager.getDamageLevel(p) * 2));
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        int healthlevel = Skyblockian.getCore().levelUpManager.getHealthLevel(p);

        if (healthlevel == 0)
        {
            return;
        }else if (healthlevel == 1)
        {
            p.setMaxHealth(21);
            return;
        }else if (healthlevel == 2)
        {
            p.setMaxHealth(22);
            return;
        }else if (healthlevel == 3)
        {
            p.setMaxHealth(23);
            return;
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e)
    {
        Player p = e.getPlayer();
        int healthlevel = Skyblockian.getCore().levelUpManager.getHealthLevel(p);

        if (healthlevel == 0)
        {
            return;
        }else if (healthlevel == 1)
        {
            p.setMaxHealth(21);
            return;
        }else if (healthlevel == 2)
        {
            p.setMaxHealth(22);
            return;
        }else if (healthlevel == 3)
        {
            p.setMaxHealth(23);
            return;
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if (e.getInventory().equals(Skyblockian.getCore().levelUpManager.upgrades))
        {
            e.setCancelled(true);

            if (item == null) return;

            if (item.getItemMeta().getDisplayName().equals(Skyblockian.getCore().levelUpManager.damage.getItemMeta().getDisplayName()) &&
                    item.getType().equals(Skyblockian.getCore().levelUpManager.damage.getType()))
            {
                int damagelevel = Skyblockian.getCore().levelUpManager.getDamageLevel(p);

                if (damagelevel == 0)
                {
                    if (Skyblockian.getCore().levelUpManager.hasXP(p, Math.multiplyExact(400,
                            Skyblockian.getCore().levelUpManager.getDamageLevel(p) + 1)))
                    {
                        Skyblockian.getCore().levelUpManager.removeXP(p, Math.multiplyExact(400,
                                Skyblockian.getCore().levelUpManager.getDamageLevel(p) + 1));
                        Skyblockian.getCore().levelUpManager.setDamageLevel(p, 1);
                        Skyblockian.getCore().levelUpManager.createInventory(p);
                        return;
                    }
                    else{
                        p.sendMessage(prefix + "You do not have enough XP.");
                        return;
                    }
                }else if (damagelevel == 1)
                {
                    if (Skyblockian.getCore().levelUpManager.hasXP(p, Math.multiplyExact(400,
                            Skyblockian.getCore().levelUpManager.getDamageLevel(p) + 1)))
                    {
                        Skyblockian.getCore().levelUpManager.removeXP(p, Math.multiplyExact(400,
                                Skyblockian.getCore().levelUpManager.getDamageLevel(p) + 1));
                        Skyblockian.getCore().levelUpManager.setDamageLevel(p, 2);
                        Skyblockian.getCore().levelUpManager.createInventory(p);
                        return;
                    }
                    else{
                        p.sendMessage(prefix + "You do not have enough XP.");
                        return;
                    }
                }else if (damagelevel == 2)
                {
                    if (Skyblockian.getCore().levelUpManager.hasXP(p, Math.multiplyExact(400,
                            Skyblockian.getCore().levelUpManager.getDamageLevel(p) + 1)))
                    {
                        Skyblockian.getCore().levelUpManager.removeXP(p, Math.multiplyExact(400,
                                Skyblockian.getCore().levelUpManager.getDamageLevel(p) + 1));
                        Skyblockian.getCore().levelUpManager.setDamageLevel(p, 3);
                        Skyblockian.getCore().levelUpManager.createInventory(p);
                        return;
                    }
                    else{
                        p.sendMessage(prefix + "You do not have enough XP.");
                        return;
                    }
                }else if (damagelevel == 3)
                {
                    p.sendMessage(prefix + "You're at the max level.");
                    return;
                }
            }else if (item.getItemMeta().getDisplayName().equals(Skyblockian.getCore().levelUpManager.health.getItemMeta().getDisplayName()) &&
                    item.getType().equals(Skyblockian.getCore().levelUpManager.health.getType()))
            {
                int healthlevel = Skyblockian.getCore().levelUpManager.getHealthLevel(p);

                if (healthlevel == 0)
                {
                    if (Skyblockian.getCore().levelUpManager.hasXP(p, Math.multiplyExact(900,
                            Skyblockian.getCore().levelUpManager.getHealthLevel(p) + 1)))
                    {
                        Skyblockian.getCore().levelUpManager.removeXP(p, Math.multiplyExact(900,
                                Skyblockian.getCore().levelUpManager.getHealthLevel(p) + 1));
                        Skyblockian.getCore().levelUpManager.setHealthLevel(p, 1);
                        Skyblockian.getCore().levelUpManager.createInventory(p);
                        p.setMaxHealth(21);
                        return;
                    }
                    else{
                        p.sendMessage(prefix + "You do not have enough XP.");
                        return;
                    }
                }else if (healthlevel == 1)
                {
                    if (Skyblockian.getCore().levelUpManager.hasXP(p, Math.multiplyExact(900,
                            Skyblockian.getCore().levelUpManager.getHealthLevel(p) + 1)))
                    {
                        Skyblockian.getCore().levelUpManager.removeXP(p, Math.multiplyExact(900,
                                Skyblockian.getCore().levelUpManager.getHealthLevel(p) + 1));
                        Skyblockian.getCore().levelUpManager.setHealthLevel(p, 2);
                        Skyblockian.getCore().levelUpManager.createInventory(p);
                        p.setMaxHealth(22);
                        return;
                    }
                    else{
                        p.sendMessage(prefix + "You do not have enough XP.");
                        return;
                    }
                }else if (healthlevel == 2)
                {
                    if (Skyblockian.getCore().levelUpManager.hasXP(p, Math.multiplyExact(900,
                            Skyblockian.getCore().levelUpManager.getHealthLevel(p) + 1)))
                    {
                        Skyblockian.getCore().levelUpManager.removeXP(p, Math.multiplyExact(900,
                                Skyblockian.getCore().levelUpManager.getHealthLevel(p) + 1));
                        Skyblockian.getCore().levelUpManager.setHealthLevel(p, 3);
                        Skyblockian.getCore().levelUpManager.createInventory(p);
                        p.setMaxHealth(23);
                        return;
                    }
                    else{
                        p.sendMessage(prefix + "You do not have enough XP.");
                        return;
                    }
                }else if (healthlevel == 3)
                {
                    p.sendMessage(prefix + "You're at the max level.");
                    return;
                }
            }
        }
    }
}
