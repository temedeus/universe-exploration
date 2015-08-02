package com.universe.exploration.starsystem;

import com.universe.exploration.common.tools.IngameAstronomicalConstants;
import com.universe.exploration.common.tools.StarsystemComponentTypes;
import com.universe.exploration.common.tools.MathTools;
import com.universe.exploration.common.tools.RandomizationTools;
import com.universe.exploration.common.tools.exceptions.PlanetCountOutOfRangeException;
import com.universe.exploration.starsystem.components.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Plane.PlaneSide;

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
	
		System.out.println("Planet count = " + planetCount);
		// Planet count between configured limits.
		if(MathTools.betweenIntRangeInclusively(planetCount, this.uConf.getMaxPlanetCount(), this.uConf.getMinPlanetCount())) {
			this.starsystem.setPlanetCount(planetCount);
		} else {
			throw new PlanetCountOutOfRangeException("Planet count must be between " + this.uConf.getMinPlanetCount() + " and " + this.uConf.getMaxPlanetCount() + ". Current value: " + planetCount);
		}
		
		populateWithPlanets(planetCount);
		
		String tmpStarType = RandomizationTools.getStringFromWeightedRandomArray(uConf.getStartypeListing());

		StarsystemComponentTypes x = StarsystemComponentTypes.valueOf(tmpStarType);
		StarAbstractComponent systemstar = new StarAbstractComponent();
		systemstar.setcomponentType(StarsystemComponentTypes.valueOf(tmpStarType));
		
		this.starsystem.setSystemstar(systemstar);
		
		return this.starsystem;
	}
	
	/**
	 * Populate starsystem with planets
	 * @param planetCount
	 */
	private void populateWithPlanets(int planetCount) {
		double planetarySpace = 
				(IngameAstronomicalConstants.MAX_ORBITAL_RADIUS.getValue() - 
						IngameAstronomicalConstants.MIN_ORBITAL_RADIUS.getValue()) / 
						(planetCount);
		
		double previousOrbitalRadious = 0;
		
		// Generate required number of planets.
		for(int x = 0; x < planetCount; x++) {
			PlanetAbstractComponent planet = new PlanetAbstractComponent();
			
			// Generate all the new values
			String tmpPlanetType = RandomizationTools.getStringFromWeightedRandomArray(uConf.getPlanettypeListing());
			double planetOrbitalVelocity = RandomizationTools.getRandomDouble(IngameAstronomicalConstants.MIN_ORBITAL_VELOCITY.getValue(), IngameAstronomicalConstants.MAX_ORBITAL_VELOCITY.getValue());
			
			double minOrbitalRadius = x * planetarySpace + IngameAstronomicalConstants.MIN_ORBITAL_RADIUS.getValue();
			double maxOrbitalRadius = minOrbitalRadius + planetarySpace;

			minOrbitalRadius = verifyDistance(minOrbitalRadius, previousOrbitalRadious);
			
			double orbitalRadius = RandomizationTools.getRandomDouble(minOrbitalRadius, maxOrbitalRadius);
			previousOrbitalRadious = orbitalRadius;
			
			System.out.println("Min o.rad=" + minOrbitalRadius + " / max o.rad =" + maxOrbitalRadius + " / cur o.rad=" + orbitalRadius);
			// Set values
			planet.setcomponentType(StarsystemComponentTypes.valueOf(tmpPlanetType));
			planet.setOrbitalVelocity(planetOrbitalVelocity);
			planet.setOrbitalRadius(orbitalRadius);
			
			// Add planet
			this.starsystem.addPlanet(planet);
		}
	}
	
	/**
	 * Ensure that new distance is far away enough from the old distance
	 * @param distance
	 * @param previousDistance
	 * @return
	 */
	private double verifyDistance(double distance, double previousDistance) {
		double difference = distance - previousDistance;
		
		if(difference <= IngameAstronomicalConstants.MIN_DIFFERENCE_BETWEEN_ADJACENT_PLANET_RADII.getValue()) {
			return distance + IngameAstronomicalConstants.MIN_DIFFERENCE_BETWEEN_ADJACENT_PLANET_RADII.getValue() - difference;
		} else {
			return distance;
		}
	}
}
