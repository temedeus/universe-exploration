/**
 * 
 */
package com.universe.exploration.casualty;

import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * <p>
 * Create possible causes of death. See
 * {@link ApplicableSurveyIncidentCategoryList} .
 * </p>
 * 
 * @author 30.10.2015 Teemu Puurunen
 *
 */
class ApplicableSurveyIncidentFactory {

    public ApplicableSurveyIncidentCategoryList createListofApplicableCauseOfDeath(PlanetCelestialComponent planet) {
	ApplicableSurveyIncidentCategoryList acd = new ApplicableSurveyIncidentCategoryList();

	acd.add(SurveyIncidentCategory.GENERAL);

	if (!planet.isOxygenFound()) {
	    acd.add(SurveyIncidentCategory.LACK_OF_OXYGEN);
	}

	switch (planet.getLifeforms()) {
	case CIVILIZED:
	    acd.add(SurveyIncidentCategory.CIVILIZATION);
	    acd.add(SurveyIncidentCategory.BACTERIAL);
	    acd.add(SurveyIncidentCategory.ANIMAL);
	    break;

	case ANIMAL:
	    acd.add(SurveyIncidentCategory.ANIMAL);
	    acd.add(SurveyIncidentCategory.BACTERIAL);
	    break;

	case VEGETATION:
	    acd.add(SurveyIncidentCategory.BACTERIAL);
	    break;
	case BACTERIAL:
	    acd.add(SurveyIncidentCategory.BACTERIAL);
	    break;
	case NONE:
	    // Just wanted to get rid of the warning without suppress. I guess
	    // this is cheating.
	    break;
	}

	return acd;
    }
}
