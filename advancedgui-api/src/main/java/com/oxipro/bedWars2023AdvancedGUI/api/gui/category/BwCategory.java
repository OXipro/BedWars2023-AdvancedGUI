package com.oxipro.bedWars2023AdvancedGUI.api.gui.category;

public class BwCategory {

    private final String key;
    private final int slot;
    private final String itemMaterial;
    private final int amount;


    public BwCategory(
            String key,
            int slot,
            String itemMaterial,
            int amount
    ) {
        this.key = key;
        this.slot = slot;
        this.itemMaterial = itemMaterial;
        this.amount = amount;
    }

    public String getKey() {
        return key;
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

}