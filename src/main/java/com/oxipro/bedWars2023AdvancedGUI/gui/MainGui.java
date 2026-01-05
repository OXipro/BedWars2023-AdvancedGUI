package com.oxipro.bedWars2023AdvancedGUI.gui;

import com.oxipro.bedWars2023AdvancedGUI.util.ArenaItem;
import com.oxipro.bedWars2023AdvancedGUI.util.ItemBuilder;
import com.saicone.rtag.util.ServerInstance;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import com.tomkeuper.bedwars.proxy.api.RemoteReJoin;
import com.tomkeuper.bedwars.proxy.arenamanager.ArenaManager;
import com.tomkeuper.bedwars.proxy.language.English;
import io.papermc.paper.persistence.PersistentDataContainerView;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.*;

public class MainGui extends AbstractGui {

    RemoteReJoin rj;
    private Player player;
    private final Language language;

    public MainGui(GuiManager guiManager, Player player) {
        super(guiManager, 4, "BedWars");
        this.player = player;
        rj = guiManager.resume().getReJoin(player);
        this.language = guiManager.getBwProxyService().getPlayerLanguage(player);
        draw();
    }

    @Override
    protected void draw() {



//        guiManager.arenas().getTopArenas().forEach((slot, material) ->
//                inventory.setItem(slot, new ItemBuilder(material).build())
//        );

        guiManager.arenas().ArenaItemStackMapRefresh(player);
//        guiManager.getLogger().info("1");
        List<ItemStack> items = new ArrayList<>(
                guiManager.arenas().getArenaItemStackMap().values()
        );
//        guiManager.getLogger().info("2 " + items);

        int startSlot = 12;
        int offset = 0;

        for (int i = 0; i < 5; i++) {

            int index = offset + i;
            int slot = startSlot + i;

            if (index < items.size()) {
                inventory.setItem(slot, items.get(index));
//                guiManager.getLogger().info("3 " + items.get(index));
            } else {
                inventory.setItem(slot, new ArenaItem(null, language).createArenaItem());
            }
        }

        Component rjg;
        Material mt;
        List<String> lore;
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

        inventory.setItem(10,
                new ItemBuilder(Material.NETHER_STAR)
                        .name(guiManager.getMMMsg(player, ITEM_QUICKJOIN_NAME))
                        .loreCMP(List.of(guiManager.getMMMsg(player, ITEM_QUICKJOIN_LORE)))
                        .build()
        );

        inventory.setItem(35,
                new ItemBuilder(mt)
                        .name(rjg)
                        .lore(lore)
                        .build()
        );

        inventory.setItem(27,
                new ItemBuilder(Material.CHEST)
                        .name(guiManager.getMMMsg(player, ITEM_CATEGORIES_NAME))
                        .loreCMP(List.of(guiManager.getMMMsg(player, ITEM_CATEGORIES_LORE)))
                        .build()
        );

        inventory.setItem(28,
                new ItemBuilder(Material.BLAZE_ROD)
                        .name(guiManager.getMMMsg(player, ITEM_HBM_NAME))
                        .loreCMP(List.of(guiManager.getMMMsg(player, ITEM_HBM_LORE)))
                        .build()
        );

        inventory.setItem(29,
                new ItemBuilder(Material.EMERALD)
                        .name(guiManager.getMMMsg(player, ITEM_QB_NAME))
                        .loreCMP(List.of(guiManager.getMMMsg(player, ITEM_QB_LORE)))
                        .build()
        );
    }

    @Override
    public void onClick(InventoryClickEvent event) {

        int slot = event.getRawSlot();

        if (slot == 10) {
            guiManager.getBwProxyService().getBwproxy().getArenaUtil().joinRandomArena(player);
            return;
        }

        if (slot == 27) {
            guiManager.openCategoryGui(player);
            return;
        }

        if (slot == 28) {
            guiManager.getHBMService().getHbmAPI().getMenuUtil().openCategoryMenu(player);
            return;
        }

        if (slot == 35) {
            if (rj != null) {
                guiManager.resume().rejoin(player);
            }
            return;
        }

        PersistentDataContainerView pdc =  inventory.getItem(slot).getPersistentDataContainer();
        if (pdc != null) {
            if (pdc.has(new NamespacedKey("agui", "arena"))) {
                CachedArena cachedArena = ArenaManager.getArenaByIdentifier(pdc.get(new NamespacedKey("agui", "arena"), PersistentDataType.STRING));
                cachedArena.addPlayer(player, null);
            }
            return;
        }

    }
}
