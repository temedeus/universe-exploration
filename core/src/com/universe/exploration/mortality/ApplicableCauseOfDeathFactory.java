/**
 * 
 */
package com.universe.exploration.mortality;

import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * @author 30.10.2015 Teemu Puurunen 
 *
 */
public class ApplicableCauseOfDeathFactory {

	public ApplicableCauseOfDeathCategories createListofApplicableCauseOfDeath(PlanetCelestialComponent planet) {
		ApplicableCauseOfDeathCategories acd = new ApplicableCauseOfDeathCategories();
		
		acd.add(CauseOfDeathCategory.GENERAL);
		
		if(!planet.isOxygenFound()) {
			acd.add(CauseOfDeathCategory.LACK_OF_OXYGEN);
		}
		
		switch(planet.getLifeforms()) {
			case CIVILIZED:
				acd.add(CauseOfDeathCategory.CIVILIZATION);
				break;
				
			case ANIMAL:
				acd.add(CauseOfDeathCategory.ANIMAL);
				break;
				
			case VEGETATION:
				acd.add(CauseOfDeathCategory.BACTERIAL);
				break;
			case BACTERIAL:
				acd.add(CauseOfDeathCategory.BACTERIAL);
				break;
			case NONE:
				// Just wanted to get rid of the warning without suppress. I guess this is cheating.
				break;
		}
		
		return acd;
	}
}
