package com.oxipro.bedWars2023AdvancedGUI;

import com.oxipro.bedWars2023AdvancedGUI.api.AdvancedGUI;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport.PlatformSupport;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.VersionSupport;
import com.oxipro.bedWars2023AdvancedGUI.command.BWAGUICommand;
import com.oxipro.bedWars2023AdvancedGUI.config.ConfigurationManager;
import com.oxipro.bedWars2023AdvancedGUI.gui.GuiManager;
import com.oxipro.bedWars2023AdvancedGUI.language.LanguageManager;
import com.oxipro.bedWars2023AdvancedGUI.listener.InventoryClickListener;
import com.oxipro.bedWars2023AdvancedGUI.listener.InventoryCloseListener;
import com.oxipro.bedWars2023AdvancedGUI.service.*;
import com.oxipro.platform.support.legacy.legacy;
import com.oxipro.platform.support.modern.paper.paperModern;
import com.oxipro.version.support.v1_8_R3.v1_8_R3;
import com.oxipro.version.support.v1_12_R1.v1_12_R1;
import com.oxipro.version.support.v1_16_R3.v1_16_R3;
import com.oxipro.version.support.v1_17_R1.v1_17_R1;
import com.oxipro.version.support.v1_18_R2.v1_18_R2;
import com.oxipro.version.support.v1_20_R4.v1_20_R4;
import com.oxipro.version.support.v1_21_R5.v1_21_R5;
import com.tomkeuper.bedwars.proxy.BedWarsProxy;
import com.tomkeuper.bedwars.proxy.api.BedWars;
import com.tomkeuper.bedwars.proxy.libs.bukkit.Metrics;
import me.kiiya.hotbarmanager.HotbarManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    private BedWars bwproxy = null;
    private me.kiiya.hotbarmanager.api.HotbarManager hbm = null;
    private Plugin plugin;
    private static final String minecraftVersion = Bukkit.getServer().getBukkitVersion().split("-")[0];
//    private static String nmsVersion = Bukkit.getServer().getClass().getName().split("\\.")[3];
    private static String nmsVersion;
    private static final String platform = Bukkit.getServer().getName();
    private VersionSupport versionSupport;
    private PlatformSupport platformSupport;



    @Override
    public void onEnable() {
        plugin = this;
        Plugin bedWarsPlugin = Bukkit.getPluginManager().getPlugin("BWProxy2023");
        Plugin hotBarManagerPlugin = Bukkit.getPluginManager().getPlugin("HotbarManager");

        nmsVersion = getNMSVersion();

        getLogger().info("Detected Minecraft: " + minecraftVersion);
        getLogger().info("Detected Platform: " + platform);
        getLogger().info("NMS Version: " + nmsVersion);

        switch (platform) {
            case "Paper":
                getLogger().info("Using paper platform support");
                platformSupport = new paperModern(plugin, platform);
                break;
            default:
                getLogger().warning("Falling back to legacy no other platform support found");
                platformSupport = new legacy(plugin, platform);
                break;
        }

        switch (minecraftVersion) {
            case "1.8.8":
                versionSupport = new v1_8_R3(plugin, nmsVersion, platformSupport);
                break;
            case "1.12.2":
                versionSupport = new v1_12_R1(plugin, nmsVersion, platformSupport);
                break;
            case "1.16.5":
                versionSupport = new v1_16_R3(plugin, nmsVersion, platformSupport);
                break;
            case "1.17.1":
                versionSupport = new v1_17_R1(plugin, nmsVersion, platformSupport);
                break;
            case "1.18.2":
                versionSupport = new v1_18_R2(plugin, nmsVersion, platformSupport);
                break;
            case "1.20.6":
                versionSupport = new v1_20_R4(plugin, nmsVersion, platformSupport);
                break;
            case "1.21.8":
                versionSupport = new v1_21_R5(plugin, nmsVersion, platformSupport);
                break;
            default:
                getLogger().severe("No nms found for " + minecraftVersion + " " + nmsVersion);
                Bukkit.getPluginManager().disablePlugin(plugin);
                break;
        }




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
        ArenaService arenaService = new ArenaService(this, bwProxyService, configManager.getConfig(), languageManager, versionSupport);
        CategoryService categoryService = new CategoryService(this);
        PlayerResumeService resumeService = new PlayerResumeService(this, bwproxy);
        HotbarManagerService hbmService = new HotbarManagerService(this, hbm);

        languageManager.load();

        GuiManager guiManager = new GuiManager(
                this,
                arenaService,
                categoryService,
                resumeService,
                configManager,
                bwProxyService,
                hbmService,
                languageManager,
                versionSupport
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
                AdvancedGUI.class,
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

    public String getNMSVersion() {
        String packageName = Bukkit.getServer().getClass().getPackage().getName(); // org.bukkit.craftbukkit.V1_21_R4
        return packageName.substring(packageName.lastIndexOf(".") + 1); // V1_21_R4
    }
}
