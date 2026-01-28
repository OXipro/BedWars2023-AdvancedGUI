package com.oxipro.version.support.v1_21_R5;

import com.oxipro.bedWars2023AdvancedGUI.api.ItemType;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport.ItemMetaBuilder;
import com.oxipro.bedWars2023AdvancedGUI.api.gui.category.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.api.language.LanguageManager;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.net.URL;
import java.util.List;

public class ItemBuilder implements com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.ItemBuilder {

    private ItemStack item;
    private final ItemMeta meta;
    private Player player;
    private v1_21_R5 v1_21_r5;
    private LanguageManager languageManager;


    private BwCategory category;
    private ItemType type;
    private CachedArena arena;
    private String url;
    private boolean isHead;
    private String name;
    private List<String> lore;
    private Language language;

    public ItemBuilder(String input, v1_21_R5 v1_21_r5, LanguageManager languageManager) {
        Material material = Material.matchMaterial(input);

        if (material != null) {

        } else if (isUrl(input)) {
            material = Material.PLAYER_HEAD;
            isHead = true;
            url = input;
        } else {
            material = Material.BARRIER;
        }

        this.languageManager = languageManager;
        this.item = new ItemStack(material);
        this.meta = this.item.getItemMeta();
        this.v1_21_r5 = v1_21_r5;
    }

    @Override
    public ItemBuilder player(Player player) {
        this.player = player;
        return this;
    }

    @Override
    public ItemBuilder name(String name) {
//        String parsed = player != null ? PlaceholderAPI.setPlaceholders(player, name) : name;
        this.name = name;
        return this;
    }

    @Override
    public ItemBuilder lore(List<String> lore) {
        if (!lore.isEmpty()) {
//            List<String> lines = new ArrayList<>();
//            for (String line : lore) {
//                String parsed = player != null ? PlaceholderAPI.setPlaceholders(player, line) : line;
//                lines.add(parsed);
//            }
            this.lore = lore;
        }
        return this;
    }

    @Override
    public ItemBuilder glow(boolean glow) {
        if (glow) {
            meta.addEnchant(Enchantment.THORNS, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        return this;
    }

    @Override
    public ItemBuilder amount(int amount) {
        item.setAmount(amount);
        return this;
    }

    @Override
    public ItemBuilder setCategory(BwCategory category) {
        this.category = category;
        return this;
    }

    @Override
    public ItemBuilder setArena(CachedArena arena) {
        this.arena = arena;
        return this;
    }

    @Override
    public ItemBuilder setType(ItemType type) {
        this.type = type;
        return this;
    }

    @Override
    public ItemBuilder setLanguage(Language language) {
        this.language = language;
        return this;
    }


    @Override
    public ItemStack build() {
        item.setItemMeta(meta);

        if (isHead && url != null) {
            item = v1_21_r5.playerHead(this.item, url);
        }
        ItemMetaBuilder itemMetaBuilder = v1_21_r5.getPlatformSupport().getItemMetaBuilder(item, languageManager);

        if (language != null) {
            itemMetaBuilder.setLanguage(language);
        }
        if (player != null) {
            itemMetaBuilder.setPlayer(player);
        }
        if (arena != null) {
            itemMetaBuilder.setArena(arena);
        }
        if (category != null) {
            itemMetaBuilder.setCategory(category);
        }

        if (name != null) {
            itemMetaBuilder.setDisplayName(name);
        }
        if (lore != null) {
            itemMetaBuilder.setLore(lore);
        }

        item = itemMetaBuilder.build();
        if (category != null) {
            item = v1_21_r5.setItemTag(item, "agui.category", category.getKey());
        }
        if (type != null) {
            item = v1_21_r5.setItemTag(item, "agui.type", type.getId());
        }
        if (arena != null) {
            item = v1_21_r5.setItemTag(item, "agui.arena", arena.getRemoteIdentifier());
        }
        return item;
    }

    private boolean isUrl(String input) {
        try {
            new URL(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
