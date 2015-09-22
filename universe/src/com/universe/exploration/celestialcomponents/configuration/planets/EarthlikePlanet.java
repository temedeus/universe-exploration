/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.ComponentType;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class EarthlikePlanet extends PlanetComponent {

	
	public EarthlikePlanet() {
		componentName = "Earthlike planet";
		graphicsFiles = new String[]{"planet5.png"};
		spriteSizes = new int[]{25, 32, 64};
		
		chanceForCivilization = 20;
		
		chanceForBacterial = 80;
		
		chanceForAnimalLife = 50;
		
		CHANCE_TO_EXTRACT_WATER = 90f;
		
		CHANCE_TO_EXTRACT_OXYGEN = 95f;
		
		CHANCE_TO_FIND_FOOD = 85;
	}
}
