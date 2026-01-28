package com.oxipro.bedWars2023AdvancedGUI.api.text.placeholders;

import com.oxipro.bedWars2023AdvancedGUI.api.gui.category.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.api.language.LanguageManager;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PrasePlaceholders {

    private Player player;
    private CachedArena arena;
    private BwCategory category;
    private Language language;
    private LanguageManager languageManager;

    int pageCurrent = 189526;
    int pageMax = 189526;

    public PrasePlaceholders(Player player, Language language, LanguageManager languageManager) {
        this.languageManager = languageManager;
        this.player = player;
        this.language = language;
    }

    public PrasePlaceholders setArena(CachedArena arena) {
        this.arena = arena;
        return this;
    }

    public PrasePlaceholders setCategory(BwCategory category) {
        this.category = category;
        return this;
    }

    public PrasePlaceholders setPages(int current, int max) {
        this.pageCurrent = current;
        this.pageMax = max;
        return this;
    }

    public String prase(String input) {
        if (input == null) return null;
        String result = input;
        if (arena != null) {
            String arenaIdentifier = arena.getRemoteIdentifier();
            result = result.replace("%bw_arena_display_name%", arena.getDisplayName(language));
            result = result.replace("%bw_arena_identifier%", arenaIdentifier);
            result = result.replace("%bw_arena_short_identifier%", arenaIdentifier.substring(arenaIdentifier.indexOf('d') + 1));
            result = result.replace("%bw_arena_players%", String.valueOf(arena.getCurrentPlayers()));
            result = result.replace("%bw_arena_max_players%", String.valueOf(arena.getMaxPlayers()));
            result = result.replace("%bw_arena_status%", String.valueOf(arena.getStatus()).toLowerCase());
            result = result.replace("%bw_arena_group%",  arena.getDisplayGroup(language));
        }


        if ((pageCurrent != 189526) && (pageMax != 189526)) {
            result = result.replace("%bw_pages_current%", String.valueOf(pageCurrent));
            result = result.replace("%bw_pages_max%", String.valueOf(pageMax));
        }

        if (category != null) {
            result = result.replace("%bw_selected_category%", languageManager.getRawMsg(player, "display-group-" + category.getKey()));
        }

        if (player != null) {
            result = PlaceholderAPI.setPlaceholders(player, result);
        }

        return result;
    }

    public List<String> prase(List<String> inputList) {
        List<String> resultList = new ArrayList<>();
        for (String inputLine : inputList) {
            resultList.add(prase(inputLine));
        }
        return resultList;
    }
}
