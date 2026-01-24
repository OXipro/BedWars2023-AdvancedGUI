package com.oxipro.bedWars2023AdvancedGUI.util;

import com.oxipro.bedWars2023AdvancedGUI.service.BwProxyService;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class BW_Placeholders {
    private Language language;
    private CachedArena arena;
    private FileConfiguration config;
    private Player player;
    private BwProxyService bwProxyService;
    private String arenaIdentifier;
    private Map<String, String> placeholders;

    public BW_Placeholders(CachedArena arena, Player player, FileConfiguration config, BwProxyService bwProxyService) {
        this.arena = arena;
        this.config = config;
        this.player = player;
        this.bwProxyService = bwProxyService;
        this.arenaIdentifier = arena.getRemoteIdentifier();
    }

    public BW_Placeholders(CachedArena arena, Language language, FileConfiguration config, BwProxyService bwProxyService) {
        this.arena = arena;
        this.config = config;
        this.language = language;
        this.bwProxyService = bwProxyService;
        this.arenaIdentifier = arena.getRemoteIdentifier();
        this.placeholders = Map.of(
                "bw_arena_display_name", arena.getDisplayName(language),
                "bw_arena_identifier", arenaIdentifier,
                "bw_arena_short_identifier", arenaIdentifier.substring(arenaIdentifier.indexOf('d') + 1),
                "bw_arena_players", String.valueOf(arena.getCurrentPlayers()),
                "bw_arena_max_players", String.valueOf(arena.getMaxPlayers()),
                "bw_arena_status", String.valueOf(arena.getStatus()),
                "bw_arena_group", String.valueOf(arena.getDisplayGroup(language))
                );
    }

    public String replaceArenaPlaceholders(String inputString) {
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            inputString = inputString.replace("%" + entry.getKey() + "%", entry.getValue());
        }
        return inputString;
    }

    public List<String> replaceArenaPlaceholders(List<String> inputStringList) {
        return inputStringList.stream()
                .map(inputString -> {
                    for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                        inputString = inputString.replace("%" + entry.getKey() + "%", entry.getValue());
                    }
                    return inputString;
                })
                .toList();
    }


}
