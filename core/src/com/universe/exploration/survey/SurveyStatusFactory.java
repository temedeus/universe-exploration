/**
 * 
 */
package com.universe.exploration.survey;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.casualty.Casualty;
import com.universe.exploration.casualty.CasualtyFactory;
import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * <p>
 * Factory for creating {@link SurveyStatus} instances.
 * </p>
 * 
 * @author 25.10.2015 Teemu Puurunen
 *
 */
public class SurveyStatusFactory {

    /**
     * <p>
     * Create a {@link SurveyStatus} object based on start day and planet data.
     * </p>
     * 
     * @param startDay
     * @param crewmenCount
     *            Integer How many crewmen are on the survey team.
     * @param planet
     * @return
     */
    public SurveyStatus createSurveyStatus(int startDay, List<CrewMember> surveyTeam, PlanetCelestialComponent planet) {
	ResourcesFoundFactory rff = new ResourcesFoundFactory();
	SurveyStatus surveyStatus = new SurveyStatus();

	surveyStatus.setSurveyStartDay(startDay);
	surveyStatus.setSurveyEndDay(startDay + 2);
	surveyStatus.setResourcesFound(rff.generateFoundResource(surveyTeam.size(), planet));

	surveyStatus.setMortalities(createCasualtyList(surveyTeam, planet));

	return surveyStatus;
    }

    private ArrayList<Casualty> createCasualtyList(List<CrewMember> surveyTeam, PlanetCelestialComponent planet) {
	CasualtyFactory mf = new CasualtyFactory(planet);
	ArrayList<Casualty> casualties = new ArrayList<Casualty>();

	for (CrewMember surveyTeamMember : surveyTeam) {
	    Casualty casualty = mf.createCasualty(surveyTeamMember);
	    if (casualty != null) {
		casualties.add(casualty);
	    }
	}

	return casualties;
    }
}
