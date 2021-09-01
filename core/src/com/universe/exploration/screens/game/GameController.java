package com.universe.exploration.screens.game;

import com.universe.exploration.UniverseExploration;
import com.universe.exploration.controller.ControllerBase;
import com.universe.exploration.model.crew.GameCharacter;

import java.util.HashMap;
import java.util.Map;

public class GameController extends ControllerBase {
    private Map<Integer, Map<Integer, GameGridCell>> gameGrid;
    private static final int BOARD_X = 6;
    private static final int BOARD_Y = 10;

    public GameController(UniverseExploration universeExploration) {
        super(universeExploration);

        for (int x = 0; x < BOARD_X; x++) {
            Map<Integer, GameGridCell> row = new HashMap<>();
            for (int y = 0; y < BOARD_Y; y++) {
                row.put(y, new GameGridCell());
            }
            gameGrid.put(x, row);
        }
    }

    private class GameGridCell {
        private boolean selected;
        private int coordinateX;
        private int coordinateY;
        private GameCharacter gameCharacter;
    }

}
