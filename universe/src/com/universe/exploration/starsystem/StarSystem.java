package com.universe.exploration.starsystem;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.starsystem.components.PlanetAbstractComponent;
import com.universe.exploration.starsystem.components.StarAbstractComponent;

public class StarSystem {

	private StarSystemConfiguration uConf; 
	
	/**
	 * System star
	 */
	private StarAbstractComponent systemstar;
	
	private List<PlanetAbstractComponent> planets;

	/**
	 * Add planet to star system.
	 * @param planet
	 */
	public void addPlanet(PlanetAbstractComponent planet) {
		planets.add(planet);
	}
	
	public void emptyPlanetsList() {
		planets.clear();
	}
	
	/**
	 * @return the systemstar
	 */
	public StarAbstractComponent getSystemstar() {
		return systemstar;
	}

	/**
	 * @param systemstar the systemstar to set
	 */
	public void setSystemstar(StarAbstractComponent systemstar) {
		this.systemstar = systemstar;
	}

	/**
	 * Planets in given star system.
	 */
	private int planetCount;
	
	/**
	 * Star system constructor.
	 */
	public StarSystem() {
		this.planets = new ArrayList<PlanetAbstractComponent>();
	}
	
	/**
	 * @return the planetCount
	 */
	public int getPlanetCount() {
		return planetCount;
	}
	
	/**
	 * @param planetCount the planetCount to set
	 */
	public void setPlanetCount(int planetCount) {
		this.planetCount = planetCount;
	}
	
	/**
	 * @return the planets
	 */
	public List<PlanetAbstractComponent> getPlanets() {
		return planets;
	}

	/**
	 * @param planets the planets to set
	 */
	public void setPlanets(List<PlanetAbstractComponent> planets) {
		this.planets = planets;
	}
}
