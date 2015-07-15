/**
 * 
 */
package com.universe.exploration.common.tools;

/**
 * @author 15.7.2015 Teemu Puurunen 
 *
 */
public enum StarsystemComponentType {
	LARGE_STAR("Large star", "star1.png"),
	MEDIUM_STAR("Medium star", "star2.png"),
	SMALL_STAR("Small star", "star3.png");
	
	private final String name;

	private final String graphicsFile;
	
	StarsystemComponentType(String name, String graphicsFile) {
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
