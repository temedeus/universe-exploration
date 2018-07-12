/**
 *
 */
package com.universe.exploration.resource;

import com.universe.exploration.common.tools.RandomizationTools;
import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

import java.util.List;

/**
 * Produces a certain set of resources that were found on a survey. Generated
 * resources will be based on crew member attributes.
 *
 * @author 25.10.2015 Teemu Puurunen
 */
public class ResourcesFoundFactory {
    public ResourcesFoundBean generateFoundResource(List<CrewMember> crew, PlanetCelestialComponent planet) {
        ResourcesFoundBean resourcesFoundBean = new ResourcesFoundBean();
        int crewSize = crew.size();

        planet.getResourcesFound().forEach( resource -> {
            float amount = crewSize * randomizeAmount(resource.getResourcesFoundBoundary());

            if (amount > 0) {
                resource.setAmount(amount);
                resourcesFoundBean.addToResources(resource);
            }
        });

        return resourcesFoundBean;
    }

    private float randomizeAmount(ResourcesFoundBoundary boundary) {
        double min = boundary.getMin();
        double max = boundary.getMax();

        return (float) RandomizationTools.getRandomDouble(min, max);
    }
}
