package com.universe.exploration.gamegraphics;

import com.universe.exploration.starsystem.components.CelestialComponent;

public class PlanetGfxContainer extends GraphicsGfxContainer {

    private boolean isPlanetSelected = false;
    
    private Fader resizeFactor; 
    
    public PlanetGfxContainer() {
	super(64, "planet2.png");
	resizeFactor = new Fader(spriteSize, 500, 1, 1);
    }

    public PlanetGfxContainer(CelestialComponent starSystemComponent) {
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
