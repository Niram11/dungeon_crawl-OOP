package com.codecool.dungeoncrawl.logic.actors;


import com.codecool.dungeoncrawl.logic.engine.Cell;

public class Mage extends Actor {
    private int health = 5;
    public Mage(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "mage";
    }
}
