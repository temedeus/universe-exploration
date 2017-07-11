package com.universe.exploration.starsystem;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.starsystem.components.StarCelestialComponent;

/**
 * Represents a star system containing hte star, planets and the actualy
 * boundaries. Generated based on {@link StarSystemConfiguration}.
 * 
 * @author Teemu
 *
 */
public class StarSystem {
    private StarCelestialComponent systemstar;

    private List<PlanetCelestialComponent> planets;

    /**
     * Add planet to star system.
     * 
     * @param planet
     */
    public void addPlanet(PlanetCelestialComponent planet) {
	planets.add(planet);
    }

    public void emptyPlanetsList() {
	planets.clear();
    }

    /**
     * @return the systemstar
     */
    public StarCelestialComponent getSystemstar() {
	return systemstar;
    }

    /**
     * @param systemstar
     *            the systemstar to set
     */
    public void setSystemstar(StarCelestialComponent systemstar) {
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
	this.planets = new ArrayList<PlanetCelestialComponent>();
    }

    /**
     * @return the planetCount
     */
    public int getPlanetCount() {
	return planetCount;
    }

    /**
     * @param planetCount
     *            the planetCount to set
     */
    public void setPlanetCount(int planetCount) {
	this.planetCount = planetCount;
    }

    /**
     * @return the planets
     */
    public List<PlanetCelestialComponent> getPlanets() {
	return planets;
    }

    /**
     * @param planets
     *            the planets to set
     */
    public void setPlanets(List<PlanetCelestialComponent> planets) {
	this.planets = planets;
    }
}
