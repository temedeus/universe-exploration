/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.stars;

import com.universe.exploration.celestialcomponents.configuration.StarTemplate;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class SmallStar extends StarTemplate {
	public SmallStar() {
		templateName = "Small star";
		graphicsFiles = new String[]{"star3.png"};
		spriteSizes = new int[]{4096};
	}
}
