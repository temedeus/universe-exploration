/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.ComponentType;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class RedMineralPlanet extends PlanetComponent {

	public RedMineralPlanet() {
		componentName = "Red mineral planet";
		graphicsFiles = new String[]{"planet2.png"};
		spriteSizes = new int[]{32, 64, 70};
		
		chanceForCivilization = 7;
		
		chanceForBacterial = 20;
		
		chanceForAnimalLife = 1;
		
		CHANCE_TO_EXTRACT_WATER = 40f;
		
		CHANCE_TO_EXTRACT_OXYGEN = 30f;
		
		CHANCE_TO_FIND_FOOD = 10;
		
	}
}
