package com.oxipro.bedWars2023AdvancedGUI.gui;

import com.oxipro.bedWars2023AdvancedGUI.api.ItemType;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.VersionSupport;
import com.oxipro.bedWars2023AdvancedGUI.util.ArenaItem;
import com.oxipro.bedWars2023AdvancedGUI.util.RejoinItem;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import com.tomkeuper.bedwars.proxy.api.RemoteReJoin;
import com.tomkeuper.bedwars.proxy.arenamanager.ArenaManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.function.BiConsumer;

import static com.oxipro.bedWars2023AdvancedGUI.config.ConfigPaths.*;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.*;

public class MainGui extends AbstractGui {

    private RemoteReJoin rj;
    private Player player;
    private final Language language;
    private BukkitRunnable drawOTFArenasRunnable;
    private FileConfiguration config;
    private VersionSupport versionSupport;
    private final Map<ItemType, BiConsumer<Player, InventoryClickEvent>> itemActions = new EnumMap<>(ItemType.class);


    public MainGui(GuiManager guiManager, Player player) {
        super(guiManager, guiManager.getConfigurationManager().getConfig().getInt(GUI_MODE_ROWS), "BedWars");
        this.player = player;
        this.rj = guiManager.resume().getReJoin(player);
        this.language = guiManager.getBwProxyService().getPlayerLanguage(player);
        this.config = guiManager.getConfigurationManager().getConfig();
        this.versionSupport = guiManager.getVersionSupport();
        init();
        draw();
    }

    void drawArenas() {
        guiManager.arenas().ArenaItemStackMapRefresh(player);
        List<ItemStack> items = new ArrayList<>(
                guiManager.arenas().getArenaItemStackMap().values()
        );
        int startSlot = config.getInt(GUI_MAIN_ARENAS_START_SLOT);
        int offset = 0;

        for (int i = 0; i < config.getInt(GUI_MAIN_ARENAS_SLOTS_COUNTS); i++) {

            int index = offset + i;
            int slot = startSlot + i;

            if (index < items.size()) {
                inventory.setItem(slot, items.get(index));
            } else {
                inventory.setItem(slot, new ArenaItem(null, config, guiManager.getBwProxyService(), guiManager.getLanguageManager(), player, versionSupport).createArenaItem());
            }
        }
    }

    protected void init() {

        itemActions.put(ItemType.QUICK_JOIN, (player, event) -> {
            if (guiManager.getBwProxyService().getBwproxy().getArenaUtil().joinRandomArena(player)) {
                cancelRefreshArenas();
            }
        });

        itemActions.put(ItemType.CATEGORIES_SELECTOR_OPENER, (player, event) -> {
            guiManager.openCategoryGui(player);
            cancelRefreshArenas();
        });

        itemActions.put(ItemType.HOTBAR_MANAGER_OPENER, (player, event) -> {
            guiManager.markIgnoreClose(player);
            guiManager.getHBMService().getHbmAPI().getMenuUtil().openCategoryMenu(player);
            cancelRefreshArenas();
        });

        itemActions.put(ItemType.REJOIN, (player, event) -> {
            if (rj != null) {
                guiManager.resume().rejoin(player);
                cancelRefreshArenas();
            }
        });

        itemActions.put(ItemType.QUICK_ARENA, (player, event) -> {
            String arena = versionSupport.getItemTag(event.getCurrentItem(), "agui.arena");
            if (arena != null) {
                CachedArena cachedArena = ArenaManager.getArenaByIdentifier(arena);
                if (cachedArena.addPlayer(player, null)) {
                    cancelRefreshArenas();
                }
            }
        });
    }

