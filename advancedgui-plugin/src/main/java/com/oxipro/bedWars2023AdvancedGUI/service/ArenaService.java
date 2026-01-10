package com.oxipro.bedWars2023AdvancedGUI.service;

import com.oxipro.bedWars2023AdvancedGUI.language.LanguageManager;
import com.oxipro.bedWars2023AdvancedGUI.util.ArenaItem;
import com.tomkeuper.bedwars.proxy.api.*;
import com.tomkeuper.bedwars.proxy.arenamanager.ArenaManager;
import org.apache.commons.codec.language.bm.Lang;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.stream.Collectors;

public class ArenaService {


    private final JavaPlugin plugin;
    private final BwProxyService bwProxyService;
    private Map<CachedArena, ItemStack> arenaItemStackMap = new LinkedHashMap<>();
    private FileConfiguration configuration;
    private LanguageManager languageManager;

    public ArenaService(JavaPlugin plugin, BwProxyService bwProxyService, FileConfiguration configuration, LanguageManager languageManager) {
        this.plugin = plugin;
        this.bwProxyService = bwProxyService;
        this.configuration = configuration;
        this.languageManager = languageManager;
    }

    public List<CachedArena> getArenas(boolean onlyJoinable) {
        return ArenaManager.getArenas().stream()
                .filter(arena -> !onlyJoinable ||
                        arena.getStatus() == ArenaStatus.WAITING ||
                        arena.getStatus() == ArenaStatus.STARTING)

                .sorted(Comparator.comparingInt(CachedArena::getCurrentPlayers)
                        .reversed())
                .toList();
    }

    public static List<CachedArena> getMapsArena(Language language) {
        return ArenaManager.getArenas().stream()
                .filter(arena -> arena.getDisplayName(language) != null)
                .collect(Collectors.toMap(
                        arena -> arena.getDisplayName(language),
                        arena -> arena,
                        (a1, a2) -> a1
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey(String.CASE_INSENSITIVE_ORDER))
                .map(Map.Entry::getValue)
                .toList();
    }

    public static List<CachedArena> getMapsArenaByCategory(Language language, String category) {
        return getMapsArena(language).stream()
                .filter(arena -> category.equalsIgnoreCase(arena.getArenaGroup()))
                .toList();
    }

    public Map<CachedArena, ItemStack> getArenaItemStackMap() {
        return arenaItemStackMap;
    }

    public Map<CachedArena, ItemStack> ArenaItemStackMapRefresh(Player player) {
        arenaItemStackMap.clear();
        for (CachedArena arena : getArenas(true)) {
            ItemStack item = new ArenaItem(arena, configuration, bwProxyService, languageManager, player).createArenaItem();
            arenaItemStackMap.put(arena, item);
        }
        return arenaItemStackMap;
    }
}
