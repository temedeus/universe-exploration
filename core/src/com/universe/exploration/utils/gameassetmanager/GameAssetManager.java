package com.universe.exploration.utils.gameassetmanager;

import com.badlogic.gdx.assets.AssetManager;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.GameAsset;

public class GameAssetManager {
    private final AssetManager assetManager = new AssetManager();


    public synchronized <T> T getAsset(GameAsset gameAsset) {
        return assetManager.get(gameAsset.getPath());
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
