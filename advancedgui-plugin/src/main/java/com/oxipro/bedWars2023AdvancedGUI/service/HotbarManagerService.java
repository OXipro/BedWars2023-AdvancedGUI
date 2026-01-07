package com.oxipro.bedWars2023AdvancedGUI.service;

import me.kiiya.hotbarmanager.api.HotbarManager;
import org.bukkit.plugin.Plugin;

public class HotbarManagerService {

    private final HotbarManager hbm;
    private final Plugin plugin;

    public HotbarManagerService(Plugin plugin, HotbarManager hbm) {
        this.hbm = hbm;
        this.plugin = plugin;
    }

    public HotbarManager getHbmAPI() { return hbm; }
}