package com.oxipro.bedWars2023AdvancedGUI.gui;

import com.oxipro.bedWars2023AdvancedGUI.api.ItemType;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.VersionSupport;
import com.oxipro.bedWars2023AdvancedGUI.api.gui.category.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.api.text.placeholders.PrasePlaceholders;
import com.oxipro.bedWars2023AdvancedGUI.language.LanguageManager;
import com.oxipro.bedWars2023AdvancedGUI.util.RejoinItem;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import com.tomkeuper.bedwars.proxy.api.RemoteReJoin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static com.oxipro.bedWars2023AdvancedGUI.config.ConfigPaths.*;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.*;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.GUI_CATEGORY_MENU_QUICK_BUY_LORE;

public class CategoryMenuGui extends AbstractGui {

    private RemoteReJoin rj;
    private final BwCategory category;
    private Player player;
    private FileConfiguration config;
    private final VersionSupport versionSupport;
    private final LanguageManager languageManager;
    private final Map<ItemType, BiConsumer<Player, InventoryClickEvent>> itemActions = new EnumMap<>(ItemType.class);
    private final Language playerLanguage;


    public CategoryMenuGui(GuiManager guiManager, BwCategory category, Player player) {
        super(guiManager, guiManager.getConfigurationManager().getConfig().getInt(GUI_CATEGORY_MENU_ROWS), new PrasePlaceholders(player, guiManager.getBwProxyService().getPlayerLanguage(player), guiManager.getLanguageManager()).setCategory(category).prase(guiManager.getLanguageManager().getRawMsg(player, GUI_CATEGORY_MENU_TITLE)));
        this.category = category;
        this.player = player;
        this.rj = guiManager.resume().getReJoin(player);
        this.versionSupport = guiManager.getVersionSupport();
        this.config = guiManager.getConfigurationManager().getConfig();
        this.languageManager = guiManager.getLanguageManager();
        this.playerLanguage = guiManager.getBwProxyService().getPlayerLanguage(player);
        init();
        draw();
    }

    protected void init() {
        itemActions.put(ItemType.QUICK_JOIN, (player, event) -> {
            guiManager.getBwProxyService().getBwproxy().getArenaUtil().joinRandomFromGroup(player, category.getKey());
        });

        itemActions.put(ItemType.CATEGORIES_SELECTOR_OPENER, (player, event) -> {
            guiManager.openCategoryGui(player);
        });

        itemActions.put(ItemType.HOTBAR_MANAGER_OPENER, (player, event) -> {
            guiManager.markIgnoreClose(player);
            guiManager.getHBMService().getHbmAPI().getMenuUtil().openCategoryMenu(player);
        });

        itemActions.put(ItemType.REJOIN, (player, event) -> {
            if (rj != null) {
                guiManager.resume().rejoin(player);
            }
        });

        itemActions.put(ItemType.MAP_SELECTOR_OPENER, (player, event) -> {
            List<CachedArena> cachedarenas = guiManager.arenas().getMapsArenaByCategory(playerLanguage, category.getKey());
            if (cachedarenas != null && !cachedarenas.isEmpty()) {
                guiManager.openMapSelectorGui(player, category);
            } else {
                player.sendMessage(guiManager.getRawMsg(player, MESSAGES_NO_MAP_AVAILABLE_CATEGORY));
            }
        });

    }

    public BwCategory getCategory() {
        return category;
    }

