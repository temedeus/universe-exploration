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
public class GasGiantPlanet extends PlanetComponent {

	
	public GasGiantPlanet() {
		componentName = "Giant gas planet";
		graphicsFiles = new String[]{"planet3.png"};
		spriteSizes = new int[]{80, 100, 128, 160};
		
		chanceForCivilization = 0;
		
		chanceForBacterial = 0;
		
		chanceForAnimalLife = 0;
		
		chanceToExtractWater = 10f;
		
		chanceToExtractOxygen = 40f;
		
		chanceToFindFood = 0;
	}
}
