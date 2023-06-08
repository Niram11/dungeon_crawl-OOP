package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.engine.Cell;

public class Sword extends Item{
    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}
