package com.codecool.dungeoncrawl.logic.actorutils;

public enum KeyArrowCoordinates {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    public final int dx;
    public final int dy;
    KeyArrowCoordinates(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
    }
}
