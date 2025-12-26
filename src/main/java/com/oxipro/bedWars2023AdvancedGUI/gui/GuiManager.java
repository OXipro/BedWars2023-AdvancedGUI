package com.oxipro.bedWars2023AdvancedGUI.gui;

import com.oxipro.bedWars2023AdvancedGUI.config.ConfigurationManager;
import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategoryMenu;
import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategoryMenuLoader;
import com.oxipro.bedWars2023AdvancedGUI.service.*;
import com.tomkeuper.bedwars.proxy.api.BedWars;
import me.kiiya.hotbarmanager.api.HotbarManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class GuiManager {

    private final JavaPlugin plugin;
    private final ArenaService arenaService;
    private final CategoryService categoryService;
    private final PlayerResumeService resumeService;
    private final ConfigurationManager configurationManager;
    private final BwCategoryMenu bwCategoryMenu;
    private final BwProxyService bwProxyService;
    private final HotbarManagerService hbms;


    private final Map<Player, AbstractGui> openGuis = new HashMap<>();

    public GuiManager(
            JavaPlugin plugin,
            ArenaService arenaService,
            CategoryService categoryService,
            PlayerResumeService resumeService,
            ConfigurationManager configurationManager,
            BwProxyService bwProxyService,
            HotbarManagerService hbms
    ) {
        this.plugin = plugin;
        this.arenaService = arenaService;
        this.categoryService = categoryService;
        this.configurationManager = configurationManager;
        this.bwCategoryMenu = new BwCategoryMenuLoader(configurationManager.getConfig()).load();
        this.bwProxyService = bwProxyService;
        this.resumeService = resumeService;
        this.hbms = hbms;

    }

    public void openMainGui(Player player) {
        openGui(player, new MainGui(this, player));
    }

    public void openCategoryGui(Player player) {
        openGui(player, new CategoryGui(this));
    }

    public void openModeGui(Player player, BwCategory category) {
        openGui(player, new ModeGui(this, category, player));
    }

    private void openGui(Player player, AbstractGui gui) {
        openGuis.put(player, gui);
        player.openInventory(gui.getInventory());
    }

    public AbstractGui getOpenGui(Player player) {
        return openGuis.get(player);
    }

    public ArenaService arenas() {
        return arenaService;
    }

    public CategoryService categories() {
        return categoryService;
    }

    public PlayerResumeService resume() {
        return resumeService;
    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    public void openMapSelectorGui(Player player, BwCategory category) {
        openGui(player, new MapSelectorGui(this, category));
    }

    public BwProxyService getBwProxyService() {return bwProxyService; }

    public BwCategoryMenu loadBwCategoryMenu() {
        return bwCategoryMenu;
    }

    public Logger getLogger() { return plugin.getLogger(); }

    public HotbarManagerService getHBMService() { return hbms; }
}
