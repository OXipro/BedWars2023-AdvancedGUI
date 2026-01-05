package com.oxipro.bedWars2023AdvancedGUI.command;

import com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory.BwCategoryMenu;
import com.oxipro.bedWars2023AdvancedGUI.gui.CategoryGui;
import com.oxipro.bedWars2023AdvancedGUI.gui.GuiManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Player;

import java.util.Objects;

public class BWAGUICommand implements CommandExecutor {

    private final GuiManager guiManager;

    public BWAGUICommand(GuiManager guiManager) {
        this.guiManager = guiManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) return true;

        if (args.length == 0) {
            guiManager.openMainGui(player);
            return true;
        }

// debug stuff
//        if (Objects.equals(args[0], "refresh")) { guiManager.arenas().ArenaItemStackMapRefresh(); return true; }

        guiManager.openModeGui(player, guiManager.loadBwCategoryMenu().getCategory(args[0]));
        return true;
    }
}