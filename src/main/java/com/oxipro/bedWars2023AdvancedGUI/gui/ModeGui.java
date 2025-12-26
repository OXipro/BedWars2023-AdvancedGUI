package com.oxipro.bedWars2023AdvancedGUI.gui;

import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.util.ItemBuilder;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.RemoteReJoin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

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

        String rjg;
        Material mt;
        List<String> l;
        RemoteReJoin rj = guiManager.resume().getReJoin(player);
        if (rj == null) {
            rjg = "No game to rejoin";
            mt = Material.BARRIER;
            l = List.of("<red>There is no game to rejoin");
        } else {
            rjg = "Rejoin Game";
            mt = Material.ENDER_PEARL;
            l = List.of("<green>Rejoin Game", "<yellow>Map: " + rj.getArena().getDisplayName(guiManager.getBwProxyService().getPlayerLanguage(player)));
        }
        inventory.setItem(35,
                new ItemBuilder(mt)
                        .name(rjg)
                        .lore(l)
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
            guiManager.openMapSelectorGui(player, category);
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
