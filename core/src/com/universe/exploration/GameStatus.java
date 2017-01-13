/**
 * 
 */
package com.universe.exploration;

import com.universe.exploration.crewmember.Crew;
import com.universe.exploration.player.CrewStatusManager;
import com.universe.exploration.starsystem.StarSystem;

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
     * Star system
     */
    private static StarSystem starSystem;

    public static Crew crew;


    /**
     * Contains player spaceship status.
     */
    private CrewStatusManager crewStatus;
    
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

    /**
     * @return the starSystem
     */
    public static StarSystem getStarSystem() {
        return starSystem;
    }

    /**
     * @return the crew
     */
    public static Crew getCrew() {
        return crew;
    }

    /**
     * @return the crewStatus
     */
    public CrewStatusManager getCrewStatus() {
        return crewStatus;
    }

    /**
     * @param starSystem the starSystem to set
     */
    public static void setStarSystem(StarSystem starSystem) {
        GameStatus.starSystem = starSystem;
    }

    /**
     * @param crew the crew to set
     */
    public static void setCrew(Crew crew) {
        GameStatus.crew = crew;
    }

    /**
     * @param crewStatus the crewStatus to set
     */
    public void setCrewStatus(CrewStatusManager crewStatus) {
        this.crewStatus = crewStatus;
    }
}
