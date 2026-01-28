package com.oxipro.bedWars2023AdvancedGUI.command;

import com.oxipro.bedWars2023AdvancedGUI.gui.GuiManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BWAGUICommand implements CommandExecutor {

    private final GuiManager guiManager;

    public BWAGUICommand(GuiManager guiManager) {
        this.guiManager = guiManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (args.length == 0) {
            guiManager.openMainGui(player);
            return true;
        }
        guiManager.openModeGui(player, guiManager.getBwCategories().get(args[0]));
        return true;
    }
}