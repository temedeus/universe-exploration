/**
 *
 */
package com.universe.exploration.spritecontainer.data;

import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * @author 2.8.2015 Teemu Puurunen
 */
public class PlanetSpriteState extends SpriteContainerState {
    PlanetSpriteState() {
        starSystemComponent = new PlanetCelestialComponent();
    }

    @Override
    public void updateSpriteData() {
        if (starSystemComponent instanceof PlanetCelestialComponent) {
            positionX = (float) (((PlanetCelestialComponent) starSystemComponent).getOrbitalRadius() * (float) Math.cos((float) angle));
            positionY = (float) (((PlanetCelestialComponent) starSystemComponent).getOrbitalRadius() * (float) Math.sin((float) angle));

            angle += ((PlanetCelestialComponent) starSystemComponent).getOrbitalVelocity();
        }

    }
}
