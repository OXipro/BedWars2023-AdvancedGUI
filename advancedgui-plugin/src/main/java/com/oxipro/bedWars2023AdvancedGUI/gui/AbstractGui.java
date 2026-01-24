package com.oxipro.bedWars2023AdvancedGUI.gui;

import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;


public abstract class AbstractGui implements InventoryHolder {

    protected final GuiManager guiManager;
    protected final Inventory inventory;
    protected Player player;

    protected AbstractGui(GuiManager guiManager, int rows, String title) {
        this.guiManager = guiManager;
        this.inventory = Bukkit.createInventory(this, rows * 9, title);
    }

    protected abstract void draw();

    public abstract void onClick(InventoryClickEvent event);

    public void onClose(InventoryCloseEvent event) {}

    public Inventory getInventory() {
        return inventory;
    }
}
