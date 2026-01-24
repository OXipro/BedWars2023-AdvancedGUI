package com.oxipro.bedWars2023AdvancedGUI.language;

import com.oxipro.bedWars2023AdvancedGUI.language.LanguageDefault.*;
import com.oxipro.bedWars2023AdvancedGUI.service.BwProxyService;
import org.bukkit.entity.Player;

import java.util.List;

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

    public String getRawMsg(Player player, String path) {
        return bwproxys.getMsg(player, path);
    }

    public List<String> getRawMsgList(Player player, String path) {
        return bwproxys.getBwproxy().getLanguageUtil().getList(player, path);
    }


    // why ? bc its cool to make a manager
}
