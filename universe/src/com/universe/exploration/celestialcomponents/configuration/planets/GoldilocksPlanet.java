/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.PlanetComponent;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class GoldilocksPlanet extends PlanetComponent {

	public GoldilocksPlanet() {
		componentName = "Goldilocks planet";
		graphicsFiles = new String[]{"planet5.png"};
		spriteSizes = new int[]{25, 32, 64};
		
		chanceForCivilization = 20;
		chanceForBacterial = 80;
		chanceForAnimalLife = 50;
		chanceToExtractWater = 90f;
		chanceToExtractOxygen = 95f;
		chanceToFindFood = 85;
		chanceForVegetation = 95;
	}
}
