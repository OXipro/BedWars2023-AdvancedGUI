package com.oxipro.bedWars2023AdvancedGUI.util;

import com.tomkeuper.bedwars.proxy.api.ArenaStatus;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static com.oxipro.bedWars2023AdvancedGUI.config.ConfigPaths.*;

public class ArenaItem {
    private Language language;
    private CachedArena arena;
    private FileConfiguration fileConfiguration;

    public ArenaItem(CachedArena arena, Language language, FileConfiguration configuration) {
        this.arena = arena;
        this.language = language;
        this.fileConfiguration = configuration;
    }

    public ItemStack createArenaItem() {

        String mt = "RED_STAINED_GLASS";
        List<String> ll;

        int amount = arena != null ? Math.max(1, arena.getCurrentPlayers()) : 1;

        if (arena == null) {
            ll = List.of(
                    "",
                    " <red>There is no arena available to rejoin.",
                    ""
            );
            ItemStack item = new ItemBuilder(mt)
                    .name("<red>No arena available")
                    .lore(ll)
                    .build();
            return item;
        }
        final ArenaStatus as = arena.getStatus();
        String ArenaStatusColor = "<red>";
        if (as == ArenaStatus.WAITING )  { mt = fileConfiguration.getString(GUI_MAIN_ARENAS_MATERIAL_WAITING); ArenaStatusColor = "<yellow>";}
        if (as == ArenaStatus.STARTING )  { mt = fileConfiguration.getString(GUI_MAIN_ARENAS_MATERIAL_STARTING); ArenaStatusColor = "<green>";}
        if (as == ArenaStatus.PLAYING )  { mt = fileConfiguration.getString(GUI_MAIN_ARENAS_MATERIAL_PLAYING); ArenaStatusColor = "<blue>"; }
        if (as == ArenaStatus.RESTARTING )  { mt = fileConfiguration.getString(GUI_MAIN_ARENAS_MATERIAL_RESTARTING); ArenaStatusColor = "<dark_blue>";}


        ll = List.of(
                "",
                " <grey>Map:<white> " + arena.getDisplayName(language),
                " <grey>Category:<white> " + arena.getDisplayGroup(language),
                " <grey>Online:<white> " + arena.getCurrentPlayers() + "/" + arena.getMaxPlayers(),
                " <grey>Status: " + ArenaStatusColor + arena.getStatus(),
                "",
                "<b><color:#ffb300>Click to join!</color></b>"
        );

        String arenaIdentifier = arena.getRemoteIdentifier();
        ItemStack item = new ItemBuilder(mt)
                .name("<aqua>Arena: " + arenaIdentifier.substring(arenaIdentifier.indexOf('d') + 1))
                .lore(ll)
                .amount(amount)
                .setArena(arenaIdentifier)
                .build();
        return item;
    }
}
