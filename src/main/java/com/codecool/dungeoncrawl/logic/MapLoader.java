package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Food;
import com.codecool.dungeoncrawl.logic.ui.Cell;
import com.codecool.dungeoncrawl.logic.ui.CellType;
import com.codecool.dungeoncrawl.logic.ui.GameMap;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
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
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
