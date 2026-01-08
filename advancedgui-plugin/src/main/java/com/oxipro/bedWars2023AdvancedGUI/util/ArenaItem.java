package com.oxipro.bedWars2023AdvancedGUI.util;

import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

import static com.oxipro.bedWars2023AdvancedGUI.config.ConfigPaths.*;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.*;

public class ArenaItem {
    private Language language;
    private CachedArena arena;
    private FileConfiguration config;

    public ArenaItem(CachedArena arena, Language language, FileConfiguration configuration) {
        this.arena = arena;
        this.language = language;
        this.config = configuration;
    }

    private List<String> replaceArenaPlaceholders(List<String> inputStringList) {
        String arenaIdentifier = arena.getRemoteIdentifier();
        Map<String, String> placeholders = Map.of(
                "arena_display_name", arena.getDisplayName(language),
                "arena_identifier", arenaIdentifier,
                "arena_short_identifier", arenaIdentifier.substring(arenaIdentifier.indexOf('d') + 1),
                "arena_players", String.valueOf(arena.getCurrentPlayers()),
                "arena_max_players", String.valueOf(arena.getMaxPlayers()),
                "arena_status", String.valueOf(arena.getStatus())
        );
        return inputStringList.stream()
                .map(inputString -> {
                    for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                        inputString = inputString.replace("{" + entry.getKey() + "}", entry.getValue());
                    }
                    return inputString;
                })
                .toList();
    }

    public ItemStack createArenaItem() {

        String material = config.getString(GUI_MAIN_ARENAS_MATERIAL_NO_ARENA);
        String name = config.getString(GUI_ARENAS_DEFAULT_NAME);
        List<String> LoreList = config.getStringList(GUI_ARENAS_DEFAULT_LORE);

        int amount = arena != null ? Math.max(1, arena.getCurrentPlayers()) : 1; // prevent air with arena players 1

        if (arena == null) {
            return new ItemBuilder(material)
                    .name(config.getString(GUI_ARENAS_NO_ARENA_NAME))
                    .lore(config.getStringList(GUI_ARENAS_NO_ARENA_LORE))
                    .build();
        }

        switch (arena.getStatus()) {
            case WAITING:
                material = config.getString(GUI_MAIN_ARENAS_MATERIAL_WAITING);
                name = config.getString(GUI_ARENAS_WAITING_NAME);
                LoreList = replaceArenaPlaceholders(config.getStringList(GUI_ARENAS_WAITING_LORE));
                break;
            case STARTING:
                material = config.getString(GUI_MAIN_ARENAS_MATERIAL_STARTING);
                name = config.getString(GUI_ARENAS_STARTING_NAME);
                LoreList = replaceArenaPlaceholders(config.getStringList(GUI_ARENAS_STARTING_LORE));
                break;
            case PLAYING:
                config.getString(GUI_MAIN_ARENAS_MATERIAL_PLAYING);
                name = config.getString(GUI_ARENAS_PLAYING_NAME);
                LoreList = replaceArenaPlaceholders(config.getStringList(GUI_ARENAS_PLAYING_LORE));
                break;
            case RESTARTING:
                config.getString(GUI_MAIN_ARENAS_MATERIAL_RESTARTING);
                name = config.getString(GUI_ARENAS_RESTARTING_NAME);
                LoreList = replaceArenaPlaceholders(config.getStringList(GUI_ARENAS_RESTARTING_LORE));
                break;
        }

        String arenaIdentifier = arena.getRemoteIdentifier();
        return new ItemBuilder(material)
                .name(name)
                .lore(LoreList)
                .amount(amount)
                .setArena(arenaIdentifier)
                .build();
    }
}
