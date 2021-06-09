package com.survivorbob.bobrewards.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class RewardsBox implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(!sender.hasPermission("bob.rewards.give.box"))
        {
            return false;
        }
        Player p = (Player) sender;
        ItemStack theBox;
        ItemMeta theBoxMeta;
        List<String> theLore = new ArrayList<String>();
        switch(args[0].toLowerCase()) {
            case "green":
                theBox = new ItemStack(Material.GREEN_SHULKER_BOX, 1);
                theBoxMeta = theBox.getItemMeta();
                theBoxMeta.setDisplayName(ChatColor.GREEN + "Green Rewards Box");
                theLore.add("Place this box to allow players with " + ChatColor.GREEN + "Green Keys" + ChatColor.RESET + " to claim rewards!");
                theBoxMeta.setLore(theLore);
                theBox.setItemMeta(theBoxMeta);
                p.getInventory().addItem(theBox);
                sender.sendMessage("A " + ChatColor.GREEN + "Green Rewards Box" + ChatColor.RESET + " should now be in your inventory!");
                break;
            case "blue":
                theBox = new ItemStack(Material.BLUE_SHULKER_BOX, 1);
                theBoxMeta = theBox.getItemMeta();
                theBoxMeta.setDisplayName(ChatColor.BLUE + "Blue Rewards Box");
                theLore.add("Place this box to allow players with " + ChatColor.BLUE + "Blue Keys" + ChatColor.RESET + " to claim rewards!");
                theBoxMeta.setLore(theLore);
                theBox.setItemMeta(theBoxMeta);
                p.getInventory().addItem(theBox);
                sender.sendMessage("A " + ChatColor.BLUE + "Blue Rewards Box" + ChatColor.RESET + " should now be in your inventory!");
                break;
            case "red":
                theBox = new ItemStack(Material.RED_SHULKER_BOX, 1);
                theBoxMeta = theBox.getItemMeta();
                theBoxMeta.setDisplayName(ChatColor.RED + "Red Rewards Box");
                theLore.add("Place this box to allow players with " + ChatColor.RED + "Red Keys" + ChatColor.RESET + " to claim rewards!");
                theBoxMeta.setLore(theLore);
                theBox.setItemMeta(theBoxMeta);
                p.getInventory().addItem(theBox);
                sender.sendMessage("A " + ChatColor.RED + "Red Rewards Box" + ChatColor.RESET + " should now be in your inventory!");
                break;
            case "black":
                theBox = new ItemStack(Material.BLACK_SHULKER_BOX, 1);
                theBoxMeta = theBox.getItemMeta();
                theBoxMeta.setDisplayName(ChatColor.BLACK + "Black Rewards Box");
                theLore.add("Place this box to allow players with " + ChatColor.BLACK + "Black Keys" + ChatColor.RESET + " to claim rewards!");
                theBoxMeta.setLore(theLore);
                theBox.setItemMeta(theBoxMeta);
                p.getInventory().addItem(theBox);
                sender.sendMessage("A " + ChatColor.BLACK + "Black Rewards Box" + ChatColor.RESET + " should now be in your inventory!");
                break;
            default:
                sender.sendMessage("You must specify a rewards box of green, blue, red, or black!");
                break;
        }
        return true;
    }
}
