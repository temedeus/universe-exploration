package com.universe.exploration.utils.gameassetmanager.gameassetprovider;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.universe.exploration.utils.gameassetmanager.GameAssetManager;

import java.util.HashMap;

public class CommonAssetProvider extends AbstractGameAssetProvider {
    public CommonAssetProvider(GameAssetManager gameAssetManager) {
        super(gameAssetManager);
    }

    @Override
    public void setupAssets() {
        gameAssetManager.getAssetManager().load(CommonAsset.BITMAP_FONT.getPath(), BitmapFont.class);
        gameAssetManager.getAssetManager().load(CommonAsset.UI_SKIN.getPath(), Skin.class);
    }

    public enum CommonAsset {
        BITMAP_FONT("fonts/ueimpact_emp.fnt"),
        UI_SKIN("star-soldier/skin/star-soldier-ui.json");

        private String path;

        CommonAsset(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }
}