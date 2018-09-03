/**
 *
 */
package com.universe.exploration.starsystem.components;

import com.universe.exploration.common.tools.IngameAstronomicalConstants;
import com.universe.exploration.resource.Resource;
import com.universe.exploration.survey.LifeformLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * Planet numerical representation (e.g. speed, mass, velocity etc.) Constructor
 * makes sure at least basic values are defined if nothing is set.
 *
 * @author 15.6.2015 Teemu Puurunen
 */
public class PlanetCelestialComponent extends CelestialComponent {
    /**
     * Right now we do not use aphelion nor periphelion. As a simple solution we
     * just generate circle orbits (for simplicity) TODO: calculate radius based
     * on periphelion and aphelion
     */
    private double orbitalRadius;

    /**
     * Orbital velocity
     */
    private double orbitalVelocity;

    private LifeformLevel lifeformLevel;

    private List<Resource> resourcesFound;

    /**
     * Planet numerical representation (e.g. speed, mass, velocity etc.)
     * Constructor makes sure at least basic values are defined if nothing is
     * set.
     */
    public PlanetCelestialComponent() {
        resourcesFound = new ArrayList<>();
        this.orbitalVelocity = IngameAstronomicalConstants.MIN_ORBITAL_VELOCITY.getValue();
    }


    /**
     * @return the orbitalVelocity
     */
    public double getOrbitalVelocity() {
        return orbitalVelocity;
    }

    /**
     * @param orbitalVelocity the orbitalVelocity to set
     */
    public void setOrbitalVelocity(double orbitalVelocity) {
        this.orbitalVelocity = orbitalVelocity;
    }

    /**
     * @return the orbitalRadius
     */
    public double getOrbitalRadius() {
        return orbitalRadius;
    }

    /**
     * @param orbitalRadius the orbitalRadius to set
     */
    public void setOrbitalRadius(double orbitalRadius) {
        this.orbitalRadius = orbitalRadius;
    }

    /**
     * @return the lifeforms
     */
    public LifeformLevel getLifeforms() {
        return lifeformLevel;
    }

    /**
     * @param lifeforms the lifeforms to set
     */
    public void setLifeforms(LifeformLevel lifeformLevel) {
        this.lifeformLevel = lifeformLevel;
    }

    /**
     * @return the resourcesFound
     */
    public List<Resource> getResourcesFound() {
        return resourcesFound;
    }

    /**
     * @param resourcesFound the resourcesFound to set
     */
    public void addFoundResource(Resource resource) {
        this.resourcesFound.add(resource);
    }

    /**
     * See if resources contain given resource type. (E.g. perform class
     * comparison.)
     *
     * @return boolean
     */
    public boolean containsInstanceOfResource(Class<? extends Resource> clazz) {
        for (Resource resource : resourcesFound) {
            if (resource.getClass().equals(clazz)) {
                return true;
            }
        }

        return false;
    }

}
