/**
 * 
 */
package com.universe.exploration.model;

import com.universe.exploration.starsystem.components.PlanetAbstractComponent;

/**
 * @author 2.8.2015 Teemu Puurunen 
 *
 */
public class PlanetGfxModel extends CelestialBodyGfxModel {
	PlanetGfxModel() {
		starSystemComponent = new PlanetAbstractComponent();
	}
	
	@Override
	public void updateSpriteData() {
		if(starSystemComponent instanceof PlanetAbstractComponent) {
			this.positionX = (float) (((PlanetAbstractComponent) starSystemComponent).getOrbitalRadius() * (float)Math.cos((float)angle));
			this.positionY = (float) (((PlanetAbstractComponent) starSystemComponent).getOrbitalRadius() * (float)Math.sin((float)angle));
			
			angle += 0.001;
		}

	}
}
