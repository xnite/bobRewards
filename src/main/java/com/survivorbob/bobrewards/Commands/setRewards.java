package com.survivorbob.bobrewards.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class setRewards implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("bob.rewards.set")) {
            return false;
        }
        if(args[0].isEmpty())
        {
            sender.sendMessage(ChatColor.RED + "Error: " + ChatColor.RESET + "You must specify a rewards box color!");
            return true;
        }
        String rewards_box;
        switch(args[0].toLowerCase())
        {
            case "green":
                rewards_box = "green";
                break;
            case "blue":
                rewards_box = "blue";
                break;
            case "red":
                rewards_box = "red";
                break;
            case "black":
                rewards_box = "black";
                break;
            default:
                sender.sendMessage(ChatColor.RED + "Error: " + ChatColor.RESET + "You must specify a rewards box color!");
                return true;
        }
        Player p = (Player) sender;

        if(p.getInventory().getContents().length <= 0)
        {
            sender.sendMessage(ChatColor.RED + "Error:" + ChatColor.RESET + " Not enough items in your inventory!");
            return true;
        }

        Plugin plugin = sender.getServer().getPluginManager().getPlugin("BobRewards");
        FileConfiguration config = plugin.getConfig();


        Inventory rewards_pool_inv = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Rewards Editor: " + rewards_box.toUpperCase(Locale.ENGLISH) + " Rewards");

        ArrayList<ItemStack> rewards_pool = (ArrayList<ItemStack>) config.getList("rewards."+rewards_box.toLowerCase()+".items");

        for(ItemStack item : rewards_pool) {
            rewards_pool_inv.addItem(item);
        }

        p.openInventory(rewards_pool_inv);

        return true;
    }
}
