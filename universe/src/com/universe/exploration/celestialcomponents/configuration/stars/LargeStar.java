/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.stars;

import com.universe.exploration.celestialcomponents.configuration.ComponentType;
import com.universe.exploration.celestialcomponents.configuration.StarComponent;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class LargeStar extends StarComponent {

	public LargeStar() {
		componentName = "Large star";
		graphicsFiles = new String[]{"star1.png"};
		spriteSizes = new int[]{4096};
	}
}
