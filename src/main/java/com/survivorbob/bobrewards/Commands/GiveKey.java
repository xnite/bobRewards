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

public class GiveKey implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(!sender.hasPermission("bob.rewards.give.key"))
        {
            return false;
        }
        Player p = null;
        if(!args[1].isEmpty())
        {
            p = (Player) sender.getServer().getPlayer(args[1]);
            if(p == null) {
                sender.sendMessage(ChatColor.RED + "Error: " + ChatColor.RESET + " Could not find player: " + args[1] + "!");
                return true;
            }
        } else {
            p = (Player) sender;
        }
        ItemStack theKey;
        ItemMeta theKeyMeta;
        List<String> theLore = new ArrayList<String>();
        switch(args[0].toLowerCase()) {
            case "green":
                theKey = new ItemStack(Material.PAPER, 1);
                theKeyMeta = theKey.getItemMeta();
                theKeyMeta.setDisplayName(ChatColor.GREEN + "Green Key");
                theLore.add("Use this " + ChatColor.GREEN + "Green Key" + ChatColor.RESET + " to claim rewards!");
                theKeyMeta.setLore(theLore);
                theKey.setItemMeta(theKeyMeta);
                p.getInventory().addItem(theKey);
                sender.sendMessage("A " + ChatColor.GREEN + "Green Key" + ChatColor.RESET + " should now be in your inventory!");
                break;
            case "blue":
                theKey = new ItemStack(Material.PAPER, 1);
                theKeyMeta = theKey.getItemMeta();
                theKeyMeta.setDisplayName(ChatColor.BLUE + "Blue Key");
                theLore.add("Use this " + ChatColor.BLUE + "Blue Key" + ChatColor.RESET + " to claim rewards!");
                theKeyMeta.setLore(theLore);
                theKey.setItemMeta(theKeyMeta);
                p.getInventory().addItem(theKey);
                sender.sendMessage("A " + ChatColor.BLUE + "Blue Key" + ChatColor.RESET + " should now be in your inventory!");
                break;
            case "red":
                theKey = new ItemStack(Material.PAPER, 1);
                theKeyMeta = theKey.getItemMeta();
                theKeyMeta.setDisplayName(ChatColor.RED + "Red Key");
                theLore.add("Use this " + ChatColor.RED + "Red Key" + ChatColor.RESET + " to claim rewards!");
                theKeyMeta.setLore(theLore);
                theKey.setItemMeta(theKeyMeta);
                p.getInventory().addItem(theKey);
                sender.sendMessage("A " + ChatColor.RED + "Red Key" + ChatColor.RESET + " should now be in your inventory!");
                break;
            case "black":
                theKey = new ItemStack(Material.PAPER, 1);
                theKeyMeta = theKey.getItemMeta();
                theKeyMeta.setDisplayName(ChatColor.BLACK + "Black Key");
                theLore.add("Use this " + ChatColor.BLACK + "Black Key" + ChatColor.RESET + " to claim rewards!");
                theKeyMeta.setLore(theLore);
                theKey.setItemMeta(theKeyMeta);
                p.getInventory().addItem(theKey);
                sender.sendMessage("A " + ChatColor.BLACK + "Black Key" + ChatColor.RESET + " should now be in your inventory!");
                break;
            default:
                sender.sendMessage("You must specify a Key of green, blue, red, or black!");
                break;
        }
        return true;
    }
}
