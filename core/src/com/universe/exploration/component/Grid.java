package com.universe.exploration.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.HudAssetProvider;

import java.util.HashMap;
import java.util.Map;

public class Grid extends Table {
    private Map<Integer, Map<Integer, ImageButton>> grid;

    private SelectedCell selectedCell;
    public Grid(UniverseExploration universeExploration) {
        Texture left = universeExploration.getAssetManager().getAsset(HudAssetProvider.HudAsset.GRID_CELL);
        Texture pressed = universeExploration.getAssetManager().getAsset(HudAssetProvider.HudAsset.GRID_CELL_PRESSED);
        Texture selected = universeExploration.getAssetManager().getAsset(HudAssetProvider.HudAsset.GRID_CELL_SELECTED);
        Drawable upDrawable = new TextureRegionDrawable(new TextureRegion(left));
        Drawable pressedDrawable = new TextureRegionDrawable(new TextureRegion(pressed));
        Drawable selectedDrawable = new TextureRegionDrawable(new TextureRegion(selected));
        grid = new HashMap<>();
        selectedCell = new SelectedCell();

        for (int cx = 0; cx < 3; cx++) {
            Map<Integer, ImageButton> imageButtons = new HashMap<>();
            for (int cy = 0; cy < 5; cy++) {
                ImageButton button = new ImageButton(upDrawable, pressedDrawable, selectedDrawable);
                imageButtons.put(cy, button);
                int finalCx = cx;
                int finalCy = cy;
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        button.setChecked(true);
                        if(selectedCell.isSelected()) {
                            selectedCell.deselect();
                        }
                        selectedCell.setSelected(finalCx, finalCy);
                    }
                });
                button.getColor().a = 0.3f;
                this.add(button.pad(10));
            }
            this.row();
            grid.put(cx, imageButtons);
        }
    }

    /**
     * Reference to selected cell.
     */
    private class SelectedCell {
        private Integer coordinateX;
        private Integer coordinateY;
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
            grid.get(coordinateX).get(coordinateY).setChecked(false);
            coordinateX = NO_SELECTED_VALUE;
            coordinateY = NO_SELECTED_VALUE;
        }

        boolean isSelected() {
            return coordinateX >= 0 && coordinateY >= 0;
        }
    }
}
