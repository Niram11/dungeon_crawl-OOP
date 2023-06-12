package com.codecool.dungeoncrawl.logic.actorutils;

import com.codecool.dungeoncrawl.logic.actors.Mage;
import com.codecool.dungeoncrawl.logic.actors.Ogre;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;

public class Movement {
    public boolean isMovePossible(GameMap map, int[] vector, int positionX, int positionY) {
        Cell Cell = map.getCell(positionX + vector[0], positionY + vector[1]);
        return !Cell.getType().getTileName().equals("unwalkable");
    }

    public boolean isEnemyThere(GameMap map, int[] vector) {
        int playerX = map.getPlayer().getX();
        int playerY = map.getPlayer().getY();
        Cell nextCell = map.getCell(playerX + vector[0], playerY + vector[1]);
        return !nextCell.getType().getTileName().equals("enemy");
    }

    public void setPatrolPlaces(Ogre ogre) {
        int positionY = ogre.getY();
        int positionX = ogre.getX();
        int[] firstPlace = new int[]{positionY, positionX - 3};
        int[] patrolDestination = new int[]{positionY, positionX + 3};
        ogre.setFirstPlace(firstPlace);
        ogre.setPatrolDestination(patrolDestination);
    }

    public void goToPatrolPlace(Ogre ogre, GameMap map) {
        int positionX = ogre.getX();
        int positionY = ogre.getY();

        int[] patrolDestination = ogre.getPatrolDestination();
        int[] firstPlace = ogre.getFirstPlace();
        if (positionX - patrolDestination[1] == 0) {
            switchPatrol(ogre);
        } else {
            int[] vector = new int[]{positionX - patrolDestination[1]};
            if (vector[0] > 0) {
                int[] moveVector = new int[]{-1, 0};
                if (isMovePossible(map, moveVector, positionX, positionY) || vector[0] == 0) {
                    ogre.move(-1, 0);
                } else {
                    System.out.println("im gere");
                    switchPatrol(ogre);
                }
            } else {
                int[] moveVector = new int[]{1, 0};
                if (isMovePossible(map, moveVector, positionX, positionY) || vector[0] == 0) {
                    ogre.move(1, 0);
                } else {
                    System.out.println("im here");
                    switchPatrol(ogre);
                }
            }

        }
    }

    public boolean isPlayerNear(GameMap map, Mage mage) {
        int playerPositionX = map.getPlayer().getX();
        int playerPositionY = map.getPlayer().getY();
        int[] vector = new int[]{playerPositionX - mage.getX(), playerPositionY - mage.getY()};
        return vector[0] + vector[1] >= 5;
    }

    private void switchPatrol(Ogre ogre) {
        int[] patrolDestination = ogre.getPatrolDestination();
        int[] firstPlace = ogre.getFirstPlace();
        ogre.setPatrolDestination(firstPlace);
        ogre.setFirstPlace(patrolDestination);
    }
}