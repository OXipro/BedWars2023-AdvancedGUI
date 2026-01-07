package com.oxipro.bedWars2023AdvancedGUI.service;

import com.tomkeuper.bedwars.proxy.api.BedWars;
import com.tomkeuper.bedwars.proxy.api.Language;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BwProxyService {
    private final BedWars bwproxy;
    private final Plugin plugin;

    public BwProxyService(Plugin plugin, BedWars bwproxy) {
        this.bwproxy = bwproxy;
        this.plugin = plugin;
    }

    public BedWars getBwproxy() {
        return bwproxy;
    }

    public Language getPlayerLanguage(Player player) {
        return bwproxy.getLanguageUtil().getPlayerLanguage(player);
    }

    public String getMsg(Player player, String inputPath) { return bwproxy.getLanguageUtil().getMsg(player, inputPath); }
}
