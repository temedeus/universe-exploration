/**
 * 
 */
package com.universe.exploration;

/**
 * <p>
 * Container for general game status.
 * </p>
 * 
 * @author 17.12.2015 Teemu Puurunen
 *
 */
public class GameStatus {
    private boolean paused = false;

    private boolean planetaryMovementActive = true;

    private boolean zoomIn = false;

    /**
     * Ensures game parameters are proper for creating a survey window.
     * 
     * @param activate
     *            Boolean to tell if survey mode should be activated or
     *            deactivated.
     */
    public void activateSurveyMode(boolean activate) {
	if (activate) {
	    setPlanetaryMovementActive(false);
	    setZoomIn(true);
	} else {
	    setPlanetaryMovementActive(true);
	    setZoomIn(false);
	}
    }

    public boolean isPaused() {
	return paused;
    }

    public boolean isPlanetaryMovementActive() {
	return planetaryMovementActive;
    }

    public void setPaused(boolean paused) {
	this.paused = paused;
    }

    public void setPlanetaryMovementActive(boolean planetaryMovementActive) {
	this.planetaryMovementActive = planetaryMovementActive;
    }

    public boolean isZoomIn() {
	return zoomIn;
    }

    public void setZoomIn(boolean zoomIn) {
	this.zoomIn = zoomIn;
    }
}
