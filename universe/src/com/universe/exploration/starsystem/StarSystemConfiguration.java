package com.universe.exploration.starsystem;

import java.util.ArrayList;
import java.util.List;

import com.universe.exploration.celestialcomponents.configuration.CelestialComponentTemplate;
import com.universe.exploration.celestialcomponents.configuration.PlanetTemplate;
import com.universe.exploration.celestialcomponents.configuration.StarTemplate;

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
     * Initiate configuration TODO: maybe we could ditch the String arrays?
     * Right now we convert to String[][] for compatibility issues with
     * randomizing components. This isn't a smart way of doing this but right
     * now works.
     */
    public StarSystemConfiguration() {
	List<String[]> startypes = new ArrayList<String[]>();
	List<String[]> planettypes = new ArrayList<String[]>();

	/**
	 * Generate string array of all potential stars and planets.
	 */
	for (CelestialComponentTemplate cct : CelestialComponentTemplate.values()) {
	    String[] tmp = { cct.name(), "" + cct.getPrevalance() };

	    if (cct.getComponentType() instanceof StarTemplate) {
		startypes.add(tmp);
	    }

	    if (cct.getComponentType() instanceof PlanetTemplate) {
		planettypes.add(tmp);
	    }
	}

	startypeListing = populateListing(startypes);
	planettypeListing = populateListing(planettypes);
    }

    /**
     * TODO: this is quite clumsy as it is.
     * 
     * @param arrList
     * @return
     */
    private String[][] populateListing(List<String[]> arrList) {
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
