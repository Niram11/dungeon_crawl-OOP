package com.codecool.dungeoncrawl.logic.actorutils;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Mage;
import com.codecool.dungeoncrawl.logic.actors.Ogre;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.filemanagement.MapLoader;

public class Movement {
    public boolean isMovePossible(GameMap map, int[] vector, int positionX, int positionY) {
        Cell Cell = map.getCell(positionX + vector[0], positionY + vector[1]);
        return !Cell.getType().getTileName().equals("unwalkable");
    }

    public boolean isPlayerThere(GameMap map, int[] vector, int positionX, int positionY) {
        int playerX = map.getPlayer().getX();
        int playerY = map.getPlayer().getY();

        return positionX + vector[0] == playerX && positionY + vector[1] == playerY;
    }

    public void setPatrolPlaces(Ogre ogre) {
        //TODO: move to ogre class?
        int positionY = ogre.getY();
        int positionX = ogre.getX();
        int[] firstPlace = new int[]{positionY, positionX - 3};
        int[] patrolDestination = new int[]{positionY, positionX + 3};
        ogre.setFirstPlace(firstPlace);
        ogre.setPatrolDestination(patrolDestination);
    }

    public void goToPatrolPlace(GameMap map, Ogre ogre) {
        int positionX = ogre.getX();
        int positionY = ogre.getY();

        int[] patrolDestination = ogre.getPatrolDestination();
        if (positionX - patrolDestination[1] == 0) {
            switchPatrol(ogre);
        } else {
            int[] vector = new int[]{positionX - patrolDestination[1]};
            if (vector[0] > 0) {
                int[] moveVector = new int[]{-1, 0};
                if (isPlayerThere(map, moveVector, positionX, positionY)) {
                    attackPlayer(map, ogre);
                } else if (isMovePossible(map, moveVector, positionX, positionY) || vector[0] == 0) {
                    ogre.move(-1, 0);
                } else {
                    switchPatrol(ogre);
                }
            } else {
                int[] moveVector = new int[]{1, 0};
                if (isPlayerThere(map, moveVector, positionX, positionY)) {
                    attackPlayer(map, ogre);
                } else if (isMovePossible(map, moveVector, positionX, positionY) || vector[0] == 0) {
                    ogre.move(1, 0);
                } else {
                    switchPatrol(ogre);
                }
            }

        }
    }

    public void guard(GameMap map, Mage mage) {
        int playerPositionX = map.getPlayer().getX();
        int playerPositionY = map.getPlayer().getY();
        int[] vector = new int[]{playerPositionX - mage.getX(), playerPositionY - mage.getY()};

        int dx = Integer.compare(vector[0], 0);
        int dy = Integer.compare(vector[1], 0);
        int[] moveVector = new int[]{dx, dy};
        if (isPlayerThere(map, moveVector, mage.getX(), mage.getY())) {
            attackPlayer(map, mage);
        } else if (isPlayerNear(mage, playerPositionX, playerPositionY)) {
            mage.move(dx, dy);
        }
    }

    public boolean isPlayerNear(Mage mage, int playerPositionX, int playerPositionY) {
        int[] distance = new int[]{Math.abs(mage.getX() - playerPositionX), Math.abs(mage.getY() - playerPositionY)};
        return 5 >= distance[0] && 5 >= distance[1];
    }

    private void attackPlayer(GameMap map, Actor actor) {
        int playerHp = map.getPlayer().getHealth();
        map.getPlayer().setHealth(playerHp - actor.getAttack());
//        int actorHp = actor.getHealth();
//        actor.setHealth(actorHp - map.getPlayer().getAttack());
        System.out.println(actor.getHealth());
        if (actor.isDead()) {
            if (actor instanceof Mage) {
                MapLoader.mages.remove(actor);
            } else if (actor instanceof Ogre) {
                MapLoader.ogres.remove(actor);
            }
//            Cell cell = map.getCell(actor.getX(), actor.getY());
//            cell.setActor(null);

        }
    }

    private void switchPatrol(Ogre ogre) {
        int[] patrolDestination = ogre.getPatrolDestination();
        int[] firstPlace = ogre.getFirstPlace();
        ogre.setPatrolDestination(firstPlace);
        ogre.setFirstPlace(patrolDestination);
    }
}