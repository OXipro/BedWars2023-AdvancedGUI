package com.oxipro.bedWars2023AdvancedGUI.gui;

import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.util.ItemBuilder;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import com.tomkeuper.bedwars.proxy.arenamanager.ArenaManager;
import io.papermc.paper.persistence.PersistentDataContainerView;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class MapSelectorGui extends AbstractGui {

    private final BwCategory category;

    public MapSelectorGui(GuiManager guiManager, BwCategory category) {
        super(guiManager, 6, "Selected Category: " + category.getFancyName());
        this.category = category;
        draw();
    }

    @Override
    protected void draw() {

        Language playerLanguage = guiManager.getBwProxyService().getPlayerLanguage(player);

        int slot = 10;

        for (CachedArena cachedArena : guiManager.arenas().getMapsArenaByCategory(playerLanguage, category.getKey())) {
            if (slot == 18) {slot++; slot++; continue;}
            if (slot == 26) {slot++; slot++; continue;}
            if (slot == 35) {slot++; slot++; continue;}
            if (slot == 45) break;

            List<String> ll = List.of(
                    "",
                    " <grey>Available map selection:<green> soon<b>™",
                    " <grey>Arena(s) waiting:<green> soon<b>™",
                    "",
                    "<b><color:#ffb300>Click to join!</color></b>"
            );

            inventory.setItem(slot++, new ItemBuilder(Material.PAPER)
                        .name("<green>" + cachedArena.getDisplayName(playerLanguage))
                        .lore(ll)
                        .setArena(cachedArena.getRemoteIdentifier())
                        .build()
            );
        }


        inventory.setItem(49,
                new ItemBuilder(Material.ARROW)
                        .name("Back")
                        .build()
        );
    }

    @Override
    public void onClick(InventoryClickEvent event) {

        int slot = event.getRawSlot();
        player = (Player) event.getWhoClicked();

        if (slot >= 10 && slot <= 43) {
            PersistentDataContainerView pdc =  inventory.getItem(slot).getPersistentDataContainer();
            if (pdc != null) {
                if (pdc.has(new NamespacedKey("agui", "arena"))) {
                    CachedArena cachedArena = ArenaManager.getArenaByIdentifier(pdc.get(new NamespacedKey("agui", "arena"), PersistentDataType.STRING));
                    cachedArena.addPlayer(player, null);
                    return;
                } return;
            } return;
        }

        if (slot == 49) {
            guiManager.openModeGui((Player) event.getWhoClicked(), category);
        }
    }
}
