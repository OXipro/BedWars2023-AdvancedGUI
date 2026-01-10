package com.oxipro.bedWars2023AdvancedGUI;

import com.oxipro.bedWars2023AdvancedGUI.command.BWAGUICommand;
import com.oxipro.bedWars2023AdvancedGUI.config.ConfigurationManager;
import com.oxipro.bedWars2023AdvancedGUI.gui.GuiManager;
import com.oxipro.bedWars2023AdvancedGUI.language.LanguageManager;
import com.oxipro.bedWars2023AdvancedGUI.listener.InventoryClickListener;
import com.oxipro.bedWars2023AdvancedGUI.listener.InventoryCloseListener;
import com.oxipro.bedWars2023AdvancedGUI.service.*;
import com.tomkeuper.bedwars.proxy.BedWarsProxy;
import com.tomkeuper.bedwars.proxy.api.BedWars;
import com.tomkeuper.bedwars.proxy.libs.bukkit.Metrics;
import me.kiiya.hotbarmanager.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;


public final class   Main extends JavaPlugin {

    private BedWars bwproxy = null;
    private me.kiiya.hotbarmanager.api.HotbarManager hbm = null;
    private Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Plugin bedWarsPlugin = Bukkit.getPluginManager().getPlugin("BWProxy2023");
        Plugin hotBarManagerPlugin = Bukkit.getPluginManager().getPlugin("HotbarManager");


        if (bedWarsPlugin != null && bedWarsPlugin.isEnabled()) {
            try {
                bwproxy = BedWarsProxy.getAPI();
                getLogger().info("Bwproxy2023 OK.");
            } catch (Throwable t) {
                getLogger().severe("error with bwproxy2023 api disabling feature.");
                Bukkit.getPluginManager().disablePlugin(plugin);
                return;
            }
        } else {
            getLogger().info("bw proxy2023 not found disabling addon");
            Bukkit.getPluginManager().disablePlugin(plugin);
            return;
        }

        if (hotBarManagerPlugin != null && hotBarManagerPlugin.isEnabled()) {
            try {
                hbm = HotbarManager.getAPI();
                getLogger().info("HotbarManager from Kiiya OK. Feature loaded");
            } catch (Throwable t) {
                getLogger().severe("error with HotbarManager api disabling feature.");
            }
        } else {
            getLogger().info("HotbarManager from Kiiya not found disabling Feature");
        }

        BwProxyService bwProxyService = new BwProxyService(this, bwproxy);
        ConfigurationManager configManager = new ConfigurationManager(this);
        LanguageManager languageManager = new LanguageManager(bwProxyService);
        ArenaService arenaService = new ArenaService(this, bwProxyService, configManager.getConfig(), languageManager);
        CategoryService categoryService = new CategoryService(this);
        PlayerResumeService resumeService = new PlayerResumeService(this, bwproxy);
        HotbarManagerService hbmService = new HotbarManagerService(this, hbm);

        languageManager.load();

        if (configManager.getConfig().getBoolean("force-legacy-material-load-at-enable")) {
            //noinspection removal
            Material mt = Material.LEGACY_IRON_DOOR_BLOCK; }

        GuiManager guiManager = new GuiManager(
                this,
                arenaService,
                categoryService,
                resumeService,
                configManager,
                bwProxyService,
                hbmService,
                languageManager
        );

        getCommand("bwagui").setExecutor(new BWAGUICommand(guiManager));
        getServer().getPluginManager().registerEvents(
                new InventoryClickListener(guiManager),
                this
        );

        getServer().getPluginManager().registerEvents(
                new InventoryCloseListener(guiManager),
                this
        );

        getServer().getServicesManager().register(
                com.oxipro.bedWars2023AdvancedGUI.api.API.class,
                new com.oxipro.bedWars2023AdvancedGUI.API(this, guiManager),
                this,
                ServicePriority.Normal
        );

        new Metrics(this, 28757);
    }

    @Override
    public void onDisable() {
        getServer().getServicesManager().unregisterAll(this);
    }
}
