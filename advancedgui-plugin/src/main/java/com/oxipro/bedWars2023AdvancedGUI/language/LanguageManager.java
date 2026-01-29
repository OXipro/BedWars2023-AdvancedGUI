package com.oxipro.bedWars2023AdvancedGUI.language;

import com.oxipro.bedWars2023AdvancedGUI.language.LanguageDefault.*;
import com.oxipro.bedWars2023AdvancedGUI.service.BwProxyService;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class LanguageManager extends com.oxipro.bedWars2023AdvancedGUI.api.language.LanguageManager {

    private static enDefault enDefault;
    private BwProxyService bwproxys;
    private Plugin plugin;


    public LanguageManager(Plugin plugin, BwProxyService bwproxys) {
        this.bwproxys = bwproxys;
        this.plugin = plugin;
    }

    public void load() {
        enDefault = new enDefault();
    }

    public void save() {
        enDefault.save();
    }

    public String getRawMsg(Player player, String path) {
        String msg = bwproxys.getMsg(player, path);
        return msg;
    }

    public List<String> getRawMsgList(Player player, String path) {
        List<String> list = bwproxys.getBwproxy().getLanguageUtil().getList(player, path);
        return list;
    }

    public boolean existAndNotNull(Player player, String path) {
        var util = bwproxys.getBwproxy().getLanguageUtil();

        try {
            String s = util.getMsg(player, path);
            if (s != null && !s.trim().isEmpty()) {
                return true;
            }

            List<String> l = util.getList(player, path);
            return l != null && l.stream().anyMatch(line -> line != null && !line.trim().isEmpty());
        } catch (Exception ignored) {
            return false;
        }
    }


    // why ? bc its cool to make a manager
}
