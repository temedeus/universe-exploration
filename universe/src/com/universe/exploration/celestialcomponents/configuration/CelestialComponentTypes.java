/**
 * 
 */
package com.universe.exploration.celestialcomponents.configuration;

import com.universe.exploration.celestialcomponents.configuration.planets.AcidRainPlanet;
import com.universe.exploration.celestialcomponents.configuration.planets.ColdRockyPlanet;
import com.universe.exploration.celestialcomponents.configuration.planets.EarthlikePlanet;
import com.universe.exploration.celestialcomponents.configuration.planets.GasGiantPlanet;
import com.universe.exploration.celestialcomponents.configuration.planets.RedMineralPlanet;
import com.universe.exploration.celestialcomponents.configuration.stars.LargeStar;
import com.universe.exploration.celestialcomponents.configuration.stars.MediumStar;
import com.universe.exploration.celestialcomponents.configuration.stars.SmallStar;

/**
 * @author 15.7.2015 Teemu Puurunen 
 *
 */
public enum CelestialComponentTypes {
	// SYSTEM STARS
	LARGE_STAR(new LargeStar()),
	MEDIUM_STAR(new MediumStar()),
	SMALL_STAR(new SmallStar()),
	// PLANETS
	ACID_RAIN_PLANET(new AcidRainPlanet()),
	RED_MINERAL_PLANET(new RedMineralPlanet()),
	GAS_GIANT_PLANET(new GasGiantPlanet()),
	COLD_ROCKY_PLANET(new ColdRockyPlanet()),
	EARTLIKE_PLANET(new EarthlikePlanet());

	private final ComponentType componentType;
	
	CelestialComponentTypes(ComponentType componentType) {
		this.componentType = componentType;
	}

	/**
	 * @return the graphicsFile
	 */
	public ComponentType getComponentType() {
		return componentType;
	}
}
