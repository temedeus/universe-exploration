/**
 * 
 */
package com.universe.exploration.resource;

import com.universe.exploration.survey.Survey;

/**
 * Determines boundaries for potentially found resources on a planet
 * {@link Survey}.
 * 
 * @author 25.10.2015 Teemu Puurunen
 *
 */
public enum ResourcesFoundBoundaries {

    WATER(0, 100), AIR(0, 8), FOOD(0, 8);

    private float min;
    private float max;

    ResourcesFoundBoundaries(float min, float max) {
	this.min = min;
	this.max = max;
    }

    /**
     * @return the min
     */
    public float getMin() {
	return min;
    }

    /**
     * @return the max
     */
    public float getMax() {
	return max;
    }

    /**
     * @param min
     *            the min to set
     */
    public void setMin(float min) {
	this.min = min;
    }

    /**
     * @param max
     *            the max to set
     */
    public void setMax(float max) {
	this.max = max;
    }
}
