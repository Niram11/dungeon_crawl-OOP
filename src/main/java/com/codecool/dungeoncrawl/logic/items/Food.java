package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;

public class Food extends Item {
    public Food(Cell cell) {
        super(cell);
    }


    public void onUse(Player player) {
        player.setHealth(player.getHealth() + 2);
    }
    @Override
    public String getTileName() {
        return "food";
    }
}
