/**
 * 
 */
package com.universe.exploration;

/**
 * @author 17.12.2015 Teemu Puurunen 
 *
 */
public class GameStatus {
	private boolean paused = false;
	
	private boolean planetaryMovementActive = true;
	
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
	 * @param paused the paused to set
	 */
	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	/**
	 * @param planetaryMovementActive the planetaryMovementActive to set
	 */
	public void setPlanetaryMovementActive(boolean planetaryMovementActive) {
		this.planetaryMovementActive = planetaryMovementActive;
	}
}
