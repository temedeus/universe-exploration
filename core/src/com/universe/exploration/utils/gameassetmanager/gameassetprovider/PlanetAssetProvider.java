package com.universe.exploration.utils.gameassetmanager.gameassetprovider;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.graphics.Texture;
import com.universe.exploration.utils.gameassetmanager.GameAssetManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PlanetAssetProvider extends AbstractGameAssetProvider {
    public PlanetAssetProvider(GameAssetManager gameAssetManager) {
        super(gameAssetManager);
    }

    @Override
    public void setupAssets() {
        getGameAssetList().forEach(asset -> gameAssetManager.getAssetManager().load(asset.getPath(), Texture.class));
    }

    @Override
    List<GameAsset> getGameAssetList() {
        return Arrays.asList(PlanetAsset.values());
    }

    public enum PlanetAsset implements GameAsset {
        EARTHLIKE("images/planets/earthlike.png"),
        GAS1("images/planets/gas1.png"),
        GAS2("images/planets/gas2.png"),
        TERRESTRIAL1("images/planets/terrestrial1.png"),
        TERRESTRIAL2("images/planets/terrestrial2.png"),
        TERRESTRIAL3("images/planets/terrestrial3.png"),
        ASTRONAUT("images/astronaut.png");
        private String path;

        PlanetAsset(String path) {
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
