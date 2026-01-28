package com.oxipro.bedWars2023AdvancedGUI.util;

import com.oxipro.bedWars2023AdvancedGUI.api.ItemType;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.VersionSupport;
import com.oxipro.bedWars2023AdvancedGUI.language.LanguageManager;
import com.oxipro.bedWars2023AdvancedGUI.service.BwProxyService;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static com.oxipro.bedWars2023AdvancedGUI.config.ConfigPaths.*;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.*;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.GUI_ARENAS_NO_ARENA_LORE;

public class ArenaItem {
    private Language language;
    private CachedArena arena;
    private FileConfiguration config;
    private BwProxyService bwProxyService;
    private LanguageManager languageManager;
    private Player player;
    private VersionSupport versionSupport;

    public ArenaItem(CachedArena arena, FileConfiguration configuration, BwProxyService bwProxyService, LanguageManager languageManager, Player player, VersionSupport versionSupport) {
        this.arena = arena;
        this.config = configuration;
        this.bwProxyService = bwProxyService;
        this.languageManager = languageManager;
        this.player = player;
        this.language = bwProxyService.getPlayerLanguage(player);
        this.versionSupport = versionSupport;
    }



    public ItemStack createArenaItem() {

        String material = config.getString(GUI_MAIN_ARENAS_MATERIAL_NO_ARENA);
        String name = languageManager.getRawMsg(player, GUI_ARENAS_NO_ARENA_NAME);
        List<String> LoreList = languageManager.getRawMsgList(player, GUI_ARENAS_NO_ARENA_LORE);

        String defaultName = languageManager.getRawMsg(player, GUI_ARENAS_DEFAULT_NAME);
        List<String> defaultLoreList = languageManager.getRawMsgList(player, GUI_ARENAS_DEFAULT_LORE);

        if (arena == null) {
            return versionSupport.itemBuilder(material, languageManager)
                    .player(player)
                    .setLanguage(language)
                    .name(languageManager.getRawMsg(player, GUI_ARENAS_NO_ARENA_NAME))
                    .lore(languageManager.getRawMsgList(player, GUI_ARENAS_NO_ARENA_LORE))
                    .build();
        }

        int amount = arena != null ? Math.max(1, arena.getCurrentPlayers()) : 1; // prevent air with arena players 1

        switch (arena.getStatus()) {
            case WAITING:
                material = config.getString(GUI_MAIN_ARENAS_MATERIAL_WAITING);
                if (languageManager.existAndNotNull(player, GUI_ARENAS_WAITING_NAME)) {
                    name = languageManager.getRawMsg(player, GUI_ARENAS_WAITING_NAME);
                } else {
                    name = defaultName;
                }
                if (languageManager.existAndNotNull(player, GUI_ARENAS_WAITING_LORE)) {
                    LoreList = languageManager.getRawMsgList(player, GUI_ARENAS_WAITING_LORE);
                } else {
                    LoreList = defaultLoreList;
                }
                break;
            case STARTING:
                material = config.getString(GUI_MAIN_ARENAS_MATERIAL_STARTING);
                if (languageManager.existAndNotNull(player, GUI_ARENAS_STARTING_NAME)) {
                    name = languageManager.getRawMsg(player, GUI_ARENAS_STARTING_NAME);
                } else {
                    name = defaultName;
                }
                if (languageManager.existAndNotNull(player, GUI_ARENAS_STARTING_LORE)) {
                    LoreList = languageManager.getRawMsgList(player, GUI_ARENAS_STARTING_LORE);
                } else {
                    LoreList = defaultLoreList;
                }
                break;
            case PLAYING:
                material = config.getString(GUI_MAIN_ARENAS_MATERIAL_PLAYING);
                if (languageManager.existAndNotNull(player, GUI_ARENAS_PLAYING_NAME)) {
                    name = languageManager.getRawMsg(player, GUI_ARENAS_PLAYING_NAME);
                } else {
                    name = defaultName;
                }
                if (languageManager.existAndNotNull(player, GUI_ARENAS_PLAYING_LORE)) {
                    LoreList = languageManager.getRawMsgList(player, GUI_ARENAS_PLAYING_LORE);
                } else {
                    LoreList = defaultLoreList;
                }
                break;
            case RESTARTING:
                material = config.getString(GUI_MAIN_ARENAS_MATERIAL_RESTARTING);
                if (languageManager.existAndNotNull(player, GUI_ARENAS_RESTARTING_NAME)) {
                    name = languageManager.getRawMsg(player, GUI_ARENAS_RESTARTING_NAME);
                } else {
                    name = defaultName;
                }
                if (languageManager.existAndNotNull(player, GUI_ARENAS_RESTARTING_LORE)) {
                    LoreList = languageManager.getRawMsgList(player, GUI_ARENAS_RESTARTING_LORE);
                } else {
                    LoreList = defaultLoreList;
                }
                break;
        }

        return versionSupport.itemBuilder(material, languageManager)
                .setLanguage(language)
                .setArena(arena)
                .player(player)
                .setType(ItemType.QUICK_ARENA)
                .name(name)
                .lore(LoreList)
                .amount(amount)
                .build();
    }
}
