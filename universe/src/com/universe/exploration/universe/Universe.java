package com.universe.exploration.universe;

import com.universe.exploration.common.tools.RandomizationTools;

public class Universe {

	private String starType;
	/**
	 * @return the starType
	 */
	public String getStarType() {
		return starType;
	}
	/**
	 * @param starType the starType to set
	 */
	public void setStarType(String starType) {
		this.starType = starType;
	}
	/**
	 * @return the planetCount
	 */
	public int getPlanetCount() {
		return planetCount;
	}
	/**
	 * @param planetCount the planetCount to set
	 */
	public void setPlanetCount(int planetCount) {
		this.planetCount = planetCount;
	}
	private int planetCount;
	/**
	 * Universe constructor.

	 */
	public Universe() {
	
	}
}
