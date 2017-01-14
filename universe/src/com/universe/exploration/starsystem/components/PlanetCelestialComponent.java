/**
 * 
 */
package com.universe.exploration.starsystem.components;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.common.tools.IngameAstronomicalConstants;
import com.universe.exploration.resource.Resource;
import com.universe.exploration.survey.Lifeform;

/**
 * Planet numerical representation (e.g. speed, mass, velocity etc.) Constructor
 * makes sure at least basic values are defined if nothing is set.
 * 
 * @author 15.6.2015 Teemu Puurunen
 *
 */
public class PlanetCelestialComponent extends CelestialComponent {
    /**
     * Aphelion - distance when farthest to star. TODO: calculate radius based
     * on periphelion and aphelion
     */
    private double aphelion;

    /**
     * Periphelion - distance when nearest to star. TODO: calculate radius based
     * on periphelion and aphelion
     */
    private double periphelion;

    /**
     * Right now we do not use aphelion nor periphelion. As a simple solution we
     * just generate circle orbits (for simplicity) TODO: calculate radius based
     * on periphelion and aphelion
     */
    private double orbitalRadius;

    /**
     * Mean radius
     */
    private double meanRadius;

    /**
     * Orbital velocity
     */
    private double orbitalVelocity;

    /**
     * Mass of earth
     */
    private double massOfEarth;

    /**
     * Solar mass.
     */
    private double solarMass;

    private Lifeform lifeform;

    private List<Resource> resourcesFound;

    /**
     * Planet numerical representation (e.g. speed, mass, velocity etc.)
     * Constructor makes sure at least basic values are defined if nothing is
     * set.
     */
    public PlanetCelestialComponent() {
	resourcesFound = new ArrayList<Resource>();
	this.aphelion = IngameAstronomicalConstants.APHELION_EARTH.getValue();
	this.periphelion = IngameAstronomicalConstants.PERIPHELION_EARTH.getValue();
	this.orbitalVelocity = IngameAstronomicalConstants.MIN_ORBITAL_VELOCITY.getValue();
    }

    /**
     * @return the aphelion
     */
    public double getAphelion() {
	return aphelion;
    }

    /**
     * @param aphelion
     *            the aphelion to set
     */
    public void setAphelion(double aphelion) {
	this.aphelion = aphelion;
    }

    /**
     * @return the periphelion
     */
    public double getPeriphelion() {
	return periphelion;
    }

    /**
     * @param periphelion
     *            the periphelion to set
     */
    public void setPeriphelion(double periphelion) {
	this.periphelion = periphelion;
    }

    /**
     * @return the meanRadius
     */
    public double getMeanRadius() {
	return meanRadius;
    }

    /**
     * @param meanRadius
     *            the meanRadius to set
     */
    public void setMeanRadius(double meanRadius) {
	this.meanRadius = meanRadius;
    }

    /**
     * @return the massOfEarth
     */
    public double getMassOfEarth() {
	return massOfEarth;
    }

    /**
     * @param massOfEarth
     *            the massOfEarth to set
     */
    public void setMassOfEarth(double massOfEarth) {
	this.massOfEarth = massOfEarth;
    }

    /**
     * @return the solarMass
     */
    public double getSolarMass() {
	return solarMass;
    }

    /**
     * @param solarMass
     *            the solarMass to set
     */
    public void setSolarMass(double solarMass) {
	this.solarMass = solarMass;
    }

    /**
     * @return the orbitalVelocity
     */
    public double getOrbitalVelocity() {
	return orbitalVelocity;
    }

    /**
     * @param orbitalVelocity
     *            the orbitalVelocity to set
     */
    public void setOrbitalVelocity(double orbitalVelocity) {
	this.orbitalVelocity = orbitalVelocity;
    }

    /**
     * @return the radius
     */
    public double getRadius() {
	return orbitalRadius;
    }

    /**
     * @param radius
     *            the radius to set
     */
    public void setRadius(double radius) {
	this.orbitalRadius = radius;
    }

    /**
     * @return the orbitalRadius
     */
    public double getOrbitalRadius() {
	return orbitalRadius;
    }

    /**
     * @param orbitalRadius
     *            the orbitalRadius to set
     */
    public void setOrbitalRadius(double orbitalRadius) {
	this.orbitalRadius = orbitalRadius;
    }

    /**
     * @return the lifeforms
     */
    public Lifeform getLifeforms() {
	return lifeform;
    }

    /**
     * @param lifeforms
     *            the lifeforms to set
     */
    public void setLifeforms(Lifeform lifeform) {
	this.lifeform = lifeform;
    }

    /**
     * @return the resourcesFound
     */
    public List<Resource> getResourcesFound() {
	return resourcesFound;
    }

    /**
     * @param resourcesFound
     *            the resourcesFound to set
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
