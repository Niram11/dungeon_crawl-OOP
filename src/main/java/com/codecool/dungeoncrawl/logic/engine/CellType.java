package com.codecool.dungeoncrawl.logic.engine;

public enum CellType {
    VOID("void"),
    WALKABLE("walkable"),
    UNWALKABLE("unwalkable"),
    ENEMY("enemy");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
