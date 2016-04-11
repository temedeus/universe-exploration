/**
 * 
 */
package com.universe.exploration.userinterface.forms;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * @author 26.10.2015 Teemu Puurunen
 *
 */
public class PlanetSurveyForm extends FormContainer {

    private List<CrewMember> selectedCrewMembers = new ArrayList<CrewMember>();
    
    private Slider surveyLength;

    private PlanetCelestialComponent planet;

    /**
     * @return the planet
     */
    public PlanetCelestialComponent getPlanet() {
	return planet;
    }

    /**
     * @param planet
     *            the planet to set
     */
    public void setPlanet(PlanetCelestialComponent planet) {
	this.planet = planet;
    }

    /**
     * @return the crewmenCount
     */
    public Slider getSurveyLength() {
	return surveyLength;
    }

    /**
     * @param crewmenCount
     *            the crewmenCount to set
     */
    public void setSurveyLength(Slider crewmenCount) {
	this.surveyLength = crewmenCount;
    }

    /**
     * @return the selectedCrewMembers
     */
    public List<CrewMember> getSelectedCrewMembers() {
        return selectedCrewMembers;
    }

    /**
     * @param selectedCrewMembers the selectedCrewMembers to set
     */
    public void setSelectedCrewMembers(List<CrewMember> selectedCrewMembers) {
        this.selectedCrewMembers = selectedCrewMembers;
    }
}
