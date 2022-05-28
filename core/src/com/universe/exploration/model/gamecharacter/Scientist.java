package com.universe.exploration.model.gamecharacter;

import com.universe.exploration.model.gamecharacter.action.CharacterActionConfiguration;
import com.universe.exploration.model.gamecharacter.action.CharacterActionMode;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

public class Scientist extends GameCharacter {
    @Override
    PlanetAssetProvider.PlanetAsset getAsset() {
        return null;
    }

    @Override
    public CharacterActionConfiguration setupActions() {
        talkAction = new CharacterActionConfiguration.Builder()
                .verticalReach(1)
                .horizontalReach(1)
                .crewMemberActionType(CharacterActionMode.TALK)
                .fillReach(false)
                .crossReach(false)
                .build();

        moveAction = new CharacterActionConfiguration.Builder()
                .verticalReach(2)
                .horizontalReach(2)
                .crewMemberActionType(CharacterActionMode.MOVE)
                .fillReach(false)
                .crossReach(false)
                .build();

        attackAction = new CharacterActionConfiguration.Builder()
                .verticalReach(3)
                .horizontalReach(3)
                .crewMemberActionType(CharacterActionMode.ATTACK)
                .fillReach(false)
                .crossReach(false)
                .build();

        return null;
    }
}
