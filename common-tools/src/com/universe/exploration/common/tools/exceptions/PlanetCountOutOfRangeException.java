/**
 * 
 */
package com.universe.exploration.common.tools.exceptions;

/**
 * @author 18.6.2015 Teemu Puurunen
 *
 */
public class PlanetCountOutOfRangeException extends Exception {

    private static final long serialVersionUID = 2071201718468432753L;

    /**
     * @param string
     */
    public PlanetCountOutOfRangeException(String string) {
	super(string);
    }

}
