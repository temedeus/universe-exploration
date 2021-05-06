package com.universe.exploration.utils.gameassetmanager.gameassetprovider;

import com.badlogic.gdx.assets.AssetLoaderParameters;

class GameAsset<T, Y> {
    private String path;
    private Class<T> clazz;
    private AssetLoaderParameters<Y> assetLoaderParameters;

    public GameAsset(String path, Class<T> clazz) {
        this.path = path;
        this.clazz = clazz;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public AssetLoaderParameters<Y> getAssetLoaderParameters() {
        return assetLoaderParameters;
    }

    public void setAssetLoaderParameters(AssetLoaderParameters<Y> assetLoaderParameters) {
        this.assetLoaderParameters = assetLoaderParameters;
    }
}
