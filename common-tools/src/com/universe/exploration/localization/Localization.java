/**
 * 
 */
package com.universe.exploration.localization;

import java.util.HashMap;

/**
 * @author 27.3.2016 Teemu Puurunen 
 *
 */
public class Localization {
    private HashMap<String, String> localizationMap = new HashMap<String, String>();
    
    public void putIntoLocalizationMap(String key, String value) {
	localizationMap.put(key, value);
    }
    
    public String getFromLocalizationMap(String key) {
	return localizationMap.get(key);
    }
}
