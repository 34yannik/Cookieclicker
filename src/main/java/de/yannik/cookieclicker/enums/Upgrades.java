package de.yannik.cookieclicker.enums;

public enum Upgrades {
    CURSOR("Cursor", "cursor", 15, 1L),
    GRANDMA("Grandma", "grandma", 100, 8L),
    FARM("Farm", "farm", 1100, 85L),
    FACTORY("Factory", "factory", 12000, 780L),
    BANK("Bank", "bank", 130000, 8400L),
    PORTAL("Portal", "portal", 1400000, 61000L),
    TIME_MACHINE("Time Machine", "time_machine", 20000000, 130000L),
    ANTI_MATTER_CONVERTER("Anti-Matter Converter", "anti_matter_converter", 300000000L, 3400000L),
    PRISM("Prism", "prism", 5000000000L, 11300000L),
    CHANCER("Chancer", "chancer", 75000000000L, 56000000L);

    private final String name;
    private final String keyName;
    private final long cost;
    private final long producing;

    Upgrades(String name, String keyName, long cost, long producing) {
        this.name = name;
        this.keyName = keyName;
        this.cost = cost;
        this.producing = producing;
    }

    public String getName() {
        return name;
    }

    public String getKeyName() {
        return keyName;
    }

    public long getCost() {
        return cost;
    }

    public long getProducing() {
        return producing;
    }
}
