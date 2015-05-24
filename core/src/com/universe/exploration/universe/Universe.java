package com.universe.exploration.universe;

import com.badlogic.gdx.Gdx;
import com.universe.exploration.Configuration;
import com.universe.exploration.GeneralTools;

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
