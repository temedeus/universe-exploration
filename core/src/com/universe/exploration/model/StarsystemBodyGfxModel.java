/**
 * 
 */
package com.universe.exploration.model;

import com.universe.exploration.starsystem.components.StarSystemComponent;

/**
 * @author 2.8.2015 Teemu Puurunen 
 *
 */
public class StarsystemBodyGfxModel  implements IStarsystemBodyGfxModel {
	
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
	protected StarSystemComponent starSystemComponent;
	
	/**
	 * 
	 */
	public StarsystemBodyGfxModel() {
		positionX = 0;
		positionY = 0;
		angle = 0;
	}
	
	public void updateSpriteData() {
		
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
	public StarSystemComponent getStarSystemComponent() {
		return starSystemComponent;
	}

	/**
	 * @param starSystemComponent the starSystemComponent to set
	 */
	public void setStarSystemComponent(StarSystemComponent starSystemComponent) {
		this.starSystemComponent = starSystemComponent;
	}
}
