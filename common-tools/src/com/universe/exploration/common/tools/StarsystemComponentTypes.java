/**
 * 
 */
package com.universe.exploration.common.tools;

/**
 * @author 15.7.2015 Teemu Puurunen 
 *
 */
public enum StarsystemComponentTypes {
	// SYSTEM STARS
	LARGE_STAR("Large star", "star1.png"),
	MEDIUM_STAR("Medium star", "star2.png"),
	SMALL_STAR("Small star", "star3.png"),
	// PLANETS
	ACID_RAIN_PLANET("Large star", "planet1.png"),
	RED_MINERAL_PLANET("Medium star", "planet2.png"),
	GAS_GIANT_PLANET("Gas giant", "planet3.png"),
	COLD_ROCKY_PLANET("Large star", "planet4.png"),
	EARTLIKE_PLANET("Earthlike planet", "planet5.png");
	
	private final String name;

	private final String graphicsFile;
	
	StarsystemComponentTypes(String name, String graphicsFile) {
		this.name = name;
		this.graphicsFile = graphicsFile;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the graphicsFile
	 */
	public String getGraphicsFile() {
		return graphicsFile;
	}
}
