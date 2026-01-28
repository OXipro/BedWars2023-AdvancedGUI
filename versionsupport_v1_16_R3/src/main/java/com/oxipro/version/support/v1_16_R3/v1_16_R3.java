package com.oxipro.version.support.v1_16_R3;

import com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport.PlatformSupport;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.ItemBuilder;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.VersionSupport;
import com.oxipro.bedWars2023AdvancedGUI.api.language.LanguageManager;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class v1_16_R3 extends VersionSupport {

    public v1_16_R3(Plugin plugin, String name, PlatformSupport platformSupport) {
        super(plugin, name, platformSupport);
    }

    @Override
    public ItemBuilder itemBuilder(String material, LanguageManager languageManager) {
        return new com.oxipro.version.support.v1_16_R3.ItemBuilder(material, this, languageManager);
    }

    public ItemStack playerHead(ItemStack item, String base64) {
        PlayerHead playerHead = new PlayerHead();
        return playerHead.applyTexture(item, base64);
    }

    @Override
    public ItemStack setItemTag(ItemStack item, String key, String value) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = nmsItem.getTag() != null ? nmsItem.getTag() : new NBTTagCompound();
        tag.setString(key, value);
        nmsItem.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    @Override
    public String getItemTag(ItemStack item, String key) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = nmsItem.getTag();
        if (tag != null && tag.hasKey(key)) return tag.getString(key);
        return null;
    }
}