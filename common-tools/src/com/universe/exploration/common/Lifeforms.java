/**
 * 
 */
package com.universe.exploration.common;

/**
 * @author 27.9.2015 Teemu Puurunen
 *
 */
public enum Lifeforms {
    CIVILIZED("TITLE_LIFEFORMS_CIVILIZED", 4), ANIMAL("TITLE_LIFEFORMS_ANIMAL", 3), VEGETATION("TITLE_LIFEFORMS_VEGETATION", 2), BACTERIAL(
	    "TITLE_LIFEFORMS_BACTERIAL", 1), NONE("TITLE_LIFEFORMS_NONE", 0);

    private final String localKey;

    private final int rank;

    /**
     * @return the localKey
     */
    public String getLocalKey() {
	return localKey;
    }

    Lifeforms(String localKey, int rank) {
	this.localKey = localKey;
	this.rank = rank;
    }

    /**
     * @return the rank
     */
    public int getRank() {
	return rank;
    }
}
