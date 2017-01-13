/**
 * 
 */
package com.universe.exploration.survey;

import com.universe.exploration.resource.Air;
import com.universe.exploration.resource.Food;
import com.universe.exploration.resource.Resource;
import com.universe.exploration.resource.Water;

/**
 * Determines boundaries for potentially found resources.
 * 
 * @author 25.10.2015 Teemu Puurunen
 *
 */
public enum ResourcesFoundBoundaries {

    WATER(Water.class, 0, 100), AIR(Air.class, 0, 8), FOOD(Food.class, 0, 8);

    private float min;
    private float max;
    private Class<? extends Resource> clazz;

    ResourcesFoundBoundaries(Class<? extends Resource> clazz, float min, float max) {
	this.clazz = clazz;
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

    /**
     * @return the clazz
     */
    public Class<? extends Resource> getClazz() {
        return clazz;
    }

    /**
     * @param clazz the clazz to set
     */
    public void setClazz(Class<? extends Resource> clazz) {
        this.clazz = clazz;
    }
}
