package com.universe.exploration.model.crew;

import com.universe.exploration.model.crew.action.CrewMemberAction;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

public class Soldier extends GameCharacter {
    @Override
    PlanetAssetProvider.PlanetAsset getAsset() {
        return PlanetAssetProvider.PlanetAsset.ASTRONAUT;
    }

    @Override
    public CrewMemberAction setupActions() {
        talkAction = new CrewMemberAction.Builder()
                .verticalReach(2)
                .horizontalReach(2)
                .crewMemberActionType(CrewMemberAction.CrewMemberActionType.TALK)
                .fillReach(false)
                .crossReach(false)
                .build();

        moveAction = new CrewMemberAction.Builder()
                .verticalReach(2)
                .horizontalReach(2)
                .crewMemberActionType(CrewMemberAction.CrewMemberActionType.MOVE)
                .fillReach(false)
                .crossReach(false)
                .build();
        return null;
    }
}
