package com.universe.exploration.utils.gameassetmanager.gameassetprovider;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.graphics.Texture;
import com.universe.exploration.utils.gameassetmanager.GameAssetManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class HudAssetProvider extends AbstractGameAssetProvider {
    public HudAssetProvider(GameAssetManager gameAssetManager) {
        super(gameAssetManager);
    }

    @Override
    public void setupAssets() {
        getGameAssetList().forEach(asset -> gameAssetManager.getAssetManager().load(asset.getPath(), Texture.class));
    }

    @Override
    List<GameAsset> getGameAssetList() {
        return Arrays.asList(HudAsset.values());
    }

    public enum HudAsset implements GameAsset {
        ARROW_LEFT("images/game_menu/arrow_left.png"),
        ARROW_RIGHT("images/game_menu/arrow_right.png");

        private String path;

        HudAsset(String path) {
            this.path = path;
        }

        @Override
        public String getPath() {
            return path;
        }

        @Override
        public Class<?> getClazz() {
            return null;
        }

        @Override
        public Optional<AssetLoaderParameters<?>> getAssetLoaderParameter() {
            return Optional.empty();
        }
    }
}
