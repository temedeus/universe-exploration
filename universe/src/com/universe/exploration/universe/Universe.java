package com.universe.exploration.universe;

public class Universe {

	private UniverseConfiguration uConf; 
	
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
	public void setPlanetCount(int planetCount) throws PlanetCountOutOfRangeException {
		if(planetCount < this.uConf.getMinPlanetCount() || planetCount > this.uConf.getMaxPlanetCount()) {
			throw new PlanetCountOutOfRangeException("Planet count must be between " + this.uConf.getMinPlanetCount() + " and " + this.uConf.getMaxPlanetCount());
		} else {
			this.planetCount = planetCount;
		}
		
	}
	
	private int planetCount;
	/**
	 * Universe constructor.

	 */
	public Universe(UniverseConfiguration ue) {
		this.uConf = ue;
	}
}
