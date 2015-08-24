/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.ComponentType;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class RedMineralPlanet extends ComponentType {
	public RedMineralPlanet() {
		componentName = "Red mineral planet";
		graphicsFiles = new String[]{"planet2.png"};
		spriteSizes = new int[]{32, 64, 70};
	}
}
