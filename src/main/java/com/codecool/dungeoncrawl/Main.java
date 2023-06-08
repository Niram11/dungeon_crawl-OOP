package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.actorutils.KeyArrowCoordinates;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.filemanagement.MapLoader;
import com.codecool.dungeoncrawl.logic.engine.Tiles;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.*;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Text healthText = new Text();
    Text defenseText = new Text();
    Text attackText = new Text();
    Text inventoryText = new Text();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10, 10, 10, 10));
        ui.setStyle("-fx-background-color: #482c3c;" +
                "-fx-font-size: 20px;" +
                "-fx-font-family: Comic Sans MS;");
        // TODO: MOVE TO UI PACKAGE
        ui.add(new Text("Health: "), 0, 1);
        ui.add(healthText, 1, 1);
        ui.add(new Text("Defense: "), 0, 2);
        ui.add(defenseText, 1, 2);
        ui.add(new Text("Attack: "), 0, 3);
        ui.add(attackText, 1, 3);
        ui.add(new Text("Inventory: "), 0, 4);
        ui.add(inventoryText, 0, 5);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setLeft(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
//        scene.setOnKeyPressed(keyEvent -> {
//            onKeyPressed(keyEvent, primaryStage);
//        });

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }
    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP, W -> {
                map.getPlayer().move(KeyArrowCoordinates.UP.dx, KeyArrowCoordinates.UP.dy);
                refresh();
            }
            case DOWN, S -> {
                map.getPlayer().move(KeyArrowCoordinates.DOWN.dx, KeyArrowCoordinates.DOWN.dy);
                refresh();
            }
            case LEFT, A -> {
                map.getPlayer().move(KeyArrowCoordinates.LEFT.dx, KeyArrowCoordinates.LEFT.dy);
                refresh();
            }
            case RIGHT, D -> {
                map.getPlayer().move(KeyArrowCoordinates.RIGHT.dx, KeyArrowCoordinates.RIGHT.dy);
                refresh();
            }
            case ESCAPE -> System.exit(0);
//            case I -> {
//                getModalInventory(primaryStage);
//            }
        }
//    private void onKeyPressed(KeyEvent keyEvent) {
//        Movement movement = new Movement();
//        switch (keyEvent.getCode()) {
//            case UP:
//                int[] vector  = new int[] {KeyArrowCoordinates.RIGHT.dx, KeyArrowCoordinates.RIGHT.dy};
//                if (!movement.isMovePossible(map, vector)) {
//                    break;
//                }
//                map.getPlayer().move(0, -1);
//                refresh();
//                break;
//            case DOWN:
//                vector  = new int[] {0, 1};
//                if (!movement.isMovePossible(map, vector)) {
//                    break;
//                }
//                map.getPlayer().move(0, 1);
//                refresh();
//                break;
//            case LEFT:
//                vector  = new int[] {-1, 0};
//                if (!movement.isMovePossible(map, vector)) {
//                    break;
//                }
//                map.getPlayer().move(-1, 0);
//                refresh();
//                break;
//            case RIGHT:
//                vector  = new int[] {1, 0};
//                if (!movement.isMovePossible(map, vector)) {
//                    break;
//                }
//                map.getPlayer().move(1,0);
//                refresh();
//            }

    }

    private void getModalInventory(Stage primaryStage) {
        Stage modalStage = new Stage();
        modalStage.initOwner(primaryStage);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initStyle(StageStyle.UTILITY);
        modalStage.setTitle("Modal window");

        VBox modalRoot = new VBox();
        Scene modalScene = new Scene(modalRoot, 200, 150);

        modalStage.setScene(modalScene);
        modalStage.showAndWait();

    }


    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else if (cell.getGameObject() != null) {
                    Tiles.drawTile(context, cell.getGameObject(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthText.setText(String.valueOf(map.getPlayer().getHealth()));
        attackText.setText(String.valueOf(map.getPlayer().getAttack()));
        defenseText.setText(String.valueOf(map.getPlayer().getDefense()));
        StringBuilder inventoryTextBuilder = new StringBuilder();
        map.getPlayer().getEquipment().getInventory().forEach(item -> inventoryTextBuilder.append(item.getClass().getSimpleName()).append("\n"));
        inventoryText.setText(String.valueOf(inventoryTextBuilder));
    }
}
