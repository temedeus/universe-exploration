/**
 *
 */
package com.universe.exploration.spritecontainer.data;

import com.universe.exploration.starsystem.components.CelestialComponent;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * Abstract sprite container state. Describes position in Gdx space.
 *
 * @author 2.8.2015 Teemu Puurunen
 */
public class SpriteContainerState {

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

    public SpriteContainerState() {
        positionX = 0;
        positionY = 0;
        angle = 0;
    }

    /**
     * Make planets go around their system.
     */
    public void update() {
        positionX = (float) (((PlanetCelestialComponent) starSystemComponent).getOrbitalRadius() * (float) Math.cos(angle));
        positionY = (float) (((PlanetCelestialComponent) starSystemComponent).getOrbitalRadius() * (float) Math.sin(angle));

        angle += ((PlanetCelestialComponent) starSystemComponent).getOrbitalVelocity();
    }

    /**
     * @return the positionX
     */
    public float getPositionX() {
        return positionX;
    }

    /**
     * @return the positionY
     */
    public float getPositionY() {
        return positionY;
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
        this.angle = (float) starSystemComponent.getAngle();
    }
}
