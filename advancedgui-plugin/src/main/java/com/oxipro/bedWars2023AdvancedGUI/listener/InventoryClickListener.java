package com.oxipro.bedWars2023AdvancedGUI.listener;

import com.oxipro.bedWars2023AdvancedGUI.gui.*;
import me.kiiya.hotbarmanager.HotbarManager;
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
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() != null) {
            if (guiManager.getHBMService().isHBMEnabled() && (guiManager.getOpenGui(player) != null)) {
                String tag = HotbarManager.getInstance().getVersionSupport().getItemTag(event.getCurrentItem(), "hbm");
                if (tag != null) {
                    if (tag.equals("back")) {
                        AbstractGui gui = guiManager.getOpenGui(player);
                        if (gui instanceof CategoryMenuGui) {
                            CategoryMenuGui categoryMenuGUI = (CategoryMenuGui) gui;
                            guiManager.openModeGui(player, categoryMenuGUI.getCategory());
                        } else if (gui instanceof MainGui) {
                            guiManager.openMainGui(player);
                        }
                    }
                }
            }
        }

        if (!(event.getInventory().getHolder() instanceof AbstractGui)) {
            return;
        }

        AbstractGui gui = (AbstractGui) event.getInventory().getHolder();

        event.setCancelled(true);

        if (event.getClickedInventory() == null) return;
        if (event.getClickedInventory() != event.getInventory()) return;

        gui.onClick(event);
    }
}
