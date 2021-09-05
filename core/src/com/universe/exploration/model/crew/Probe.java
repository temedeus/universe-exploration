package com.universe.exploration.model.crew;

import com.universe.exploration.model.crew.action.CrewMemberAction;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

public class Probe extends GameCharacter {
    @Override
    PlanetAssetProvider.PlanetAsset getAsset() {
        return null;
    }

    @Override
    public CrewMemberAction setupActions() {
        return null;
    }
}
