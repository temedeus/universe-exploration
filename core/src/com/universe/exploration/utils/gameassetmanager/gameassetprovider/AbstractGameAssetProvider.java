package com.universe.exploration.utils.gameassetmanager.gameassetprovider;

import com.universe.exploration.utils.gameassetmanager.GameAssetManager;

import java.util.Map;

public abstract class AbstractGameAssetProvider {
    protected Map<String, GameAsset> gameAssets;

    protected GameAssetManager gameAssetManager;

    AbstractGameAssetProvider(GameAssetManager gameAssetManager) {
        this.gameAssetManager = gameAssetManager;
    }

    abstract void setupAssets();

    public Map<String, GameAsset> getAssets() {
        return gameAssets;
    }
}
