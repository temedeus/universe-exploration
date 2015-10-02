/**
 * 
 */
package com.universe.exploration.ueui.data;

import com.universe.exploration.common.tools.MathTools;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * @author 1.10.2015 Teemu Puurunen 
 *
 */
public class PlanetSurvey extends DataAndValueContainer {
	public PlanetSurvey(PlanetCelestialComponent celestialComponent) {
		super(celestialComponent);
	}
	
	/* (non-Javadoc)
	 * @see com.universe.exploration.ui.data.DataAndValueContainer#createPairs()
	 */
	@Override
	public void createPairs() {
		// TODO: Abstract generating this data? Maybe create another table abstraction?
		PlanetCelestialComponent cc = (PlanetCelestialComponent) auxiliaryDataContainer; 
		
		add(new DataAndValuePair("", Localizer.get("DESC_PLANET_TYPE"), cc.getComponentName()));
		add(new DataAndValuePair("", Localizer.get("SUBHEADER_ORBITAL_RADIUS"), MathTools.roundedFloatAsStringDefault((float)cc.getOrbitalRadius())));
		add(new DataAndValuePair("", Localizer.get("SUBHEADER_PRESENT_LIFEFORMS"), Localizer.get(cc.getLifeforms().getLocalKey())));

	}


}
