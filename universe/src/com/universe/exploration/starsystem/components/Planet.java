/**
 * 
 */
package com.universe.exploration.starsystem.components;

import com.universe.exploration.common.tools.AstronomicalConstants;

/**
 * Planet numerical representation (e.g. speed, mass, velocity etc.)
 * Constructor makes sure at least basic values are defined if
 * nothing is set.
 * 
 * @author 15.6.2015 Teemu Puurunen 
 *
 */
public class Planet extends StarSystemComponent {
	/**
	 * Aphelion - distance when farthest to star.
	 */
	private double aphelion;
	
	/**
	 * Periphelion - distance when nearest to star.
	 */
	private double periphelion;
	
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
	
	/**
	 * Planet numerical representation (e.g. speed, mass, velocity etc.)
	 * Constructor makes sure at least basic values are defined if
	 * nothing is set.
	 */
	public Planet() {
		this.aphelion = AstronomicalConstants.APHELION_EARTH.getValue();
		this.periphelion = AstronomicalConstants.PERIPHELION_EARTH.getValue();
		this.orbitalVelocity = AstronomicalConstants.ORBITAL_VELOCITY.getValue();
	}
	
	/**
	 * @return the aphelion
	 */
	public double getAphelion() {
		return aphelion;
	}

	/**
	 * @param aphelion the aphelion to set
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
	 * @param periphelion the periphelion to set
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
	 * @param meanRadius the meanRadius to set
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
	 * @param massOfEarth the massOfEarth to set
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
	 * @param solarMass the solarMass to set
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
	 * @param orbitalVelocity the orbitalVelocity to set
	 */
	public void setOrbitalVelocity(double orbitalVelocity) {
		this.orbitalVelocity = orbitalVelocity;
	}
}
