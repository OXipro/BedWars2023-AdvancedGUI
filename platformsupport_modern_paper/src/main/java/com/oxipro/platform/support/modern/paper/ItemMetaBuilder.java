package com.oxipro.platform.support.modern.paper;

import com.oxipro.bedWars2023AdvancedGUI.api.gui.category.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.api.language.LanguageManager;
import com.oxipro.bedWars2023AdvancedGUI.api.text.placeholders.PrasePlaceholders;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemMetaBuilder extends com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport.ItemMetaBuilder {


    private static final MiniMessage MINI = MiniMessage.miniMessage();
    private static final LegacyComponentSerializer LEGACY =
            LegacyComponentSerializer.builder()
                    .character('&')
                    .hexColors()
                    .useUnusualXRepeatedCharacterHexFormat() // §x§F§F§0§0§0§0
                    .build();
    private CachedArena arena;
    private Player player;
    private BwCategory bwCategory;
    private Language language;
    private ItemStack item;
    private LanguageManager languageManager;


    public ItemMetaBuilder(ItemStack item, LanguageManager languageManager) {
        this.languageManager = languageManager;
        this.item = item;
    }

    public Component convert(String input) {
        if (input == null || input.isEmpty()) return Component.empty();

        input = new PrasePlaceholders(this.player, this.language, this.languageManager)
                .setCategory(bwCategory)
                .setArena(arena)
                .prase(input);

        input = input.replace('§', '&');

        Component component = MINI.deserialize(input);

        String legacySerialized = LEGACY.serialize(component);
        return LEGACY.deserialize(legacySerialized);
    }

    @Override
    public com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport.ItemMetaBuilder setArena(CachedArena arena) {
        this.arena = arena;
        return this;
    }

    @Override
    public com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport.ItemMetaBuilder setPlayer(Player player) {
        this.player = player;
        return this;
    }

    @Override
    public com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport.ItemMetaBuilder setCategory(BwCategory bwCategory) {
        this.bwCategory = bwCategory;
        return this;
    }

    @Override
    public com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport.ItemMetaBuilder setLanguage(Language language) {
        this.language = language;
        return this;
    }



    @Override
    public ItemMetaBuilder setDisplayName(String input) {
        if (item == null || input == null) return this;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return this;

        meta.displayName(convert("<reset>" + input).decoration(TextDecoration.ITALIC, false));
        item.setItemMeta(meta);

        return this;
    }

    @Override
    public ItemMetaBuilder setLore(List<String> input) {
        if (item == null || input == null) return this;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return this;

        List<Component> lore = new ArrayList<>(input.size());
        for (String line : input) {
            lore.add(convert("<white>" + line).decoration(TextDecoration.ITALIC, false));
        }

        meta.lore(lore);
        item.setItemMeta(meta);

        return this;
    }

    @Override
    public ItemStack build() {
        return item;
    }
}