    @Override
    protected void draw() {

        if (config.getBoolean(GUI_CATEGORY_MENU_QUICK_JOIN_ENABLED)) {
            inventory.setItem(config.getInt(GUI_CATEGORY_MENU_QUICK_JOIN_SLOT),
                    versionSupport.itemBuilder(config.getString(GUI_CATEGORY_MENU_QUICK_JOIN_MATERIAL), languageManager)
                            .setLanguage(playerLanguage)
                            .player(player)
                            .setCategory(category)
                            .setType(ItemType.QUICK_JOIN)
                            .name(languageManager.getRawMsg(player, GUI_QUICK_JOIN_NAME))
                            .lore(languageManager.getRawMsgList(player, GUI_QUICK_JOIN_LORE))
                            .build()
            );
        }

        if (config.getBoolean(GUI_CATEGORY_MENU_MAP_SELECTOR_ENABLED)) {
            inventory.setItem(config.getInt(GUI_CATEGORY_MENU_MAP_SELECTOR_SLOT),
                    versionSupport.itemBuilder(config.getString(GUI_CATEGORY_MENU_MAP_SELECTOR_MATERIAL), languageManager)
                            .setLanguage(playerLanguage)
                            .player(player)
                            .setCategory(category)
                            .setType(ItemType.MAP_SELECTOR_OPENER)
                            .name(languageManager.getRawMsg(player, GUI_CATEGORY_MENU_MAP_SELECTOR_NAME))
                            .lore(languageManager.getRawMsgList(player, GUI_CATEGORY_MENU_MAP_SELECTOR_LORE))
                            .build()
            );
        }

        if (config.getBoolean(GUI_CATEGORY_MENU_CATEGORIES_ENABLED)) {
            inventory.setItem(config.getInt(GUI_CATEGORY_MENU_CATEGORIES_SLOT),
                    versionSupport.itemBuilder(config.getString(GUI_CATEGORY_MENU_CATEGORIES_MATERIAL), languageManager)
                            .setLanguage(playerLanguage)
                            .player(player)
                            .setCategory(category)
                            .setType(ItemType.CATEGORIES_SELECTOR_OPENER)
                            .name(languageManager.getRawMsg(player, GUI_CATEGORY_MENU_CATEGORIES_NAME))
                            .lore(languageManager.getRawMsgList(player, GUI_CATEGORY_MENU_CATEGORIES_LORE))
                            .build()
            );
        }

        if (config.getBoolean(GUI_CATEGORY_MENU_HOTBAR_ENABLED)) {
            inventory.setItem(config.getInt(GUI_CATEGORY_MENU_HOTBAR_SLOT),
                    versionSupport.itemBuilder(config.getString(GUI_CATEGORY_MENU_HOTBAR_MATERIAL), languageManager)
                            .setLanguage(playerLanguage)
                            .player(player)
                            .setCategory(category)
                            .setType(ItemType.HOTBAR_MANAGER_OPENER)
                            .name(languageManager.getRawMsg(player, GUI_CATEGORY_MENU_HBM_NAME))
                            .lore(languageManager.getRawMsgList(player, GUI_CATEGORY_MENU_HBM_LORE))
                            .build()
            );
        }

        if (config.getBoolean(GUI_CATEGORY_MENU_QUICK_BUY_ENABLED)) {
            inventory.setItem(config.getInt(GUI_CATEGORY_MENU_QUICK_BUY_SLOT),
                    versionSupport.itemBuilder(config.getString(GUI_CATEGORY_MENU_QUICK_BUY_MATERIAL), languageManager)
                            .setLanguage(playerLanguage)
                            .player(player)
                            .setCategory(category)
                            .setType(ItemType.LOBBY_EDITOR_OPENER)
                            .name(languageManager.getRawMsg(player, GUI_CATEGORY_MENU_QUICK_BUY_NAME))
                            .lore(languageManager.getRawMsgList(player, GUI_CATEGORY_MENU_QUICK_BUY_LORE))
                            .build()
            );
        }

        if (config.getBoolean(GUI_CATEGORY_MENU_REJOIN_ENABLED)) {
            inventory.setItem(config.getInt(GUI_CATEGORY_MENU_REJOIN_SLOT),
                    new RejoinItem(guiManager.getConfigurationManager().getConfig(), guiManager.getBwProxyService(), languageManager, player, versionSupport)
                            .rejoin(rj)
                            .build()
            );
        }
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        ItemType type = ItemType.fromId(versionSupport.getItemTag(event.getCurrentItem(), "agui.type"));
        BiConsumer<Player, InventoryClickEvent> action = itemActions.get(type);
        if (action != null) { action.accept(player, event); }

        //
//        int slot = event.getRawSlot();
//
//        Player player = (Player) event.getWhoClicked();
//
//        if (slot == 12) {
//            guiManager.getBwProxyService().getBwproxy().getArenaUtil().joinRandomFromGroup(player, category.getKey());
//        }
//
//        if (slot == 14) {
//            List<CachedArena> cachedarenas = guiManager.arenas().getMapsArenaByCategory(guiManager.getBwProxyService().getPlayerLanguage(player), category.getKey());
//            if (cachedarenas != null && !cachedarenas.isEmpty()) {
//                guiManager.openMapSelectorGui(player, category);
//            } else {player.sendMessage(guiManager.getRawMsg(player, MESSAGES_NO_MAP_AVAILABLE_CATEGORY));}
//        }
//
//        if (slot == 27) {
//            guiManager.openCategoryGui(player);
//        }
//
//        if (slot == 28) {
//            guiManager.getHBMService().getHbmAPI().getMenuUtil().openHotbarMenu(player);
//        }
//
//        if (slot == 29) {
//            guiManager.categories().openQuickBuyEditor();
//        }
//
//        if (slot == 35) {
//            guiManager.resume().rejoin(player);
//        }
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        guiManager.removeOpenGui((Player) event.getPlayer());
    }
}
