/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.PlanetComponent;

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
		
		chanceToExtractWater = 5f;
		
		chanceToExtractOxygen = 6f;
		
		chanceToFindFood = 0;
	}
}
