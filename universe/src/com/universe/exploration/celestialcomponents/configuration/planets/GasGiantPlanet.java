/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.ComponentType;

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
		
		CHANCE_TO_EXTRACT_WATER = 10f;
		
		CHANCE_TO_EXTRACT_OXYGEN = 40f;
		
		CHANCE_TO_FIND_FOOD = 0;
	}
}
