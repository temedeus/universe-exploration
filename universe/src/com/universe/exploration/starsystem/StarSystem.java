package com.universe.exploration.starsystem;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.starsystem.components.Planet;
import com.universe.exploration.starsystem.components.Star;

public class StarSystem {

	private StarSystemConfiguration uConf; 
	
	/**
	 * System star
	 */
	private Star systemstar;
	
	private List<Planet> planets;

	/**
	 * Add planet to star system.
	 * @param planet
	 */
	public void addPlanet(Planet planet) {
		planets.add(planet);
	}
	
	public void emptyPlanetsList() {
		planets.clear();
	}
	
	/**
	 * @return the systemstar
	 */
	public Star getSystemstar() {
		return systemstar;
	}

	/**
	 * @param systemstar the systemstar to set
	 */
	public void setSystemstar(Star systemstar) {
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
		this.planets = new ArrayList<Planet>();
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
	public List<Planet> getPlanets() {
		return planets;
	}

	/**
	 * @param planets the planets to set
	 */
	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}
}
