/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.PlanetConfiguration;

/**
 * @author 24.8.2015 Teemu Puurunen
 *
 */
public class ColdRockyPlanet extends PlanetConfiguration {
    public ColdRockyPlanet() {
	templateName = "Cold rocky planet";
	graphicsFiles = new String[] { "planet4.png" };
	spriteSizes = new int[] { 4, 8, 10 };

	chanceForCivilization = 0;

	chanceForBacterial = 1f;

	chanceForAnimalLife = 0;

	chanceToExtractWater = 15f;

	chanceToExtractOxygen = 10f;

	chanceToFindFood = 0;

	chanceForVegetation = 0;
    }
}
