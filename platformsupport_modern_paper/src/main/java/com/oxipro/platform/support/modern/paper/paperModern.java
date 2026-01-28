package com.oxipro.platform.support.modern.paper;

import com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport.ItemMetaBuilder;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport.PlatformSupport;
import com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport.ItemBuilder;
import com.oxipro.bedWars2023AdvancedGUI.api.language.LanguageManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class paperModern extends PlatformSupport {




    public paperModern(Plugin plugin, String name) {
        super(plugin, name);
    }

    @Override
    public ItemMetaBuilder getItemMetaBuilder(ItemStack item, LanguageManager languageManager) {
        return new com.oxipro.platform.support.modern.paper.ItemMetaBuilder(item, languageManager);
    }

}