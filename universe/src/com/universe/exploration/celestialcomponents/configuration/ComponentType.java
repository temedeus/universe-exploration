/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration;

import com.universe.exploration.common.tools.RandomizationTools;

/**
 * @author 24.8.2015 Teemu Puurunen 
 *
 */
public class ComponentType {
	protected String componentName;

	protected String[] graphicsFiles;
	
	protected int[] spriteSizes;
	
	public String getRandomGraphicsFile() {
		int x = RandomizationTools.getRandomInteger(0, graphicsFiles.length-1);
		return graphicsFiles[x];
	}
	
	public int getRandomSpriteSize() {
		int x = RandomizationTools.getRandomInteger(0, spriteSizes.length-1);
		return spriteSizes[x];
	}
	
	/**
	 * @return the componentName
	 */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * @param componentName the componentName to set
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
}
