/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.stars;

import com.universe.exploration.celestialcomponents.configuration.ComponentType;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class LargeStar extends ComponentType {

	public LargeStar() {
		componentName = "Large star";
		graphicsFiles = new String[]{"star1.png"};
		spriteSizes = new int[]{4096};
	}
}