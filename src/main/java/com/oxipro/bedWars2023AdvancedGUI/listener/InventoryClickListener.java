package com.oxipro.bedWars2023AdvancedGUI.listener;

import com.oxipro.bedWars2023AdvancedGUI.gui.AbstractGui;
import com.oxipro.bedWars2023AdvancedGUI.gui.GuiManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    private final GuiManager guiManager;

    public InventoryClickListener(GuiManager guiManager) {
        this.guiManager = guiManager;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        if (!(event.getInventory().getHolder() instanceof AbstractGui gui)) {
            return;
        }

        event.setCancelled(true);

        if (event.getClickedInventory() == null) return;
        if (event.getClickedInventory() != event.getInventory()) return;

        gui.onClick(event);
    }
}
