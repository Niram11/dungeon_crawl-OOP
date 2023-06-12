package com.codecool.dungeoncrawl.logic.actors;


import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.CellType;

import java.util.Objects;

public class Mage extends Actor {
    private int health = 5;
    public Mage(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "mage";
    }
    public void move(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);
        if (nextCell.getType() == CellType.UNWALKABLE) {
            return;
        } else if (!Objects.isNull(nextCell.getGameObject()) && nextCell.getGameObject().isInteractive()) {
            return;
        }
        getCell().setActor(null);
        nextCell.setActor(this);
        setCell(nextCell);
    }
}
