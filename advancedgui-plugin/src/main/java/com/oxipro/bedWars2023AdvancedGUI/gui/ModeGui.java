package com.oxipro.bedWars2023AdvancedGUI.gui;

import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.util.ItemBuilder;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.RemoteReJoin;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.*;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.REJOIN_AVAILABLE_LORE;

public class ModeGui extends AbstractGui {

    private final BwCategory category;
    private Player player;

    public ModeGui(GuiManager guiManager, BwCategory category, Player player) {
        super(guiManager, 4, "Selected Mode: " + category.getFancyName());
        this.category = category;
        this.player = player;
        draw();
    }

    @Override
    protected void draw() {

        inventory.setItem(12,
                new ItemBuilder(Material.NETHER_STAR)
                        .name("Random Map")
                        .build()
        );

        inventory.setItem(14,
                new ItemBuilder(Material.MAP)
                        .name("Select Map")
                        .build()
        );

        inventory.setItem(27,
                new ItemBuilder(Material.CHEST)
                        .name("Categories")
                        .build()
        );

        inventory.setItem(28,
                new ItemBuilder(Material.BLAZE_ROD)
                        .name("Hotbar Manager")
                        .build()
        );

        inventory.setItem(29,
                new ItemBuilder(Material.EMERALD)
                        .name("Quick Buy")
                        .build()
        );

        Component rjg;
        Material mt;
        List<String> lore;
        RemoteReJoin rj = guiManager.resume().getReJoin(player);
        if (rj == null) {
            rjg = guiManager.getMMMsg(player, REJOIN_UNAVAILABLE_NAME);
            mt = Material.BARRIER;
            lore = List.of(guiManager.getBwProxyService().getMsg(player, REJOIN_UNAVAILABLE_LORE));
        } else {
            rjg = guiManager.getMMMsg(player, REJOIN_AVAILABLE_NAME);
            mt = Material.ENDER_PEARL;
            lore = List.of(guiManager.getBwProxyService().getMsg(player, REJOIN_AVAILABLE_LORE));
            lore.replaceAll(s -> s.replace("{arena_display_name}", rj.getArena().getDisplayName(guiManager.getBwProxyService().getPlayerLanguage(player))));
        }
        inventory.setItem(35,
                new ItemBuilder(mt)
                        .name(rjg)
                        .lore(lore)
                        .build()
        );
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
            } else {player.sendMessage(guiManager.getMMMsg(player, NO_MAP_AVAILAIBLE_CATEGORY));}
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
}
