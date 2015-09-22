/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.ComponentType;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class AcidRainPlanet extends PlanetComponent{
	public AcidRainPlanet() {
		componentName = "Acid rain planet";
		graphicsFiles = new String[]{"planet1.png"};
		spriteSizes = new int[]{32, 64, 70};
		
		chanceForCivilization = 0;
		
		chanceForBacterial = 0;
		
		chanceForAnimalLife = 0;
		
		CHANCE_TO_EXTRACT_WATER = 5f;
		
		CHANCE_TO_EXTRACT_OXYGEN = 6f;
		
		CHANCE_TO_FIND_FOOD = 0;
	}
}
