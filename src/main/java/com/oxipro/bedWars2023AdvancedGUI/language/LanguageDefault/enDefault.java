package com.oxipro.bedWars2023AdvancedGUI.language.LanguageDefault;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.*;

public class enDefault {

    YamlConfiguration c;
    File file;

    public enDefault() {
        setupLanguage();
    }

    public void setupLanguage() {
        file = new File(Bukkit.getPluginManager().getPlugin("BWProxy2023").getDataFolder().getPath() + "/Languages/", "messages_en.yml");
        c = YamlConfiguration.loadConfiguration(file);

        c.addDefault(REJOIN_AVAILABLE_NAME, "<green>Rejoin Game");
        c.addDefault(REJOIN_AVAILABLE_LORE, List.of("<green>Rejoin Game", "<yellow>Map: {arena_display_name}"));
        c.addDefault(REJOIN_UNAVAILABLE_NAME, "<red>No game to rejoin");
        c.addDefault(REJOIN_UNAVAILABLE_LORE, List.of("<red>There is no game to rejoin"));
        c.addDefault(NO_MAP_AVAILAIBLE_CATEGORY, "<red>There are no maps available for this category");
        c.addDefault(ITEM_QUICKJOIN_NAME, "Quick Join!");
        c.addDefault(ITEM_QUICKJOIN_LORE, List.of("<yellow>Quickly join the most ready to start game!"));
        c.addDefault(ITEM_CATEGORIES_NAME, "Categories");
        c.addDefault(ITEM_CATEGORIES_LORE, List.of("<yellow>Chose the BedWars Category"));
        c.addDefault(ITEM_HBM_NAME, "HotBar manager");
        c.addDefault(ITEM_HBM_LORE, List.of("<yellow>Open HotBar Manager"));
        c.addDefault(ITEM_QB_NAME, "SoonTM");
        c.addDefault(ITEM_QB_LORE, List.of("SOON tm"));



        c.options().copyDefaults(true);
        save();
    }

    public void save() {
        try {
            c.save(file);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save en language file", e);
        }
    }


}
