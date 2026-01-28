package com.oxipro.bedWars2023AdvancedGUI.gui;


import com.oxipro.bedWars2023AdvancedGUI.api.gui.category.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.config.ConfigPaths;
import com.oxipro.bedWars2023AdvancedGUI.language.LanguageManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class BwCategoryMenuLoader {

    private final FileConfiguration config;
    private final LanguageManager languageManager;

    public BwCategoryMenuLoader(FileConfiguration config, LanguageManager languageManager) {
        this.config = config;
        this.languageManager = languageManager;
    }

    public Map<String, BwCategory> load() {

        Map<String, BwCategory> categories = new HashMap<>();

        ConfigurationSection configCategoriesSection = config.getConfigurationSection(ConfigPaths.
                GUI_CATEGORIES_SELECTOR_CATEGORIES);

        for (String key : configCategoriesSection.getKeys(false)) {

            ConfigurationSection section = configCategoriesSection.getConfigurationSection(key);

            if (section == null) continue;

            int slot = section.getInt("slot");
            String material = section.getString("item-material", "STONE");
            int amount = section.getInt("item-amount", 1);


            BwCategory category = new BwCategory(
                    key,
                    slot,
                    material,
                    amount
            );

            categories.put(key, category);
        }

        return categories;
    }
}
