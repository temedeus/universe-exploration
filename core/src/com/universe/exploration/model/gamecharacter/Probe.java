package com.universe.exploration.model.gamecharacter;

import com.universe.exploration.model.gamecharacter.action.CharacterActionConfiguration;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

public class Probe extends GameCharacter {
    @Override
    public PlanetAssetProvider.PlanetAsset getAsset() {
        return PlanetAssetProvider.PlanetAsset.PROBE;
    }
    @Override
    public CharacterActionConfiguration setupActions() {
        return null;
    }
}
