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
 * <p
 * 
 * @author 30.10.2015 Teemu Puurunen
 *
 */
class ApplicableSurveyIncidentFactory {

    /**
     * 
     * 
     * @param planet
     * @return
     */
    protected ApplicableSurveyIncidentCategoryList createListofApplicableCauseOfDeath(PlanetCelestialComponent planet) {
	ApplicableSurveyIncidentCategoryList applicableSurveyIncidents = new ApplicableSurveyIncidentCategoryList();

	applicableSurveyIncidents.add(SurveyIncidentCategory.GENERAL);

	if (!planet.isOxygenFound()) {
	    applicableSurveyIncidents.add(SurveyIncidentCategory.LACK_OF_OXYGEN);
	}

	applicableSurveyIncidents.addAll(planet.getLifeforms().provideSurveyIncidentCategoryList());

	return applicableSurveyIncidents;
    }
}
