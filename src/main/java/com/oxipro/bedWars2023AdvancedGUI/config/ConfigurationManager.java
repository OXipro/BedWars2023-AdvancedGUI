package com.oxipro.bedWars2023AdvancedGUI.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ConfigurationManager {

    private final JavaPlugin plugin;
    private final File configFile;
    private FileConfiguration config;

    public ConfigurationManager(JavaPlugin plugin) {
        this.plugin = plugin;

        File folder = new File(
                Bukkit.getWorldContainer(),
                "plugins/BWProxy2023/Addons/AdvancedGUI"
        );

        if (!folder.exists()) {
            folder.mkdirs();
        }

        this.configFile = new File(folder, "config.yml");
        load();
    }

    public void load() {
        config = YamlConfiguration.loadConfiguration(configFile);

        if (!configFile.exists()) {
            saveDefault();
        }

        InputStream defStream = plugin.getResource("config.yml");
        if (defStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(
                    new InputStreamReader(defStream, StandardCharsets.UTF_8)
            );
            config.setDefaults(defConfig);
            config.options().copyDefaults(true);
            //save();
        }
    }

    public void save() {
        try {
            config.save(configFile);
        } catch (Exception e) {
            plugin.getLogger().severe("cannot save config");
            e.printStackTrace();
        }
    }

    private void saveDefault() {
        try (InputStream in = plugin.getResource("config.yml")) {
            if (in != null) {
                java.nio.file.Files.copy(
                        in,
                        configFile.toPath(),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void reload() {
        load();
    }
}
