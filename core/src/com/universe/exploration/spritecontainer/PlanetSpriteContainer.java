package com.universe.exploration.spritecontainer;

import com.universe.exploration.starsystem.components.CelestialComponent;

public class PlanetSpriteContainer extends SpriteContainer {

    private boolean isPlanetSelected = false;
    
    private Fader resizeFactor; 
    
    public PlanetSpriteContainer() {
	super(64, "planet2.png");
	resizeFactor = new Fader(spriteSize, 500, 1, 1);
    }

    public PlanetSpriteContainer(CelestialComponent starSystemComponent) {
	super(starSystemComponent);
	resizeFactor = new Fader(spriteSize, 500, 10, 10);
    }
    
    public void handleZooming() {
	resizeFactor.updateAlpha(!isPlanetSelected);
	
	smallVersion.setSize(resizeFactor.getAlphaValue(), resizeFactor.getAlphaValue());
	smallVersion.setOriginCenter();
    }

    /**
     * @return the isPlanetSelected
     */
    public boolean isPlanetSelected() {
        return isPlanetSelected;
    }
    
    /**
     * @param isPlanetSelected the isPlanetSelected to set
     */
    public void setPlanetSelected(boolean isPlanetSelected) {
        this.isPlanetSelected = isPlanetSelected;
    }
    
    
}
