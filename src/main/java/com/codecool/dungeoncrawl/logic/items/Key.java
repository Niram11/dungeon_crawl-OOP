package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;

public class Key extends Item{
    public Key(Cell cell) {
        super(cell);
    }
    @Override
    public void onUse(Player player) {

    }

    @Override
    public String getTileName() {
        return "key";
    }
}
