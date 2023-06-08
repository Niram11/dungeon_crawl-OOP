package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.Drawable;

public abstract class Actor implements Drawable{
    private Cell cell;
    private int health = 10;
    private int defense = 1;

    private int attack = 2;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
    }

    public boolean isDead() {
        return health == 0;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public int getAttack() {
        return attack;
    }
}
