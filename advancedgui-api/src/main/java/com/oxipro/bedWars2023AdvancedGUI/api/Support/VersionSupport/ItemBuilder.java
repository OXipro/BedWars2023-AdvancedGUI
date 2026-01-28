package com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport;

import com.oxipro.bedWars2023AdvancedGUI.api.ItemType;
import com.oxipro.bedWars2023AdvancedGUI.api.gui.category.BwCategory;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;

import java.util.List;

public interface ItemBuilder {

    ItemBuilder player(Player player);

    ItemBuilder name(String name);

    ItemBuilder lore(List<String> lore);

    ItemBuilder glow(boolean glow);

    ItemBuilder amount(int amount);

    ItemBuilder setCategory(BwCategory value);

    ItemBuilder setArena(CachedArena value);

    ItemBuilder setType(ItemType type);

    ItemBuilder setLanguage(Language language);


    ItemStack build();
}
