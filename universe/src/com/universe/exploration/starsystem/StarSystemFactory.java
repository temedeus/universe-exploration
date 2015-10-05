package com.universe.exploration.starsystem;

import com.universe.exploration.celestialcomponents.configuration.CelestialComponentTypes;
import com.universe.exploration.celestialcomponents.configuration.PlanetComponent;
import com.universe.exploration.common.tools.IngameAstronomicalConstants;
import com.universe.exploration.common.tools.MathTools;
import com.universe.exploration.common.tools.RandomizationTools;
import com.universe.exploration.common.tools.exceptions.PlanetCountOutOfRangeException;
import com.universe.exploration.starsystem.components.*;
import common.universe.exploration.common.Lifeforms;

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
		
		String tmpStarType = RandomizationTools.getRandomStringFromWeightedArray(uConf.getStartypeListing());

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
		double planetarySpace = MathTools.calculatePlaneterySpace(planetCount);
				
		double previousOrbitalRadious = 0;
		
		// Generate required number of planets.
		for(int x = 0; x < planetCount; x++) {
			// Container for our planet data.
			PlanetCelestialComponent planet = new PlanetCelestialComponent();
			
			// Setup planet component ready.
			String tmpPlanetType = RandomizationTools.getRandomStringFromWeightedArray(uConf.getPlanettypeListing());
			PlanetComponent cc = (PlanetComponent)CelestialComponentTypes.valueOf(tmpPlanetType).getComponentType();
			
			// Generate all the new values
			planet = calculatePlanetOrbitalData(planet, cc, planetarySpace, previousOrbitalRadious, x);
			planet = calculatePlanetHabitability(planet, cc);
			planet = setupPlanetBasicInfo(planet, cc);
			
			// Store old orbital radius value so we do not create orbits that cross with previous ones.
			previousOrbitalRadious = planet.getOrbitalRadius();
			
			// Add planet
			starsystem.addPlanet(planet);
		}
	}
	
	private PlanetCelestialComponent calculatePlanetOrbitalData(PlanetCelestialComponent planet, PlanetComponent cc, double planetarySpace, double previousOrbitalRadious, int x) {
		double planetOrbitalVelocity = RandomizationTools.getRandomDouble(IngameAstronomicalConstants.MIN_ORBITAL_VELOCITY.getValue(), IngameAstronomicalConstants.MAX_ORBITAL_VELOCITY.getValue());
		
		double minOrbitalRadius = x * planetarySpace + IngameAstronomicalConstants.MIN_ORBITAL_RADIUS.getValue();
		double maxOrbitalRadius = minOrbitalRadius + planetarySpace;
		minOrbitalRadius = verifyDistance(minOrbitalRadius, previousOrbitalRadious);
		
		
		double orbitalRadius = RandomizationTools.getRandomDouble(minOrbitalRadius, maxOrbitalRadius);
		double angle = MathTools.generateRandomAngle();
		
		planet.setOrbitalVelocity(planetOrbitalVelocity);
		planet.setOrbitalRadius(orbitalRadius);
		planet.setAngle(angle);
		
		return planet;
	}
	
	private PlanetCelestialComponent calculatePlanetHabitability(PlanetCelestialComponent planet, PlanetComponent cc) {
		planet.setOxygenFound(MathTools.calculateIfOddsHit(cc.getChanceToExtractOxygen()));
		planet.setWaterFound(MathTools.calculateIfOddsHit(cc.getChanceToExtractWater()));
		planet.setLifeforms(cc.randomizePlanetLife(planet.isWaterFound() && planet.isOxygenFound()));
		planet = calculateFoodPresence(planet);
		
		return planet;
	}
	
	private PlanetCelestialComponent calculateFoodPresence(PlanetCelestialComponent planet) {
		if(planet.getLifeforms().getRank() >= Lifeforms.VEGETATION.getRank()) {
			planet.setFoodFound(true);
		}
		
		return planet;
	}
	
	/**
	 * Sprite configuration and component related data.
	 * 
	 * @param planet
	 * @return
	 */
	private PlanetCelestialComponent setupPlanetBasicInfo(PlanetCelestialComponent planet, PlanetComponent cc) {
		planet.setGraphicsFile(cc.getRandomGraphicsFile());
		planet.setSpriteSize(cc.getRandomSpriteSize());
		planet.setComponentName(cc.getComponentName());
		
		return planet;
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
