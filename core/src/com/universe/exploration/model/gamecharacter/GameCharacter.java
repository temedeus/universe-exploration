package com.universe.exploration.model.gamecharacter;

import com.universe.exploration.model.gamecharacter.action.CrewMemberActionConfiguration;
import com.universe.exploration.model.gamecharacter.action.CharacterActionMode;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

public abstract class GameCharacter {
    private boolean selected;

    private int coordinateX;
    private int coordinateY;

    abstract PlanetAssetProvider.PlanetAsset getAsset();

    protected CrewMemberActionConfiguration talkAction;

    protected CrewMemberActionConfiguration moveAction;

    protected CrewMemberActionConfiguration attackAction;

    abstract CrewMemberActionConfiguration setupActions();

    public CrewMemberActionConfiguration getSelectedAction(CharacterActionMode characterActionMode) {
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
