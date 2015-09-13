package com.universe.exploration;

/**
 * GameConfiguration.java
 * 
 * Game configuration.
 * TODO: XML configuration instead of hard-coding.
 * 
 * @author 6.6.2015 Teemu Puurunen 
 *
 */
public class CoreConfiguration {

	/**
	 * Background tile width/height (for simplicity tiles are all squares so width equals height)
	 */
	public static final int bgSize = 2048;
	
	public static final float maxVelocity = 5;
	
	public static final float minVelocity = 2;
	
	public static final int SYSTEMSTAR_SIZE = 4096;
	
	public static final int MAX_CREWMEN = 10;
	
	public static final float MAX_AIR = 100;
	
	public static final float MAX_WATER = 100;
	
	public static final float MAX_POWER = 100;

	public static final float MAX_FOOD = 100;
	
	public static final String uiSkin = "uiskin.json";
}
