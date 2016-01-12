/**
 * 
 */
package com.universe.exploration.view;

/**
 * @author 12.1.2016 Teemu Puurunen 
 *
 */
public class SelectedPlanet {
	private PlanetGfxContainer selectedPlanet;

	private static final float alphaMin = 0.0f;
	private static final float alphaMax = 1.0f;
	private static final float decrementRate = 0.05f;
	private static final float incrementRate = 0.05f;
	private static final AlphaFader planetFader = new AlphaFader(alphaMin, alphaMax, decrementRate, incrementRate);
	
	/**
	 * 
	 */
	public SelectedPlanet() {
		planetFader.setAlphaValue(0.0f);
	}
	
	public void handleAlpha(boolean fadeAway) {
		selectedPlanet.getEnlarged().setAlpha(planetFader.updateAlpha(fadeAway));
	}
	
	/**
	 * @return the selectedPlanet
	 */
	public PlanetGfxContainer getSelectedPlanet() {
		return selectedPlanet;
	}

	/**
	 * @param selectedPlanet the selectedPlanet to set
	 */
	public void setSelectedPlanet(PlanetGfxContainer selectedPlanet) {
		this.selectedPlanet = selectedPlanet;
	}
	
	
}
