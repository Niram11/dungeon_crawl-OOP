package com.codecool.dungeoncrawl.logic.gameobject;

import com.codecool.dungeoncrawl.logic.engine.Cell;

public class Gate extends GameObject {

    private String tileName = "closed_gate";

    public Gate(Cell cell) {
        super(cell);
        setInteractive(true);
    }

    public void setTileName(String tileName) {
        this.tileName = tileName;
    }

    @Override
    public void onInteraction() {
        setTileName("open_gate");
    }

    @Override
    public String getTileName() {
        return tileName;
    }
}
