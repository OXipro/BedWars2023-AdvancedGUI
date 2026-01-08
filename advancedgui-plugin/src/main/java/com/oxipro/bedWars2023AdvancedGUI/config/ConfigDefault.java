package com.oxipro.bedWars2023AdvancedGUI.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static com.oxipro.bedWars2023AdvancedGUI.config.ConfigPaths.*;

public class ConfigDefault {

    YamlConfiguration c;
    File file;

    public ConfigDefault() {
        setupConfig();
    }


    public YamlConfiguration setupConfig() {
        file = new File(Bukkit.getPluginManager().getPlugin("BWProxy2023").getDataFolder().getPath() + "/Addons/AdvancedGUI/", "config.yml");
        c = YamlConfiguration.loadConfiguration(file);

        c.addDefault(GUI_MAIN_ARENAS_REFRESH_DELAY, 40L);
        c.addDefault(GUI_MAIN_ARENAS_REFRESH_ENABLED, true);
        c.addDefault(GUI_MAIN_ARENAS_START_SLOT, 12);
        c.addDefault(GUI_MAIN_ARENAS_SLOTS_COUNTS, 5);
        c.addDefault(GUI_MAIN_ARENAS_SHOW_FULL, false);
        c.addDefault(GUI_MAIN_ARENAS_SHOW_WAITING, true);
        c.addDefault(GUI_MAIN_ARENAS_SHOW_STARTING, true);
        c.addDefault(GUI_MAIN_ARENAS_SHOW_RESTARTING, false);
        c.addDefault(GUI_MAIN_ARENAS_SHOW_PLAYING, false);
        c.addDefault(GUI_MAIN_ARENAS_MATERIAL_WAITING, "YELLOW_STAINED_GLASS");
        c.addDefault(GUI_MAIN_ARENAS_MATERIAL_STARTING, "LIME_STAINED_GLASS");
        c.addDefault(GUI_MAIN_ARENAS_MATERIAL_PLAYING, "LIGHT_BLUE_STAINED_GLASS");
        c.addDefault(GUI_MAIN_ARENAS_MATERIAL_RESTARTING, "BLUE_STAINED_GLASS");
        c.addDefault(GUI_MAIN_ARENAS_MATERIAL_NO_ARENA, "BARRIER");


        c.addDefault(GUI_MAIN_REJOIN_ENABLED, true);
        c.addDefault(GUI_MAIN_REJOIN_SLOT, 35);
        c.addDefault(GUI_MAIN_REJOIN_MATERIAL_AVAILABLE, "ENDER_PEARL");
        c.addDefault(GUI_MAIN_REJOIN_MATERIAL_UNAVAILABLE, "BARRIER");

        c.addDefault(GUI_MAIN_QUICK_JOIN_ENABLED, true);
        c.addDefault(GUI_MAIN_QUICK_JOIN_SLOT, 10);
        c.addDefault(GUI_MAIN_QUICK_JOIN_MATERIAL, "NETHER_STAR");

        c.addDefault(GUI_MAIN_CATEGORIES_ENABLED, true);
        c.addDefault(GUI_MAIN_CATEGORIES_SLOT, 27);
        c.addDefault(GUI_MAIN_CATEGORIES_MATERIAL, "CHEST");

        c.addDefault(GUI_MAIN_HOTBAR_ENABLED, true);
        c.addDefault(GUI_MAIN_HOTBAR_SLOT, 28);
        c.addDefault(GUI_MAIN_HOTBAR_MATERIAL, "BLAZE_ROD");

        c.addDefault(GUI_MAIN_QUICK_BUY_ENABLED, false);
        c.addDefault(GUI_MAIN_QUICK_BUY_SLOT, 29);
        c.addDefault(GUI_MAIN_QUICK_BUY_MATERIAL, "EMERALD");


        c.options().copyDefaults(true);
        save();

        return c;
    }

    public void save() {
        try {
            c.save(file);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save default config file", e);
        }
    }

}
