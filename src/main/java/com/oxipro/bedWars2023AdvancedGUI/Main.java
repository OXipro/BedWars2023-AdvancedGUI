package com.oxipro.bedWars2023AdvancedGUI;

import com.oxipro.bedWars2023AdvancedGUI.command.BWAGUICommand;
import com.oxipro.bedWars2023AdvancedGUI.config.ConfigurationManager;
import com.oxipro.bedWars2023AdvancedGUI.gui.GuiManager;
import com.oxipro.bedWars2023AdvancedGUI.listener.InventoryClickListener;
import com.oxipro.bedWars2023AdvancedGUI.service.*;
import com.tomkeuper.bedwars.proxy.BedWarsProxy;
import com.tomkeuper.bedwars.proxy.api.BedWars;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import me.kiiya.hotbarmanager.HotbarManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public final class Main extends JavaPlugin {

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
        ArenaService arenaService = new ArenaService(this, bwProxyService);
        CategoryService categoryService = new CategoryService(this);
        PlayerResumeService resumeService = new PlayerResumeService(this, bwproxy);
        HotbarManagerService hbmService = new HotbarManagerService(this, hbm);

        GuiManager guiManager = new GuiManager(
                this,
                arenaService,
                categoryService,
                resumeService,
                configManager,
                bwProxyService,
                hbmService
        );

        getCommand("bwagui").setExecutor(new BWAGUICommand(guiManager));
        getServer().getPluginManager().registerEvents(
                new InventoryClickListener(guiManager),
                this
        );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
