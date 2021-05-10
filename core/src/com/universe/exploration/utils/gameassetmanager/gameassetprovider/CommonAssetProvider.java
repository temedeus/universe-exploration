package com.universe.exploration.utils.gameassetmanager.gameassetprovider;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.universe.exploration.utils.gameassetmanager.GameAssetManager;

public class CommonAssetProvider extends AbstractGameAssetProvider {
    public CommonAssetProvider(GameAssetManager gameAssetManager) {
        super(gameAssetManager);
    }

    @Override
    public void setupAssets() {
        gameAssetManager.getAssetManager().load(CommonAsset.BITMAP_FONT.getPath(), BitmapFont.class);
        gameAssetManager.getAssetManager().load(CommonAsset.UI_SKIN.getPath(), Skin.class);
    }

    public enum CommonAsset implements GameAsset {
        BITMAP_FONT("fonts/ueimpact_emp.fnt"),
        UI_SKIN("star-soldier/skin/star-soldier-ui.json");

        private String path;

        CommonAsset(String path) {
            this.path = path;
        }

        @Override
        public String getPath() {
            return path;
        }
    }
}