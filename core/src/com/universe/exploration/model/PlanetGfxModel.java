/**
 * 
 */
package com.universe.exploration.model;

import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * @author 2.8.2015 Teemu Puurunen 
 *
 */
public class PlanetGfxModel extends CelestialBodyGfxModel {
	PlanetGfxModel() {
		starSystemComponent = new PlanetCelestialComponent();
	}
	
	@Override
	public void updateSpriteData() {
		if(starSystemComponent instanceof PlanetCelestialComponent) {
			this.positionX = (float) (((PlanetCelestialComponent) starSystemComponent).getOrbitalRadius() * (float)Math.cos((float)angle));
			this.positionY = (float) (((PlanetCelestialComponent) starSystemComponent).getOrbitalRadius() * (float)Math.sin((float)angle));
			
			angle += ((PlanetCelestialComponent) starSystemComponent).getOrbitalVelocity();
		}

	}
}
