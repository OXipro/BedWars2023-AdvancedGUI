package com.oxipro.version.support.v1_21_R5;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.URL;
import java.util.UUID;

public class PlayerHead {

    public static ItemStack applyUrl(ItemStack item, String url) {
        if (item == null || url == null || url.isEmpty()) return item;
        if (!(item.getItemMeta() instanceof SkullMeta)) return item;

        SkullMeta meta = (SkullMeta) item.getItemMeta();

        try {
            PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
            PlayerTextures textures = profile.getTextures();
            textures.setSkin(new URL(url));
            profile.setTextures(textures);
            meta.setOwnerProfile(profile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        item.setItemMeta(meta);
        return item;
    }
}

