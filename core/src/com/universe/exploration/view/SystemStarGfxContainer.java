package com.universe.exploration.view;

import com.universe.exploration.starsystem.components.StarSystemComponent;

public class SystemStarGfxContainer extends GraphicsGfxContainer {	
	public SystemStarGfxContainer() {
		super(4096, "star1");
	}
	
	public SystemStarGfxContainer(StarSystemComponent starSystemComponent) {
		super(starSystemComponent);
	}
}
