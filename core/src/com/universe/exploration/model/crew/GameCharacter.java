package com.universe.exploration.model.crew;

import com.universe.exploration.model.crew.action.CrewMemberAction;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

public abstract class GameCharacter {
    abstract  PlanetAssetProvider.PlanetAsset getAsset();

    protected CrewMemberAction talkAction;

    protected CrewMemberAction moveAction;

    public CrewMemberAction getTalkAction() {
        return  talkAction;
    }

    public CrewMemberAction getMoveAction() {
        return moveAction;
    }

    abstract CrewMemberAction setupActions();
}
