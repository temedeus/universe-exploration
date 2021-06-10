package com.universe.exploration.utils.gameassetmanager.gameassetprovider;

import com.universe.exploration.utils.gameassetmanager.GameAssetManager;

import java.util.List;
import java.util.Map;

public abstract class AbstractGameAssetProvider {
    protected Map<String, GameAsset> gameAssets;

    protected GameAssetManager gameAssetManager;

    public AbstractGameAssetProvider(GameAssetManager gameAssetManager) {
        this.gameAssetManager = gameAssetManager;
    }

    public abstract void setupAssets();

    abstract List<GameAsset> getGameAssetList();

    public Map<String, GameAsset> getAssets() {
        return gameAssets;
    }
}
