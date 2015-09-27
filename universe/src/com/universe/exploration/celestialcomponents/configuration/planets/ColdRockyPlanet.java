/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.ComponentType;
import com.universe.exploration.celestialcomponents.configuration.PlanetComponent;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class ColdRockyPlanet extends PlanetComponent{
	public ColdRockyPlanet() {
		componentName = "Cold rocky planet";
		graphicsFiles = new String[]{"planet4.png"};
		spriteSizes = new int[]{16, 32, 50};
		
		chanceForCivilization = 0;
		
		chanceForBacterial = 1f;
		
		chanceForAnimalLife = 0;
		
		chanceToExtractWater = 15f;
		
		chanceToExtractOxygen = 10f;
		
		chanceToFindFood = 0;
	}
}
