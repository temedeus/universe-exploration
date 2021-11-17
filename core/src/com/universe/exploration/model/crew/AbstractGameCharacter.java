package com.universe.exploration.model.crew;

import com.universe.exploration.model.crew.action.CrewMemberActionConfiguration;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

public abstract class AbstractGameCharacter {
    private boolean selected;

    private int coordinateX;
    private int coordinateY;


    protected CrewMemberActionConfiguration talkAction;

    protected CrewMemberActionConfiguration moveAction;

    abstract  PlanetAssetProvider.PlanetAsset getAsset();


    public CrewMemberActionConfiguration getTalkAction() {
        return  talkAction;
    }

    public CrewMemberActionConfiguration getMoveAction() {
        return moveAction;
    }

    abstract CrewMemberActionConfiguration setupActions();

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

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

}
