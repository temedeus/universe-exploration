package com.universe.exploration.universe;

import com.universe.exploration.common.tools.RandomizationTools;

public class Universe {
	/**
	 * Star type.
	 * @access private
	 */
	private String starType = "";

	/**
	 * Planet count in the system.
	 * @access private
	 */
	private int planetCount;
	
	/**
	 * Planet types. Create this array once planet count has been randomized.
	 * @access private
	 */
	private String planetTypes[];
	
	/**
	 * Universe constructor.

	 */
	public Universe() {
	
	}
	
	public String getStarType() {
		return starType;
	}

	public void setStarType(String starType) {
		this.starType = starType;
	}
	
	public int getPlanetCount() {
		return planetCount;
	}

	public void setPlanetCount(int planetCount) {
		this.planetCount = planetCount;
	}
}
