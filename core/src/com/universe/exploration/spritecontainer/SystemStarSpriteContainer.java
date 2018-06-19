package com.universe.exploration.spritecontainer;

import com.universe.exploration.starsystem.components.CelestialComponent;

public class SystemStarSpriteContainer extends SpriteContainer {
    public SystemStarSpriteContainer() {
        super(4096, "star1");
    }

    public SystemStarSpriteContainer(CelestialComponent starSystemComponent) {
        super(starSystemComponent);
    }
}
