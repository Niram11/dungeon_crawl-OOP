package com.codecool.dungeoncrawl.logic.filemanagement;

import com.codecool.dungeoncrawl.logic.actors.Mage;
import com.codecool.dungeoncrawl.logic.actors.Ogre;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.actorutils.Movement;
import com.codecool.dungeoncrawl.logic.gameobject.Gate;
import com.codecool.dungeoncrawl.logic.items.Food;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.CellType;
import com.codecool.dungeoncrawl.logic.engine.GameMap;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapLoader {
    private List<Ogre> ogres = new ArrayList<>();

    public GameMap loadMap() {
        Movement movement = new Movement();
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.VOID);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.VOID);
                            break;
                        case '#':
                            cell.setType(CellType.UNWALKABLE);
                            break;
                        case '.':
                            cell.setType(CellType.WALKABLE);
                            break;
                        case 's':
                            cell.setType(CellType.WALKABLE);
//                            cell.setType(CellType.ENEMY);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.WALKABLE);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'a':
                            cell.setType(CellType.WALKABLE);
                            new Food(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.WALKABLE);
                            new Key(cell);
                            break;
                        case 'm':
                            cell.setType(CellType.WALKABLE);
                            new Sword(cell);
                            break;
                        case 'g':
                            cell.setType(CellType.WALKABLE);
                            new Gate(cell);
                            break;
                        case 'O':
//                            cell.setType(CellType.ENEMY);
                            Ogre ogre = new Ogre(cell);
                            movement.setPatrolPlaces(ogre);
                            ogres.add(ogre);
                            break;
                        case 'M':
//                            cell.setType(CellType.ENEMY);
                            new Mage(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

    public List<Ogre> getOgres() {
        return ogres;
    }
}
