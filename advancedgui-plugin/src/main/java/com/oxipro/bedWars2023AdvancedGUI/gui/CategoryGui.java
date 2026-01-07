package com.oxipro.bedWars2023AdvancedGUI.gui;

import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategoryMenu;
import com.oxipro.bedWars2023AdvancedGUI.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;


public class CategoryGui extends AbstractGui {

    private BwCategoryMenu categoryMenu;

    public CategoryGui(GuiManager guiManager) {
        super(guiManager,guiManager.loadBwCategoryMenu().getMenuRows(),guiManager.loadBwCategoryMenu().getTitle());
        this.categoryMenu = guiManager.loadBwCategoryMenu();
        draw();
    }

    @Override
    protected void draw() {
        categoryMenu = guiManager.loadBwCategoryMenu();
        for (BwCategory category : categoryMenu.getCategories().values()) {
            Material material = Material.matchMaterial(category.getItemMaterial());
            if (material == null) {
                material = Material.BARRIER;
            }
            ItemStack item = new ItemBuilder(material)
                    .name(category.getItemName())
                    .lore(category.getItemLore())
                    .glow(category.isItemGlow())
                    .amount(category.getAmount())
                    .setCategory(category.getKey())
                    .build();
            inventory.setItem(category.getSlot(), item);
        }
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        int slot = event.getRawSlot();

        categoryMenu = guiManager.loadBwCategoryMenu();
        if (inventory.getItem(slot) == null) return;

        BwCategory category = categoryMenu.getCategory(inventory.getItem(slot).getPersistentDataContainer().get(new NamespacedKey("agui", "category"), PersistentDataType.STRING));
        guiManager.openModeGui((Player) event.getWhoClicked(), category);
    }
}
