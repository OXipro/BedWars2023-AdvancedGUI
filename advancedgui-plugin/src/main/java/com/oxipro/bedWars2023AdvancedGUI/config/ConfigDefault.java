package com.oxipro.bedWars2023AdvancedGUI.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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

        final String newfile = c.getString("gui.main");

// ---------------- MAIN GUI ----------------
        c.addDefault(ConfigPaths.GUI_MAIN_ROWS, 4);

// quick-arenas
        c.addDefault(ConfigPaths.GUI_MAIN_ARENAS_ENABLED, true);
        c.addDefault(ConfigPaths.GUI_MAIN_ARENAS_REFRESH_ENABLED, true);
        c.addDefault(ConfigPaths.GUI_MAIN_ARENAS_REFRESH_DELAY, 40L);
        c.addDefault(ConfigPaths.GUI_MAIN_ARENAS_START_SLOT, 12);
        c.addDefault(ConfigPaths.GUI_MAIN_ARENAS_SLOTS, Arrays.asList(12,13,14,15,16));

        c.addDefault(ConfigPaths.GUI_MAIN_ARENAS_SHOW_WAITING, true);
        c.addDefault(ConfigPaths.GUI_MAIN_ARENAS_SHOW_STARTING, true);
        c.addDefault(ConfigPaths.GUI_MAIN_ARENAS_SHOW_RESTARTING, false);
        c.addDefault(ConfigPaths.GUI_MAIN_ARENAS_SHOW_PLAYING, false);
        c.addDefault(ConfigPaths.GUI_MAIN_ARENAS_SHOW_FULL, false);

        c.addDefault(ConfigPaths.GUI_MAIN_ARENAS_MATERIAL_WAITING, "YELLOW_STAINED_GLASS");
        c.addDefault(ConfigPaths.GUI_MAIN_ARENAS_MATERIAL_STARTING, "LIME_STAINED_GLASS");
        c.addDefault(ConfigPaths.GUI_MAIN_ARENAS_MATERIAL_PLAYING, "LIGHT_BLUE_STAINED_GLASS");
        c.addDefault(ConfigPaths.GUI_MAIN_ARENAS_MATERIAL_RESTARTING, "BLUE_STAINED_GLASS");
        c.addDefault(ConfigPaths.GUI_MAIN_ARENAS_MATERIAL_NO_ARENA, "BARRIER");

// rejoin
        c.addDefault(ConfigPaths.GUI_MAIN_REJOIN_ENABLED, true);
        c.addDefault(ConfigPaths.GUI_MAIN_REJOIN_SLOT, 35);
        c.addDefault(ConfigPaths.GUI_MAIN_REJOIN_MATERIAL_AVAILABLE, "ENDER_PEARL");
        c.addDefault(ConfigPaths.GUI_MAIN_REJOIN_MATERIAL_UNAVAILABLE, "BARRIER");

// quick-join
        c.addDefault(ConfigPaths.GUI_MAIN_QUICK_JOIN_ENABLED, true);
        c.addDefault(ConfigPaths.GUI_MAIN_QUICK_JOIN_SLOT, 10);
        c.addDefault(ConfigPaths.GUI_MAIN_QUICK_JOIN_MATERIAL, "NETHER_STAR");

// categories
        c.addDefault(ConfigPaths.GUI_MAIN_CATEGORIES_ENABLED, true);
        c.addDefault(ConfigPaths.GUI_MAIN_CATEGORIES_SLOT, 27);
        c.addDefault(ConfigPaths.GUI_MAIN_CATEGORIES_MATERIAL, "CHEST");

// hotbar manager
        c.addDefault(ConfigPaths.GUI_MAIN_HOTBAR_ENABLED, true);
        c.addDefault(ConfigPaths.GUI_MAIN_HOTBAR_SLOT, 28);
        c.addDefault(ConfigPaths.GUI_MAIN_HOTBAR_MATERIAL, "BLAZE_ROD");

// quick-buy
        c.addDefault(ConfigPaths.GUI_MAIN_QUICK_BUY_ENABLED, false);
        c.addDefault(ConfigPaths.GUI_MAIN_QUICK_BUY_SLOT, 29);
        c.addDefault(ConfigPaths.GUI_MAIN_QUICK_BUY_MATERIAL, "EMERALD");


