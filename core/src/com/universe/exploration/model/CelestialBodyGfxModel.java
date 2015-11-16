/**
 * 
 */
package com.universe.exploration.model;

import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.starsystem.components.CelestialComponent;

/**
 * @author 2.8.2015 Teemu Puurunen 
 *
 */
public class CelestialBodyGfxModel  implements IStarsystemBodyGfxModel {
	
	/**
	 * Gdx position
	 */
	protected float positionX;
	
	/**
	 * Gdx position
	 */
	protected float positionY;
	
	/**
	 * Gdx angle
	 */
	protected float angle;
	
	/**
	 * Contains planetary data
	 */
	protected CelestialComponent starSystemComponent;
	
	/**
	 * 
	 */
	public CelestialBodyGfxModel() {
		positionX = 0;
		positionY = 0;
		angle = 0;
	}
	
	public void updateSpriteData() {
		positionX = (float) (((PlanetCelestialComponent) starSystemComponent).getOrbitalRadius() * (float)Math.cos((float)angle));
		positionY = (float) (((PlanetCelestialComponent) starSystemComponent).getOrbitalRadius() * (float)Math.sin((float)angle));
		
		angle += ((PlanetCelestialComponent) starSystemComponent).getOrbitalVelocity();
	}
	
	/**
	 * @return the positionX
	 */
	public float getPositionX() {
		return positionX;
	}

	/**
	 * @param positionX the positionX to set
	 */
	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	/**
	 * @return the positionY
	 */
	public float getPositionY() {
		return positionY;
	}

	/**
	 * @param positionY the positionY to set
	 */
	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}

	/**
	 * @return the angle
	 */
	public float getAngle() {
		return angle;
	}

	/**
	 * @param angle the angle to set
	 */
	public void setAngle(float angle) {
		this.angle = angle;
	}

	/**
	 * @return the starSystemComponent
	 */
	public CelestialComponent getStarSystemComponent() {
		return starSystemComponent;
	}

	/**
	 * @param starSystemComponent the starSystemComponent to set
	 */
	public void setStarSystemComponent(CelestialComponent starSystemComponent) {
		this.starSystemComponent = starSystemComponent;
		this.angle = (float)starSystemComponent.getAngle();
	}
}
