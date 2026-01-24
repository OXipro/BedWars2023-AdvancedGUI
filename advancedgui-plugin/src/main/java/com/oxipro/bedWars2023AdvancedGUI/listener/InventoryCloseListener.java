package com.oxipro.bedWars2023AdvancedGUI.listener;

import com.oxipro.bedWars2023AdvancedGUI.gui.AbstractGui;
import com.oxipro.bedWars2023AdvancedGUI.gui.GuiManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {
    private final GuiManager guiManager;

    public InventoryCloseListener(GuiManager guiManager) {
        this.guiManager = guiManager;
    }

    @EventHandler
    public void onClick(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player)) return;

        if (!(event.getInventory().getHolder() instanceof AbstractGui)) {
            return;
        }

        if (guiManager.shouldIgnoreClose( (Player) event.getPlayer())) {
            return;
        }

        AbstractGui gui = (AbstractGui) event.getInventory().getHolder();

        if (event.getInventory() == null) return;
        if (event.getInventory() != event.getInventory()) return;

        gui.onClose(event);
    }

}
