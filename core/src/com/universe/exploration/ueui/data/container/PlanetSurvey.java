/**
 * 
 */
package com.universe.exploration.ueui.data.container;

import com.universe.exploration.common.tools.MathTools;
import com.universe.exploration.common.tools.Units;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.ueui.data.DataPair;

/**
 * @author 1.10.2015 Teemu Puurunen 
 *
 */
public class PlanetSurvey extends DataPairContainer {
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
		
		String water = (cc.isWaterFound()) ? "Liquid water" : "None";
		String food = (cc.isFoodFound()) ? "GENERAL_YES" : "GENERAL_NO";
		String oxygen = (cc.isOxygenFound()) ? "GENERAL_YES" : "GENERAL_NO";
		
		float orbitalRadius = ((float)cc.getOrbitalRadius() / 1000);
		String orbitalRadiusToString = MathTools.roundedFloatAsStringDefault(orbitalRadius) + " " + Units.AU.getUnit();
		
		add(new DataPair("", Localizer.get("DESC_PLANET_TYPE"), cc.getComponentName()));
		add(new DataPair("", Localizer.get("SUBHEADER_ORBITAL_RADIUS"), orbitalRadiusToString));
		add(new DataPair("", Localizer.get("SUBHEADER_PRESENT_LIFEFORMS"), Localizer.get(cc.getLifeforms().getLocalKey())));
		add(new DataPair("", Localizer.get("SUBHEADER_WATER_FOUND"), Localizer.get(water)));
		add(new DataPair("", Localizer.get("SUBHEADER_FOOD_FOUND"), Localizer.get(food)));
		add(new DataPair("", Localizer.get("SUBHEADER_OXYGEN_PRESENT"), Localizer.get(oxygen)));
	}


}
