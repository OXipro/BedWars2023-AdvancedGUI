package com.oxipro.bedWars2023AdvancedGUI.gui;

import com.oxipro.bedWars2023AdvancedGUI.api.ItemType;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.VersionSupport;
import com.oxipro.bedWars2023AdvancedGUI.api.gui.category.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.api.language.LanguageManager;
import com.oxipro.bedWars2023AdvancedGUI.api.text.placeholders.PrasePlaceholders;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import com.tomkeuper.bedwars.proxy.arenamanager.ArenaManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static com.oxipro.bedWars2023AdvancedGUI.api.ItemType.MAP;
import static com.oxipro.bedWars2023AdvancedGUI.config.ConfigPaths.*;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.*;


public class MapSelectorGui extends AbstractGui {

    private final BwCategory category;
    private final VersionSupport versionSupport;
    private final FileConfiguration config;
    private final LanguageManager languageManager;
    private final Map<ItemType, BiConsumer<Player, InventoryClickEvent>> itemActions = new EnumMap<>(ItemType.class);

    // pages things yn
    private int currentPage = 0;
    private int maxPages = 0;



    public MapSelectorGui(GuiManager guiManager, BwCategory category, Player player) {
        super(guiManager, guiManager.getConfigurationManager().getConfig().getInt(GUI_MAP_SELECTOR_ROWS), new PrasePlaceholders(player, guiManager.getBwProxyService().getPlayerLanguage(player), guiManager.getLanguageManager()).prase(guiManager.getLanguageManager().getRawMsg(player,"display-group-" + category.getKey())));
        this.category = category;
        this.versionSupport = guiManager.getVersionSupport();
        this.config = guiManager.getConfigurationManager().getConfig();
        this.languageManager = guiManager.getLanguageManager();
        this.player = player;
        init();
        draw();
    }

    protected void init() {

        itemActions.put(ItemType.CATEGORY, (player, event) -> {
            guiManager.openModeGui(player, category);
        });

        itemActions.put(ItemType.PAGE_NEXT, (player, event) -> {
            if (currentPage < maxPages - 1) {
                currentPage++;
                clear();
                draw();
            }
        });

        itemActions.put(ItemType.PAGE_BACK, (player, event) -> {
            if (currentPage > 0) {
                currentPage--;
                clear();
                draw();
            }
        });

        itemActions.put(ItemType.MAP, (player, event) -> {
            String arena = versionSupport.getItemTag(event.getCurrentItem(), "agui.arena");
            if (arena != null) {
                CachedArena cachedArena = ArenaManager.getArenaByIdentifier(arena);
                if (cachedArena.addPlayer(player, null)) {
                    // ...
                }
            }
        });
    }

    private void clear() {
        List<Integer> slots = config.getIntegerList(GUI_MAP_SELECTOR_MAPS_SLOTS);
        for (int slot : slots) {
            inventory.setItem(slot, null);
        }
    }

