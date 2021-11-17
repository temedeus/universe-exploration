package com.universe.exploration.model.crew;

import com.universe.exploration.model.crew.action.CrewMemberActionConfiguration;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

public class Probe extends GameCharacter {
    @Override
    PlanetAssetProvider.PlanetAsset getAsset() {
        return null;
    }

    @Override
    public CrewMemberActionConfiguration setupActions() {
        return null;
    }
}
