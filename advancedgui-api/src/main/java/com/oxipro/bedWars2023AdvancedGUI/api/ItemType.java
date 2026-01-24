package com.oxipro.bedWars2023AdvancedGUI.api;

public enum ItemType {
    HOTBAR_MANAGER_OPENER("hotbar_manager"),
    LOBBY_EDITOR_OPENER("lobby_editor"),
    CATEGORIES_SELECTOR_OPENER("categories_selector"),
    MAP_SELECTOR_OPENER("map_selector"),
    REJOIN("rejoin"),
    QUICK_ARENA("quick_arena"),
    QUICK_JOIN("quick_join");

    private final String id;

    ItemType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static ItemType fromId(String id) {
        for (ItemType t : values()) {
            if (t.id.equalsIgnoreCase(id)) {
                return t;
            }
        }
        return null; // ou une valeur par défaut
    }
}