    @Override
    protected void draw() {
        drawArenas();

        if (config.getBoolean(GUI_MAIN_REJOIN_ENABLED)) {
            inventory.setItem(config.getInt(GUI_MAIN_REJOIN_SLOT),
                    new RejoinItem(config, guiManager.getBwProxyService(), guiManager.getLanguageManager(), player, versionSupport)
                            .rejoin(rj)
                            .build()
            );
        }

        if (config.getBoolean(GUI_MAIN_QUICK_JOIN_ENABLED)) {
            inventory.setItem(config.getInt(GUI_MAIN_QUICK_JOIN_SLOT),
                    versionSupport.itemBuilder(config.getString(GUI_MAIN_QUICK_JOIN_MATERIAL))
                            .name(guiManager.getRawMsg(player, GUI_QUICK_JOIN_NAME))
                            .lore(guiManager.getRawMsgList(player, GUI_QUICK_JOIN_LORE))
                            .setType(ItemType.QUICK_JOIN)
                            .player(player)
                            .build()
            );
        }

        if (config.getBoolean(GUI_MAIN_CATEGORIES_ENABLED)) {
            inventory.setItem(config.getInt(GUI_MAIN_CATEGORIES_SLOT),
                    versionSupport.itemBuilder(config.getString(GUI_MAIN_CATEGORIES_MATERIAL))
                            .name(guiManager.getRawMsg(player, GUI_CATEGORIES_NAME))
                            .lore(guiManager.getRawMsgList(player, GUI_CATEGORIES_LORE))
                            .setType(ItemType.CATEGORIES_SELECTOR_OPENER)
                            .player(player)
                            .build()
            );
        }

        if (config.getBoolean(GUI_MAIN_HOTBAR_ENABLED)) {
            inventory.setItem(config.getInt(GUI_MAIN_HOTBAR_SLOT),
                    versionSupport.itemBuilder(config.getString(GUI_MAIN_HOTBAR_MATERIAL))
                            .name(guiManager.getRawMsg(player, GUI_HBM_NAME))
                            .lore(guiManager.getRawMsgList(player, GUI_HBM_LORE))
                            .setType(ItemType.HOTBAR_MANAGER_OPENER)
                            .player(player)
                            .build()
            );
        }

        if (config.getBoolean(GUI_MAIN_QUICK_BUY_ENABLED)) {
            inventory.setItem(config.getInt(GUI_MAIN_QUICK_BUY_SLOT),
                    versionSupport.itemBuilder(config.getString(GUI_MAIN_QUICK_BUY_MATERIAL))
                            .name(guiManager.getRawMsg(player, GUI_QUICK_BUY_NAME))
                            .lore(guiManager.getRawMsgList(player, GUI_QUICK_BUY_LORE))
                            .setType(ItemType.LOBBY_EDITOR_OPENER)
                            .player(player)
                            .build()
            );
        }

        if (config.getBoolean(GUI_MAIN_ARENAS_REFRESH_ENABLED)) {
            drawOTFArenasRunnable = new BukkitRunnable() {
                @Override
                public void run() {
                    drawArenas();
                }
            };
            long onCloseRunnableDelay = config.getLong(GUI_MAIN_ARENAS_REFRESH_DELAY);
            drawOTFArenasRunnable.runTaskTimerAsynchronously(guiManager.getPlugin(), onCloseRunnableDelay, onCloseRunnableDelay);
        }
    }

    private boolean cancelRefreshArenas() {
        if (!config.getBoolean(GUI_MAIN_ARENAS_REFRESH_ENABLED)) return false;
        if (drawOTFArenasRunnable!= null) {
            drawOTFArenasRunnable.cancel();
            return false;
        }
        return false;
    }

    @Override
    public void onClick(InventoryClickEvent event) {

        ItemType type = ItemType.fromId(versionSupport.getItemTag(event.getCurrentItem(), "agui.type"));
        BiConsumer<Player, InventoryClickEvent> action = itemActions.get(type);
        if (action != null) { action.accept(player, event); }

//        int slot = event.getRawSlot();
//        switch (Objects.requireNonNull(ItemType.fromId(versionSupport.getItemTag(event.getCurrentItem(), "agui.type")))) {
//            case QUICK_JOIN:
//                if (guiManager.getBwProxyService().getBwproxy().getArenaUtil().joinRandomArena(player)) {
//                    cancelRefreshArenas();
//                }
//                return;
//            case CATEGORIES_SELECTOR_OPENER:
//                guiManager.openCategoryGui(player);
//                cancelRefreshArenas();
//                return;
//            case HOTBAR_MANAGER_OPENER:
//                guiManager.markIgnoreClose(player);
//                guiManager.getHBMService().getHbmAPI().getMenuUtil().openCategoryMenu(player);
//                cancelRefreshArenas();
//                return;
//            case REJOIN:
//                if (rj != null) {
//                    guiManager.resume().rejoin(player);
//                    cancelRefreshArenas();
//                }
//                return;
//            case QUICK_ARENA:
//                String arena = versionSupport.getItemTag(event.getCurrentItem(), "agui.arena");
//                if ( arena != null ) {
//                    CachedArena cachedArena = ArenaManager.getArenaByIdentifier(arena);
//                    if (cachedArena.addPlayer(player, null)) {
//                        cancelRefreshArenas();
//                    }
//                }
//        }
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        cancelRefreshArenas();
        guiManager.removeOpenGui((Player) event.getPlayer());
    }
}
