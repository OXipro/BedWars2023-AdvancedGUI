package com.oxipro.version.support.v1_21_R5;

import com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport.PlatformSupport;
import org.bukkit.NamespacedKey;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.ItemBuilder;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.VersionSupport;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;


public final class v1_21_R5 extends VersionSupport {
    private final Plugin plugin;

    public v1_21_R5(Plugin plugin, String name, PlatformSupport platformSupport) {
        super(plugin, name, platformSupport);
        this.plugin = plugin;
    }

    @Override
    public ItemBuilder itemBuilder(String material) {
        return new com.oxipro.version.support.v1_21_R5.ItemBuilder(material, this);
    }

    public ItemStack playerHead(ItemStack item, String url) {
        PlayerHead playerHead = new PlayerHead();
        return playerHead.applyUrl(item, url);
    }

    @Override
    public ItemStack setItemTag(ItemStack item, String key, String value) {
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;

        PersistentDataContainer container = meta.getPersistentDataContainer();
        NamespacedKey nk = new NamespacedKey(plugin, key);
        container.set(nk, PersistentDataType.STRING, value);

        item.setItemMeta(meta);
        return item;
    }

    @Override
    public String getItemTag(ItemStack item, String key) {
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;

        getPlugin().getLogger().info(key);
        getPlugin().getLogger().info(item.toString());

        PersistentDataContainer container = meta.getPersistentDataContainer();
        NamespacedKey nk = new NamespacedKey(plugin, key);

        return container.has(nk, PersistentDataType.STRING) ? container.get(nk, PersistentDataType.STRING) : null;
    }
}
