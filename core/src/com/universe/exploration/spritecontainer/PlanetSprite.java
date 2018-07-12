package com.universe.exploration.spritecontainer;

import com.universe.exploration.starsystem.components.CelestialComponent;

import java.util.Optional;

public class PlanetSprite extends SpriteContainer {

    private boolean isPlanetSelected = false;

    private Fader fader;

    public PlanetSprite(CelestialComponent starSystemComponent) {
        super(starSystemComponent);
        fader = new Fader(spriteSize, 500, 10, 10);
   }

    public void handleZooming() {
        fader.updateAlpha(!isPlanetSelected);

        smallVersion.setSize(fader.getAlphaValue(), fader.getAlphaValue());
        smallVersion.setOriginCenter();
    }

    /**
     * @param isPlanetSelected the isPlanetSelected to set
     */
    public void setPlanetSelected(boolean isPlanetSelected) {
        this.isPlanetSelected = isPlanetSelected;
    }

}
