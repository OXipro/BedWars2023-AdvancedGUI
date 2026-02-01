package com.oxipro.bedWars2023AdvancedGUI.support.bw2023;

import com.tomkeuper.bedwars.proxy.api.BedWars;
import org.bukkit.plugin.Plugin;

public class BW2023Proxy {
    private final Plugin plugin;
    private final BedWars bwproxy;

    public BW2023Proxy(Plugin plugin, BedWars bwproxy) {
        this.plugin = plugin;
        this.bwproxy = bwproxy;
        start();
    }
    public void start() {
        bwproxy.getAddonsUtil().registerAddon(new BW2023ProxyAddon(plugin));
    }
}
