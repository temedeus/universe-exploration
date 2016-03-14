package com.universe.exploration.starsystem;

import java.util.ArrayList;

import com.universe.exploration.celestialcomponents.configuration.CelestialComponentTypes;
import com.universe.exploration.celestialcomponents.configuration.PlanetComponent;
import com.universe.exploration.celestialcomponents.configuration.StarComponent;

/**
 * Config
 * 
 * Configuration class for universe generation. TODO: create schema and XML
 * configuration based on schema. Config class in future should just read this
 * XML configuration instead of having everything hard-coded.
 * 
 * @author 6.6.2015 Teemu Puurunen
 *
 */
public class StarSystemConfiguration {
    /**
     * Max planet count
     * 
     * @access private
     */
    private final int maxPlanetCount = 6;

    /**
     * Minimum of planets.
     * 
     * @access private
     */
    private final int minPlanetCount = 0;

    private String[][] startypeListing;

    /**
     * Planet types and their weighted probabilities.
     * 
     * @access private
     */
    private String[][] planettypeListing;

    /**
     * Initiate configuration TODO: maybe we could ditch the String arrays and
     * utilize ArrayList solely? Right now we convert to String[][] for
     * compatibility issues with randomizing components. This likely involves
     * creating a new randomization method.
     */
    public StarSystemConfiguration() {
	ArrayList<String[]> startypes = new ArrayList<String[]>();
	ArrayList<String[]> planettypes = new ArrayList<String[]>();

	for (CelestialComponentTypes cct : CelestialComponentTypes.values()) {
	    String[] tmp = { cct.name(), "" + cct.getPrevalance() };

	    if (cct.getComponentType() instanceof StarComponent) {
		startypes.add(tmp);
	    }

	    if (cct.getComponentType() instanceof PlanetComponent) {
		planettypes.add(tmp);
	    }
	}

	startypeListing = populateListing(startypes);
	planettypeListing = populateListing(planettypes);
    }

    /**
     * Populate String[][] with ArraList items. TODO: this is custom work for
     * and extremely stupid implementation of how stars and planets are randomly
     * generated. Please see {@link StarSystemConfiguration} and try to get rid
     * of this bullshit.
     * 
     * @param arrList
     * @return
     */
    private String[][] populateListing(ArrayList<String[]> arrList) {
	String[][] listing = new String[arrList.size()][2];
	int x = 0;

	for (String[] listItemAsStringArray : arrList) {
	    listing[x++] = listItemAsStringArray;
	}

	return listing;
    }

    /**
     * @return the minPlanetCount
     */
    public int getMinPlanetCount() {
	return minPlanetCount;
    }

    /**
     * @return the maxPlanetCount
     */
    public int getMaxPlanetCount() {
	return maxPlanetCount;
    }

    /**
     * @return the startypeListing
     */
    public String[][] getStartypeListing() {
	return this.startypeListing;
    }

    /**
     * @param startypeListing
     *            the startypeListing to set
     */
    public void setStartypeListing(String[][] startypeListing) {
	this.startypeListing = startypeListing;
    }

    /**
     * @return the planettypeListing
     */
    public String[][] getPlanettypeListing() {
	return planettypeListing;
    }

    /**
     * @param planettypeListing
     *            the planettypeListing to set
     */
    public void setPlanettypeListing(String[][] planettypeListing) {
	this.planettypeListing = planettypeListing;
    }
}
