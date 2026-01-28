package com.oxipro.bedWars2023AdvancedGUI.gui;

import com.oxipro.bedWars2023AdvancedGUI.api.ItemType;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.VersionSupport;
import com.oxipro.bedWars2023AdvancedGUI.api.gui.category.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.api.text.placeholders.PrasePlaceholders;
import com.oxipro.bedWars2023AdvancedGUI.language.LanguageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.*;
import static com.oxipro.bedWars2023AdvancedGUI.config.ConfigPaths.*;
import static com.oxipro.bedWars2023AdvancedGUI.language.LanguagePaths.GUI_CATEGORIES_SELECTOR_CATEGORIES;


public class CategoriesSelectorGui extends AbstractGui {

    private Map<String, BwCategory> BwCategories;
    private VersionSupport versionSupport;
    private GuiManager guiManager;
    private LanguageManager languageManager;
    private Player player;

    public CategoriesSelectorGui(GuiManager guiManager, Player player) {
        super(guiManager,guiManager.getConfigurationManager().getConfig().getInt(GUI_CATEGORIES_SELECTOR_ROWS), new PrasePlaceholders(player, guiManager.getBwProxyService().getPlayerLanguage(player), guiManager.getLanguageManager()).prase(guiManager.getLanguageManager().getRawMsg(player, GUI_CATEGORIES_SELECTOR_TITLE)));
        this.guiManager = guiManager;
        this.BwCategories = guiManager.getBwCategories();
        this.versionSupport = guiManager.getVersionSupport();
        this.languageManager = guiManager.getLanguageManager();
        this.player = player;
        draw();
    }

    @Override
    protected void draw() {
//        categoryMenu = guiManager.getBwCategories();
        for (BwCategory category : BwCategories.values()) {
            String material = category.getItemMaterial();
            if (material == null) {
                material ="BARRIER";
            }
            guiManager.getLogger().info(category.getKey());
            String languagePath = GUI_CATEGORIES_SELECTOR_CATEGORIES + category.getKey() + ".";
            ItemStack item = versionSupport.itemBuilder(material, guiManager.getLanguageManager())
                    .setLanguage(guiManager.getBwProxyService().getPlayerLanguage(player))
                    .player(player)
                    .glow(false)
                    .amount(category.getAmount())
                    .setCategory(category)
                    .setType(ItemType.CATEGORY)
                    .name(languageManager.getRawMsg(player, languagePath + "name"))
                    .lore(languageManager.getRawMsgList(player, languagePath + "lore"))
                    .build();
            inventory.setItem(category.getSlot(), item);
        }
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        int slot = event.getRawSlot();

        if (inventory.getItem(slot) == null) return;

        ItemStack item = event.getCurrentItem();

        if (ItemType.fromId(versionSupport.getItemTag(item, "agui.type")) == ItemType.CATEGORY) {
            guiManager.getLogger().info("Item: " + item);
            guiManager.getLogger().info("Category tag: " + versionSupport.getItemTag(item, "agui.category"));

            String aguiCategory = versionSupport.getItemTag(event.getCurrentItem(), "agui.category");
            if (aguiCategory == null) return;

            BwCategories = guiManager.getBwCategories();
            BwCategory category = BwCategories.get(aguiCategory);
            if (category == null) return;


//        String aguiCategory = versionSupport.getItemTag(inventory.getItem(slot), "agui.category");
            guiManager.getLogger().info(String.valueOf(slot));
            guiManager.getLogger().info(event.getCurrentItem().toString());
            guiManager.getLogger().info(aguiCategory);
//        BwCategory category = categoryMenu.getCategory(aguiCategory);
            guiManager.openModeGui((Player) event.getWhoClicked(), category);
        }
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        guiManager.removeOpenGui((Player) event.getPlayer());
    }
}
