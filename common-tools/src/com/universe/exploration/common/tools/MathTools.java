/**
 * 
 */
package com.universe.exploration.common.tools;

/**
 * @author 8.7.2015 Teemu Puurunen 
 *
 */
public class MathTools {
	/**
	 * Check if val is betwee max and min (inclusively).
	 * @param val
	 * @param max
	 * @param min
	 * @return
	 */
	public static boolean betweenIntRangeInclusively(int val, int max, int min) {
		if(val >= min && val <= max) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Check if val is betwee max and min (exclusively).
	 * @param val
	 * @param max
	 * @param min
	 * @return
	 */
	public static boolean betweenIntRangeExclusively(int val, int max, int min) {
		if(val > min && val < max) {
			return true;
		} else {
			return false;
		}
	}
}
