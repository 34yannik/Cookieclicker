package de.yannik.cookieclicker.enums;

import org.bukkit.Material;

public enum Items {
    MENU_ITEM("§6Cookie-Fabrik", "fabrik_menu_item", "§7Deine Fabrik! ", false, Material.NETHER_STAR);

    private final String name;
    private final String keyName;
    private final String description;
    private final boolean tradable;
    private final Material material;

    Items(String name, String keyName, String description, boolean tradable, Material material) {
        this.name = name;
        this.keyName = keyName;
        this.description = description;
        this.tradable = tradable;
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public String getKeyName() {
        return keyName;
    }

    public String getDescription() {
        return description;
    }

    public boolean isTradable() {
        return tradable;
    }

    public Material getMaterial() {
        return material;
    }
}
