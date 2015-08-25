/**
 * 
 */
package com.universe.exploration.localization;

/**
 * @author 25.8.2015 Teemu Puurunen 
 *
 */
public class Localizer {
	/**
	 * Get localization based on given key. Return key if none found.
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		try {
			String result = LocalEn.valueOf(key).getDescription();
			return result;
		} catch(Exception e) {
			return key;
		}
	}
}
