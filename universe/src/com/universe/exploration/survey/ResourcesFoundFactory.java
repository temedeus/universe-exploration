/**
 * 
 */
package com.universe.exploration.survey;

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

	if (planet.isFoodFound()) {
	    float food = crewSize * randomizeAmount(ResourcesFoundBoundaries.WATER);
	    resourcesFoundBean.setFood(food);
	}

	if (planet.isWaterFound()) {
	    float water = crewSize * randomizeAmount(ResourcesFoundBoundaries.WATER);
	    resourcesFoundBean.setWater(water);
	}

	if (planet.isOxygenFound()) {
	    float oxygen = crewSize * randomizeAmount(ResourcesFoundBoundaries.AIR);
	    resourcesFoundBean.setAir(oxygen);
	}

	return resourcesFoundBean;
    }

    private float randomizeAmount(ResourcesFoundBoundaries boundary) {
	double min = boundary.getMin();
	double max = boundary.getMax();

	return (float) RandomizationTools.getRandomDouble(min, max);
    }
}
