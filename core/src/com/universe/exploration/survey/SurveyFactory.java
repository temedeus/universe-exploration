/**
 * 
 */
package com.universe.exploration.survey;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.casualty.Casualty;
import com.universe.exploration.casualty.CasualtyFactory;
import com.universe.exploration.crew.CrewMemberStatus;
import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * <p>
 * Factory for creating {@link Survey} instances.
 * </p>
 * 
 * @author 25.10.2015 Teemu Puurunen
 *
 */
public class SurveyFactory {

    /**
     * <p>
     * Create a {@link Survey} object based on start day and planet data.
     * </p>
     * 
     * @param startDay
     * @param crewmenCount
     *            Integer How many crewmen are on the survey team.
     * @param planet
     * @return
     */
    public Survey createSurveyStatus(int startDay, int surveyLength, List<CrewMember> surveyTeam, PlanetCelestialComponent planet) {
	ResourcesFoundFactory rff = new ResourcesFoundFactory();
	Survey surveyStatus = new Survey();

	surveyStatus.setSurveyStartDay(startDay);
	surveyStatus.setSurveyEndDay(startDay + surveyLength);
	surveyStatus.setResourcesFound(rff.generateFoundResource(surveyTeam.size(), planet));
	setMemberStatusOnSurvey(surveyTeam);
	
	surveyStatus.setMortalities(createCasualtyList(surveyTeam, planet));

	return surveyStatus;
    }
    
    private void setMemberStatusOnSurvey(List<CrewMember> surveyTeam) {
	for(CrewMember member : surveyTeam) {
	    member.setStatus(CrewMemberStatus.ONSURVEY);
	}
    }

    private List<Casualty> createCasualtyList(List<CrewMember> surveyTeam, PlanetCelestialComponent planet) {
	CasualtyFactory mf = new CasualtyFactory(planet);
	List<Casualty> casualties = new ArrayList<Casualty>();

	for (CrewMember surveyTeamMember : surveyTeam) {
	    Casualty casualty = mf.createCasualty(surveyTeamMember);
	    if (casualty != null) {
		casualties.add(casualty);
	    }
	}

	return casualties;
    }
}
