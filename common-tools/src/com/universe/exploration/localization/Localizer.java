/**
 * 
 */
package com.universe.exploration.localization;

/**
 * <p>
 * Use this class to get localized strings. You can either use a string or
 * {@link LocalKey}.
 * </p>
 * 
 * @author 25.8.2015 Teemu Puurunen
 *
 */
public class Localizer {
    /**
     * Use string local key. Get localization based on given key. Return key (as string) if none
     * found.
     * 
     * @param key
     * @return
     */
    public static String get(String key) {
	try {
	    String result = LocalEn.valueOf(key).getDescription();
	    return result;
	} catch (Exception e) {
	    return key;
	}
    }

    /**
     * Get localization based on given {@link LocalKey}. Return key (as string)
     * if none found.
     * 
     * @param key
     * @return
     */
    public static String get(LocalKey local) {
	try {
	    String result = LocalEn.valueOf(local.getLocalKey()).getDescription();
	    return result;
	} catch (Exception e) {
	    return local.getLocalKey();
	}
    }
}
