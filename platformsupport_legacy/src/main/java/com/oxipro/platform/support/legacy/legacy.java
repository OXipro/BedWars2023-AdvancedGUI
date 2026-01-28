package com.oxipro.platform.support.legacy;

import com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport.ItemMetaBuilder;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport.PlatformSupport;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.ItemBuilder;
import com.oxipro.bedWars2023AdvancedGUI.api.language.LanguageManager;
import com.oxipro.bedWars2023AdvancedGUI.api.text.placeholders.PrasePlaceholders;
import com.tomkeuper.bedwars.proxy.api.Language;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class legacy extends PlatformSupport {




    public legacy(Plugin plugin, String name) {
        super(plugin, name);
    }

    @Override
    public ItemMetaBuilder getItemMetaBuilder(ItemStack item, LanguageManager languageManager) {
        return new com.oxipro.platform.support.legacy.ItemMetaBuilder(item, languageManager);
    }


    //    public String convert(String input) {
//        if (input == null || input.isEmpty()) return input;
//
//        input = input.replace("&", "§");
//
//        Component comp = MINI.deserialize(input);
//
//        new PrasePlaceholders(this.player, this.language);
//
//        return LEGACY.serialize(comp);
//    }

//    @Override
//    public ItemStack applyDisplayName(ItemStack item, String input, Player player, Language language) {
//        this.player = player;
//        this.language = language;
//        if (item == null || input == null) return item;
//
//        ItemMeta meta = item.getItemMeta();
//        if (meta == null) return item;
//
//        meta.setDisplayName(convert(input));
//        item.setItemMeta(meta);
//
//        return item;
//    }
//
//    @Override
//    public ItemStack applyLore(ItemStack item, List<String> input, Player player,  Language language) {
//        this.player = player;
//        this.language = language;
//        if (item == null || input == null) return item;
//
//        ItemMeta meta = item.getItemMeta();
//        if (meta == null) return item;
//
//        List<String> lore = new ArrayList<>(input.size());
//
//        for (String line : input) {
//            lore.add(convert(line));
//        }
//
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//
//        return item;
//    }

}