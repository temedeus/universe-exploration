/**
 * 
 */
package com.universe.exploration.casualty;

import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * <p>
 * Create possible causes of death. See {@link ApplicableCauseOfDeathCategories}
 * .
 * </p>
 * 
 * @author 30.10.2015 Teemu Puurunen
 *
 */
class ApplicableCauseOfDeathFactory {

    public ApplicableCauseOfDeathCategories createListofApplicableCauseOfDeath(PlanetCelestialComponent planet) {
	ApplicableCauseOfDeathCategories acd = new ApplicableCauseOfDeathCategories();

	acd.add(CauseOfDeathCategory.GENERAL);

	if (!planet.isOxygenFound()) {
	    acd.add(CauseOfDeathCategory.LACK_OF_OXYGEN);
	}

	switch (planet.getLifeforms()) {
	case CIVILIZED:
	    acd.add(CauseOfDeathCategory.CIVILIZATION);
	    acd.add(CauseOfDeathCategory.BACTERIAL);
	    acd.add(CauseOfDeathCategory.ANIMAL);
	    break;

	case ANIMAL:
	    acd.add(CauseOfDeathCategory.ANIMAL);
	    acd.add(CauseOfDeathCategory.BACTERIAL);
	    break;

	case VEGETATION:
	    acd.add(CauseOfDeathCategory.BACTERIAL);
	    break;
	case BACTERIAL:
	    acd.add(CauseOfDeathCategory.BACTERIAL);
	    break;
	case NONE:
	    // Just wanted to get rid of the warning without suppress. I guess
	    // this is cheating.
	    break;
	}

	return acd;
    }
}
