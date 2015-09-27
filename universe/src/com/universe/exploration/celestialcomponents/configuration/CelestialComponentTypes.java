/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration;

import com.universe.exploration.celestialcomponents.configuration.planets.AcidRainPlanet;
import com.universe.exploration.celestialcomponents.configuration.planets.ColdRockyPlanet;
import com.universe.exploration.celestialcomponents.configuration.planets.GoldilocksPlanet;
import com.universe.exploration.celestialcomponents.configuration.planets.GasGiantPlanet;
import com.universe.exploration.celestialcomponents.configuration.planets.TerrestrialPlanet;
import com.universe.exploration.celestialcomponents.configuration.stars.LargeStar;
import com.universe.exploration.celestialcomponents.configuration.stars.MediumStar;
import com.universe.exploration.celestialcomponents.configuration.stars.SmallStar;

/**
 * @author 15.7.2015 Teemu Puurunen 
 *
 */
public enum CelestialComponentTypes {
	// SYSTEM STARS
	LARGE_STAR(new LargeStar(), 10),
	MEDIUM_STAR(new MediumStar(), 3),
	SMALL_STAR(new SmallStar(), 5),
	// PLANETS
	ACID_RAIN_PLANET(new AcidRainPlanet(), 6),
	RED_MINERAL_PLANET(new TerrestrialPlanet(), 4),
	GAS_GIANT_PLANET(new GasGiantPlanet(), 10),
	COLD_ROCKY_PLANET(new ColdRockyPlanet(), 15),
	EARTLIKE_PLANET(new GoldilocksPlanet(), 2);

	private final ComponentType componentType;
	
	private final int prevalance; 

	CelestialComponentTypes(ComponentType componentType, int prevalance) {
		this.componentType = componentType;
		this.prevalance = prevalance; 
	}

	/**
	 * @return the graphicsFile
	 */
	public ComponentType getComponentType() {
		return componentType;
	}
	
	/**
	 * @return the prevalance
	 */
	public int getPrevalance() {
		return prevalance;
	}
}
