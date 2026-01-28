package com.oxipro.platform.support.legacy;

import com.oxipro.bedWars2023AdvancedGUI.api.gui.category.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.api.language.LanguageManager;
import com.oxipro.bedWars2023AdvancedGUI.api.text.placeholders.PrasePlaceholders;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemMetaBuilder extends com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport.ItemMetaBuilder {

    private static final MiniMessage MINI = MiniMessage.miniMessage();
    private static final LegacyComponentSerializer LEGACY = LegacyComponentSerializer.legacySection();
    private Player player;
    private Language language;
    private BwCategory bwCategory;
    private CachedArena arena;
    private ItemStack item;
    private LanguageManager languageManager;


    public ItemMetaBuilder(ItemStack item, LanguageManager languageManager) {
        this.languageManager = languageManager;
        this.item = item;
    }

    private String convert(String input) {
        if (input == null || input.isEmpty()) return input;

        input = new PrasePlaceholders(this.player, this.language, this.languageManager).setCategory(bwCategory).setArena(arena).prase(input);

        input = input.replace("&", "§");

        Component comp = MINI.deserialize(input);

        return LEGACY.serialize(comp);
    }


    @Override
    public ItemMetaBuilder setArena(CachedArena arena) {
        this.arena = arena;
        return this;
    }

    @Override
    public ItemMetaBuilder setPlayer(Player player) {
        this.player = player;
        return this;
    }

    @Override
    public ItemMetaBuilder setCategory(BwCategory bwCategory) {
        this.bwCategory = bwCategory;
        return this;
    }

    @Override
    public ItemMetaBuilder setLanguage(Language language) {
        this.language = language;
        return this;
    }

    @Override
    public ItemMetaBuilder setDisplayName(String input) {
        if (item == null || input == null) return this;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return this;

        meta.setDisplayName(ChatColor.RESET + convert(input));

        item.setItemMeta(meta);

        return this;
    }

    @Override
    public ItemMetaBuilder setLore(List<String> input) {
        if (item == null || input == null) return this;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return this;

        List<String> lore = new ArrayList<>(input.size());

        for (String line : input) {
            lore.add(ChatColor.RESET + convert(line));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        return this;
    }

    @Override
    public ItemStack build() {
        return item;
    }
}
