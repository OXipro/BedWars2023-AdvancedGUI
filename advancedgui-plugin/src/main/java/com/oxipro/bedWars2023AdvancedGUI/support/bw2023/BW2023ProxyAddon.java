package com.oxipro.bedWars2023AdvancedGUI.support.bw2023;

import com.tomkeuper.bedwars.proxy.api.addon.Addon;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class BW2023ProxyAddon extends Addon {
    private final Plugin plugin;

    public BW2023ProxyAddon(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().get(0);
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String getName() {
        return plugin.getDescription().getName();
    }

    @Override
    public String getDescription() {
        return plugin.getDescription().getDescription();
    }

    @Override
    public void load() {
        Bukkit.getPluginManager().enablePlugin(plugin);
    }

    @Override
    public void unload() {
        Bukkit.getPluginManager().disablePlugin(plugin);
    }
}
