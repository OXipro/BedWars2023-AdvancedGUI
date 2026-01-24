package com.oxipro.bedWars2023AdvancedGUI.gui;

import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.VersionSupport;
import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategoryMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;


public class CategoryGui extends AbstractGui {

    private BwCategoryMenu categoryMenu;
    private VersionSupport versionSupport;

    public CategoryGui(GuiManager guiManager) {
        super(guiManager,guiManager.loadBwCategoryMenu().getMenuRows(),guiManager.loadBwCategoryMenu().getTitle());
        this.categoryMenu = guiManager.loadBwCategoryMenu();
        this.versionSupport = guiManager.getVersionSupport();
        draw();
    }

    @Override
    protected void draw() {
        categoryMenu = guiManager.loadBwCategoryMenu();
        for (BwCategory category : categoryMenu.getCategories().values()) {
            String material = category.getItemMaterial();
            if (material == null) {
                material ="BARRIER";
            }
            guiManager.getLogger().info(category.getKey());
            ItemStack item = versionSupport.itemBuilder(material)
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

        ItemStack item = event.getCurrentItem();

        guiManager.getLogger().info("Item: " + item);
        guiManager.getLogger().info("Category tag: " + versionSupport.getItemTag(item, "agui.category"));

        String aguiCategory = versionSupport.getItemTag(event.getCurrentItem(), "agui.category");
        if (aguiCategory == null) return; // ignore si pas de tag

        BwCategory category = categoryMenu.getCategory(aguiCategory);
        if (category == null) return; // ignore si la catégorie n'existe pas


//        String aguiCategory = versionSupport.getItemTag(inventory.getItem(slot), "agui.category");
        guiManager.getLogger().info(String.valueOf(slot));
        guiManager.getLogger().info(event.getCurrentItem().toString());
        guiManager.getLogger().info(aguiCategory);
//        BwCategory category = categoryMenu.getCategory(aguiCategory);
        guiManager.openModeGui((Player) event.getWhoClicked(), category);
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        guiManager.removeOpenGui((Player) event.getPlayer());
    }
}
