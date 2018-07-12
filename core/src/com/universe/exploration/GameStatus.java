package com.universe.exploration;

import com.universe.exploration.crewmember.Crew;
import com.universe.exploration.player.CrewStatusManager;
import com.universe.exploration.starsystem.StarSystem;
import com.universe.exploration.survey.SurveyContainer;

/**
 * Game status.
 *
 * @author 17.12.2015 Teemu Puurunen
 */
public class GameStatus {
    private boolean paused = false;

    private boolean planetaryMovementActive = true;

    private boolean zoomIn = false;

    private SurveyContainer surveyStatusContainer;

    /**
     * Star system
     */
    private StarSystem starSystem;

    public Crew crew;

    /**
     * Contains player spaceship status.
     */
    private CrewStatusManager crewStatus;

    /**
     * Ensures game parameters are proper for creating a survey window.
     *
     * @param activate Boolean to tell if survey mode should be activated or
     *                 deactivated.
     */
    public void activateSurveyMode(boolean activate) {
            setPlanetaryMovementActive(!activate);
            setZoomIn(activate);
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
    public StarSystem getStarSystem() {
        return starSystem;
    }

    /**
     * @return the crew
     */
    public Crew getCrew() {
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
    public void setStarSystem(StarSystem starSystem) {
        this.starSystem = starSystem;
    }

    /**
     * @param crew the crew to set
     */
    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    /**
     * @param crewStatus the crewStatus to set
     */
    public void setCrewStatus(CrewStatusManager crewStatus) {
        this.crewStatus = crewStatus;
    }

    /**
     * @return the surveyStatusContainer
     */
    public SurveyContainer getSurveyStatusContainer() {
        return surveyStatusContainer;
    }

    /**
     * @param surveyStatusContainer the surveyStatusContainer to set
     */
    public void setSurveyStatusContainer(SurveyContainer surveyStatusContainer) {
        this.surveyStatusContainer = surveyStatusContainer;
    }
}
