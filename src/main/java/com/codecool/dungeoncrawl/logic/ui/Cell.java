package com.codecool.dungeoncrawl.logic.ui;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private Item item;
    private GameObject gameObject;
    private GameMap gameMap;
    private int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public Item getItem() {
        return item;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
