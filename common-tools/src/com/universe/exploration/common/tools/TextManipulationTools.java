/**
 * 
 */
package com.universe.exploration.common.tools;

import java.util.List;

/**
 * Text manipulation tools.
 * 
 * @author 15.1.2017 Teemu Puurunen
 *
 */
public class TextManipulationTools {
    public static String implodeListAsString(List<String> list, String separator) {
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
