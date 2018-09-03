/**
 *
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.PlanetTemplate;

/**
 * @author 24.8.2015 Teemu Puurunen
 */
public class ColdRockyPlanet extends PlanetTemplate {
    @Override
    public void setup() {
        templateName = "Cold rocky planet";

        graphicsFiles.add("planet4.png");
        spriteSizes.add(40);
        spriteSizes.add(80);
        spriteSizes.add(100);

        chanceForCivilization = 0;

        chanceForBacterial = 1f;

        chanceForAnimalLife = 0;

        chanceToExtractWater = 15f;

        chanceToExtractOxygen = 10f;

        chanceToFindFood = 0;

        chanceForVegetation = 0;
    }
}
