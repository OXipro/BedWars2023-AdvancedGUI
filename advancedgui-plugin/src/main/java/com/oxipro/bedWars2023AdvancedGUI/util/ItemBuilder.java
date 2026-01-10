package com.oxipro.bedWars2023AdvancedGUI.util;

import com.destroystokyo.paper.profile.ProfileProperty;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;
    private final MiniMessage mm;
    private Player player;
    private Boolean head = false;

    public static boolean isBase64(String base64) {
        try {
            Base64.getDecoder().decode(base64);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
        this.mm = MiniMessage.miniMessage();
    }

    public ItemBuilder(String material) {
        Material realMaterial = Material.BARRIER;
        if (Material.valueOf(material) == null) {
            if (isBase64(material)) {
                realMaterial = Material.PLAYER_HEAD;
                head = true;
            }
        } else {
            realMaterial = Material.valueOf(material);
        }
        this.item = new ItemStack(realMaterial);
        if (head) {item.toString().split("name=textures, value=")[1].split(", signature=")[0].equals(material);}
        this.meta = item.getItemMeta();
        this.mm = MiniMessage.miniMessage();
    }

    public ItemBuilder player(Player player) {
        this.player = player;
        return this;
    }

    public ItemBuilder name(String name) {
        meta.displayName(mm.deserialize(PlaceholderAPI.setPlaceholders(player , name)).decoration(TextDecoration.ITALIC, false));
        return this;
    }

    public ItemBuilder name(Component name) {
        meta.displayName(name.decoration(TextDecoration.ITALIC, false));
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        if (!lore.isEmpty()) {
            List<Component> loreComponents = new ArrayList<>();
            for (String line : lore) {
                loreComponents.add(mm.deserialize(PlaceholderAPI.setPlaceholders(player , line)).decoration(TextDecoration.ITALIC, false));
            }
            meta.lore(loreComponents);
        }
        return this;
    }

    public ItemBuilder loreCMP(List<Component> lore) {
        if (!lore.isEmpty()) {
            List<Component> loreComponents = new ArrayList<>();
            for (Component line : lore) {
                loreComponents.add(line.decoration(TextDecoration.ITALIC, false));
            }
            meta.lore(loreComponents);
        }
        return this;
    }


    public ItemBuilder glow(boolean glow) {
        if (glow) {
            meta.addEnchant(Enchantment.THORNS, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        return this;
    }

    public ItemBuilder amount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder setCategory(String value) {
        meta.getPersistentDataContainer().set(new NamespacedKey("agui", "category"), PersistentDataType.STRING, value);
        return this;
    }

    public ItemBuilder setArena(String value) {
        meta.getPersistentDataContainer().set(new NamespacedKey("agui", "arena"), PersistentDataType.STRING, value);
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }
}
