package com.universe.exploration.model.starsystem;

import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

public enum PlanetType {
    EARTHLIKE(PlanetAssetProvider.PlanetAsset.EARTHLIKE),
    GAS1(PlanetAssetProvider.PlanetAsset.GAS1),
    GAS2(PlanetAssetProvider.PlanetAsset.GAS2),
    TERRESTRIAL1(PlanetAssetProvider.PlanetAsset.TERRESTRIAL1),
    TERRESTRIAL2(PlanetAssetProvider.PlanetAsset.TERRESTRIAL2),
    TERRESTRIAL3(PlanetAssetProvider.PlanetAsset.TERRESTRIAL3);

    private final PlanetAssetProvider.PlanetAsset planetAsset;

    PlanetType(PlanetAssetProvider.PlanetAsset planetAsset) {
        this.planetAsset = planetAsset;
    }

    public PlanetAssetProvider.PlanetAsset getPlanetAsset() {
        return planetAsset;
    }
}
