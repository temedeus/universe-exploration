/**
 * 
 */
package com.universe.exploration.common.tools;

/**
 * @author 5.10.2015 Teemu Puurunen
 *
 */
public enum Units {
    AU("AU");

    private final String unit;

    Units(String unit) {
	this.unit = unit;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
	return unit;
    }
}
