package com.universe.exploration.model;

import com.universe.exploration.utils.gameassetmanager.GameAssetManager;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.HudAssetProvider;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

public enum GameScreen {
    MAIN_MENU((manager) -> {
    }),
    INITIAL_LOADING((manager) -> {
    }),
    GAME((manager) -> {
        new PlanetAssetProvider(manager).setupAssets();
        new HudAssetProvider(manager).setupAssets();
    }),
    COMBAT((manager) -> {
        new PlanetAssetProvider(manager).setupAssets();
        new HudAssetProvider(manager).setupAssets();
    });

    private AssetLoader assetLoader;

    GameScreen(AssetLoader assetLoader) {
        this.assetLoader = assetLoader;
    }

    public void retrieveAssets(GameAssetManager gameAssetManager) {
        assetLoader.loadAssets(gameAssetManager);
    }

    @FunctionalInterface
    interface AssetLoader {
        void loadAssets(GameAssetManager gameAssetManager);
    }
}
