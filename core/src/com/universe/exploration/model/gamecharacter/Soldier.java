package com.universe.exploration.model.gamecharacter;

import com.universe.exploration.model.gamecharacter.action.CrewMemberActionConfiguration;
import com.universe.exploration.model.gamecharacter.action.CharacterActionMode;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

public class Soldier extends GameCharacter {
    @Override
    PlanetAssetProvider.PlanetAsset getAsset() {
        return PlanetAssetProvider.PlanetAsset.ASTRONAUT;
    }

    @Override
    public CrewMemberActionConfiguration setupActions() {
        talkAction = new CrewMemberActionConfiguration.Builder()
                .verticalReach(1)
                .horizontalReach(1)
                .crewMemberActionType(CharacterActionMode.TALK)
                .fillReach(false)
                .crossReach(false)
                .build();

        moveAction = new CrewMemberActionConfiguration.Builder()
                .verticalReach(2)
                .horizontalReach(2)
                .crewMemberActionType(CharacterActionMode.MOVE)
                .fillReach(false)
                .crossReach(false)
                .build();

        attackAction = new CrewMemberActionConfiguration.Builder()
                .verticalReach(3)
                .horizontalReach(3)
                .crewMemberActionType(CharacterActionMode.MOVE)
                .fillReach(false)
                .crossReach(false)
                .build();

        return null;
    }
}
