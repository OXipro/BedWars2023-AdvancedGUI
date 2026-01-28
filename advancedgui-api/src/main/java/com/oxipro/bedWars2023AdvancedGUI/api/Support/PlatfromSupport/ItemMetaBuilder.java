package com.oxipro.bedWars2023AdvancedGUI.api.Support.PlatfromSupport;

import com.oxipro.bedWars2023AdvancedGUI.api.gui.category.BwCategory;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.Language;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class ItemMetaBuilder {

    public abstract ItemMetaBuilder setArena(CachedArena arena);

    public abstract ItemMetaBuilder setPlayer (Player player);

    public abstract ItemMetaBuilder setCategory(BwCategory bwCategory);

    public abstract ItemMetaBuilder setLanguage(Language language);

    public abstract ItemMetaBuilder setDisplayName(String input);

    public abstract ItemMetaBuilder setLore(List<String> input);

    public abstract ItemStack build();


}
