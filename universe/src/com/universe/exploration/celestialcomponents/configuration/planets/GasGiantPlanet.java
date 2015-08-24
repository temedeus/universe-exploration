/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.ComponentType;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class GasGiantPlanet extends ComponentType {
	public GasGiantPlanet() {
		componentName = "Giant gas planet";
		graphicsFiles = new String[]{"planet3.png"};
		spriteSizes = new int[]{80, 100, 128, 160};
	}
}
