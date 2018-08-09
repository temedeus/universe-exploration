/**
 *
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.PlanetConfiguration;

import java.util.ArrayList;

/**
 * Interior terrestrial planet template.
 *
 * @author 14.1.2017 Teemu Puurunen
 */
public class InteriorTerrestrialPlanet extends PlanetConfiguration {
    @Override
    public void setup() {
        templateName = "Interior terrestrial planet";
        graphicsFiles = new ArrayList<>();
        graphicsFiles.add("planet1.png");
        spriteSizes.add(100);
        spriteSizes.add(120);
        spriteSizes.add(140);

        chanceForCivilization = 0;
        chanceForBacterial = 1;
        chanceForAnimalLife = 0;
        chanceToExtractWater = 5f;
        chanceToExtractOxygen = 6f;
        chanceToFindFood = 0;
        chanceForVegetation = 0;
    }
}
