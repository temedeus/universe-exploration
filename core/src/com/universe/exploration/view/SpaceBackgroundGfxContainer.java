package com.universe.exploration.view;
import com.universe.exploration.CoreConfiguration;

public class SpaceBackgroundGfxContainer extends GraphicsGfxContainer {
	public SpaceBackgroundGfxContainer() {
		this.spriteSize = CoreConfiguration.bgSize;
		this.graphicsSource = "spacebackground1.png";
		
		setup();
	}

}
