package com.codecool.dungeoncrawl.logic.actorutils;

import com.codecool.dungeoncrawl.logic.actors.Ogre;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;

public class Movement {
    public boolean isMovePossible(GameMap map, int[] vector) {
        int playerX = map.getPlayer().getX();
        int playerY = map.getPlayer().getY();
        Cell nextCell = map.getCell(playerX + vector[0], playerY + vector[1]);
        return !nextCell.getType().getTileName().equals("enemy") && !nextCell.getType().getTileName().equals("wall");
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

    public void goToPatrolPlace(Ogre ogre) {
        int positionX = ogre.getX();
        int[] patrolDestination = ogre.getPatrolDestination();
        int[] firstPlace = ogre.getFirstPlace();
        if (patrolDestination[1] - positionX == 0) {
            ogre.setPatrolDestination(firstPlace);
            ogre.setFirstPlace(patrolDestination);
            //TODO: (Dominik) move ogre in new direction
        } else {
//            int vector =  {positionX - patrolDestination[1]};
            //TODO: (Dominik) move ogre into patrol place direction
        }
    }
}