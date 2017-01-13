/**
 * 
 */
package com.universe.exploration.casualty;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.common.tools.RandomizationTools;

/**
 * <p>
 * Use this picker to randomly choose {@link SurveyIncident} from applicable
 * list of causes of death.
 * </p>
 * 
 * @author 30.10.2015 Teemu Puurunen
 *
 */
class SurveyIncidentRandomPicker {

    private List<SurveyIncident> listOfApplicableCauseOfDeaths;

    SurveyIncidentRandomPicker(List<SurveyIncidentCategory> applicableCauseOfDeath) {
	listOfApplicableCauseOfDeaths = new ArrayList<SurveyIncident>();

	for (SurveyIncident causeOfDeath : SurveyIncident.values()) {
	    SurveyIncidentCategory categories = causeOfDeath.getCauseOfDeathCategory();
	    if (applicableCauseOfDeath.contains(categories)) {
		listOfApplicableCauseOfDeaths.add(causeOfDeath);
	    }
	}
    }

    public SurveyIncident pickRandomSurveyIncident() {
	int index = RandomizationTools.getRandomInteger(0, listOfApplicableCauseOfDeaths.size() - 1);
	return listOfApplicableCauseOfDeaths.get(index);
    }
}
