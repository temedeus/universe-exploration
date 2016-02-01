package com.universe.exploration.gamegraphics;

import com.universe.exploration.starsystem.components.CelestialComponent;

public class SystemStarGfxContainer extends GraphicsGfxContainer {
    public SystemStarGfxContainer() {
	super(4096, "star1");
    }

    public SystemStarGfxContainer(CelestialComponent starSystemComponent) {
	super(starSystemComponent);
    }
}
