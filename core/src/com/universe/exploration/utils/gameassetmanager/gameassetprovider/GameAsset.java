package com.universe.exploration.utils.gameassetmanager.gameassetprovider;

import com.badlogic.gdx.assets.AssetLoaderParameters;

import java.util.Optional;

public interface GameAsset {
    String getPath();

    Class<?> getClazz();

    Optional<AssetLoaderParameters<?>> getAssetLoaderParameter();

}