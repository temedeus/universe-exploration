/**
 * 
 */
package com.universe.exploration.resource;

import java.util.List;

import com.universe.exploration.common.tools.RandomizationTools;
import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * Produces a certain set of resources that were found on a survey. Generated
 * resources will be based on crew member attributes.
 * 
 * @author 25.10.2015 Teemu Puurunen
 *
 */
public class ResourcesFoundFactory {
    public ResourcesFoundBean generateFoundResource(List<CrewMember> crew, PlanetCelestialComponent planet) {
	ResourcesFoundBean resourcesFoundBean = new ResourcesFoundBean();
	int crewSize = crew.size();

	for (Resource resource : planet.getResourcesFound()) {
	    float amount = crewSize * randomizeAmount(resource.getBoundary());

	    if (amount > 0) {
		resource.setAmount(amount);
		resourcesFoundBean.addToResources(resource);
	    }

	}

	return resourcesFoundBean;
    }

    private float randomizeAmount(ResourcesFoundBoundaries boundary) {
	double min = boundary.getMin();
	double max = boundary.getMax();

	return (float) RandomizationTools.getRandomDouble(min, max);
    }
}
