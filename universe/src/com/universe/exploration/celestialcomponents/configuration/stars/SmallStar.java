/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.stars;

import com.universe.exploration.celestialcomponents.configuration.StarConfiguration;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class SmallStar extends StarConfiguration {
	public SmallStar() {
		templateName = "Small star";
		graphicsFiles = new String[]{"star3.png"};
		spriteSizes = new int[]{4096};
	}
}
