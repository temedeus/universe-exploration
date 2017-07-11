/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.stars;

import com.universe.exploration.celestialcomponents.configuration.StarConfiguration;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class LargeStar extends StarConfiguration {

	public LargeStar() {
		templateName = "Large star";
		graphicsFiles = new String[]{"star1.png"};
		spriteSizes = new int[]{4096};
	}
}
