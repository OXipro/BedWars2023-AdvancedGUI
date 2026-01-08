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

        c.addDefault(GUI_ARENAS_DEFAULT_NAME, "<aqua>Arena: {arena_short_identifier}");
        c.addDefault(GUI_ARENAS_DEFAULT_LORE, List.of(
                "",
                " <grey>Map:<white> {arena_display_name}",
                " <grey>Category:<white> {arena_group}",
                " <grey>Online:<white> {arena_players}/{arena_max_players}",
                " <grey>Status: {arena_status}",
                "",
                "<b><color:#ffb300>Click to join!</color></b>"
        ));

        c.addDefault(GUI_ARENAS_WAITING, Collections.emptyList());

        c.addDefault(GUI_ARENAS_STARTING, Collections.emptyList());

        c.addDefault(GUI_ARENAS_PLAYING, Collections.emptyList());

        c.addDefault(GUI_ARENAS_RESTARTING_NAME, "<aqua>Restarting: {arena_short_identifier}");
        c.addDefault(GUI_ARENAS_RESTARTING_LORE, List.of(
                "",
                " <blue>this arena is restarting you can't join it",
                ""
        ));

        c.addDefault(GUI_ARENAS_NO_ARENA_NAME, "<red>No arena available");
        c.addDefault(GUI_ARENAS_NO_ARENA_LORE, List.of(
                "",
                " <red>There is no arena available to rejoin.",
                ""
        ));

        c.addDefault("addons.advanced-gui.gui.quick-arenas.no-arena.name", "<red>No arena available");
        c.addDefault("addons.advanced-gui.gui.quick-arenas.no-arena.lore", List.of(
                "",
                " <red>There is no arena available to rejoin.",
                ""
        ));
        c.addDefault(GUI_REJOIN_AVAILABLE_NAME, "<green>Rejoin Game");
        c.addDefault(GUI_REJOIN_AVAILABLE_LORE, List.of(
                "<green>Rejoin Game",
                "<yellow>Map: {arena_display_name}"
        ));

        c.addDefault(GUI_REJOIN_UNAVAILABLE_NAME, "<red>No game to rejoin");
        c.addDefault(GUI_REJOIN_UNAVAILABLE_LORE, List.of(
                "<red>There is no game to rejoin"
        ));

        c.addDefault(GUI_QUICK_JOIN_NAME, "Quick Join!");
        c.addDefault(GUI_QUICK_JOIN_LORE, List.of(
                "<yellow>Quickly join the most ready to start game!"
        ));

        c.addDefault(GUI_CATEGORIES_NAME, "Categories");
        c.addDefault(GUI_CATEGORIES_LORE, List.of(
                "<yellow>Chose the BedWars Category"
        ));

        c.addDefault(GUI_HBM_NAME, "HotBar manager");
        c.addDefault(GUI_HBM_LORE, List.of(
                "<yellow>Open HotBar Manager"
        ));

        c.addDefault(GUI_QUICK_BUY_NAME, "SoonTM");
        c.addDefault(GUI_QUICK_BUY_LORE, List.of(
                "SOON tm"
        ));
        
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
