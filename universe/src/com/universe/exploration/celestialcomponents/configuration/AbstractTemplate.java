/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration;

import com.universe.exploration.common.tools.RandomizationTools;

/**
 * @author 24.8.2015 Teemu Puurunen
 *
 */
public abstract class AbstractTemplate {

    /**
     * Identifier for component so that we can refer to it from UI-side.
     */
    protected String ComponentID;

    /**
     * @return the componentID
     */
    public String getComponentID() {
	return ComponentID;
    }

    /**
     * @param componentID
     *            the componentID to set
     */
    public void setComponentID(String componentID) {
	ComponentID = componentID;
    }

    protected String templateName;

    protected String[] graphicsFiles;

    protected int[] spriteSizes;

    public String getRandomGraphicsFile() {
	int x = RandomizationTools.getRandomInteger(0, graphicsFiles.length - 1);
	return graphicsFiles[x];
    }

    public int getRandomSpriteSize() {
	int x = RandomizationTools.getRandomInteger(0, spriteSizes.length - 1);
	return spriteSizes[x];
    }

    /**
     * @return the componentName
     */
    public String getComponentName() {
	return templateName;
    }

    /**
     * @param componentName
     *            the componentName to set
     */
    public void setComponentName(String componentName) {
	this.templateName = componentName;
    }
}