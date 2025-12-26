package com.oxipro.bedWars2023AdvancedGUI.gui;

import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MapSelectorGui extends AbstractGui {

    private final BwCategory category;

    public MapSelectorGui(GuiManager guiManager, BwCategory category) {
        super(guiManager, 6, "Selected Category: " + category.getFancyName());
        this.category = category;
        draw();
    }

    @Override
    protected void draw() {

        int slot = 10;

        for (String map : guiManager.arenas().getMaps(category.getKey())) {
            if (slot == 18) {slot++; continue;}
            if (slot == 27) {slot++; continue;}
            if (slot == 36) {slot++; continue;}
            if (slot == 45) break;
            inventory.setItem(slot++, new ItemBuilder(Material.PAPER).name(map).build());
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

        if (slot >= 9 && slot <= 26) {
            if (inventory.getItem(slot) == null) return;
            String map = inventory.getItem(slot)
                    .getItemMeta()
                    .getDisplayName()
                    .replace("§a", "");
            guiManager.arenas().joinSpecificMap(category.getKey(), map);
            return;
        }

        if (slot == 49) {
            guiManager.openModeGui((Player) event.getWhoClicked(), category);
        }
    }
}
