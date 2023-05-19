package com.universe.exploration.component.boardgrid;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.controller.GameController;
import com.universe.exploration.model.Coordinate;
import com.universe.exploration.model.gamecharacter.GameCharacter;
import com.universe.exploration.utils.gameassetmanager.GameAssetManager;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.HudAssetProvider;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardGrid extends Table {
    private enum PrecedingGridNodePosition {
        HORIZONTALLY_PRECEDING_GRIDNODE,
        VERTICALLY_PRECEDING_GRIDNODE
    }

    List<Coordinate> paintedCoordinates;
    private Map<Integer, Map<Integer, GridNode>> grid;
    private SelectedCell selectedCell;
    private GameController gameController;
    private BoardGraph boardGraph;

    public BoardGrid(UniverseExploration universeExploration, GameController gameController) {
        this.gameController = gameController;
        this.paintedCoordinates = new ArrayList<>();

        grid = new HashMap<>();
        selectedCell = new SelectedCell();

        for (int cy = 0; cy < 6; cy++) {
            Map<Integer, GridNode> imageButtons = new HashMap<>();
            for (int cx = 0; cx < 10; cx++) {
                ImageButton button = createGridButton(universeExploration.getAssetManager());
                GridNode node = new GridNode(button, cx, cy);

                imageButtons.put(cx, node);
                button.addListener(getGridNodeClickListener(button, cx, cy));
                button.getColor().a = 0.3f;

                add(button.pad(10));
            }
            row();
            grid.put(cy, imageButtons);
        }
        createPathFindingConnections();

        // TODO: this is just an AI example
        GraphPath<GridNode> path = boardGraph.findPath( grid.get(2).get(0), grid.get(5).get(3));
        path.forEach(gridNode -> gridNode.getImageButton().setColor(Color.FIREBRICK));

        pack();
    }

    private void createPathFindingConnections() {
        this.boardGraph = new BoardGraph();
        List<GameCharacter> allCharacters = new ArrayList<>();
        allCharacters.addAll(gameController.getPlayerCharacters());
        allCharacters.addAll(gameController.getNpcs());


        IntStream.range(0, 6).forEach(coordinateY -> IntStream.range(0, 10).forEach(coordinateX -> {
            boolean gridAvailable = allCharacters.
                    stream()
                    .noneMatch(npc -> npc.getCoordinateX() == coordinateX && npc.getCoordinateY() == coordinateY);

            if (gridAvailable) {
                GridNode gridNode = grid.get(coordinateY).get(coordinateX);
                Map<PrecedingGridNodePosition, GridNode> previousNodes = boardGraph.getGridNodes().stream()
                        .filter(prevNode -> prevNode.getY() == coordinateY && prevNode.getX() == coordinateX - 1 ||
                                prevNode.getX() == coordinateX && prevNode.getY() == coordinateY - 1)
                        .collect(Collectors.toMap(node -> generateKey(node, coordinateX, coordinateY), Function.identity()));
                boardGraph.addGridNode(gridNode);

                createConnectionWhenPossible(previousNodes, PrecedingGridNodePosition.HORIZONTALLY_PRECEDING_GRIDNODE, gridNode);
                createConnectionWhenPossible(previousNodes, PrecedingGridNodePosition.VERTICALLY_PRECEDING_GRIDNODE, gridNode);
            }
        }));
    }

    public void redrawPaintedArea() {
        if (selectedCell.isSelected()) {
            // First reset active cells.
            if (!paintedCoordinates.isEmpty()) {
                paintedCoordinates.forEach(coordinate -> getImageButtonAt(coordinate.getY(), coordinate.getX()).setChecked(false));
                paintedCoordinates = new ArrayList<>();
            }

            paintedCoordinates = gameController.getAreaToPaint(new Coordinate(selectedCell.coordinateX, selectedCell.coordinateY));
            // Painted coordinates means we selected a character and we now have selection area.
            if (!paintedCoordinates.isEmpty()) {
                paintedCoordinates.forEach(coordinate -> getImageButtonAt(coordinate.getY(), coordinate.getX()).setChecked(true));
            }
        }
    }

    public Vector2 getCellPosition(int x, int y) {
        return getImageButtonAt(x, y).localToParentCoordinates(new Vector2(this.getX(), this.getY()));
    }


    private void createConnectionWhenPossible(Map<PrecedingGridNodePosition, GridNode> previousNodes, PrecedingGridNodePosition precedingGridNodePosition, GridNode gridNode) {
        if(previousNodes.containsKey(precedingGridNodePosition)) {
            GridNode previousNode = previousNodes.get(precedingGridNodePosition);
            boardGraph.connectGridNodes(previousNode, gridNode);
        }
    }

    private PrecedingGridNodePosition generateKey(GridNode gridNode, int coordinateX, int coordinateY) {
        return gridNode.getY() == coordinateY && gridNode.getX() == coordinateX - 1 ?
                PrecedingGridNodePosition.HORIZONTALLY_PRECEDING_GRIDNODE :
                PrecedingGridNodePosition.VERTICALLY_PRECEDING_GRIDNODE;
    }

    private ClickListener getGridNodeClickListener(Button button, int finalCx, int finalCy) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                final Coordinate coordinateClicked = new Coordinate(finalCx, finalCy);
                // Character is already selected, so perform action or simply deselect.
                if (selectedCell.isSelected()) {
                    selectedCell.deselect();
                    Optional<Coordinate> matches = paintedCoordinates.stream().filter(painted -> painted.equals(coordinateClicked)).findFirst();
                    if (matches.isPresent()) {
                        gameController.applyActionWhenPossible(coordinateClicked);
                    }
                    if (!paintedCoordinates.isEmpty()) {
                        paintedCoordinates.forEach(coordinate -> getImageButtonAt(coordinate.getX(), coordinate.getY()).setChecked(false));
                        paintedCoordinates = new ArrayList<>();
                    }
                    button.setChecked(false);
                } else {
                    paintedCoordinates = gameController.getAreaToPaint(coordinateClicked);
                    // Painted coordinates means we selected a character and we now have selection area.
                    if (!paintedCoordinates.isEmpty()) {
                        selectedCell.setSelected(finalCx, finalCy);
                        paintedCoordinates.forEach(coordinate -> getImageButtonAt(coordinate.getX(), coordinate.getY()).setChecked(true));
                    } else {
                        button.setChecked(false);
                    }
                }
            }
        };
    }

    private ImageButton getImageButtonAt(int x, int y) {
        return grid.get(y).get(x).getImageButton();
    }

    private ImageButton createGridButton(GameAssetManager gameAssetManager) {
        Texture up = gameAssetManager.getAsset(HudAssetProvider.HudAsset.GRID_CELL);
        Texture pressed = gameAssetManager.getAsset(HudAssetProvider.HudAsset.GRID_CELL_PRESSED);
        Texture selected = gameAssetManager.getAsset(HudAssetProvider.HudAsset.GRID_CELL_SELECTED);
        Drawable upDrawable = new TextureRegionDrawable(new TextureRegion(up));
        Drawable pressedDrawable = new TextureRegionDrawable(new TextureRegion(pressed));
        Drawable selectedDrawable = new TextureRegionDrawable(new TextureRegion(selected));
        return new ImageButton(upDrawable, pressedDrawable, selectedDrawable);
    }

    /**
     * Reference to selected cell.
     */
    private class SelectedCell {
        Integer coordinateX;
        Integer coordinateY;
        private Integer NO_SELECTED_VALUE = -1;

        SelectedCell() {
            coordinateX = NO_SELECTED_VALUE;
            coordinateY = NO_SELECTED_VALUE;
        }

        void setSelected(Integer x, Integer y) {
            this.coordinateX = x;
            this.coordinateY = y;
        }

        void deselect() {
            getImageButtonAt(coordinateX, coordinateY).setChecked(false);
            coordinateX = NO_SELECTED_VALUE;
            coordinateY = NO_SELECTED_VALUE;
        }

        boolean isSelected() {
            return coordinateX >= 0 && coordinateY >= 0;
        }
    }
}
