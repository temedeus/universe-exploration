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

    /**
     * @return the paused
     */
    public boolean isPaused() {
	return paused;
    }

    /**
     * @return the planetaryMovementActive
     */
    public boolean isPlanetaryMovementActive() {
	return planetaryMovementActive;
    }

    /**
     * @param paused
     *            the paused to set
     */
    public void setPaused(boolean paused) {
	this.paused = paused;
    }

    /**
     * @param planetaryMovementActive
     *            the planetaryMovementActive to set
     */
    public void setPlanetaryMovementActive(boolean planetaryMovementActive) {
	this.planetaryMovementActive = planetaryMovementActive;
    }

    /**
     * @return the zoomIn
     */
    public boolean isZoomIn() {
	return zoomIn;
    }

    /**
     * @param zoomIn
     *            the zoomIn to set
     */
    public void setZoomIn(boolean zoomIn) {
	this.zoomIn = zoomIn;
    }
}
