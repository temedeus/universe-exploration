/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.PlanetComponent;

/**
 * @author 24.8.2015 Teemu Puurunen
 *
 */
public class InteriorTerrestrialPlanet extends PlanetComponent {
    public InteriorTerrestrialPlanet() {
	componentName = "Interior terrestrial planet";
	graphicsFiles = new String[] { "planet1.png" };
	spriteSizes = new int[] { 10, 12, 14 };

	chanceForCivilization = 0;
	chanceForBacterial = 1;
	chanceForAnimalLife = 0;
	chanceToExtractWater = 5f;
	chanceToExtractOxygen = 6f;
	chanceToFindFood = 0;
	chanceForVegetation = 0;
    }
}
