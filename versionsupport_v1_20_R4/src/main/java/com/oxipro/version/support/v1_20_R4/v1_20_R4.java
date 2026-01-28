package com.oxipro.version.support.v1_20_R4;

import com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport.PlatformSupport;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.ItemBuilder;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.VersionSupport;
import com.oxipro.bedWars2023AdvancedGUI.api.language.LanguageManager;
import com.saicone.rtag.RtagItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class v1_20_R4 extends VersionSupport {
    public v1_20_R4(Plugin plugin, String name, PlatformSupport platformSupport) {
        super(plugin, name, platformSupport);
    }

    public ItemStack playerHead(ItemStack item, String base64) {
        PlayerHead playerHead = new PlayerHead();
        return playerHead.applyTexture(item, base64);
    }

    @Override
    public ItemBuilder itemBuilder(String material, LanguageManager languageManager) {
        return new com.oxipro.version.support.v1_20_R4.ItemBuilder(material, this, languageManager);
    }

    @Override
    public ItemStack setItemTag(ItemStack item, String key, String value) {
        return RtagItem.edit(item, tag -> {
            tag.set(value, key);
        });
    }

    @Override
    public String getItemTag(ItemStack item, String key) {
        RtagItem tag = new RtagItem(item);
        return tag.hasTag(key) ? tag.get(key) : null;
    }
}
