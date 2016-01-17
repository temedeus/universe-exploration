/**
 * 
 */
package com.universe.exploration.mortality;

import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * @author 25.10.2015 Teemu Puurunen 
 *
 */
public class MortalityFactory {

	private PlanetCelestialComponent planet;
	
	/**
	 * 
	 */
	public MortalityFactory(PlanetCelestialComponent planet) {
		this.planet = planet;
	}
	
	public Mortality makeMortality() {
		Mortality mortality = new Mortality();
		
		ApplicableCauseOfDeathFactory acdf = new ApplicableCauseOfDeathFactory();
		CauseOfDeathRandomPicker codFactory = new CauseOfDeathRandomPicker(acdf.createListofApplicableCauseOfDeath(planet));
		
		mortality.setCauseOfDeath(codFactory.pickRandomCauseOfDeath());
		mortality.setCrewmenID(1); // TODO: implement individual crewmen
		
		return mortality;
	}
}
