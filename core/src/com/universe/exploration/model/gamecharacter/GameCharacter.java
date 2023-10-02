package com.universe.exploration.model.gamecharacter;

import com.universe.exploration.model.gamecharacter.action.CharacterActionConfiguration;
import com.universe.exploration.model.gamecharacter.action.CharacterActionMode;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

public abstract class GameCharacter {
    private boolean selected;

    private int coordinateX;
    private int coordinateY;

    public abstract PlanetAssetProvider.PlanetAsset getAsset();

    protected CharacterActionConfiguration specialAttackAction;

    protected CharacterActionConfiguration moveAction;

    protected CharacterActionConfiguration attackAction;

    abstract CharacterActionConfiguration setupActions();

    public CharacterActionConfiguration getSelectedActionConfiguration(CharacterActionMode characterActionMode) {
        switch (characterActionMode) {
            case MOVE:
                return  moveAction;
            case TALK:
                return specialAttackAction;
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
