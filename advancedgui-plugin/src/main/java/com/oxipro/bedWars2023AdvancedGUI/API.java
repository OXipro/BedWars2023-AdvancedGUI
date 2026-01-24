package com.oxipro.bedWars2023AdvancedGUI;

import com.oxipro.bedWars2023AdvancedGUI.api.AdvancedGUI;
import com.oxipro.bedWars2023AdvancedGUI.gui.GuiManager;
import org.bukkit.entity.Player;

public class API implements AdvancedGUI {
    private final Main plugin;
    private final GuiManager guiManager;

    public API(Main plugin, GuiManager guiManager) {
        this.plugin = plugin;
        this.guiManager = guiManager;
    }

    @Override
    public void openGUI(Player player) {
        guiManager.openMainGui(player);
    }
}
