package com.oxipro.bedWars2023AdvancedGUI.gui;

import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.VersionSupport;
import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.util.RejoinItem;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.RemoteReJoin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.List;

import static com.oxipro.bedWars2023AdvancedGUI.config.ConfigPaths.*;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.*;

public class ModeGui extends AbstractGui {

    private RemoteReJoin rj;
    private final BwCategory category;
    private Player player;
    private FileConfiguration config;
    private final VersionSupport versionSupport;

    public ModeGui(GuiManager guiManager, BwCategory category, Player player) {
        super(guiManager, 4, "Selected Mode: " + category.getFancyName());
        this.category = category;
        this.player = player;
        this.rj = guiManager.resume().getReJoin(player);
        this.versionSupport = guiManager.getVersionSupport();
        this.config = guiManager.getConfigurationManager().getConfig();
        draw();
    }

    public BwCategory getCategory() {
        return category;
    }

    @Override
    protected void draw() {

        if (config.getBoolean(GUI_MODE_QUICK_JOIN_ENABLED)) {
            inventory.setItem(10,
                            versionSupport.itemBuilder(config.getString(GUI_MODE_QUICK_JOIN_MATERIAL))
                            .name("Random Map")
                            .build()
            );
        }

        if (config.getBoolean(GUI_MODE_MAP_SELECTOR_ENABLED)) {
            inventory.setItem(14,
                    versionSupport.itemBuilder(config.getString(GUI_MODE_MAP_SELECTOR_MATERIAL))
                            .name("Select Map")
                            .build()
            );
        }

        if (config.getBoolean(GUI_MODE_CATEGORIES_ENABLED)) {
            inventory.setItem(27,
                    versionSupport.itemBuilder(config.getString(GUI_MODE_CATEGORIES_MATERIAL))
                            .name("Categories")
                            .build()
            );
        }

        if (config.getBoolean(GUI_MODE_HOTBAR_ENABLED)) {
            inventory.setItem(28,
                    versionSupport.itemBuilder(config.getString(GUI_MODE_CATEGORIES_MATERIAL))
                            .name("Hotbar Manager")
                            .build()
            );
        }

        if (config.getBoolean(GUI_MODE_QUICK_BUY_ENABLED)) {
            inventory.setItem(29,
                    versionSupport.itemBuilder(config.getString(GUI_MODE_QUICK_BUY_MATERIAL))
                            .name("Quick Buy")
                            .build()
            );
        }

        if (config.getBoolean(GUI_MODE_REJOIN_ENABLED)) {
            inventory.setItem(29,
                    new RejoinItem(guiManager.getConfigurationManager().getConfig(), guiManager.getBwProxyService(), guiManager.getLanguageManager(), player, versionSupport)
                            .rejoin(rj)
                            .build()
            );
        }
    }

    @Override
    public void onClick(InventoryClickEvent event) {

        int slot = event.getRawSlot();

        Player player = (Player) event.getWhoClicked();

        if (slot == 12) {
            guiManager.getBwProxyService().getBwproxy().getArenaUtil().joinRandomFromGroup(player, category.getKey());
        }

        if (slot == 14) {
            List<CachedArena> cachedarenas = guiManager.arenas().getMapsArenaByCategory(guiManager.getBwProxyService().getPlayerLanguage(player), category.getKey());
            if (cachedarenas != null && !cachedarenas.isEmpty()) {
                guiManager.openMapSelectorGui(player, category);
            } else {player.sendMessage(guiManager.getRawMsg(player, MESSAGES_NO_MAP_AVAILABLE_CATEGORY));}
        }

        if (slot == 27) {
            guiManager.openCategoryGui(player);
        }

        if (slot == 28) {
            guiManager.getHBMService().getHbmAPI().getMenuUtil().openHotbarMenu(player);
        }

        if (slot == 29) {
            guiManager.categories().openQuickBuyEditor();
        }

        if (slot == 35) {
            guiManager.resume().rejoin(player);
        }
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        guiManager.removeOpenGui((Player) event.getPlayer());
    }
}
