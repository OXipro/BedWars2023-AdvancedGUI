package com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport;

import com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport.PlatformSupport;
import com.oxipro.bedWars2023AdvancedGUI.api.language.LanguageManager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public abstract class VersionSupport {
    private final Plugin plugin;
    private final String versionName;
    private final PlatformSupport platformSupport;

    public VersionSupport(Plugin plugin, String versionName, PlatformSupport platformSupport) {
        this.versionName = versionName;
        this.plugin = plugin;
        this.platformSupport = platformSupport;
    }

    public String getName() {
        return versionName;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public PlatformSupport getPlatformSupport() { return this.platformSupport; }

    public abstract ItemStack setItemTag(ItemStack item, String key, String value);

    public abstract String getItemTag(ItemStack item, String key);

    public abstract ItemBuilder itemBuilder(String material, LanguageManager languageManager);

}