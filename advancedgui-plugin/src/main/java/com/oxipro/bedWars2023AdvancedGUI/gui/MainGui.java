package com.oxipro.bedWars2023AdvancedGUI.gui;

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

import java.util.ArrayList;
import java.util.List;

import static com.oxipro.bedWars2023AdvancedGUI.config.ConfigPaths.*;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.*;

public class MainGui extends AbstractGui {

    private RemoteReJoin rj;
    private Player player;
    private final Language language;
    private BukkitRunnable drawOTFArenasRunnable;
    private FileConfiguration config;
    private VersionSupport versionSupport;




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
    }

    @Override
    protected void draw() {
        drawArenas();

        if (config.getBoolean(GUI_MAIN_REJOIN_ENABLED)) {
//            Component rjg;
//            String mt;
//            List<String> lore;
//            if (rj == null) {
//                rjg = guiManager.getMMMsg(player, GUI_REJOIN_UNAVAILABLE_NAME);
//                mt = config.getString(GUI_MAIN_REJOIN_MATERIAL_UNAVAILABLE);
//                lore = List.of(guiManager.getBwProxyService().getMsg(player, GUI_REJOIN_UNAVAILABLE_LORE));
//            } else {
//                rjg = guiManager.getMMMsg(player, GUI_REJOIN_AVAILABLE_NAME);
//                mt = config.getString(GUI_MAIN_REJOIN_MATERIAL_AVAILABLE);
//                lore = List.of(guiManager.getBwProxyService().getMsg(player, GUI_REJOIN_AVAILABLE_LORE));
                    // lore.replaceAll(s -> s.replace("{arena_display_name}", rj.getArena().getDisplayName(guiManager.getBwProxyService().getPlayerLanguage(player))));
//            }
//
//            inventory.setItem(config.getInt(GUI_MAIN_REJOIN_SLOT),
//                    new ItemBuilder(mt)
//                            .name(rjg)
//                            .lore(lore)
//                            .build()
//            );
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
                            .setType("quick_join")
                            .build()
            );
        }

        if (config.getBoolean(GUI_MAIN_CATEGORIES_ENABLED)) {
            inventory.setItem(config.getInt(GUI_MAIN_CATEGORIES_SLOT),
                    versionSupport.itemBuilder(config.getString(GUI_MAIN_CATEGORIES_MATERIAL))
                            .name(guiManager.getRawMsg(player, GUI_CATEGORIES_NAME))
                            .lore(guiManager.getRawMsgList(player, GUI_CATEGORIES_LORE))
                            .setType("categories")
                            .build()
            );
        }

        if (config.getBoolean(GUI_MAIN_HOTBAR_ENABLED)) {
            inventory.setItem(config.getInt(GUI_MAIN_HOTBAR_SLOT),
                    versionSupport.itemBuilder(config.getString(GUI_MAIN_HOTBAR_MATERIAL))
                            .name(guiManager.getRawMsg(player, GUI_HBM_NAME))
                            .lore(guiManager.getRawMsgList(player, GUI_HBM_LORE))
                            .setType("hbm")
                            .build()
            );
        }

        if (config.getBoolean(GUI_MAIN_QUICK_BUY_ENABLED)) {
            inventory.setItem(config.getInt(GUI_MAIN_QUICK_BUY_SLOT),
                    versionSupport.itemBuilder(config.getString(GUI_MAIN_QUICK_BUY_MATERIAL))
                            .name(guiManager.getRawMsg(player, GUI_QUICK_BUY_NAME))
                            .lore(guiManager.getRawMsgList(player, GUI_QUICK_BUY_LORE))
                            .setType("quick_buy")
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
        int slot = event.getRawSlot();
        switch (slot) {
            case 10:
                if (guiManager.getBwProxyService().getBwproxy().getArenaUtil().joinRandomArena(player)) {
                    cancelRefreshArenas();
                }
                return;
            case 27:
                guiManager.openCategoryGui(player);
                cancelRefreshArenas();
                return;
            case 28:
                guiManager.markIgnoreClose(player);
                guiManager.getHBMService().getHbmAPI().getMenuUtil().openCategoryMenu(player);
                cancelRefreshArenas();
                return;
            case 35:
                if (rj != null) {
                    guiManager.resume().rejoin(player);
                    cancelRefreshArenas();
                }
                return;
            default:
                String arena = versionSupport.getItemTag(event.getCurrentItem(), "agui.arena");
                if ( arena != null ) {
                    CachedArena cachedArena = ArenaManager.getArenaByIdentifier(arena);
                    if (cachedArena.addPlayer(player, null)) {
                        cancelRefreshArenas();
                    }
                }
        }



//        if (slot == 10) {
//            guiManager.openCategoryGui(player);
//            return;
//        }
//
//        if (slot == 27) {
//            guiManager.openCategoryGui(player);
//            return;
//        }
//
//        if (slot == 28) {
//            guiManager.getHBMService().getHbmAPI().getMenuUtil().openCategoryMenu(player);
//            return;
//        }
//
//        if (slot == 35) {
//            if (rj != null) {
//                guiManager.resume().rejoin(player);
//            }
//            return;
//        }
//        PersistentDataContainerView pdc =  inventory.getItem(slot).getPersistentDataContainer();
//        if (pdc != null) {
//            if (pdc.has(new NamespacedKey("agui", "arena"))) {
//                CachedArena cachedArena = ArenaManager.getArenaByIdentifier(pdc.get(new NamespacedKey("agui", "arena"), PersistentDataType.STRING));
//                cachedArena.addPlayer(player, null);
//            }
//            return;
//        }

    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        cancelRefreshArenas();
        guiManager.removeOpenGui((Player) event.getPlayer());
    }
}
