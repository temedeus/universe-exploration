/**
 *
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.PlanetTemplate;

/**
 * @author 24.8.2015 Teemu Puurunen
 */
public class GoldilocksPlanet extends PlanetTemplate {
    @Override
    public void setup() {
        templateName = "Goldilocks planet";
        graphicsFiles.add("planet5.png");
        spriteSizes.add(100);
        spriteSizes.add(120);
        spriteSizes.add(140);

        chanceForCivilization = 20;
        chanceForBacterial = 80;
        chanceForAnimalLife = 50;
        chanceToExtractWater = 90f;
        chanceToExtractOxygen = 95f;
        chanceToFindFood = 85;
        chanceForVegetation = 95;
    }
}
