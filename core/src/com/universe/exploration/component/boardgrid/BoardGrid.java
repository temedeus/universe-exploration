package com.universe.exploration.component.boardgrid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.model.Coordinate;
import com.universe.exploration.screens.game.GameController;
import com.universe.exploration.utils.gameassetmanager.GameAssetManager;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.HudAssetProvider;

import java.util.*;

public class BoardGrid extends Table {
    private Map<Integer, Map<Integer, GridNode>> grid;

    private SelectedCell selectedCell;

    private GameController gameController;

    List<Coordinate> paintedCoordinates;

    public BoardGrid(UniverseExploration universeExploration, GameController gameController) {
        this.gameController = gameController;
        this.paintedCoordinates = new ArrayList<>();

        grid = new HashMap<>();
        selectedCell = new SelectedCell();

        for (int cy = 0; cy < 6; cy++) {
            Map<Integer, GridNode> imageButtons = new HashMap<>();
            for (int cx = 0; cx < 10; cx++) {
                int finalCx = cx;
                int finalCy = cy;

                ImageButton button = createGridButton(universeExploration.getAssetManager());
                imageButtons.put(cx, new GridNode(button, cx, cy));

                button.addListener(new ClickListener() {
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
                });
                button.getColor().a = 0.3f;
                add(button.pad(10));
            }
            row();
            grid.put(cy, imageButtons);
        }
        pack();
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
