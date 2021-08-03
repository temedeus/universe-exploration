package com.universe.exploration.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.HudAssetProvider;

public class Grid extends Table {
    public Grid(UniverseExploration universeExploration) {
        Texture left = universeExploration.getAssetManager().getAsset(HudAssetProvider.HudAsset.GRID_CELL);
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(left));
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 5; y++) {
                ImageButton button = new ImageButton(drawable);
                button.getColor().a = 0.3f;
                this.add(button.pad(10));
            }
            this.row();
        }
    }
}
