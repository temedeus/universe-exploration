/**
 * 
 */
package com.universe.exploration.mortality;

import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * Factory for {@link Casualty}. We need {@link PlanetCelestialComponent} to properly generate the reason for death.
 * @author 25.10.2015 Teemu Puurunen
 *
 */
public class CasualtyFactory {

    private PlanetCelestialComponent planet;

    /**
	 * 
	 */
    public CasualtyFactory(PlanetCelestialComponent planet) {
	this.planet = planet;
    }

    public Casualty createCasualty() {
	Casualty casualty = new Casualty();

	ApplicableCauseOfDeathFactory acdf = new ApplicableCauseOfDeathFactory();
	CauseOfDeathRandomPicker codFactory = new CauseOfDeathRandomPicker(acdf.createListofApplicableCauseOfDeath(planet));

	casualty.setCauseOfDeath(codFactory.pickRandomCauseOfDeath());
	casualty.setCrewmenID(1); // TODO: implement individual crewmen

	return casualty;
    }
}
