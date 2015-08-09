/**
 * 
 */
package com.universe.exploration.model;

import com.universe.exploration.starsystem.components.PlanetAbstractComponent;

/**
 * @author 4.8.2015 Teemu Puurunen 
 *
 */
public class SystemstarGfxModel extends CelestialBodyGfxModel {
	public SystemstarGfxModel() {
		
	}
	
	@Override 
	public void updateSpriteData() {
		this.positionX = (float) (((PlanetAbstractComponent) starSystemComponent).getOrbitalRadius() * (float)Math.cos((float)angle));
		this.positionY = (float) (((PlanetAbstractComponent) starSystemComponent).getOrbitalRadius() * (float)Math.sin((float)angle));
		
		angle += 0.001;
	}
}
