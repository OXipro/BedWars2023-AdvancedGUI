package com.oxipro.bedWars2023AdvancedGUI.gui;

import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.VersionSupport;
import com.oxipro.bedWars2023AdvancedGUI.config.ConfigurationManager;
import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategoryMenu;
import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategoryMenuLoader;
import com.oxipro.bedWars2023AdvancedGUI.language.LanguageManager;
import com.oxipro.bedWars2023AdvancedGUI.service.*;

import net.kyori.adventure.text.Component;
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
    private final BwCategoryMenu bwCategoryMenu;
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
        this.bwCategoryMenu = new BwCategoryMenuLoader(configurationManager.getConfig()).load();
        this.bwProxyService = bwProxyService;
        this.resumeService = resumeService;
        this.hbms = hbms;
        this.languageManager = languageManager;
        this.versionSupport = versionSupport;

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
        openGui(player, new MapSelectorGui(this, category));
    }

    public BwProxyService getBwProxyService() {return bwProxyService; }

    public Component getMMMsg(Player player, String path) {return languageManager.getMMMsg(player, path); }

    public String getRawMsg(Player player, String path) {return languageManager.getRawMsg(player, path); }

    public List<String> getRawMsgList(Player player, String path) {return languageManager.getRawMsgList(player, path); }

    public LanguageManager getLanguageManager() {return languageManager; }

    public BwCategoryMenu loadBwCategoryMenu() {
        return bwCategoryMenu;
    }

    public Logger getLogger() { return plugin.getLogger(); }

    public Plugin getPlugin() { return plugin; }

    public HotbarManagerService getHBMService() { return hbms; }

    public VersionSupport getVersionSupport() {return versionSupport;}
}
