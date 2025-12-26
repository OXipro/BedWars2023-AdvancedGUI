package com.oxipro.bedWars2023AdvancedGUI.service;

import com.oxipro.bedWars2023AdvancedGUI.util.ArenaItem;
import com.tomkeuper.bedwars.proxy.api.*;
import com.tomkeuper.bedwars.proxy.arenamanager.ArenaManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class ArenaService {


    private final JavaPlugin plugin;
    private final BwProxyService bwProxyService;
    private Map<CachedArena, ItemStack> arenaItemStackMap = new LinkedHashMap<>();

    public ArenaService(JavaPlugin plugin, BwProxyService bwProxyService) {
        this.plugin = plugin;
        this.bwProxyService = bwProxyService;
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

    public Map<CachedArena, ItemStack> getArenaItemStackMap() {
        return arenaItemStackMap;
    }

    public Map<CachedArena, ItemStack> ArenaItemStackMapRefresh(Player player) {
        arenaItemStackMap.clear();
        for (CachedArena arena : getArenas(true)) {
            ItemStack item = new ArenaItem(arena, bwProxyService.getPlayerLanguage(player)).createArenaItem();
            arenaItemStackMap.put(arena, item);
        }
        return arenaItemStackMap;
    }

    public List<String> getMaps(String category) {
        // BWProxy hook here
        return List.of(
                "Airship",
                "Castle",
                "Forest",
                "Desert"
        );
    }

    public void quickJoinRandom() {
    }

    public void quickJoinCategoryRandom(String category) {
    }

    public void joinSpecificMap(String category, String map) {
        // BWProxy API join map
    }
}
