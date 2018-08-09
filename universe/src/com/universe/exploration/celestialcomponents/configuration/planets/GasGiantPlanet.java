/**
 *
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.PlanetConfiguration;

/**
 * @author 24.8.2015 Teemu Puurunen
 */
public class GasGiantPlanet extends PlanetConfiguration {
    @Override
    public void setup() {
        templateName = "Giant gas planet";

        graphicsFiles.add("gasplanet/planet3.png");
        graphicsFiles.add("gasplanet/gasplanet1.png");
        spriteSizes.add(400);
        spriteSizes.add(500);
        spriteSizes.add(550);

        chanceForCivilization = 0;

        chanceForBacterial = 0;

        chanceForAnimalLife = 0;

        chanceToExtractWater = 10f;

        chanceToExtractOxygen = 40f;

        chanceToFindFood = 0;
        chanceForVegetation = 0;
    }
}
