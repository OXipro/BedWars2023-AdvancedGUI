package com.oxipro.bedWars2023AdvancedGUI.util;

import com.oxipro.bedWars2023AdvancedGUI.api.ItemType;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.VersionSupport;
import com.oxipro.bedWars2023AdvancedGUI.api.gui.category.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.language.LanguageManager;
import com.oxipro.bedWars2023AdvancedGUI.service.BwProxyService;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import com.tomkeuper.bedwars.proxy.api.RemoteReJoin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static com.oxipro.bedWars2023AdvancedGUI.config.ConfigPaths.*;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.*;

public class RejoinItem {
    private Language language;
    private CachedArena arena;
    private FileConfiguration config;
    private BwProxyService bwProxyService;
    private LanguageManager languageManager;
    private Player player;
    private final VersionSupport versionSupport;

    private String mt;
    private String rjg;
    private List<String> lore;
    private RemoteReJoin rj;
    private BwCategory category;



    public RejoinItem(FileConfiguration configuration, BwProxyService bwProxyService, LanguageManager languageManager, Player player, VersionSupport versionSupport) {
        this.config = configuration;
        this.bwProxyService = bwProxyService;
        this.languageManager = languageManager;
        this.player = player;
        this.language = bwProxyService.getPlayerLanguage(player);
        this.versionSupport = versionSupport;
    }

    public RejoinItem rejoin(RemoteReJoin rj) {
        this.rj = rj;
        if (rj == null) {
            mt = config.getString(GUI_MAIN_REJOIN_MATERIAL_UNAVAILABLE);
            rjg = languageManager.getRawMsg(player, GUI_REJOIN_UNAVAILABLE_NAME);
            lore = languageManager.getRawMsgList(player, GUI_REJOIN_UNAVAILABLE_LORE);
        } else {
            this.arena = rj.getArena();
            mt = config.getString(GUI_MAIN_REJOIN_MATERIAL_AVAILABLE);
            rjg = languageManager.getRawMsg(player, GUI_REJOIN_AVAILABLE_NAME);
            lore = languageManager.getRawMsgList(player, GUI_REJOIN_AVAILABLE_LORE);
        }
        return this;
    }

    public ItemStack build() {
        return versionSupport.itemBuilder(mt, languageManager)
                .setLanguage(bwProxyService.getPlayerLanguage(player))
                .setType(ItemType.REJOIN)
                .player(player)
                .setArena(arena)
                .name(rjg)
                .lore(lore)
                .build();
    }
}
