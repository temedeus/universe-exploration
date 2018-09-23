/**
 *
 */
package com.universe.exploration.userinterface.data.container;

import com.universe.exploration.common.tools.Units;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.resource.Air;
import com.universe.exploration.resource.Food;
import com.universe.exploration.resource.Water;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * Contains information regarding a planet.
 *
 * @author 1.10.2015 Teemu Puurunen
 */
public class PlanetInformation extends DataPairContainer {
    public PlanetInformation(PlanetCelestialComponent celestialComponent) {
        super(celestialComponent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.universe.exploration.ui.data.DataAndValueContainer#createPairs()
     */
    @Override
    public void createPairs() {
        PlanetCelestialComponent cc = (PlanetCelestialComponent) auxiliaryDataContainer;

        String waterFound = (cc.containsInstanceOfResource(Water.class)) ? "GENERIC_LIQUID_WATER" : "GENERAL_NONE";
        String foodFound = (cc.containsInstanceOfResource(Food.class)) ? "GENERAL_YES" : "GENERAL_NO";
        String oxygenFound = (cc.containsInstanceOfResource(Air.class)) ? "GENERAL_YES" : "GENERAL_NO";

        float orbitalRadius = ((float) cc.getOrbitalRadius() / 1000);
        String orbitalRadiusToString = String.valueOf(Math.round(orbitalRadius)) + " " + Units.AU.getUnit();

        addNoId(Localizer.getInstance().get("DESC_PLANET_TYPE"), cc.getComponentName());
        addNoId(Localizer.getInstance().get("SUBHEADER_ORBITAL_RADIUS"), orbitalRadiusToString);
        addNoId(Localizer.getInstance().get("SUBHEADER_PRESENT_LIFEFORMS"), Localizer.getInstance().get(cc.getLifeforms().getLocalKey()));
        addNoId(Localizer.getInstance().get("SUBHEADER_WATER_FOUND"), Localizer.getInstance().get(waterFound));
        addNoId(Localizer.getInstance().get("SUBHEADER_FOOD_FOUND"), Localizer.getInstance().get(foodFound));
        addNoId(Localizer.getInstance().get("SUBHEADER_OXYGEN_PRESENT"), Localizer.getInstance().get(oxygenFound));
    }

}
