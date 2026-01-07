package com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory;

import java.util.List;

public class BwCategory {

    private final String key;
    private final String fancyName;
    private final int slot;
    private final String itemMaterial;
    private final int amount;
    private final String itemName;
    private final List<String> itemLore;
    private final boolean itemGlow;


    public BwCategory(
            String key,
            String fancyName,
            int slot,
            String itemMaterial,
            int amount,
            String itemName,
            List<String> itemLore,
            boolean itemGlow
    ) {
        this.key = key;
        this.fancyName = fancyName;
        this.slot = slot;
        this.itemMaterial = itemMaterial;
        this.itemName = itemName;
        this.itemLore = itemLore;
        this.itemGlow = itemGlow;
        this.amount = amount;
    }

    public String getKey() {
        return key;
    }

    public String getFancyName() {
        return fancyName != null ? fancyName : key;
    }

    public int getSlot() {
        return slot;
    }


    public String getItemMaterial() {
        return itemMaterial;
    }

    public int getAmount() {
        return amount;
    }

    public String getItemName() {
        return itemName;
    }

    public List<String> getItemLore() {
        return itemLore;
    }

    public boolean isItemGlow() {
        return itemGlow;
    }
}