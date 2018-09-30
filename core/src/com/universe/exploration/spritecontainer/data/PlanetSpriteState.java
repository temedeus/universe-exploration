/**
 *
 */
package com.universe.exploration.spritecontainer.data;

import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * @author 2.8.2015 Teemu Puurunen
 */
public class PlanetSpriteState extends SpriteContainerState {
    @Override
    public void update() {
        if (starSystemComponent instanceof PlanetCelestialComponent) {
            positionX = (float) (((PlanetCelestialComponent) starSystemComponent).getOrbitalRadius() * (float) Math.cos(angle));
            positionY = (float) (((PlanetCelestialComponent) starSystemComponent).getOrbitalRadius() * (float) Math.sin( angle));

            angle += ((PlanetCelestialComponent) starSystemComponent).getOrbitalVelocity();
        }

    }
}
