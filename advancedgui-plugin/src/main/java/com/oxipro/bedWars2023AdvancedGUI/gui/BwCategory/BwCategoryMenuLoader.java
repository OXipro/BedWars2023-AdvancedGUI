package com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BwCategoryMenuLoader {

    private final FileConfiguration config;

    public BwCategoryMenuLoader(FileConfiguration config) {
        this.config = config;
    }

    public BwCategoryMenu load() {

        ConfigurationSection root =
                config.getConfigurationSection("bw-categories-menu");

        if (root == null) {
            throw new IllegalStateException("bw-categories-menu not found in config.yml");
        }

        int menuRows = root.getInt("menu-rows", 6);
        String title = root.getString("title", "Categories");

        Map<String, BwCategory> categories = new HashMap<>();

        ConfigurationSection categoriesSection =
                root.getConfigurationSection("categories");

        if (categoriesSection != null) {
            for (String key : categoriesSection.getKeys(false)) {

                ConfigurationSection section =
                        categoriesSection.getConfigurationSection(key);

                if (section == null) continue;

                String fancyName = section.getString("fancy-name", null);
                int slot = section.getInt("slot");
                String material = section.getString("item-material", "STONE");
                int amount = section.getInt("item-amount", 1);
                String itemName = section.getString("item-name", "");
                List<String> lore = section.getStringList("item-lore");
                boolean glow = section.getBoolean("item-glow", false);

                BwCategory category = new BwCategory(
                        key,
                        fancyName,
                        slot,
                        material,
                        amount,
                        itemName,
                        lore,
                        glow
                );

                categories.put(key, category);
            }
        }

        return new BwCategoryMenu(menuRows, title, categories);
    }
}
