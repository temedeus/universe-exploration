/**
 * 
 */
package com.universe.exploration.casualty;

import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * Factory for {@link Casualty}. We need {@link PlanetCelestialComponent} to
 * properly generate the reason for death.
 * 
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

    public Casualty createCasualty(CrewMember crewMember) {
	Casualty casualty = new Casualty();

	ApplicableSurveyIncidentFactory acdf = new ApplicableSurveyIncidentFactory();
	SurveyIncidentRandomPicker codFactory = new SurveyIncidentRandomPicker(acdf.createListofApplicableCauseOfDeath(planet));

	casualty.setCauseOfDeath(codFactory.pickRandomCauseOfDeath());
	casualty.setMember(crewMember);

	return casualty;
    }
}
