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
public class MediumStar extends StarComponent {
	/**
	 * 
	 */
	public MediumStar() {
		componentName = "Medium star";
		graphicsFiles = new String[]{"star2.png"};
		spriteSizes = new int[]{4096};
	}
}
