package com.universe.exploration.starsystem;

import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.starsystem.components.StarCelestialComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a star system containing hte star, planets and the actualy
 * boundaries. Generated based on {@link StarSystemConfiguration}.
 *
 * @author Teemu
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

    /**
     * @return the systemstar
     */
    public StarCelestialComponent getSystemstar() {
        return systemstar;
    }

    /**
     * @param systemstar the systemstar to set
     */
    public void setSystemstar(StarCelestialComponent systemstar) {
        this.systemstar = systemstar;
    }

    /**
     * Star system constructor.
     */
    public StarSystem() {
        this.planets = new ArrayList<>();
    }

    /**
     * @return the planets
     */
    public List<PlanetCelestialComponent> getPlanets() {
        return planets;
    }

    /**
     * @param planets the planets to set
     */
    public void setPlanets(List<PlanetCelestialComponent> planets) {
        this.planets = planets;
    }
}
