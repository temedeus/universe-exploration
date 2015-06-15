/**
 * 
 */
package com.universe.exploration.common.tools;

/**
 * @author 15.6.2015 Teemu Puurunen 
 *
 */
public enum AstronomicalConstants {
	APHELION_EARTH(1.0, "AU"),
	PERIPHELION_EARTH(1.0, "AU");
	
	private final double value;
	private final String explanation;
	
	AstronomicalConstants(double value, String explanation) {
		this.value = value;
		this.explanation = explanation;
	}
	
	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @return the explanation
	 */
	public String getExplanation() {
		return explanation;
	}

}
