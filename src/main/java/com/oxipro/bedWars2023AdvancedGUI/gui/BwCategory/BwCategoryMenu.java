package com.oxipro.bedWars2023AdvancedGUI.gui.BwCategory;


import java.util.Map;

public class BwCategoryMenu {
    private int menuRows;
    private String title;
    private Map<String, BwCategory> categories;

    public BwCategoryMenu(int menuRows, String title, Map<String, BwCategory> categories) {
        this.menuRows = menuRows;
        this.title = title;
        this.categories = categories;
    }

    public int getMenuRows() {
        return menuRows;
    }

    public String getTitle() {
        return title;
    }

    public Map<String, BwCategory> getCategories() {
        return categories;
    }

    public BwCategory getCategory(String key) {
        return categories.get(key);
    }
}
