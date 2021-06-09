package com.survivorbob.bobrewards.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.logging.Logger;

public class InventoryCloseEvent implements Listener {
    @EventHandler
    public void onCloseInventory(org.bukkit.event.inventory.InventoryCloseEvent event)
    {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("BobRewards");
        FileConfiguration config = plugin.getConfig();
        Inventory inv = event.getInventory();
        InventoryView alsoInv = inv.getViewers().get(0).getOpenInventory();

        if(alsoInv.getTitle().startsWith(ChatColor.GOLD + "Rewards Editor: "))
        {
            Player vp = (Player) Bukkit.getServer().getPlayer(inv.getViewers().get(0).getUniqueId());
            Logger logger = Bukkit.getLogger();
            if(!vp.hasPermission("bob.rewards.set"))
            {
                logger.warning(vp.getName() + " tried to edit rewards!");
                return;
            }
            String theBoxName = alsoInv.getTitle().toLowerCase();
            theBoxName = theBoxName.replaceAll(".*rewards editor: ", "");
            theBoxName = theBoxName.replaceAll(" rewards$", "");
            ArrayList<ItemStack> toSave = new ArrayList<ItemStack>();
            for(ItemStack theItem : inv.getContents())
            {
                if(theItem != null)
                {
                    toSave.add(theItem);
                }
            }
            config.set("rewards." + theBoxName + ".items", toSave);
            try {
                vp.sendMessage("Saving Reward items for " + theBoxName.toUpperCase());
                plugin.saveConfig();
                vp.sendMessage("Wrote reward items to configuration file!");
            } catch (Exception e) {
                vp.sendMessage("Failed to write to configuration!");
            }
        }
    }
}
