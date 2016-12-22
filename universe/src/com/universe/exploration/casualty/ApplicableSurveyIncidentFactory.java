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
	ApplicableSurveyIncidentCategoryList applicableSurveyIncidents = new ApplicableSurveyIncidentCategoryList();

	applicableSurveyIncidents.add(SurveyIncidentCategory.GENERAL);

	if (!planet.isOxygenFound()) {
	    applicableSurveyIncidents.add(SurveyIncidentCategory.LACK_OF_OXYGEN);
	}

	switch (planet.getLifeforms()) {
	case CIVILIZED:
	    applicableSurveyIncidents.add(SurveyIncidentCategory.CIVILIZATION);
	    applicableSurveyIncidents.add(SurveyIncidentCategory.BACTERIAL);
	    applicableSurveyIncidents.add(SurveyIncidentCategory.ANIMAL);
	    break;

	case ANIMAL:
	    applicableSurveyIncidents.add(SurveyIncidentCategory.ANIMAL);
	    applicableSurveyIncidents.add(SurveyIncidentCategory.BACTERIAL);
	    break;

	case VEGETATION:
	    applicableSurveyIncidents.add(SurveyIncidentCategory.BACTERIAL);
	    break;
	case BACTERIAL:
	    applicableSurveyIncidents.add(SurveyIncidentCategory.BACTERIAL);
	    break;
	case NONE:
	    // Just wanted to get rid of the warning without suppress. I guess
	    // this is cheating.
	    break;
	}

	return applicableSurveyIncidents;
    }
}
