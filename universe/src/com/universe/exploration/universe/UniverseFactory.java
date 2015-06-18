package com.universe.exploration.universe;

import com.universe.exploration.common.tools.RandomizationTools;

/**
 * 
 * @author 7.6.2015 Teemu Puurunen 
 *
 */
public class UniverseFactory {
	private Universe universe;
	private UniverseConfiguration uConf;

	/**
	 * Universe factory constructor
	 */
	public UniverseFactory() {
		uConf = new UniverseConfiguration();
		
		this.universe = new Universe(uConf);
		
		try {
			this.universe.setPlanetCount(RandomizationTools.getRandomInteger(uConf.getMinPlanetCount(), uConf.getMaxPlanetCount()));
		} catch(PlanetCountOutOfRangeException e) {
			
		}
		
		this.universe.setStarType(RandomizationTools.getStringFromWeightedRandomArray(uConf.getStartypeListing()));
	}
	
	/**
	 * Get universe
	 * @return
	 */
	public Universe getUniverse() {
		return universe;
	}

	/**
	 * Set universe
	 * @param universe
	 */
	public void setUniverse(Universe universe) {
		this.universe = universe;
	}
}
