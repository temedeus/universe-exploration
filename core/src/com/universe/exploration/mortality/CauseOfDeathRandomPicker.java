/**
 * 
 */
package com.universe.exploration.mortality;

import java.util.ArrayList;

import com.universe.exploration.common.tools.RandomizationTools;

/**
 * <p>
 * Use this picker to randomly choose {@link CauseOfDeath} from applicable list
 * of causes of death.
 * </p>
 * 
 * @author 30.10.2015 Teemu Puurunen
 *
 */
public class CauseOfDeathRandomPicker {

    private ArrayList<CauseOfDeath> listOfApplicableCauseOfDeaths;

    CauseOfDeathRandomPicker(ApplicableCauseOfDeathCategories applicableCauseOfDeath) {
	listOfApplicableCauseOfDeaths = new ArrayList<CauseOfDeath>();

	for (CauseOfDeath causeOfDeath : CauseOfDeath.values()) {
	    CauseOfDeathCategory cdc = causeOfDeath.getCauseOfDeathCategory();
	    if (applicableCauseOfDeath.contains(cdc)) {
		listOfApplicableCauseOfDeaths.add(causeOfDeath);
	    }
	}
    }

    public CauseOfDeath pickRandomCauseOfDeath() {
	int index = RandomizationTools.getRandomInteger(0, listOfApplicableCauseOfDeaths.size() - 1);
	return listOfApplicableCauseOfDeaths.get(index);
    }
}
