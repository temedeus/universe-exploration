/**
 *
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.PlanetConfiguration;

/**
 * @author 24.8.2015 Teemu Puurunen
 */
public class ExteriorTerrestrialPlanet extends PlanetConfiguration {
    @Override
    public void setup() {
        templateName = "Exterior terrestrial planet";

        graphicsFiles.add("planet2.png");
        spriteSizes.add(80);
        spriteSizes.add(100);
        spriteSizes.add(120);

        chanceForCivilization = 7;
        chanceForBacterial = 20;
        chanceForAnimalLife = 1;
        chanceToExtractWater = 40f;
        chanceToExtractOxygen = 30f;
        chanceToFindFood = 10;
        chanceForVegetation = 10;
    }
}
