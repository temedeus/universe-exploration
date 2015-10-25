/**
 * 
 */
package com.universe.exploration.survey;

import com.universe.exploration.common.tools.RandomizationTools;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * @author 25.10.2015 Teemu Puurunen 
 *
 */
public class ResourcesFoundFactory {
	public ResourcesFound generateFoundResource(PlanetCelestialComponent planet) {
		ResourcesFound r = new ResourcesFound();
		
		if(planet.isFoodFound()) {
			float food = randomizeAmount(ResourcesFoundBoundaries.WATER);
			r.setFood(food);
		}
		
		if(planet.isWaterFound()) {
			float water = randomizeAmount(ResourcesFoundBoundaries.WATER);
			r.setWater(water);
		}
		
		
		if(planet.isOxygenFound()) {
			float oxygen = randomizeAmount(ResourcesFoundBoundaries.AIR);
			r.setAir(oxygen);
		}
		
		return r;
	}
	
	private float randomizeAmount(ResourcesFoundBoundaries boundary) {
		double min = boundary.getMin();
		double max = boundary.getMax();
		
		return (float)RandomizationTools.getRandomDouble(min, max);
	}
}
