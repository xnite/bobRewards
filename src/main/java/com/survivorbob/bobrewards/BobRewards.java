package com.survivorbob.bobrewards;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BobRewards extends JavaPlugin {

    public FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager PM = getServer().getPluginManager();
        double conf_version = config.getDouble("conf_version");
        if(conf_version <= 0.99)
        {
            saveDefaultConfig();
        }
        // Register Commands:
            this.getCommand("givebox").setExecutor(new com.survivorbob.bobrewards.Commands.RewardsBox());
            this.getCommand("givekey").setExecutor(new com.survivorbob.bobrewards.Commands.GiveKey());
            this.getCommand("setrewards").setExecutor(new com.survivorbob.bobrewards.Commands.setRewards());
        // Register Events:
            PM.registerEvents(new com.survivorbob.bobrewards.Events.ClickRewardsBox(), this);
            PM.registerEvents(new com.survivorbob.bobrewards.Events.InventoryCloseEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
