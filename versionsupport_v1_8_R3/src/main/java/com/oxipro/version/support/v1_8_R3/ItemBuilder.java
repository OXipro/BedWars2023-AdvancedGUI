package com.oxipro.version.support.v1_8_R3;

import com.oxipro.bedWars2023AdvancedGUI.api.ItemType;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport.ItemMetaBuilder;
import com.oxipro.bedWars2023AdvancedGUI.api.gui.category.BwCategory;
import com.oxipro.bedWars2023AdvancedGUI.api.language.LanguageManager;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.util.Base64;
import java.util.List;

public class ItemBuilder implements com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.ItemBuilder {

    private ItemStack item;
    private final ItemMeta meta;
    private Player player;
    private Boolean head = false;
    private v1_8_R3 v1_8_r3;
    private LanguageManager languageManager;

    private BwCategory category;
    private ItemType type;
    private CachedArena arena;
    private String base64;
    private boolean isHead;
    private String name;
    private List<String> lore;
    private Language language;


    public ItemBuilder(String input, v1_8_R3 v1_8_r3, LanguageManager languageManager) {
        Material material = Material.matchMaterial(input);

        if (material != null) {

        } else if (isBase64(input)) {
            material = Material.SKULL_ITEM;
            isHead = true;
            base64 = input;
        } else {
            material = Material.BARRIER;
        }
        this.languageManager = languageManager;
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
        this.v1_8_r3 = v1_8_r3;
    }

    @Override
    public ItemBuilder player(Player player) {
        this.player = player;
        return this;
    }

    @Override
    public ItemBuilder name(String name) {
//         String parsed = player != null ? PlaceholderAPI.setPlaceholders(player, name) : name;
//        String colored = translate(parsed);
//        meta.setDisplayName(colored);
        this.name = name;
        return this;
    }

    @Override
    public ItemBuilder lore(List<String> lore) {
        if (!lore.isEmpty()) {
//            List<String> lines = new ArrayList<>();
//            for (String line : lore) {
//                String parsed = player != null ? PlaceholderAPI.setPlaceholders(player, line) : line;
//                lines.add(translate(parsed));
//            }
//            meta.setLore(lines);
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
//            this.item = v1_8_r3.setItemTag(item, "agui.category" , value);
        this.category = category;
        return this;
    }

    @Override
    public ItemBuilder setArena(CachedArena arena) {
//        this.item = v1_8_r3.setItemTag(item, "agui.arena" , value);
        this.arena = arena;
        return this;
    }

    @Override
    public ItemBuilder setType(ItemType type) {
//        this.item = v1_8_r3.setItemTag(item, "agui.type" , type);
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
        if (isHead && base64 != null) {
            item = v1_8_r3.playerHead(this.item, base64);
        }
        ItemMetaBuilder itemMetaBuilder = v1_8_r3.getPlatformSupport().getItemMetaBuilder(item, languageManager);

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
            item = v1_8_r3.setItemTag(item, "agui.category", category.getKey());
        }
        if (type != null) {
            item = v1_8_r3.setItemTag(item, "agui.type", type.getId());
        }
        if (arena != null) {
            item = v1_8_r3.setItemTag(item, "agui.arena", arena.getRemoteIdentifier());
        }
        return item;
    }

    private String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static boolean isBase64(String base64) {
        try {
            Base64.getDecoder().decode(base64);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
