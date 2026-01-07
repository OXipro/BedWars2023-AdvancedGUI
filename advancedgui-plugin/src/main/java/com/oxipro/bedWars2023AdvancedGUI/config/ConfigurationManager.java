package com.oxipro.bedWars2023AdvancedGUI.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ConfigurationManager {

    private final JavaPlugin plugin;
    private File configFile;
    private FileConfiguration config;
    private ConfigDefault configDefault;

    public ConfigurationManager(JavaPlugin plugin) {
        this.plugin = plugin;

        File folder = new File(
                Bukkit.getPluginManager().getPlugin("BWProxy2023").getDataFolder().getPath() + "/Addons/AdvancedGUI/"
        );
        if (!folder.exists()) {
            folder.mkdirs();
        }

        this.configDefault = new ConfigDefault();
        load();
    }

    public void load() {
        configFile = configDefault.file;
        config = configDefault.c;

        if (!configFile.exists()) {
            save();
        }
    }

    public void save() {
        configDefault.save();
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void reload() {
        load();
    }
}
