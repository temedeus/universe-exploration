/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration.stars;

import com.universe.exploration.celestialcomponents.configuration.StarTemplate;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class MediumStar extends StarTemplate {
	/**
	 * 
	 */
	public MediumStar() {
		templateName = "Medium star";
		graphicsFiles = new String[]{"star2.png"};
		spriteSizes = new int[]{4096};
	}
}
