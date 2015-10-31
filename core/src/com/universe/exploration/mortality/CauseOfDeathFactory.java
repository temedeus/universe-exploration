/**
 * 
 */
package com.universe.exploration.mortality;

import java.util.ArrayList;

import com.universe.exploration.common.tools.RandomizationTools;

/**
 * @author 30.10.2015 Teemu Puurunen 
 *
 */
public class CauseOfDeathFactory {

	
	private ArrayList<CauseOfDeath> listOfApplicableCauseOfDeaths;
	
	CauseOfDeathFactory(ApplicableCauseOfDeathCategories applicableCauseOfDeath) {
		listOfApplicableCauseOfDeaths = new ArrayList<CauseOfDeath>();
		
		for(CauseOfDeath causeOfDeath : CauseOfDeath.values()) {
			CauseOfDeathCategory cdc = causeOfDeath.getCauseOfDeathCategory();
			if(applicableCauseOfDeath.contains(cdc)) {
				listOfApplicableCauseOfDeaths.add(causeOfDeath);
			}
		}
	}
	
	public CauseOfDeath createRandomCauseOfDeath() {
		int index = RandomizationTools.getRandomInteger(0, listOfApplicableCauseOfDeaths.size() - 1);
		return listOfApplicableCauseOfDeaths.get(index);
	}
}
