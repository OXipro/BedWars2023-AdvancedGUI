package com.oxipro.bedWars2023AdvancedGUI.api.Support.VersionSupport;

import com.oxipro.bedWars2023AdvancedGUI.api.ItemType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;

import java.util.List;

public interface ItemBuilder {

    ItemBuilder player(Player player);

    ItemBuilder name(String name);

    ItemBuilder lore(List<String> lore);

    ItemBuilder glow(boolean glow);

    ItemBuilder amount(int amount);

    ItemBuilder setCategory(String value);

    ItemBuilder setArena(String value);

    ItemBuilder setType(ItemType type);

    ItemStack build();
}
