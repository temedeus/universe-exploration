/**
 * 
 */
package com.universe.exploration.ueui.forms;

import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * @author 26.10.2015 Teemu Puurunen 
 *
 */
public class PlanetSurveyForm extends FormContainer {

	private Slider crewmenCount;

	private PlanetCelestialComponent planet;
	
	/**
	 * @return the planet
	 */
	public PlanetCelestialComponent getPlanet() {
		return planet;
	}

	/**
	 * @param planet the planet to set
	 */
	public void setPlanet(PlanetCelestialComponent planet) {
		this.planet = planet;
	}

	/**
	 * @return the crewmenCount
	 */
	public Slider getCrewmenCount() {
		return crewmenCount;
	}

	/**
	 * @param crewmenCount the crewmenCount to set
	 */
	public void setCrewmenCount(Slider crewmenCount) {
		this.crewmenCount = crewmenCount;
	}
}
