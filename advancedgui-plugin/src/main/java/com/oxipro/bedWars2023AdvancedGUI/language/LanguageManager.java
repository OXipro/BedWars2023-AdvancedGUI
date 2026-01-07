package com.oxipro.bedWars2023AdvancedGUI.language;

import com.oxipro.bedWars2023AdvancedGUI.language.LanguageDefault.*;
import com.oxipro.bedWars2023AdvancedGUI.service.BwProxyService;
import com.tomkeuper.bedwars.proxy.api.BedWars;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public class LanguageManager {

    private static enDefault enDefault;
    private BwProxyService bwproxys;

    public LanguageManager(BwProxyService bwproxys) {
        this.bwproxys = bwproxys;
    }

    public void load() {
        enDefault = new enDefault();
    }

    public void save() {
        enDefault.save();
    }

    public Component getMMMsg(Player player, String path) {
        return MiniMessage.miniMessage().deserialize(bwproxys.getMsg(player, path));
    }

    public String getRawMsg(Player player, String path) {
        return bwproxys.getMsg(player, path);
    }

    // why ? bc its cool to make a manager
}
