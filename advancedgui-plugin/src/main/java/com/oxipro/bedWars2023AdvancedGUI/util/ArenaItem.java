package com.oxipro.bedWars2023AdvancedGUI.util;

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

public class ArenaItem {
    private Language language;
    private CachedArena arena;
    private FileConfiguration config;
    private BwProxyService bwProxyService;
    private BW_Placeholders bwPlaceholders;
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
        String name = languageManager.getRawMsg(player, GUI_ARENAS_DEFAULT_NAME);
        List<String> LoreList = languageManager.getRawMsgList(player, GUI_ARENAS_DEFAULT_LORE);

        if (arena == null) {
            return versionSupport.itemBuilder(material)
                    .name(languageManager.getRawMsg(player, GUI_ARENAS_NO_ARENA_NAME))
                    .lore(languageManager.getRawMsgList(player, GUI_ARENAS_NO_ARENA_LORE))
                    .build();
        }
        this.bwPlaceholders = new BW_Placeholders(arena, language, config, bwProxyService);

        int amount = arena != null ? Math.max(1, arena.getCurrentPlayers()) : 1; // prevent air with arena players 1
        switch (arena.getStatus()) {
            case WAITING:
                material = config.getString(GUI_MAIN_ARENAS_MATERIAL_WAITING);
                name = bwPlaceholders.replaceArenaPlaceholders(languageManager.getRawMsg(player, GUI_ARENAS_WAITING_NAME));
                LoreList = bwPlaceholders.replaceArenaPlaceholders(languageManager.getRawMsgList(player, GUI_ARENAS_WAITING_LORE));
                break;
            case STARTING:
                material = config.getString(GUI_MAIN_ARENAS_MATERIAL_STARTING);
                name = bwPlaceholders.replaceArenaPlaceholders(languageManager.getRawMsg(player, GUI_ARENAS_STARTING_NAME));
                LoreList = bwPlaceholders.replaceArenaPlaceholders(languageManager.getRawMsgList(player, GUI_ARENAS_STARTING_LORE));
                break;
            case PLAYING:
                material = config.getString(GUI_MAIN_ARENAS_MATERIAL_PLAYING);
                name = bwPlaceholders.replaceArenaPlaceholders(languageManager.getRawMsg(player, GUI_ARENAS_PLAYING_NAME));
                LoreList = bwPlaceholders.replaceArenaPlaceholders(languageManager.getRawMsgList(player, GUI_ARENAS_PLAYING_LORE));
                break;
            case RESTARTING:
                material = config.getString(GUI_MAIN_ARENAS_MATERIAL_RESTARTING);
                name = bwPlaceholders.replaceArenaPlaceholders(languageManager.getRawMsg(player, GUI_ARENAS_RESTARTING_NAME));
                LoreList = bwPlaceholders.replaceArenaPlaceholders(languageManager.getRawMsgList(player, GUI_ARENAS_RESTARTING_LORE));
                break;
        }

        String arenaIdentifier = arena.getRemoteIdentifier();
        return versionSupport.itemBuilder(material)
                .name(name)
                .lore(LoreList)
                .amount(amount)
                .setArena(arenaIdentifier)
                .player(player)
                .build();
    }
}