// ---------------- CATEGORIES SELECTOR ----------------
        c.addDefault(ConfigPaths.GUI_CATEGORIES_SELECTOR_ROWS, 3);

        if (newfile == null) {
            String base = ConfigPaths.GUI_CATEGORIES_SELECTOR_CATEGORIES;

            c.addDefault(base + "1x8." + ConfigPaths.GUI_CATEGORIES_SELECTOR_SLOT, 11);
            c.addDefault(base + "1x8." + ConfigPaths.GUI_CATEGORIES_SELECTOR_ITEM_MATERIAL, "CHEST");
            c.addDefault(base + "1x8." + ConfigPaths.GUI_CATEGORIES_SELECTOR_ITEM_AMOUNT, 1);

            c.addDefault(base + "2x8." + ConfigPaths.GUI_CATEGORIES_SELECTOR_SLOT, 12);
            c.addDefault(base + "2x8." + ConfigPaths.GUI_CATEGORIES_SELECTOR_ITEM_MATERIAL, "CHEST");
            c.addDefault(base + "2x8." + ConfigPaths.GUI_CATEGORIES_SELECTOR_ITEM_AMOUNT, 1);

            c.addDefault(base + "3x4." + ConfigPaths.GUI_CATEGORIES_SELECTOR_SLOT, 13);
            c.addDefault(base + "3x4." + ConfigPaths.GUI_CATEGORIES_SELECTOR_ITEM_MATERIAL, "CHEST");
            c.addDefault(base + "3x4." + ConfigPaths.GUI_CATEGORIES_SELECTOR_ITEM_AMOUNT, 1);

            c.addDefault(base + "4x4." + ConfigPaths.GUI_CATEGORIES_SELECTOR_SLOT, 14);
            c.addDefault(base + "4x4." + ConfigPaths.GUI_CATEGORIES_SELECTOR_ITEM_MATERIAL, "CHEST");
            c.addDefault(base + "4x4." + ConfigPaths.GUI_CATEGORIES_SELECTOR_ITEM_AMOUNT, 1);

            c.addDefault(base + "2x2." + ConfigPaths.GUI_CATEGORIES_SELECTOR_SLOT, 15);
            c.addDefault(base + "2x2." + ConfigPaths.GUI_CATEGORIES_SELECTOR_ITEM_MATERIAL, "CHEST");
            c.addDefault(base + "2x2." + ConfigPaths.GUI_CATEGORIES_SELECTOR_ITEM_AMOUNT, 1);
        }


// ---------------- CATEGORY MENU ----------------
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_ROWS, 4);

// rejoin
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_REJOIN_ENABLED, true);
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_REJOIN_SLOT, 35);
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_REJOIN_MATERIAL_AVAILABLE, "ENDER_PEARL");
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_REJOIN_MATERIAL_UNAVAILABLE, "BARRIER");

// quick-join
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_QUICK_JOIN_ENABLED, true);
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_QUICK_JOIN_SLOT, 12);
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_QUICK_JOIN_MATERIAL, "NETHER_STAR");

// categories
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_CATEGORIES_ENABLED, true);
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_CATEGORIES_SLOT, 27);
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_CATEGORIES_MATERIAL, "CHEST");

// hotbar manager
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_HOTBAR_ENABLED, true);
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_HOTBAR_SLOT, 28);
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_HOTBAR_MATERIAL, "BLAZE_ROD");

// quick-buy
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_QUICK_BUY_ENABLED, false);
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_QUICK_BUY_SLOT, 29);
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_QUICK_BUY_MATERIAL, "EMERALD");

// map-selector
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_MAP_SELECTOR_ENABLED, true);
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_MAP_SELECTOR_SLOT, 14);
        c.addDefault(ConfigPaths.GUI_CATEGORY_MENU_MAP_SELECTOR_MATERIAL, "MAP");


// ---------------- MAP SELECTOR ----------------
        c.addDefault(ConfigPaths.GUI_MAP_SELECTOR_ROWS, 6);

// back-page
        c.addDefault(ConfigPaths.GUI_MAP_SELECTOR_BACK_PAGE_ENABLED, true);
        c.addDefault(ConfigPaths.GUI_MAP_SELECTOR_BACK_PAGE_SLOT, 18);
        c.addDefault(ConfigPaths.GUI_MAP_SELECTOR_BACK_PAGE_MATERIAL, "ARROW");

// next-page
        c.addDefault(ConfigPaths.GUI_MAP_SELECTOR_NEXT_PAGE_ENABLED, true);
        c.addDefault(ConfigPaths.GUI_MAP_SELECTOR_NEXT_PAGE_SLOT, 26);
        c.addDefault(ConfigPaths.GUI_MAP_SELECTOR_NEXT_PAGE_MATERIAL, "ARROW");

// back button
        c.addDefault(ConfigPaths.GUI_MAP_SELECTOR_BACK_ENABLED, true);
        c.addDefault(ConfigPaths.GUI_MAP_SELECTOR_BACK_SLOT, 49);
        c.addDefault(ConfigPaths.GUI_MAP_SELECTOR_BACK_MATERIAL, "ARROW");

// maps slots
        c.addDefault(ConfigPaths.GUI_MAP_SELECTOR_MAPS_SLOTS,
                Arrays.asList(10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,37,38,39,40,41,42,43)
        );

        c.addDefault(ConfigPaths.GUI_MAP_SELECTOR_MAPS_AVAILABLE_MATERIAL, "LIME_STAINED_GLASS");
        c.addDefault(ConfigPaths.GUI_MAP_SELECTOR_MAPS_UNAVAILABLE_MATERIAL, "RED_STAINED_GLASS");



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
