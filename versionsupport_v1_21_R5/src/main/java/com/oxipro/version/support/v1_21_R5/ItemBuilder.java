package com.oxipro.version.support.v1_21_R5;

import com.oxipro.bedWars2023AdvancedGUI.api.ItemType;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ItemBuilder implements com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.ItemBuilder {

    private ItemStack item;
    private final ItemMeta meta;
    private Player player;
    private v1_21_R5 v1_21_r5;

    private String category;
    private ItemType type;
    private String arena;
    private String url;
    private boolean isHead;
    private String name;
    private List<String> lore;


//    public ItemBuilder(String material, v1_21_R5 v1_21_r5) {
//        Material realMaterial = Material.BARRIER;
//        if (Material.matchMaterial(material) == null) {
//            if (isBase64(material)) {
//                realMaterial = Material.PLAYER_HEAD;
//                head = true;
//            }
//        } else {
//            realMaterial = Material.matchMaterial(material);
//        }
//        this.item = new ItemStack(realMaterial);
//        this.meta = item.getItemMeta();
//        this.v1_21_r5 = v1_21_r5;
//        if (head) {
//            PlayerHead.applyTexture(item, material);
//        }
//    }

    public ItemBuilder(String input, v1_21_R5 v1_21_r5) {
        Material material = Material.matchMaterial(input);

        if (material != null) {

        } else if (isUrl(input)) {
            material = Material.PLAYER_HEAD;
            isHead = true;
            url = input;
        } else {
            material = Material.BARRIER;
        }


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

    @Override
    public ItemBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    @Override
    public ItemBuilder setArena(String arena) {
        //this.item = v1_21_r5.setItemTag(item, "agui.arena" , value);
        this.arena = arena;
        return this;
    }

    @Override
    public ItemBuilder setType(ItemType type) {
//        this.item = v1_21_r5.setItemTag(item, "agui.type" , type);
        this.type = type;
        return this;
    }

    @Override
    public ItemStack build() {
        item.setItemMeta(meta);

        if (isHead && url != null) {
            item = v1_21_r5.playerHead(this.item, url);
        }
        if (name != null) {
            v1_21_r5.getPlatformSupport().applyDisplayName(item, name);
        }
        if (lore != null) {
            v1_21_r5.getPlatformSupport().applyLore(item, lore);
        }
        if (category != null) {
            item = v1_21_r5.setItemTag(item, "agui.category", category);
        }
        if (type != null) {
            item = v1_21_r5.setItemTag(item, "agui.type", type.getId());
        }
        if (arena != null) {
            item = v1_21_r5.setItemTag(item, "agui.arena", arena);
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
