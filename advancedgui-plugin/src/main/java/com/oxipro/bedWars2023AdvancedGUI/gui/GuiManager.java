package com.oxipro.bedWars2023AdvancedGUI.gui;

import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.VersionSupport;
import com.oxipro.bedWars2023AdvancedGUI.config.ConfigurationManager;
import com.oxipro.bedWars2023AdvancedGUI.api.gui.category.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.language.LanguageManager;
import com.oxipro.bedWars2023AdvancedGUI.service.*;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.logging.Logger;

public class GuiManager {

    private final JavaPlugin plugin;
    private final ArenaService arenaService;
    private final CategoryService categoryService;
    private final PlayerResumeService resumeService;
    private final ConfigurationManager configurationManager;
    private final Map<String, BwCategory> bwCategories;
    private final BwProxyService bwProxyService;
    private final HotbarManagerService hbms;
    private final LanguageManager languageManager;
    private final VersionSupport versionSupport;
    private final Set<UUID> ignoreNextClose = new HashSet<>();


    private final Map<Player, AbstractGui> openGuis = new HashMap<>();

    public GuiManager(
            JavaPlugin plugin,
            ArenaService arenaService,
            CategoryService categoryService,
            PlayerResumeService resumeService,
            ConfigurationManager configurationManager,
            BwProxyService bwProxyService,
            HotbarManagerService hbms,
            LanguageManager languageManager,
            VersionSupport versionSupport
    ) {
        this.plugin = plugin;
        this.arenaService = arenaService;
        this.categoryService = categoryService;
        this.configurationManager = configurationManager;
        this.bwProxyService = bwProxyService;
        this.resumeService = resumeService;
        this.hbms = hbms;
        this.languageManager = languageManager;
        this.versionSupport = versionSupport;
        this.bwCategories = new BwCategoryMenuLoader(configurationManager.getConfig(), languageManager).load();
    }

    public void openMainGui(Player player) {
        openGui(player, new MainGui(this, player));
    }

    public void openCategoryGui(Player player) {
        openGui(player, new CategoriesSelectorGui(this, player));
    }

    public void openModeGui(Player player, BwCategory category) {
        openGui(player, new CategoryMenuGui(this, category, player));
    }

    private void openGui(Player player, AbstractGui gui) {
        openGuis.put(player, gui);
        gui.player = player;
        player.openInventory(gui.getInventory());
    }

    public AbstractGui getOpenGui(Player player) {
        return openGuis.get(player);
    }

    public AbstractGui removeOpenGui(Player player) {
        return openGuis.remove(player);
    }

    public void markIgnoreClose(Player player) {
        ignoreNextClose.add(player.getUniqueId());
    }

    public boolean shouldIgnoreClose(Player player) {
        return ignoreNextClose.remove(player.getUniqueId());
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
        openGui(player, new MapSelectorGui(this, category, player));
    }

    public BwProxyService getBwProxyService() { return bwProxyService; }

    public String getRawMsg(Player player, String path) { return languageManager.getRawMsg(player, path); }

    public List<String> getRawMsgList(Player player, String path) { return languageManager.getRawMsgList(player, path); }

    public LanguageManager getLanguageManager() { return languageManager; }

    public Map<String, BwCategory> getBwCategories() {
        return bwCategories;
    }

    public Logger getLogger() { return plugin.getLogger(); }

    public Plugin getPlugin() { return plugin; }

    public HotbarManagerService getHBMService() { return hbms; }

    public VersionSupport getVersionSupport() { return versionSupport; }
}
