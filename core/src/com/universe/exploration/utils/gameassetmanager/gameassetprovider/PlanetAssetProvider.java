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
        EARTHLIKE(ASSET_ROOT + "planets/earthlike.png"),
        GAS1(ASSET_ROOT + "planets/gas1.png"),
        GAS2(ASSET_ROOT + "planets/gas2.png"),
        TERRESTRIAL1(ASSET_ROOT + "planets/terrestrial1.png"),
        TERRESTRIAL2(ASSET_ROOT + "planets/terrestrial2.png"),
        TERRESTRIAL3(ASSET_ROOT + "planets/terrestrial3.png"),
        ASTRONAUT(ASSET_ROOT + "character/astronaut.jpg"),
        SCIENTIST(ASSET_ROOT + "character/scientist.jpg"),
        PROBE(ASSET_ROOT + "character/probe.jpg"),
        AMAZALIEN(ASSET_ROOT + "character/amazalien.jpg"),
        GUMMY_BEAR_ALIEN(ASSET_ROOT + "character/gummybearalien.jpg"),
        HORSE_ALIEN(ASSET_ROOT + "character/horsealien.jpg"),
        PLEASANT_ALIEN(ASSET_ROOT + "character/pleasantalien.jpg"),
        MONSTROSITY(ASSET_ROOT + "character/monstrosity.jpg"),
        TORTALIEN(ASSET_ROOT + "character/tortalien.jpg"),
        ROCK_ALIEN(ASSET_ROOT + "character/rockalien.jpg"),
        PINKALIEN(ASSET_ROOT + "character/pinkalien.jpg"),
        FUZZY_ALIEN(ASSET_ROOT + "character/fuzzyalien.jpg");
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
