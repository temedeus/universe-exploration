/**
 * 
 */
package com.universe.exploration.universe;

/**
 * @author 18.6.2015 Teemu Puurunen 
 *
 */
public class PlanetCountOutOfRangeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2071201718468432753L;
	
	public PlanetCountOutOfRangeException() {
		
	}

	/**
	 * @param string
	 */
	public PlanetCountOutOfRangeException(String string) {
		super(string);
	}

}