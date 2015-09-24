/**
 * 
 */
package com.universe.exploration.common.tools;

import java.text.DecimalFormat;

import javax.annotation.processing.RoundEnvironment;

/**
 * @author 8.7.2015 Teemu Puurunen 
 *
 */
public class MathTools {
	/**
	 * Check if val is between max and min (inclusively).
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
	 * @param val Value to decrease from
	 * @param dec Amount of decrement
	 * @return float result
	 */
	public static float decreaseIfResultPositive(float val, float dec) {
		return (val - dec >= 0) ? val - dec : 0;
	}
	
	/**
	 * Calculate if odds hit according to given percentage
	 * @param percentage
	 * @return
	 */
	public static boolean calculateIfOddsHit(float percentage) {
		return ((float)Math.random() < percentage) ? true : false;
	}
	
	public static String roundedFloatAsStringDefault(float f) {
		return roundedFloatAsString(f, 3);
	}
	
	public static String roundedFloatAsString(float f, int precision) {
		DecimalFormat df = new DecimalFormat("#.#####");
		return df.format(f);
	}
}
