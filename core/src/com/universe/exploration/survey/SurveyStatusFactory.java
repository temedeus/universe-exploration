/**
 * 
 */
package com.universe.exploration.survey;

import java.util.ArrayList;

import com.universe.exploration.casualty.Casualty;
import com.universe.exploration.casualty.CasualtyFactory;
import com.universe.exploration.common.tools.MathTools;
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
    public SurveyStatus createSurveyStatus(int startDay, int crewmenCount, PlanetCelestialComponent planet) {
	ResourcesFoundFactory rff = new ResourcesFoundFactory();
	SurveyStatus surveyStatus = new SurveyStatus();

	int crewmenCasualtyCount = calculateHowManyCrewmenWillDie(crewmenCount, planet);

	surveyStatus.setCrewmenInSurveyTeam(crewmenCount);
	surveyStatus.setSurveyStartDay(startDay);
	surveyStatus.setSurveyEndDay(startDay + 2);
	surveyStatus.setResourcesFound(rff.generateFoundResource(crewmenCount, planet));

	surveyStatus.setMortalities(createCasualtyList(crewmenCasualtyCount, planet));

	return surveyStatus;
    }

    private int calculateHowManyCrewmenWillDie(int crewmenCount, PlanetCelestialComponent planet) {
	int count = 0;

	// TODO: implement some sort of logic. The way "5" is added there
	// because there should always be at least
	// some risk of dying on a planet.
	float chanceToDie = 5 + planet.getLifeforms().getRank() * 2;

	for (int x = 0; x < crewmenCount; x++) {
	    if (MathTools.calculateIfOddsHit(chanceToDie)) {
		count++;
	    }
	}

	return count;
    }

    private ArrayList<Casualty> createCasualtyList(int count, PlanetCelestialComponent planet) {
	CasualtyFactory mf = new CasualtyFactory(planet);
	ArrayList<Casualty> casualties = new ArrayList<Casualty>();

	for (int x = 0; x < count; x++) {
	    casualties.add(mf.createCasualty());
	}

	return casualties;
    }

}
