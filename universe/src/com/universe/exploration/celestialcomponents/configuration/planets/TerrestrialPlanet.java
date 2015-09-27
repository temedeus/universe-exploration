/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.PlanetComponent;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class TerrestrialPlanet extends PlanetComponent {

	public TerrestrialPlanet() {
		componentName = "Red mineral planet";
		graphicsFiles = new String[]{"planet2.png"};
		spriteSizes = new int[]{32, 64, 70};
		
		chanceForCivilization = 7;
		chanceForBacterial = 20;
		chanceForAnimalLife = 1;
		chanceToExtractWater = 40f;
		chanceToExtractOxygen = 30f;
		chanceToFindFood = 10;
		
	}
}
