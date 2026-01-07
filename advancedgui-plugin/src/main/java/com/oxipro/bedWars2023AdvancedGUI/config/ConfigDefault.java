package com.oxipro.bedWars2023AdvancedGUI.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.*;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.ITEM_CATEGORIES_LORE;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.ITEM_CATEGORIES_NAME;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.ITEM_HBM_LORE;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.ITEM_HBM_NAME;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.ITEM_QB_LORE;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.ITEM_QB_NAME;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.ITEM_QUICKJOIN_LORE;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.ITEM_QUICKJOIN_NAME;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.NO_MAP_AVAILAIBLE_CATEGORY;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.REJOIN_UNAVAILABLE_LORE;

public class ConfigDefault {

    YamlConfiguration c;
    File file;

    public ConfigDefault() {
        setupConfig();
    }


    public YamlConfiguration setupConfig() {
        file = new File(Bukkit.getPluginManager().getPlugin("BWProxy2023").getDataFolder().getPath() + "/Addons/AdvancedGUI/", "config.yml");
        c = YamlConfiguration.loadConfiguration(file);

        c.addDefault(REJOIN_AVAILABLE_NAME, "<green>Rejoin Game");


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
