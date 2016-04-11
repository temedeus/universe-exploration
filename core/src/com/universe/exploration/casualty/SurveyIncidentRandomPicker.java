/**
 * 
 */
package com.universe.exploration.casualty;

import java.util.ArrayList;

import com.universe.exploration.common.tools.RandomizationTools;

/**
 * <p>
 * Use this picker to randomly choose {@link SurveyIncident} from applicable list
 * of causes of death.
 * </p>
 * 
 * @author 30.10.2015 Teemu Puurunen
 *
 */
class SurveyIncidentRandomPicker {

    private ArrayList<SurveyIncident> listOfApplicableCauseOfDeaths;

    SurveyIncidentRandomPicker(ApplicableSurveyIncidentCategoryList applicableCauseOfDeath) {
	listOfApplicableCauseOfDeaths = new ArrayList<SurveyIncident>();

	for (SurveyIncident causeOfDeath : SurveyIncident.values()) {
	    SurveyIncidentCategory cdc = causeOfDeath.getCauseOfDeathCategory();
	    if (applicableCauseOfDeath.contains(cdc)) {
		listOfApplicableCauseOfDeaths.add(causeOfDeath);
	    }
	}
    }

    public SurveyIncident pickRandomCauseOfDeath() {
	int index = RandomizationTools.getRandomInteger(0, listOfApplicableCauseOfDeaths.size() - 1);
	return listOfApplicableCauseOfDeaths.get(index);
    }
}
