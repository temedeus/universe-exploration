package com.universe.exploration.starsystem;

/**
 * Config
 * 
 * Configuration class for universe generation. Class was not created as static so as not 
 * to show it around elsewhere in the project (to avoid confusion).
 * TODO: create schema and XML configuration based on schema. Config class in future
 * should just read this XML configuration instead of having everything hard-coded.
 * 
 * @author 6.6.2015 Teemu Puurunen 
 *
 */
public class StarSystemConfiguration {
	/** 
	 * Max planet count
	 * @access private
	 */
	private int maxPlanetCount = 6;

	/**
	 * Minimum of planets.
	 * @access private
	 */
	private int minPlanetCount = 0;
	
	private String[][] startypeListing = {
		{"LARGE_STAR", "10"}, 
		{"MEDIUM_STAR", "3"}, 
		{"SMALL_STAR", "5"}
	};
	
	/**
	 * Planet types and their weighted probabilities.
	 * @access private
	 */
	private String[][] planettypeListing = {
		{"ACID_RAIN_PLANET", "6"},
		{"RED_MINERAL_PLANET", "4"},
		{"GAS_GIANT_PLANET", "10"},
		{"COLD_ROCKY_PLANET", "15"},
		{"EARTLIKE_PLANET", "2"}
	};

	/**
	 * Initiate configuration
	 */
	public StarSystemConfiguration() {
		
	}

	/**
	 * @return the minPlanetCount
	 */
	public int getMinPlanetCount() {
		return minPlanetCount;
	}

	/**
	 * @param minPlanetCount the minPlanetCount to set
	 */
	public void setMinPlanetCount(int minPlanetCount) {
		this.minPlanetCount = minPlanetCount;
	}
	
	/**
	 * @return the maxPlanetCount
	 */
	public int getMaxPlanetCount() {
		return maxPlanetCount;
	}

	/**
	 * @param maxPlanetCount the maxPlanetCount to set
	 */
	public void setMaxPlanetCount(int maxPlanetCount) {
		this.maxPlanetCount = maxPlanetCount;
	}	
	
	/**
	 * @return the startypeListing
	 */
	public String[][] getStartypeListing() {
		return this.startypeListing;
	}

	/**
	 * @param startypeListing the startypeListing to set
	 */
	public void setStartypeListing(String[][] startypeListing) {
		this.startypeListing = startypeListing;
	}
	
	/**
	 * @return the planettypeListing
	 */
	public String[][] getPlanettypeListing() {
		return planettypeListing;
	}

	/**
	 * @param planettypeListing the planettypeListing to set
	 */
	public void setPlanettypeListing(String[][] planettypeListing) {
		this.planettypeListing = planettypeListing;
	}
}
