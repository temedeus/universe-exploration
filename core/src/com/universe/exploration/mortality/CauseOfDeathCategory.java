/**
 * 
 */
package com.universe.exploration.mortality;

/**
 * <p>Enumeration stating categories for various types of {@link CauseOfDeath}. Listing aforementioned enumeration items
 * under categories is necessary because we need to know possible reasons can lead a crewman to die on a survey.</p>
 * 
 * <p>Each {@link CauseOfDeath} has one category in itself.</p>
 * @author 25.10.2015 Teemu Puurunen 
 *
 */
public enum CauseOfDeathCategory {
	GENERAL,
	LACK_OF_OXYGEN,
	CIVILIZATION,
	ANIMAL,
	BACTERIAL;
}
