/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.ComponentType;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class ColdRockyPlanet extends ComponentType {
	public ColdRockyPlanet() {
		componentName = "Cold rocky planet";
		graphicsFiles = new String[]{"planet4.png"};
		spriteSizes = new int[]{16, 32, 50};
	}
}
