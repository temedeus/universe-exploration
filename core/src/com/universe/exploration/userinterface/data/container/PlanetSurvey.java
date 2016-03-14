/**
 * 
 */
package com.universe.exploration.userinterface.data.container;

import com.universe.exploration.common.tools.MathTools;
import com.universe.exploration.common.tools.Units;
import com.universe.exploration.localization.LocalKey;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.userinterface.data.DataPair;

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

	LocalKey waterFound = (cc.isWaterFound()) ? LocalKey.GENERIC_LIQUID_WATER : LocalKey.GENERAL_NONE;
	LocalKey foodFound = (cc.isFoodFound()) ? LocalKey.GENERAL_YES : LocalKey.GENERAL_NO;
	LocalKey oxygenFound = (cc.isOxygenFound()) ? LocalKey.GENERAL_YES : LocalKey.GENERAL_NO;

	float orbitalRadius = ((float) cc.getOrbitalRadius() / 1000);
	String orbitalRadiusToString = MathTools.roundedFloatAsString(orbitalRadius) + " " + Units.AU.getUnit();

	add(new DataPair("", Localizer.get(LocalKey.DESC_PLANET_TYPE), cc.getComponentName()));
	add(new DataPair("", Localizer.get(LocalKey.SUBHEADER_ORBITAL_RADIUS), orbitalRadiusToString));
	add(new DataPair("", Localizer.get(LocalKey.SUBHEADER_PRESENT_LIFEFORMS), Localizer.get(cc.getLifeforms().getLocalKey())));
	add(new DataPair("", Localizer.get(LocalKey.SUBHEADER_WATER_FOUND), Localizer.get(waterFound)));
	add(new DataPair("", Localizer.get(LocalKey.SUBHEADER_FOOD_FOUND), Localizer.get(foodFound)));
	add(new DataPair("", Localizer.get(LocalKey.SUBHEADER_OXYGEN_PRESENT), Localizer.get(oxygenFound)));
    }

}
