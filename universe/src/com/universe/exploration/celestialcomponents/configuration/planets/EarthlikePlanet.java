/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.planets;

import com.universe.exploration.celestialcomponents.configuration.ComponentType;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class EarthlikePlanet extends ComponentType {
	public EarthlikePlanet() {
		componentName = "Earthlike planet";
		graphicsFiles = new String[]{"planet5.png"};
		spriteSizes = new int[]{25, 32, 64};
	}
}
