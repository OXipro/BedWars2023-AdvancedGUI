package com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport;

import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.ItemBuilder;
import com.oxipro.bedWars2023AdvancedGUI.api.language.LanguageManager;
import com.tomkeuper.bedwars.proxy.api.Language;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;

public abstract class PlatformSupport {
    private final Plugin plugin;
    private final String versionName;

    public PlatformSupport(Plugin plugin, String versionName) {
        this.versionName = versionName;
        this.plugin = plugin;
    }

    public String getName() {
        return versionName;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public abstract ItemMetaBuilder getItemMetaBuilder(ItemStack item, LanguageManager languageManager);


//    public abstract ItemStack applyDisplayName(ItemStack item, String input, Player player, Language language);
//
//    public abstract ItemStack applyLore(ItemStack item, List<String> input, Player player, Language language);


}
