/**
 * 
 */
package com.universe.exploration.survey;

import java.util.ArrayList;

import com.universe.exploration.common.tools.MathTools;
import com.universe.exploration.mortality.Mortality;
import com.universe.exploration.mortality.MortalityFactory;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * <p>Factory for creating {@link SurveyStatus} instances.</p>
 * @author 25.10.2015 Teemu Puurunen 
 *
 */
public class SurveyStatusFactory {

	/**
	 * <p>Create a {@link SurveyStatus} object based on start day and planet data.</p>
	 * @param startDay
	 * @param crewmenCount Integer How many crewmen are on the survey team.
	 * @param planet
	 * @return
	 */
	public SurveyStatus createSurveyStatus(int startDay, int crewmenCount, PlanetCelestialComponent planet) {
		ResourcesFoundFactory rff = new ResourcesFoundFactory();
		SurveyStatus surveyStatus = new SurveyStatus();
		
		int crewmenMortalityCount = calculateHowManyCrewmenWillDie(crewmenCount, planet);
		
		surveyStatus.setCrewmenInSurveyTeam(crewmenCount);
		surveyStatus.setSurveyStartDay(startDay);
		surveyStatus.setSurveyEndDay(startDay + 2);
		surveyStatus.setResourcesFound(rff.generateFoundResource(planet));
		
		surveyStatus.setMortalities(createMortalityList(crewmenMortalityCount, planet));
		
		return surveyStatus;
	}
	
	private int calculateHowManyCrewmenWillDie(int crewmenCount, PlanetCelestialComponent planet) {
		int count = 0;
		
		// TODO: implement some sort of logic. The way "5" is added there because there should always be at least
		// some risk of dying on a planet.
		float chanceToDie = 5 + planet.getLifeforms().getRank() * 2;
		
		for(int x=0; x < crewmenCount; x++) {
			if(MathTools.calculateIfOddsHit(chanceToDie)) {
				count++;
			}
		}
		
		return count;
	}
	
	private ArrayList<Mortality> createMortalityList(int count, PlanetCelestialComponent planet) {
		MortalityFactory mf = new MortalityFactory(planet);
		ArrayList<Mortality> mortalities = new ArrayList<Mortality>();
		
		for(int x=0; x < count; x++) {
			mortalities.add(mf.makeMortality());
		}
		
		return mortalities;
	}

}
