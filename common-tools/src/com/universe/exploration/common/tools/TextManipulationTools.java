/**
 * 
 */
package com.universe.exploration.common.tools;

import java.util.ArrayList;

/**
 * <p>
 * Generic tools for text manipulation.
 * </p>
 * 
 * @author 31.10.2015 Teemu Puurunen
 *
 */
public class TextManipulationTools {
    public static String joinArrayListString(ArrayList<String> list, String separator) {
	String returnVal = "";

	for (String str : list) {
	    returnVal = returnVal.concat(str);

	    if (list.indexOf(str) != list.size() - 1) {
		returnVal = returnVal.concat(separator);
	    }
	}

	return returnVal;
    }
}
