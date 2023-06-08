package com.codecool.dungeoncrawl.logic.actors;


import com.codecool.dungeoncrawl.logic.engine.Cell;

public class Ogre extends Actor {
    private int health = 15;
    private int[] firstPlace;
    private int[] patrolDestination;
    public Ogre(Cell cell) {
        super(cell);
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
}
