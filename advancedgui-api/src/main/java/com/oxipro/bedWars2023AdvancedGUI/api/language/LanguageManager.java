package com.oxipro.bedWars2023AdvancedGUI.api.language;

import org.bukkit.entity.Player;

import java.util.List;

public abstract class LanguageManager {

    public abstract String getRawMsg(Player player, String path);

    public abstract List<String> getRawMsgList(Player player, String path);
}
