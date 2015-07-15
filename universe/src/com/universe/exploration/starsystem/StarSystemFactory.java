package com.universe.exploration.starsystem;

import com.universe.exploration.common.tools.StarsystemComponentType;
import com.universe.exploration.common.tools.MathTools;
import com.universe.exploration.common.tools.RandomizationTools;
import com.universe.exploration.common.tools.exceptions.PlanetCountOutOfRangeException;
import com.universe.exploration.starsystem.components.*;

/**
 * 
 * @author 7.6.2015 Teemu Puurunen 
 *
 */
public class StarSystemFactory {
	private StarSystem starsystem;
	private StarSystemConfiguration uConf;
	
	/**
	 * Default configuration
	 */
	public StarSystemFactory() {
		this.uConf = new StarSystemConfiguration();
		this.starsystem = new StarSystem();
	}
	
	/**
	 * Custom configuration configuration
	 * @param StarSystemConfiguration uConf
	 */
	public StarSystemFactory(StarSystemConfiguration uConf) {
		this.uConf = uConf;
		this.starsystem = new StarSystem();
	}

	/**
	 * Create a universe
	 * @return Universe universe
	 */
	public StarSystem makeUniverse() throws PlanetCountOutOfRangeException {	
		int planetCount = RandomizationTools.getRandomInteger(uConf.getMinPlanetCount(), uConf.getMaxPlanetCount());
	
		// Planet count between configured limits.
		if(MathTools.betweenIntRangeInclusively(planetCount, this.uConf.getMaxPlanetCount(), this.uConf.getMinPlanetCount())) {
			this.starsystem.setPlanetCount(planetCount);
		} else {
			throw new PlanetCountOutOfRangeException("Planet count must be between " + this.uConf.getMinPlanetCount() + " and " + this.uConf.getMaxPlanetCount() + ". Current value: " + planetCount);
		}
		
		for(int x=0; x < planetCount; x++) {
			Planet planet = new Planet();
		}
		
		String tmpStarType = RandomizationTools.getStringFromWeightedRandomArray(uConf.getStartypeListing());

		StarsystemComponentType x = StarsystemComponentType.valueOf(tmpStarType);
		Star systemstar = new Star();
		systemstar.setcomponentType(StarsystemComponentType.valueOf(tmpStarType));
		
		this.starsystem.setSystemstar(systemstar);
		
		return this.starsystem;
	}
}
