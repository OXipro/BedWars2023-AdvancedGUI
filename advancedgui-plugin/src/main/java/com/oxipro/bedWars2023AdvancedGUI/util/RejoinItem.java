package com.oxipro.bedWars2023AdvancedGUI.util;

import com.oxipro.bedWars2023AdvancedGUI.gui.GuiManager;
import com.oxipro.bedWars2023AdvancedGUI.language.LanguageManager;
import com.oxipro.bedWars2023AdvancedGUI.service.BwProxyService;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import com.tomkeuper.bedwars.proxy.api.RemoteReJoin;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static com.oxipro.bedWars2023AdvancedGUI.config.ConfigPaths.*;
import static com.oxipro.bedWars2023AdvancedGUI.config.ConfigPaths.GUI_MAIN_ARENAS_MATERIAL_PLAYING;
import static com.oxipro.bedWars2023AdvancedGUI.config.ConfigPaths.GUI_MAIN_ARENAS_MATERIAL_RESTARTING;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.*;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.GUI_ARENAS_RESTARTING_LORE;

public class RejoinItem {
    private Language language;
    private CachedArena arena;
    private FileConfiguration config;
    private BwProxyService bwProxyService;
    private BW_Placeholders bwPlaceholders;
    private LanguageManager languageManager;
    private Player player;


    public RejoinItem(FileConfiguration configuration, BwProxyService bwProxyService, LanguageManager languageManager, Player player) {
        this.config = configuration;
        this.bwProxyService = bwProxyService;
        this.languageManager = languageManager;
        this.player = player;
        this.language = bwProxyService.getPlayerLanguage(player);
    }

    public ItemStack createRejoinItem(RemoteReJoin rj) {

        String rjg;
            String mt;
            List<String> lore;
            if (rj == null) {
                mt = config.getString(GUI_MAIN_REJOIN_MATERIAL_UNAVAILABLE);
                rjg = languageManager.getRawMsg(player, GUI_REJOIN_UNAVAILABLE_NAME);
                lore = languageManager.getRawMsgList(player, GUI_REJOIN_UNAVAILABLE_LORE);
            } else {
                this.arena = rj.getArena();
                this.bwPlaceholders = new BW_Placeholders(arena, language, config, bwProxyService);
                mt = config.getString(GUI_MAIN_REJOIN_MATERIAL_AVAILABLE);
                rjg = bwPlaceholders.replaceArenaPlaceholders(languageManager.getRawMsg(player, GUI_REJOIN_AVAILABLE_NAME));
                lore = bwPlaceholders.replaceArenaPlaceholders(languageManager.getRawMsgList(player, GUI_REJOIN_AVAILABLE_LORE));
            }

            return new ItemBuilder(mt)
                            .name(rjg)
                            .lore(lore)
                            .build();
    }
}
