package com.universe.exploration.starsystem;

public class StarSystem {

	private StarSystemConfiguration uConf; 
	
	private String starType;

	private int planetCount;
	
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
	 * Star system constructor.
	 */
	public StarSystem() {
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
}
