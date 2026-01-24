package com.oxipro.version.support.v1_20_R4;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ItemBuilder implements com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.ItemBuilder {

    private ItemStack item;
    private final ItemMeta meta;
    private Player player;
    private Boolean head = false;
    private v1_20_R4 v1_20_r4;

    private String category;
    private String type;
    private String arena;
    private String base64;
    private boolean isHead;
    private String name;
    private List<String> lore;

    public ItemBuilder(String input, v1_20_R4 v1_20_r4) {
        Material material = Material.matchMaterial(input);

        if (material != null) {

        } else if (isBase64(input)) {
            material = Material.PLAYER_HEAD;
            isHead = true;
            base64 = input;
        } else {
            material = Material.BARRIER;
        }
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
        this.v1_20_r4 = v1_20_r4;
    }

    @Override
    public ItemBuilder player(Player player) {
        this.player = player;
        return this;
    }

    @Override
    public ItemBuilder name(String name) {
        String parsed = player != null ? PlaceholderAPI.setPlaceholders(player, name) : name;
//        String colored = translate(parsed);
//        meta.setDisplayName(colored);
        this.name = parsed;
        return this;
    }

    @Override
    public ItemBuilder lore(List<String> lore) {
        if (!lore.isEmpty()) {
            List<String> lines = new ArrayList<>();
            for (String line : lore) {
                String parsed = player != null ? PlaceholderAPI.setPlaceholders(player, line) : line;
                lines.add(parsed);
            }
//            meta.setLore(lines);
            this.lore = lines;
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

//    @Override
//    public ItemBuilder setCategory(String value) {
//        this.item = v1_20_r4.setItemTag(item, "agui.category" , value);
//        return this;
//    }
//
//    @Override
//    public ItemBuilder setArena(String value) {
//        this.item = v1_20_r4.setItemTag(item, "agui.arena" , value);
//        return this;
//    }
//
//    @Override
//    public ItemBuilder setType(String type) {
//        this.item = v1_20_r4.setItemTag(item, "agui.type" , type);
//        return this;
//    }

    @Override
    public ItemBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    @Override
    public ItemBuilder setArena(String arena) {
        this.arena = arena;
        return this;
    }

    @Override
    public ItemBuilder setType(String type) {
        this.type = type;
        return this;
    }


    @Override
    public ItemStack build() {
        item.setItemMeta(meta);

        if (isHead && base64 != null) {
            item = v1_20_r4.playerHead(this.item, base64);
        }
        
        if (name != null) {
            v1_20_r4.getPlatformSupport().applyDisplayName(item, name);
        }
        if (lore != null) {
            v1_20_r4.getPlatformSupport().applyLore(item, lore);
        }
        if (category != null) {
            item = v1_20_r4.setItemTag(item, "agui.category", category);
        }
        if (type != null) {
            item = v1_20_r4.setItemTag(item, "agui.type", type);
        }
        if (arena != null) {
            item = v1_20_r4.setItemTag(item, "agui.arena", arena);
        }

        return item;
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
