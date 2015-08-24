/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.ComponentType;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class AcidRainPlanet extends ComponentType {
	public AcidRainPlanet() {
		componentName = "Acid rain planet";
		graphicsFiles = new String[]{"planet1.png"};
		spriteSizes = new int[]{32, 64, 70};
	}
}
