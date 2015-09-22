package com.universe.exploration.starsystem;

import com.universe.exploration.celestialcomponents.configuration.CelestialComponentTypes;
import com.universe.exploration.celestialcomponents.configuration.planets.PlanetComponent;
import com.universe.exploration.common.tools.IngameAstronomicalConstants;
import com.universe.exploration.common.tools.MathTools;
import com.universe.exploration.common.tools.RandomizationTools;
import com.universe.exploration.common.tools.exceptions.PlanetCountOutOfRangeException;
import com.universe.exploration.starsystem.components.*;

/**
 * 
 * Generates a new star system.
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
	 * Create a star system
	 * @return StarSystem starsystem
	 */
	public StarSystem makeStarSystem() throws PlanetCountOutOfRangeException {	
		int planetCount = RandomizationTools.getRandomInteger(uConf.getMinPlanetCount(), uConf.getMaxPlanetCount());
	
		// Planet count between configured limits.
		if(MathTools.betweenIntRangeInclusively(planetCount, this.uConf.getMaxPlanetCount(), this.uConf.getMinPlanetCount())) {
			this.starsystem.setPlanetCount(planetCount);
		} else {
			throw new PlanetCountOutOfRangeException("Planet count must be between " + this.uConf.getMinPlanetCount() + " and " + this.uConf.getMaxPlanetCount() + ". Current value: " + planetCount);
		}
		
		populateWithPlanets(planetCount);
		
		String tmpStarType = RandomizationTools.getStringFromWeightedRandomArray(uConf.getStartypeListing());

		StarCelestialComponent systemstar = new StarCelestialComponent();
		systemstar.setGraphicsFile(CelestialComponentTypes.valueOf(tmpStarType).getComponentType().getRandomGraphicsFile());
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
			PlanetCelestialComponent planet = new PlanetCelestialComponent();
			
			// Generate all the new values
			String tmpPlanetType = RandomizationTools.getStringFromWeightedRandomArray(uConf.getPlanettypeListing());
			double planetOrbitalVelocity = RandomizationTools.getRandomDouble(IngameAstronomicalConstants.MIN_ORBITAL_VELOCITY.getValue(), IngameAstronomicalConstants.MAX_ORBITAL_VELOCITY.getValue());
			
			double minOrbitalRadius = x * planetarySpace + IngameAstronomicalConstants.MIN_ORBITAL_RADIUS.getValue();
			double maxOrbitalRadius = minOrbitalRadius + planetarySpace;

			minOrbitalRadius = verifyDistance(minOrbitalRadius, previousOrbitalRadious);
			
			double orbitalRadius = RandomizationTools.getRandomDouble(minOrbitalRadius, maxOrbitalRadius);
			previousOrbitalRadious = orbitalRadius;
			
			double angle = RandomizationTools.getRandomDouble(0, 360);
			
			//System.out.println("Min o.rad=" + minOrbitalRadius + " / max o.rad =" + maxOrbitalRadius + " / cur o.rad=" + orbitalRadius);
			// Set values
			PlanetComponent cc = (PlanetComponent)CelestialComponentTypes.valueOf(tmpPlanetType).getComponentType();
			
			if(MathTools.calculateIfOddsHit(cc.getCHANCE_CIVILIZATION())) {
				planet.setLifeforms("civilized");
			} else {
				if(MathTools.calculateIfOddsHit(cc.getCHANCE_ANIMAL())) {
					planet.setLifeforms("animal");
				} else {
					if(MathTools.calculateIfOddsHit(cc.getChanceForBacterial())) {
						planet.setLifeforms("bacterial");
					} else {
						planet.setLifeforms("none");
					}
				}
			}
			
			planet.setGraphicsFile(cc.getRandomGraphicsFile());
			planet.setOrbitalVelocity(planetOrbitalVelocity);
			planet.setOrbitalRadius(orbitalRadius);
			planet.setAngle(angle);
			planet.setSpriteSize(cc.getRandomSpriteSize());
			planet.setComponentName(cc.getComponentName());
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
