package com.survivorbob.bobrewards.Events;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

public class ClickRewardsBox implements Listener {
    @EventHandler
    public void onRightClickRewardsBox(PlayerInteractEvent event)
    {
        Block theBox = event.getClickedBlock();
        Player player = (Player) event.getPlayer();
        Logger logger = player.getServer().getLogger();
        Plugin plugin = player.getServer().getPluginManager().getPlugin("BobRewards");
        FileConfiguration config = plugin.getConfig();

        if(theBox.getState() instanceof Container && ((Container) theBox.getState()).getCustomName().contains("Rewards Box"))
        {
            event.setCancelled(true);
            ItemStack heldItem = player.getInventory().getItemInMainHand();

            int min_money = 0;
            int max_money = 0;

            int min_items = 2;
            int max_items = 3;

            ArrayList<ItemStack> reward_pool = null;
            if(((Container) theBox.getState()).getCustomName().contains(ChatColor.GREEN + "Green Rewards Box"))
            {
                if(!heldItem.getType().equals(Material.PAPER) || !heldItem.getItemMeta().getDisplayName().contains(ChatColor.GREEN + "Green Key")) {
                    player.sendMessage("You do not have a " + ChatColor.GREEN + "Green Key" + ChatColor.RESET + " in your main hand!");
                    return;
                }
                min_money = config.getInt("rewards.green.money.min");
                max_money = config.getInt("rewards.green.money.max");

                max_items = (config.getInt("rewards.green.max_items")|config.getInt("default_max_items")|3);
                min_items = (config.getInt("rewards.green.min_items")|config.getInt("default_min_items")|2);

                reward_pool = (ArrayList<ItemStack>) config.getList("rewards.green.items");
            }

            if(((Container) theBox.getState()).getCustomName().contains(ChatColor.BLUE + "Blue Rewards Box"))
            {
                if(!heldItem.getType().equals(Material.PAPER) || !heldItem.getItemMeta().getDisplayName().contains(ChatColor.BLUE + "Blue Key")) {
                    player.sendMessage("You do not have a " + ChatColor.BLUE + "Blue Key" + ChatColor.RESET + " in your main hand!");
                    return;
                }
                min_money = config.getInt("rewards.blue.money.min");
                max_money = config.getInt("rewards.blue.money.max");

                max_items = (config.getInt("rewards.blue.max_items")|config.getInt("default_max_items")|3);
                min_items = (config.getInt("rewards.blue.min_items")|config.getInt("default_min_items")|2);

                reward_pool = (ArrayList<ItemStack>) config.getList("rewards.blue.items");
            }

            if(((Container) theBox.getState()).getCustomName().contains(ChatColor.RED + "Red Rewards Box"))
            {
                if(!heldItem.getType().equals(Material.PAPER) || !heldItem.getItemMeta().getDisplayName().contains(ChatColor.RED + "Red Key")) {
                    player.sendMessage("You do not have a " + ChatColor.RED + "Red Key" + ChatColor.RESET + " in your main hand!");
                    return;
                }
                min_money = config.getInt("rewards.red.money.min");
                max_money = config.getInt("rewards.red.money.max");

                max_items = (config.getInt("rewards.red.max_items")|config.getInt("default_max_items")|3);
                min_items = (config.getInt("rewards.red.min_items")|config.getInt("default_min_items")|2);

                reward_pool = (ArrayList<ItemStack>) config.getList("rewards.red.items");
            }

            if(((Container) theBox.getState()).getCustomName().contains(ChatColor.BLACK + "Black Rewards Box"))
            {
                if(!heldItem.getType().equals(Material.PAPER) || !heldItem.getItemMeta().getDisplayName().contains(ChatColor.BLACK + "Black Key")) {
                    player.sendMessage("You do not have a " + ChatColor.BLACK + "Black Key" + ChatColor.RESET + " in your main hand!");
                    return;
                }
                min_money = config.getInt("rewards.black.money.min");
                max_money = config.getInt("rewards.black.money.max");

                max_items = (config.getInt("rewards.black.max_items")|config.getInt("default_max_items")|3);
                min_items = (config.getInt("rewards.black.min_items")|config.getInt("default_min_items")|2);

                reward_pool = (ArrayList<ItemStack>) config.getList("rewards.black.items");
            }


            logger.info(player.getName() + " claimed a " + heldItem.getItemMeta().getDisplayName() + ChatColor.RESET + "!");
            heldItem.setAmount(Math.subtractExact(heldItem.getAmount(), 1));
            player.getInventory().setItemInMainHand(heldItem);

            Inventory rewards = Bukkit.createInventory(null, 36, ChatColor.GOLD + "Rewards Box");

            Random randomNum = new Random();

            int myRandNum = randomNum.nextInt((max_items - min_items) + 1);
            for(int i = 0; i <= myRandNum; i++)
            {
                int randSel = randomNum.nextInt(reward_pool.size()-1);
                rewards.addItem(reward_pool.get(randSel));
            }
            player.openInventory(rewards);

            if(min_money > 0 && max_money > 0)
            {
                int myRandMoney = randomNum.nextInt((config.getInt("rewards.green.money.max") - config.getInt("rewards.green.money.min"))) + 1;
                RegisteredServiceProvider<Economy> economyService = plugin.getServer().getServicesManager().getRegistration(Economy.class);
                Economy economy = economyService.getProvider();
                if(economy != null)
                {
                    economy.depositPlayer((OfflinePlayer) Bukkit.getOfflinePlayer(player.getUniqueId()), myRandMoney);
                    player.sendMessage("You received " + ChatColor.GREEN + "$" + myRandMoney);
                }
            }
        }
    }
}
