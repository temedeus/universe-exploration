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
	LARGE_STAR("Large star", new LargeStar()),
	MEDIUM_STAR("Medium star", new MediumStar()),
	SMALL_STAR("Small star", new SmallStar()),
	// PLANETS
	ACID_RAIN_PLANET("Large star", new AcidRainPlanet()),
	RED_MINERAL_PLANET("Medium star", new RedMineralPlanet()),
	GAS_GIANT_PLANET("Gas giant", new GasGiantPlanet()),
	COLD_ROCKY_PLANET("Large star", new ColdRockyPlanet()),
	EARTLIKE_PLANET("Earthlike planet", new EarthlikePlanet());

	private final String name;

	private final ComponentType componentType;
	
	CelestialComponentTypes(String name, ComponentType componentType) {
		this.name = name;
		this.componentType = componentType;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the graphicsFile
	 */
	public ComponentType getComponentType() {
		return componentType;
	}
}
