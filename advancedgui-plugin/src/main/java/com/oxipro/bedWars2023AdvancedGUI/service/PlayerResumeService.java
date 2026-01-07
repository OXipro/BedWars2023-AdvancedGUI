package com.oxipro.bedWars2023AdvancedGUI.service;

import com.tomkeuper.bedwars.proxy.api.BedWars;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.RemoteReJoin;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerResumeService {

    private final JavaPlugin plugin;
    private final BedWars bwproxy;

    public PlayerResumeService(JavaPlugin plugin, BedWars bwproxy) {
        this.plugin = plugin;
        this.bwproxy =  bwproxy;
    }

    public RemoteReJoin getReJoin(Player player) {
        return bwproxy.getArenaUtil().getReJoin(player.getUniqueId());
    }

    public void rejoin(Player player) {
        RemoteReJoin rj = getReJoin(player);
        rj.getArena().reJoin(rj);
    }

    public CachedArena getRejoinArena(Player player) {
        return bwproxy.getArenaUtil().getReJoin(player.getUniqueId()).getArena();
    }
}
