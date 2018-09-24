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
 */
public enum ResourcesFoundBoundary {

    WATER(0, 100), AIR(0, 8), FOOD(0, 8);

    private final float min;
    private final float max;

    ResourcesFoundBoundary(float min, float max) {
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
}
