package com.universe.exploration.model.crew;

import com.universe.exploration.model.crew.action.CrewMemberAction;
import com.universe.exploration.model.crew.action.CrewMemberActionType;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

public class Scientist extends GameCharacter {
    @Override
    PlanetAssetProvider.PlanetAsset getAsset() {
        return null;
    }

    @Override
    public CrewMemberAction setupActions() {
        talkAction = new CrewMemberAction.Builder()
                .verticalReach(3)
                .horizontalReach(3)
                .crewMemberActionType(CrewMemberActionType.TALK)
                .fillReach(false)
                .crossReach(false)
                .build();

        talkAction = new CrewMemberAction.Builder()
                .verticalReach(3)
                .horizontalReach(3)
                .crewMemberActionType(CrewMemberActionType.WALK)
                .fillReach(false)
                .crossReach(false)
                .build();

        return null;
    }
}
