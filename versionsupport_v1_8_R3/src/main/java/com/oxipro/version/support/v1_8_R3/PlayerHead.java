package com.oxipro.version.support.v1_8_R3;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import java.lang.reflect.Field;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.UUID;

public class PlayerHead {

    public static ItemStack applyTexture(ItemStack item, String base64Texture) {
        if (item == null || base64Texture == null || base64Texture.isEmpty()) return item;

        if (!(item.getItemMeta() instanceof SkullMeta)) return item;

        SkullMeta meta = (SkullMeta) item.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", base64Texture));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        item.setItemMeta(meta);
        return item;
    }
}
