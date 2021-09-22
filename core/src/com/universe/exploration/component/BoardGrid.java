package com.universe.exploration.component;

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
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.HudAssetProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardGrid extends Table {
    private Map<Integer, Map<Integer, ImageButton>> grid;

    private SelectedCell selectedCell;

    private GameController gameController;

    List<Coordinate> paintedCoordinates;

    public BoardGrid(UniverseExploration universeExploration, GameController gameController) {
        this.gameController = gameController;
        this.paintedCoordinates = new ArrayList<>();
        Texture up = universeExploration.getAssetManager().getAsset(HudAssetProvider.HudAsset.GRID_CELL);
        Texture pressed = universeExploration.getAssetManager().getAsset(HudAssetProvider.HudAsset.GRID_CELL_PRESSED);
        Texture selected = universeExploration.getAssetManager().getAsset(HudAssetProvider.HudAsset.GRID_CELL_SELECTED);
        Drawable upDrawable = new TextureRegionDrawable(new TextureRegion(up));
        Drawable pressedDrawable = new TextureRegionDrawable(new TextureRegion(pressed));
        Drawable selectedDrawable = new TextureRegionDrawable(new TextureRegion(selected));
        grid = new HashMap<>();
        selectedCell = new SelectedCell();

        for (int cy = 0; cy < 6; cy++) {
            Map<Integer, ImageButton> imageButtons = new HashMap<>();
            for (int cx = 0; cx < 10; cx++) {
                ImageButton button = new ImageButton(upDrawable, pressedDrawable, selectedDrawable);
                imageButtons.put(cx, button);
                int finalCx = cx;
                int finalCy = cy;
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (selectedCell.isSelected()) {
                            selectedCell.deselect();
                            if(!paintedCoordinates.isEmpty()) {
                                paintedCoordinates.forEach(coordinate -> grid.get(coordinate.getY()).get(coordinate.getX()).setChecked(false));
                                paintedCoordinates = new ArrayList<>();
                            }
                            button.setChecked(false);
                        } else {
                            paintedCoordinates = gameController.getAreaToPaint(new Coordinate(finalCx, finalCy));
                            if(!paintedCoordinates.isEmpty()) {
                                selectedCell.setSelected(finalCx, finalCy);
                                paintedCoordinates.forEach(coordinate -> grid.get(coordinate.getY()).get(coordinate.getX()).setChecked(true));
                            } else {
                                button.setChecked(false);
                            }
                        }
                    }
                });
                button.getColor().a = 0.3f;
                this.add(button.pad(10));
            }
            this.row();
            grid.put(cy, imageButtons);
        }
    }

    public Vector2 getCellPosition(int x, int y) {
        return grid.get(y).get(x).localToParentCoordinates(new Vector2(this.getX(), this.getY()));
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
            grid.get(coordinateY).get(coordinateX).setChecked(false);
            coordinateX = NO_SELECTED_VALUE;
            coordinateY = NO_SELECTED_VALUE;
        }

        boolean isSelected() {
            return coordinateX >= 0 && coordinateY >= 0;
        }
    }
}
