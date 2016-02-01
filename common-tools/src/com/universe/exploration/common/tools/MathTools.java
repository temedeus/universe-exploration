/**
 * 
 */
package com.universe.exploration.common.tools;

/**
 * <p>
 * Semi-generic math tools for all-around use in the game.
 * </p>
 * 
 * @author 8.7.2015 Teemu Puurunen
 *
 */
public class MathTools {
    /**
     * Check if val is between max and min (inclusively).
     * 
     * @param val
     * @param max
     * @param min
     * @return
     */
    public static boolean betweenIntRangeInclusively(int val, int max, int min) {
	return (val >= min && val <= max) ? true : false;
    }

    /**
     * Check if val is between max and min (exclusively).
     * 
     * @param val
     * @param max
     * @param min
     * @return
     */
    public static boolean betweenIntRangeExclusively(int val, int max, int min) {
	return (val > min && val < max) ? true : false;
    }

    /**
     * Check if val is between max and min (exclusively).
     * 
     * @param val
     * @param max
     * @param min
     * @return
     */
    public static boolean betweenFloatRangeExclusively(float val, float max, float min) {
	return (val > min && val < max) ? true : false;
    }

    /**
     * Check if val is between max and min (inclusively).
     * 
     * @param val
     * @param max
     * @param min
     * @return
     */
    public static boolean betweenFloatRangeInclusively(float val, float max, float min) {
	return (val >= min && val <= max) ? true : false;
    }

    /**
     * Decrease value if result above zero. Otherwise return zero.
     * 
     * @param val
     *            Value to decrease from
     * @param dec
     *            Amount of decrement
     * @return float result
     */
    public static float decreaseIfResultPositive(float val, float dec) {
	return (val - dec >= 0) ? val - dec : 0;
    }

    /**
     * Calculate if odds hit according to given percentage
     * 
     * @param percentage
     * @return
     */
    public static boolean calculateIfOddsHit(float percentage) {
	return ((float) Math.random() < (percentage / 100)) ? true : false;
    }

    public static String roundedFloatAsString(float f) {
	return roundedFloatAsStringByGivenPrecision(f, 3);
    }

    public static String roundedFloatAsStringByGivenPrecision(float f, int precision) {
	return "" + Math.round(f);
    }

    /**
     * Generates a random angle as double (0-360 degrees).
     * 
     * @return
     */
    public static double generateRandomAngle() {
	return RandomizationTools.getRandomDouble(0, 360);
    }

    /**
     * Calculate how much planetary space an individual planet may have to be
     * positioned into.
     * 
     * @param planetCount
     * @return
     */
    public static double calculatePlanetarySpace(int planetCount) {
	return (IngameAstronomicalConstants.MAX_ORBITAL_RADIUS.getValue() - IngameAstronomicalConstants.MIN_ORBITAL_RADIUS.getValue())
		/ (planetCount);
    }
}
