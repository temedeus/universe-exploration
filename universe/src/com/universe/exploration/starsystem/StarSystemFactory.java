package com.universe.exploration.starsystem;

import com.universe.exploration.common.tools.IngameAstronomicalConstants;
import com.universe.exploration.common.tools.StarsystemComponentTypes;
import com.universe.exploration.common.tools.MathTools;
import com.universe.exploration.common.tools.RandomizationTools;
import com.universe.exploration.common.tools.exceptions.PlanetCountOutOfRangeException;
import com.universe.exploration.starsystem.components.*;
import com.badlogic.gdx.Gdx;

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
		
		double previousOrbitalRadius = 0.0;
		
		// Generate required number of planets.
		for(int x = 0; x < planetCount; x++) {
			double minimumRequiredSpaceForRest = 
					IngameAstronomicalConstants.MIN_DIFFERENCE_BETWEEN_ADJACENT_PLANET_RADII.getValue() * (planetCount - x);
			
			Planet planet = new Planet();
			
			// Generate all the new values
			String tmpPlanetType = RandomizationTools.getStringFromWeightedRandomArray(uConf.getPlanettypeListing());
			double planetOrbitalVelocity = RandomizationTools.getRandomDouble(IngameAstronomicalConstants.MIN_ORBITAL_VELOCITY.getValue(), IngameAstronomicalConstants.MAX_ORBITAL_VELOCITY.getValue());
			
			double minOrbitalRadius = IngameAstronomicalConstants.MIN_ORBITAL_RADIUS.getValue() + previousOrbitalRadius;
			double maxOrbitalRadius = IngameAstronomicalConstants.MAX_ORBITAL_RADIUS.getValue() - minimumRequiredSpaceForRest;
			
			double orbitalRadius = RandomizationTools.getRandomDouble(minOrbitalRadius, maxOrbitalRadius);
			previousOrbitalRadius += orbitalRadius + IngameAstronomicalConstants.MIN_DIFFERENCE_BETWEEN_ADJACENT_PLANET_RADII.getValue();
			
			
			System.out.println("Min orbital radius: " + minOrbitalRadius);
			System.out.println("Max orbital radius: " + minOrbitalRadius);
			System.out.println("Min orbital radius: " + minOrbitalRadius);
			// Set values
			planet.setcomponentType(StarsystemComponentTypes.valueOf(tmpPlanetType));
			planet.setOrbitalVelocity(planetOrbitalVelocity);
			planet.setOrbitalRadius(orbitalRadius);
			
			// Add planet
			this.starsystem.addPlanet(planet);
		}
		
		String tmpStarType = RandomizationTools.getStringFromWeightedRandomArray(uConf.getStartypeListing());

		StarsystemComponentTypes x = StarsystemComponentTypes.valueOf(tmpStarType);
		Star systemstar = new Star();
		systemstar.setcomponentType(StarsystemComponentTypes.valueOf(tmpStarType));
		
		this.starsystem.setSystemstar(systemstar);
		
		return this.starsystem;
	}
}