    private void drawPaginationItems(Language playerLanguage) {

        if (config.getBoolean(GUI_MAP_SELECTOR_BACK_PAGE_ENABLED) && currentPage > 0) {
            inventory.setItem(config.getInt(GUI_MAP_SELECTOR_BACK_PAGE_SLOT),
                    versionSupport.itemBuilder(config.getString(GUI_MAP_SELECTOR_BACK_PAGE_MATERIAL), guiManager.getLanguageManager())
                            .setLanguage(playerLanguage)
                            .setCategory(category)
                            .player(player)
                            .setType(ItemType.PAGE_BACK)
                            .name(languageManager.getRawMsg(player, GUI_MAP_BACK_PAGE_NAME))
                            .lore(languageManager.getRawMsgList(player, GUI_MAP_BACK_PAGE_LORE))
                            .build()
            );
        }

        if (config.getBoolean(GUI_MAP_SELECTOR_NEXT_PAGE_ENABLED) && currentPage < maxPages - 1) {
            inventory.setItem(config.getInt(GUI_MAP_SELECTOR_NEXT_PAGE_SLOT),
                    versionSupport.itemBuilder(config.getString(GUI_MAP_SELECTOR_NEXT_PAGE_MATERIAL), guiManager.getLanguageManager())
                            .setLanguage(playerLanguage)
                            .setCategory(category)
                            .player(player)
                            .setType(ItemType.PAGE_NEXT)
                            .name(languageManager.getRawMsg(player, GUI_MAP_NEXT_PAGE_NAME))
                            .lore(languageManager.getRawMsgList(player, GUI_MAP_NEXT_PAGE_LORE))
                            .build()
            );
        }

        if (config.getBoolean(GUI_MAP_SELECTOR_BACK_ENABLED)) {
            inventory.setItem(config.getInt(GUI_MAP_SELECTOR_BACK_SLOT),
                    versionSupport.itemBuilder(config.getString(GUI_MAP_SELECTOR_BACK_MATERIAL), guiManager.getLanguageManager())
                            .setLanguage(playerLanguage)
                            .setCategory(category)
                            .player(player)
                            .setType(ItemType.CATEGORY)
                            .name(languageManager.getRawMsg(player, GUI_MAP_BACK_NAME))
                            .lore(languageManager.getRawMsgList(player, GUI_MAP_BACK_LORE))
                            .build()
            );
        }
    }

//    @Override
//    protected void draw() {
//
//        Language playerLanguage = guiManager.getBwProxyService().getPlayerLanguage(player);
//
//        List<Integer> slots = config.getIntegerList(GUI_MAP_SELECTOR_MAPS_SLOTS);
//
//        List<CachedArena> arenas = guiManager.arenas().getMapsArenaByCategory(playerLanguage, category.getKey());
//
//        int index = 0;
//        for (CachedArena arena : arenas) {
//            if (index >= slots.size()) break;
//
//            int slot = slots.get(index);
//            index++;
//
//
//            inventory.setItem(slot,
//                    versionSupport.itemBuilder(config.getString(GUI_MAP_SELECTOR_MAPS_AVAILABLE_MATERIAL), guiManager.getLanguageManager())
//                            .setLanguage(playerLanguage)
//                            .setArena(arena)
//                            .setCategory(category)
//                            .player(player)
//                            .setType(MAP)
//                            .name(languageManager.getRawMsg(player, GUI_MAPS_AVAILABLE_LORE))
//                            .lore(languageManager.getRawMsgList(player, GUI_MAPS_AVAILABLE_NAME))
//                            .build()
//            );
//        }
//
//        if (config.getBoolean(GUI_MAP_SELECTOR_BACK_ENABLED)) {
//            inventory.setItem(config.getInt(GUI_MAP_SELECTOR_BACK_SLOT),
//                    versionSupport.itemBuilder(config.getString(GUI_MAP_SELECTOR_BACK_MATERIAL), guiManager.getLanguageManager())
//                            .setLanguage(playerLanguage)
//                            .setCategory(category)
//                            .player(player)
//                            .setType(ItemType.CATEGORY)
//                            .name(languageManager.getRawMsg(player, GUI_MAP_BACK_NAME))
//                            .lore(languageManager.getRawMsgList(player, GUI_MAP_BACK_LORE))
//                            .build()
//            );
//        }
//
//        if (config.getBoolean(GUI_MAP_SELECTOR_BACK_PAGE_ENABLED)) {
//            inventory.setItem(config.getInt(GUI_MAP_SELECTOR_BACK_PAGE_SLOT),
//                    versionSupport.itemBuilder(config.getString(GUI_MAP_SELECTOR_BACK_PAGE_MATERIAL), guiManager.getLanguageManager())
//                            .setLanguage(playerLanguage)
//                            .setCategory(category)
//                            .player(player)
//                            .setType(ItemType.PAGE_BACK)
//                            .name(languageManager.getRawMsg(player, GUI_MAP_BACK_PAGE_NAME))
//                            .lore(languageManager.getRawMsgList(player, GUI_MAP_BACK_PAGE_LORE))
//                            .build()
//            );
//        }
//
//        if (config.getBoolean(GUI_MAP_SELECTOR_NEXT_PAGE_ENABLED)) {
//            inventory.setItem(config.getInt(GUI_MAP_SELECTOR_NEXT_PAGE_SLOT),
//                    versionSupport.itemBuilder(config.getString(GUI_MAP_SELECTOR_NEXT_PAGE_MATERIAL), guiManager.getLanguageManager())
//                            .setLanguage(playerLanguage)
//                            .setCategory(category)
//                            .player(player)
//                            .setType(ItemType.PAGE_NEXT)
//                            .name(languageManager.getRawMsg(player, GUI_MAP_NEXT_PAGE_NAME))
//                            .lore(languageManager.getRawMsgList(player, GUI_MAP_NEXT_PAGE_LORE))
//                            .build()
//            );
//        }
//    }

    @Override
    protected void draw() {
        Language playerLanguage = guiManager.getBwProxyService().getPlayerLanguage(player);

        List<Integer> slots = config.getIntegerList(GUI_MAP_SELECTOR_MAPS_SLOTS);
        List<CachedArena> arenas = guiManager.arenas().getMapsArenaByCategory(playerLanguage, category.getKey());

        int perPage = slots.size();
        maxPages = (int) Math.ceil((double) arenas.size() / perPage);

        int start = currentPage * perPage;
        int end = Math.min(start + perPage, arenas.size());

        for (int i = start; i < end; i++) {
            int slot = slots.get(i - start);
            CachedArena arena = arenas.get(i);

            inventory.setItem(slot,
                    versionSupport.itemBuilder(config.getString(GUI_MAP_SELECTOR_MAPS_AVAILABLE_MATERIAL), guiManager.getLanguageManager())
                            .setLanguage(playerLanguage)
                            .setArena(arena)
                            .setCategory(category)
                            .player(player)
                            .setType(MAP)
                            .name(languageManager.getRawMsg(player, GUI_MAPS_AVAILABLE_NAME))
                            .lore(languageManager.getRawMsgList(player, GUI_MAPS_AVAILABLE_LORE))
                            .build()
            );
        }

        drawPaginationItems(playerLanguage);
    }

    @Override
    public void onClick(InventoryClickEvent event) {

        ItemType type = ItemType.fromId(versionSupport.getItemTag(event.getCurrentItem(), "agui.type"));
        BiConsumer<Player, InventoryClickEvent> action = itemActions.get(type);
        if (action != null) { action.accept(player, event); }

        //        int slot = event.getRawSlot();
//        player = (Player) event.getWhoClicked();
//
//        if (slot >= 10 && slot <= 43) {
//            String arena = versionSupport.getItemTag(event.getCurrentItem(), "agui_arena");
//            if ( arena != null ) {
//                CachedArena cachedArena = ArenaManager.getArenaByIdentifier(arena);
//                cachedArena.addPlayer(player, null);
//            }
//        }
//
//        if (slot == 49) {
//            guiManager.openModeGui((Player) event.getWhoClicked(), category);
//        }
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        guiManager.removeOpenGui((Player) event.getPlayer());
    }
}
