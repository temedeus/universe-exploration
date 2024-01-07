package com.universe.exploration.component.boardgrid;

import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
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
import com.universe.exploration.controller.game.CombatController;
import com.universe.exploration.model.Coordinate;
import com.universe.exploration.model.gamecharacter.GameCharacter;
import com.universe.exploration.utils.gameassetmanager.GameAssetManager;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.HudAssetProvider;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardGrid extends Table {
    List<Coordinate> paintedCoordinates;
    private Map<Integer, Map<Integer, GridNode>> grid;
    private SelectedCell selectedCell;
    private CombatController combatController;
    private BoardGraph boardGraph;
    private UniverseExploration universeExploration;

    private GraphPath<GridNode> path = new DefaultGraphPath<>();
    private static final Color DEFAULT_COLOR = Color.valueOf("ffffff4c");

    public BoardGrid(UniverseExploration universeExploration, CombatController combatController) {
        this.combatController = combatController;
        this.paintedCoordinates = new ArrayList<>();
        this.universeExploration = universeExploration;

        grid = new HashMap<>();
        selectedCell = new SelectedCell();

        drawBoard();

        pack();
    }

    public void redrawPotentialActionArea() {
        if (selectedCell.isSelected()) {
            // First reset active cells.
            if (!paintedCoordinates.isEmpty()) {
                paintedCoordinates.forEach(coordinate -> getImageButtonAt(coordinate.getY(), coordinate.getX()).setChecked(false));
                paintedCoordinates = new ArrayList<>();
            }

            paintedCoordinates = combatController.getAreaToPaint(new Coordinate(selectedCell.coordinateX, selectedCell.coordinateY));
            // Painted coordinates means we selected a character and we now have selection area.
            if (!paintedCoordinates.isEmpty()) {
                paintedCoordinates.forEach(coordinate -> getImageButtonAt(coordinate.getY(), coordinate.getX()).setChecked(true));
            }
        }
    }

    public Vector2 getCellPosition(int x, int y) {
        return getImageButtonAt(x, y).localToParentCoordinates(new Vector2(this.getX(), this.getY()));
    }

    private void drawBoard() {
        for (int cy = BoardConfig.BOARD_SIZE_MIN_Y; cy < BoardConfig.BOARD_SIZE_MAX_Y; cy++) {
            Map<Integer, GridNode> imageButtons = new HashMap<>();
            for (int cx = BoardConfig.BOARD_SIZE_MIN_X; cx < BoardConfig.BOARD_SIZE_MAX_X; cx++) {
                ImageButton button = createGridButton(universeExploration.getAssetManager());
                GridNode node = new GridNode(button, cx, cy);

                imageButtons.put(cx, node);
                button.addListener(createGridNodeClickListener(button, cx, cy));
                button.getColor().a = 0.3f;
                add(button.pad(10));
            }
            row();
            grid.put(cy, imageButtons);
        }
    }

    private void createPathFindingConnections() {
        this.boardGraph = new BoardGraph();
        List<GameCharacter> allCharacters = new ArrayList<>();
        allCharacters.addAll(combatController.getPlayerCharacters());
        allCharacters.addAll(combatController.getNpcs());

        IntStream.range(BoardConfig.BOARD_SIZE_MIN_Y, BoardConfig.BOARD_SIZE_MAX_Y).forEach(coordinateY -> IntStream.range(BoardConfig.BOARD_SIZE_MIN_X, BoardConfig.BOARD_SIZE_MAX_X).
                forEach(coordinateX -> {
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

    private void createConnectionWhenPossible(Map<PrecedingGridNodePosition, GridNode> previousNodes, PrecedingGridNodePosition precedingGridNodePosition, GridNode gridNode) {
        if (previousNodes.containsKey(precedingGridNodePosition)) {
            GridNode previousNode = previousNodes.get(precedingGridNodePosition);
            boardGraph.connectGridNodes(previousNode, gridNode);
        }
    }

    private PrecedingGridNodePosition generateKey(GridNode gridNode, int coordinateX, int coordinateY) {
        return gridNode.getY() == coordinateY && gridNode.getX() == coordinateX - 1 ?
                PrecedingGridNodePosition.HORIZONTALLY_PRECEDING_GRIDNODE :
                PrecedingGridNodePosition.VERTICALLY_PRECEDING_GRIDNODE;
    }

    private ClickListener createGridNodeClickListener(Button button, int finalCx, int finalCy) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                final Coordinate coordinateClicked = new Coordinate(finalCx, finalCy);
                // Character is already selected, so perform action or simply deselect.
                if (selectedCell.isSelected()) {
                    selectedCell.deselect();
                    // Clicking action area means we perform given action.
                    Optional<Coordinate> matches = paintedCoordinates.stream().filter(painted -> painted.equals(coordinateClicked)).findFirst();
                    if (matches.isPresent()) {
                        combatController.applyActionWhenPossible(coordinateClicked);
                        createPathFindingConnections();

                        // TODO: this is just an AI example
                        path.forEach(gridNode -> gridNode.getImageButton().setColor(DEFAULT_COLOR));
                        GameCharacter gameCharacter = combatController.getNpcs().get(0);
                        GameCharacter player = combatController.getPlayerCharacters().get(0);
                        GridNode node1 = grid.get(gameCharacter.getCoordinateY()-1).get(gameCharacter.getCoordinateX());
                        GridNode node2 = grid.get(player.getCoordinateY()+1).get(player.getCoordinateX());
                        path = boardGraph.findPath(node2, node1);
                        path.forEach(gridNode -> gridNode.getImageButton().setColor(Color.FIREBRICK));
                    }
                    // No match, let's clear action area.
                    if (!paintedCoordinates.isEmpty()) {
                        paintedCoordinates.forEach(coordinate -> getImageButtonAt(coordinate.getX(), coordinate.getY()).setChecked(false));
                        paintedCoordinates = new ArrayList<>();
                    }
                    button.setChecked(false);
                } else {
                    // Painted coordinates means we selected a character and we now have selection area.
                    paintedCoordinates = combatController.getAreaToPaint(coordinateClicked);
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

    private enum PrecedingGridNodePosition {
        HORIZONTALLY_PRECEDING_GRIDNODE,
        VERTICALLY_PRECEDING_GRIDNODE
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
