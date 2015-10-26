/**
 * 
 */
package com.universe.exploration.ueui.forms;

import com.badlogic.gdx.scenes.scene2d.ui.Slider;

/**
 * @author 26.10.2015 Teemu Puurunen 
 *
 */
public class PlanetSurveyForm extends FormContainer {

	private Slider crewmenCount;

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
