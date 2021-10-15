package com.universe.exploration.model.crew;

import com.universe.exploration.model.crew.action.CrewMemberAction;
import com.universe.exploration.model.crew.action.CharacterActionMode;
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
                .crewMemberActionType(CharacterActionMode.TALK)
                .fillReach(false)
                .crossReach(false)
                .build();

        moveAction = new CrewMemberAction.Builder()
                .verticalReach(3)
                .horizontalReach(3)
                .crewMemberActionType(CharacterActionMode.MOVE)
                .fillReach(false)
                .crossReach(false)
                .build();

        attackAction = new CrewMemberAction.Builder()
                .verticalReach(2)
                .horizontalReach(2)
                .crewMemberActionType(CharacterActionMode.ATTACK)
                .fillReach(false)
                .crossReach(false)
                .build();

        return null;
    }
}
