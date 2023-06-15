package com.codecool.dungeoncrawl.logic.actors;


import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.CellType;

import java.util.Objects;

public class Ogre extends Actor {
    private int[] firstPlace;
    private int[] patrolDestination;
    public Ogre(Cell cell) {
        super(cell);
        this.setHealth(15);
    }

    @Override
    public String getTileName() {
        return "ogre";
    }

    public void setFirstPlace(int[] firstPlace) {
        this.firstPlace = firstPlace;
    }
    public void setPatrolDestination(int[] patrolDestination) {
        this.patrolDestination = patrolDestination;
    }

    public int[] getPatrolDestination() {
        return patrolDestination;
    }

    public int[] getFirstPlace() {
        return firstPlace;
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
