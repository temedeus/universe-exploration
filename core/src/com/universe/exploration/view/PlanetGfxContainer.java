package com.universe.exploration.view;

import com.universe.exploration.starsystem.components.StarSystemComponent;

public class PlanetGfxContainer extends GraphicsGfxContainer {
	public PlanetGfxContainer() {
		super(64, "planet2.png");
	}
	
	public PlanetGfxContainer(StarSystemComponent starSystemComponent) {
		super(starSystemComponent);
	}
}
