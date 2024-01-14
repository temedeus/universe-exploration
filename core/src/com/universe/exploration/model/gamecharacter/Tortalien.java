package com.universe.exploration.model.gamecharacter;

import com.universe.exploration.model.gamecharacter.action.CharacterActionConfiguration;
import com.universe.exploration.model.gamecharacter.action.CharacterActionMode;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

public class Tortalien extends GameCharacter {
    @Override
    public PlanetAssetProvider.PlanetAsset getAsset() {
        return PlanetAssetProvider.PlanetAsset.TORTALIEN;
    }

    @Override
    public CharacterActionConfiguration setupActions() {
        specialAttackAction = new CharacterActionConfiguration.Builder()
                .verticalReach(1)
                .horizontalReach(1)
                .crewMemberActionType(CharacterActionMode.SPECIAL)
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
                .crewMemberActionType(CharacterActionMode.MOVE)
                .fillReach(false)
                .crossReach(false)
                .build();

        return null;
    }
}
