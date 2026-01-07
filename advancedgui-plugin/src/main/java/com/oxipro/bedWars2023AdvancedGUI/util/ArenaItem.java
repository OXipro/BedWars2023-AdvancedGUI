package com.oxipro.bedWars2023AdvancedGUI.util;

import com.tomkeuper.bedwars.proxy.api.ArenaStatus;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ArenaItem {
    private Language language;
    private CachedArena arena;
    public ArenaItem(CachedArena arena, Language language) {
        this.arena = arena;
        this.language = language;
    }

    public ItemStack createArenaItem() {

        Material mt = Material.RED_STAINED_GLASS;
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
        if (as == ArenaStatus.WAITING )  { mt = Material.YELLOW_STAINED_GLASS; ArenaStatusColor = "<yellow>";}
        if (as == ArenaStatus.STARTING )  { mt = Material.LIME_STAINED_GLASS; ArenaStatusColor = "<green>";}
        if (as == ArenaStatus.PLAYING )  { mt = Material.LIGHT_BLUE_STAINED_GLASS; ArenaStatusColor = "<blue>"; }
        if (as == ArenaStatus.RESTARTING )  { mt = Material.BLUE_STAINED_GLASS; ArenaStatusColor = "<dark_blue>";}


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
