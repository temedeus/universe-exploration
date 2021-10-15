package com.universe.exploration.model.crew;

import com.universe.exploration.model.crew.action.CrewMemberAction;
import com.universe.exploration.model.crew.action.CharacterActionMode;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

public abstract class GameCharacter {
    private boolean selected;

    private int coordinateX;
    private int coordinateY;

    abstract PlanetAssetProvider.PlanetAsset getAsset();

    protected CrewMemberAction talkAction;

    protected CrewMemberAction moveAction;

    protected CrewMemberAction attackAction;

    abstract CrewMemberAction setupActions();

    public CrewMemberAction getSelectedAction(CharacterActionMode characterActionMode) {
        switch (characterActionMode) {
            case MOVE:
                return  moveAction;
            case TALK:
                return talkAction;
            case ATTACK:
                return attackAction;
        }

        return  null;
    }

    public boolean isSelected() {
        return selected;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }


    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setCoordinates(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }
}
