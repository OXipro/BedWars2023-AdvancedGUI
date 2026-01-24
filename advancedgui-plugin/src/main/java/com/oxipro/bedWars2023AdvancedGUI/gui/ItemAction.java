//package com.oxipro.bedWars2023AdvancedGUI.gui;
//
//import com.oxipro.bedWars2023AdvancedGUI.api.ItemType;
//import org.bukkit.entity.Player;
//import org.bukkit.event.inventory.InventoryClickEvent;
//
//import java.util.EnumMap;
//import java.util.Map;
//import java.util.function.BiConsumer;
//import java.util.function.Consumer;
//
//public class ItemAction {
//
//    private final Map<ItemType, BiConsumer<Player, InventoryClickEvent>> itemActions = new EnumMap<>(ItemType.class);
//
//
//    public ItemAction() {
//
//    }
//
//    public Map<ItemType, BiConsumer<Player, InventoryClickEvent>> getItemActions() { return itemActions; }
//
//    public void init() {
//        itemActions.put(ItemType.QUICK_JOIN, player -> {
//            if (guiManager.getBwProxyService().getBwproxy().getArenaUtil().joinRandomArena(player)) {
//                cancelRefreshArenas();
//            }
//        });
//
//        itemActions.put(ItemType.CATEGORIES_SELECTOR_OPENER, player -> {
//            guiManager.openCategoryGui(player);
//            cancelRefreshArenas();
//        });
//
//        itemActions.put(ItemType.HOTBAR_MANAGER_OPENER, player -> {
//            guiManager.markIgnoreClose(player);
//            guiManager.getHBMService().getHbmAPI().getMenuUtil().openCategoryMenu(player);
//            cancelRefreshArenas();
//        });
//
//        itemActions.put(ItemType.REJOIN, player -> {
//            if (rj != null) {
//                guiManager.resume().rejoin(player);
//                cancelRefreshArenas();
//            }
//        });
//
//        itemActions.put(ItemType.QUICK_ARENA, player -> {
//            String arena = versionSupport.getItemTag(event.getCurrentItem(), "agui.arena");
//            if (arena != null) {
//                CachedArena cachedArena = ArenaManager.getArenaByIdentifier(arena);
//                if (cachedArena.addPlayer(player, null)) {
//                    cancelRefreshArenas();
//                }
//            }
//        });
//    }
//}
