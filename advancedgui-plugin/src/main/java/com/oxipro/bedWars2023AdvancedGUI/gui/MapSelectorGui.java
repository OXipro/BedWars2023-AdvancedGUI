package com.oxipro.bedWars2023AdvancedGUI.gui;

import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.VersionSupport;
import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategory;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import com.tomkeuper.bedwars.proxy.arenamanager.ArenaManager;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.List;

public class MapSelectorGui extends AbstractGui {

    private final BwCategory category;
    private final VersionSupport versionSupport;

    public MapSelectorGui(GuiManager guiManager, BwCategory category) {
        super(guiManager, 6, "Selected Category: " + category.getFancyName());
        this.category = category;
        this.versionSupport = guiManager.getVersionSupport();
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

            inventory.setItem(slot++, versionSupport.itemBuilder("PAPER")
                        .name("<green>" + cachedArena.getDisplayName(playerLanguage))
                        .lore(ll)
                        .setArena(cachedArena.getRemoteIdentifier())
                        .build()
            );
        }


        inventory.setItem(49,
                versionSupport.itemBuilder("ARROW")
                        .name("Back")
                        .build()
        );
    }

    @Override
    public void onClick(InventoryClickEvent event) {

        int slot = event.getRawSlot();
        player = (Player) event.getWhoClicked();

        if (slot >= 10 && slot <= 43) {
            String arena = versionSupport.getItemTag(event.getCurrentItem(), "agui_arena");
            if ( arena != null ) {
                CachedArena cachedArena = ArenaManager.getArenaByIdentifier(arena);
                cachedArena.addPlayer(player, null);
            }
        }

        if (slot == 49) {
            guiManager.openModeGui((Player) event.getWhoClicked(), category);
        }
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        guiManager.removeOpenGui((Player) event.getPlayer());
    }
}
