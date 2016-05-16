/**
 * 
 */
package com.universe.exploration.spritecontainer;

/**
 * <p>
 * Basically this abstraction is solely to maintain information on the selected
 * planet. Class also takes care of fading it in and out from view.
 * </p>
 * 
 * @author 12.1.2016 Teemu Puurunen
 *
 */
public class SelectedPlanet {
    private PlanetSpriteContainer selectedPlanet;

    private static final float alphaMin = 0.0f;
    private static final float alphaMax = 1.0f;
    private static final float decrementRate = 0.05f;
    private static final float incrementRate = 0.05f;
    private static final Fader planetFader = new Fader(alphaMin, alphaMax, decrementRate, incrementRate);

    public SelectedPlanet() {
	planetFader.setAlphaValue(0.0f);
    }

    public void handleAlpha(boolean fadeAway) {
	selectedPlanet.getEnlarged().setAlpha(planetFader.updateAlpha(fadeAway));
	selectedPlanet.getEnlarged().rotate(0.01f);
    }

    /**
     * @return the selectedPlanet
     */
    public PlanetSpriteContainer getSelectedPlanet() {
	return selectedPlanet;
    }

    /**
     * @param selectedPlanet
     *            the selectedPlanet to set
     */
    public void setSelectedPlanet(PlanetSpriteContainer selectedPlanet) {
	this.selectedPlanet = selectedPlanet;
    }

}
