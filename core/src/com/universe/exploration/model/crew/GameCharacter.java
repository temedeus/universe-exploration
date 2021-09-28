package com.universe.exploration.model.crew;

import com.universe.exploration.model.crew.action.CrewMemberAction;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

public abstract class GameCharacter {
    private boolean selected;

    private int coordinateX;
    private int coordinateY;

    abstract PlanetAssetProvider.PlanetAsset getAsset();

    protected CrewMemberAction talkAction;

    protected CrewMemberAction moveAction;

    protected CrewMemberAction selectedAction;

    public CrewMemberAction getTalkAction() {
        return talkAction;
    }

    public CrewMemberAction getMoveAction() {
        return moveAction;
    }

    abstract CrewMemberAction setupActions();

    public CrewMemberAction getSelectedAction() {
        return moveAction;
    }

    public void setSelectedAction(CrewMemberAction selectedAction) {
        this.selectedAction = selectedAction;
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
