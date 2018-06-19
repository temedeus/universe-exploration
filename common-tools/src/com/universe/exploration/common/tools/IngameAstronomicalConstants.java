/**
 *
 */
package com.universe.exploration.common.tools;

/**
 * Astronomical constants used in game. Units do not necessarily represent
 * anything real-life, more so they are about in-game functionality.
 *
 * @author 15.1.2017 Teemu Puurunen
 */
public enum IngameAstronomicalConstants {
    APHELION_EARTH(1.0, "AU"),
    PERIPHELION_EARTH(1.0, "AU"),
    MIN_ORBITAL_VELOCITY(0.00005, "Orbital velocity relative to that of Earth"),
    MAX_ORBITAL_VELOCITY(0.0005, "Orbital velocity relative to that of Earth"),
    MIN_ORBITAL_RADIUS(500, "Minimum radius for planet orbit"),
    MAX_ORBITAL_RADIUS(3000, "Maximum radius for planet orbit"),
    MIN_DIFFERENCE_BETWEEN_ADJACENT_PLANET_RADII(100, "Minimum difference between adjacent planet radii.");

    private final double value;
    private final String explanation;

    private IngameAstronomicalConstants(double value, String explanation) {
        this.value = value;
        this.explanation = explanation;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * @return the explanation
     */
    public String getExplanation() {
        return explanation;
    }

}
