package com.oxipro.bedWars2023AdvancedGUI.language.LanguageDefault;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
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

        final String newfile = c.getString("addons.advanced-gui");



        // ========== MAIN GUI ==========
        c.addDefault(GUI_MAIN_TITLE, "Play BedWars!");

        // --- QUICK ARENAS ---
        c.addDefault(GUI_ARENAS_DEFAULT_NAME, "<aqua>Arena: %bw_arena_short_identifier%");
        c.addDefault(GUI_ARENAS_DEFAULT_LORE, Arrays.asList(
                "",
                " <grey>Map:<white> %bw_arena_display_name%",
                " <grey>Category:<white> %bw_arena_group%",
                " <grey>Online:<white> %bw_arena_players%/%bw_arena_max_players%",
                " <grey>Status: %bw_arena_status%",
                "",
                "<b><color:#ffb300>Click to join!</color></b>"
        ));


        c.addDefault(GUI_ARENAS_WAITING, Collections.emptyList());
        c.addDefault(GUI_ARENAS_RESTARTING, Collections.emptyList());
        c.addDefault(GUI_ARENAS_STARTING, Collections.emptyList());
        c.addDefault(GUI_ARENAS_RESTARTING, Collections.emptyList());

//        c.addDefault(GUI_ARENAS_WAITING_NAME, null);
//        c.addDefault(GUI_ARENAS_WAITING_LORE, Collections.emptyList());
//
//        c.addDefault(GUI_ARENAS_STARTING_NAME, null);
//        c.addDefault(GUI_ARENAS_STARTING_LORE, Collections.emptyList());
//
//        c.addDefault(GUI_ARENAS_PLAYING_NAME, null);
//        c.addDefault(GUI_ARENAS_PLAYING_LORE, Collections.emptyList());

        c.addDefault(GUI_ARENAS_NO_ARENA_NAME, "<red>No arena available");
        c.addDefault(GUI_ARENAS_NO_ARENA_LORE, Arrays.asList(
                "",
                " <red>There is no arena available to rejoin.",
                ""
        ));


        // --- REJOIN ---
        c.addDefault(GUI_REJOIN_AVAILABLE_NAME, "<green>Rejoin Game");
        c.addDefault(GUI_REJOIN_AVAILABLE_LORE, Arrays.asList(
                "<green>Rejoin Game",
                "<yellow>Map: %bw_arena_display_name%"
        ));

        c.addDefault(GUI_REJOIN_UNAVAILABLE_NAME, "<red>No game to rejoin");
        c.addDefault(GUI_REJOIN_UNAVAILABLE_LORE, Arrays.asList(
                "<red>There is no game to rejoin"
        ));


        // --- QUICK JOIN ---
        c.addDefault(GUI_QUICK_JOIN_NAME, "Quick Join!");
        c.addDefault(GUI_QUICK_JOIN_LORE, Arrays.asList(
                "<yellow>Quickly join the most ready to start game!"
        ));


        // --- CATEGORIES ---
        c.addDefault(GUI_CATEGORIES_NAME, "Categories");
        c.addDefault(GUI_CATEGORIES_LORE, Arrays.asList(
                "<yellow>Chose the BedWars Category"
        ));


        // --- HOTBAR MANAGER ---
        c.addDefault(GUI_HBM_NAME, "HotBar manager");
        c.addDefault(GUI_HBM_LORE, Arrays.asList(
                "<yellow>Open HotBar Manager"
        ));


        // --- QUICK BUY ---
        c.addDefault(GUI_QUICK_BUY_NAME, "SoonTM");
        c.addDefault(GUI_QUICK_BUY_LORE, Arrays.asList(
                "SOON tm"
        ));



        // ========== CATEGORIES SELECTOR ==========
        c.addDefault(GUI_CATEGORIES_SELECTOR_TITLE, "Select a BedWars Category");

        if (newfile == null) {
            c.addDefault(GUI_CATEGORIES_SELECTOR_CATEGORIES + "1x8.name", "<green>BedWars Solo - 1x8");
            c.addDefault(GUI_CATEGORIES_SELECTOR_CATEGORIES + "1x8.lore", Arrays.asList(
                    "1 player in 8 teams",
                    "",
                    "<b><color:#ffb300>Click to join!</color></b>"
            ));
            c.addDefault(GUI_CATEGORIES_SELECTOR_CATEGORIES + "2x8.name", "<green>BedWars Doubles - 2x8");
            c.addDefault(GUI_CATEGORIES_SELECTOR_CATEGORIES + "2x8.lore", Arrays.asList(
                    "2 players in 8 teams",
                    "",
                    "<b><color:#ffb300>Click to join!</color></b>"
            ));
            c.addDefault(GUI_CATEGORIES_SELECTOR_CATEGORIES + "3x4.name", "<green>BedWars Triples - 3x4");
            c.addDefault(GUI_CATEGORIES_SELECTOR_CATEGORIES + "3x4.lore", Arrays.asList(
                    "3 players in 4 teams",
                    "",
                    "<b><color:#ffb300>Click to join!</color></b>"
            ));
            c.addDefault(GUI_CATEGORIES_SELECTOR_CATEGORIES + "4x4.name", "<green>BedWars Squad - 4x4");
            c.addDefault(GUI_CATEGORIES_SELECTOR_CATEGORIES + "4x4.lore", Arrays.asList(
                    "4 players in 4 teams",
                    "",
                    "<b><color:#ffb300>Click to join!</color></b>"
            ));
            c.addDefault(GUI_CATEGORIES_SELECTOR_CATEGORIES + "2x2.name", "<green>BedWars Doubles Duels - 2x2");
            c.addDefault(GUI_CATEGORIES_SELECTOR_CATEGORIES + "2x2.lore", Arrays.asList(
                    "2 players in 2 teams",
                    "",
                    "<b><color:#ffb300>Click to join!</color></b>"
            ));
        }

        // ========== CATEGORY MENU ==========
        c.addDefault(GUI_CATEGORY_MENU_TITLE, "Selected Category: %bw_selected_category%");

        // rejoin
        c.addDefault(GUI_CATEGORY_MENU_REJOIN_AVAILABLE_NAME, "<green>Rejoin Game");
        c.addDefault(GUI_CATEGORY_MENU_REJOIN_AVAILABLE_LORE, Arrays.asList(
                "<green>Rejoin Game",
                "<yellow>Map: %bw_arena_display_name%"
        ));

        c.addDefault(GUI_CATEGORY_MENU_REJOIN_UNAVAILABLE_NAME, "<red>No game to rejoin");
        c.addDefault(GUI_CATEGORY_MENU_REJOIN_UNAVAILABLE_LORE, Arrays.asList(
                "<red>There is no game to rejoin"
        ));


        // quick join
        c.addDefault(GUI_CATEGORY_MENU_QUICK_JOIN_NAME, "Quick Join!");
        c.addDefault(GUI_CATEGORY_MENU_QUICK_JOIN_LORE, Arrays.asList(
                "<yellow>Quickly join the most ready to start game!"
        ));


        // categories
        c.addDefault(GUI_CATEGORY_MENU_CATEGORIES_NAME, "Categories");
        c.addDefault(GUI_CATEGORY_MENU_CATEGORIES_LORE, Arrays.asList(
                "<yellow>Chose the BedWars Category"
        ));


        // hotbar manager
        c.addDefault(GUI_CATEGORY_MENU_HBM_NAME, "HotBar manager");
        c.addDefault(GUI_CATEGORY_MENU_HBM_LORE, Arrays.asList(
                "<yellow>Open HotBar Manager"
        ));


        // quick buy
        c.addDefault(GUI_CATEGORY_MENU_QUICK_BUY_NAME, "SoonTM");
        c.addDefault(GUI_CATEGORY_MENU_QUICK_BUY_LORE, Arrays.asList(
                "SOON tm"
        ));


        // map selector
        c.addDefault(GUI_CATEGORY_MENU_MAP_SELECTOR_NAME, "<green>Choose Map");
        c.addDefault(GUI_CATEGORY_MENU_MAP_SELECTOR_LORE, Arrays.asList(
            "",
            " Pick which map you want to play!",
            "",
            "<b><color:#ffb300>Click to choose!</color></b>"
        ));



        // ========== MAP SELECTOR ==========
        c.addDefault(GUI_MAP_SELECTOR_TITLE, "Choose a map in %bw_selected_category%");

        // pages
        c.addDefault(GUI_MAP_BACK_PAGE_NAME, "<green>Previous Page");
        c.addDefault(GUI_MAP_BACK_PAGE_LORE, Collections.emptyList());

        c.addDefault(GUI_MAP_NEXT_PAGE_NAME, "<green>Next Page");
        c.addDefault(GUI_MAP_NEXT_PAGE_LORE, Collections.emptyList());

        c.addDefault(GUI_MAP_BACK_NAME, "<green>Back to category");
        c.addDefault(GUI_MAP_BACK_LORE, Collections.emptyList());


        // map states
        c.addDefault(GUI_MAPS_AVAILABLE_NAME, "<green>%bw_map_display_name%");
        c.addDefault(GUI_MAPS_AVAILABLE_LORE, Arrays.asList(
                "",
                "Available Map selections: <green>soonTM",
                "",
                "<b><color:#ffb300>Click to join!</color></b>"
        ));

        c.addDefault(GUI_MAPS_UNAVAILABLE_NAME, "<red>No map available");
        c.addDefault(GUI_MAPS_UNAVAILABLE_LORE, Collections.emptyList());



        // ========== MESSAGES ==========
        c.addDefault(MESSAGES_NO_MAP_AVAILABLE_CATEGORY, "<red>There are no maps available for this category");




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
