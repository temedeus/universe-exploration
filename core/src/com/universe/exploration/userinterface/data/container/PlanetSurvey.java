/**
 * 
 */
package com.universe.exploration.userinterface.data.container;

import com.universe.exploration.common.tools.MathTools;
import com.universe.exploration.common.tools.Units;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * @author 1.10.2015 Teemu Puurunen
 *
 */
public class PlanetSurvey extends DataPairContainer {
    public PlanetSurvey(PlanetCelestialComponent celestialComponent) {
	super(celestialComponent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.universe.exploration.ui.data.DataAndValueContainer#createPairs()
     */
    @Override
    public void createPairs() {
	// TODO: Abstracize generating this data? Maybe create another table
	// abstraction?
	PlanetCelestialComponent cc = (PlanetCelestialComponent) auxiliaryDataContainer;

	String waterFound = (cc.isWaterFound()) ? "GENERIC_LIQUID_WATER" : "GENERAL_NONE";
	String foodFound = (cc.isFoodFound()) ? "GENERAL_YES" : "GENERAL_NO";
	String oxygenFound = (cc.isOxygenFound()) ? "GENERAL_YES" : "GENERAL_NO";

	float orbitalRadius = ((float) cc.getOrbitalRadius() / 1000);
	String orbitalRadiusToString = MathTools.roundedFloatAsString(orbitalRadius) + " " + Units.AU.getUnit();

	add("", Localizer.getInstance().get("DESC_PLANET_TYPE"), cc.getComponentName());
	add("", Localizer.getInstance().get("SUBHEADER_ORBITAL_RADIUS"), orbitalRadiusToString);
	add("", Localizer.getInstance().get("SUBHEADER_PRESENT_LIFEFORMS"), Localizer.getInstance().get(cc.getLifeforms().getLocalKey()));
	add("", Localizer.getInstance().get("SUBHEADER_WATER_FOUND"), Localizer.getInstance().get(waterFound));
	add("", Localizer.getInstance().get("SUBHEADER_FOOD_FOUND"), Localizer.getInstance().get(foodFound));
	add("", Localizer.getInstance().get("SUBHEADER_OXYGEN_PRESENT"), Localizer.getInstance().get(oxygenFound));
    }

}
