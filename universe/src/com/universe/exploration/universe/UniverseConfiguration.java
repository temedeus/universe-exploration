package com.universe.exploration.universe;

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
public class UniverseConfiguration {
	/** 
	 * Max planet count
	 * @access private
	 */
	private int maxPlanetCount = 10;

	/**
	 * Minimum of planets.
	 * @access private
	 */
	private int minPlanetCount = 0;
	
	/**
	 * Star types and their weighted propabilities.
	 * @access private
	 */
	private String[][] startypeListing = {
		{"star1", "10"}, 
		{"star2", "3"}, 
		{"star3", "5"}
	};

	/**
	 * Initiate configuration
	 */
	public UniverseConfiguration() {
		
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

}
