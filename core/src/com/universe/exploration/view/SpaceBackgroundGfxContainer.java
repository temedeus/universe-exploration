package com.universe.exploration.view;
import com.universe.exploration.CoreConfiguration;
import com.universe.exploration.common.tools.RandomizationTools;

public class SpaceBackgroundGfxContainer extends GraphicsGfxContainer {
	public SpaceBackgroundGfxContainer() {
		super(CoreConfiguration.bgSize ,"spacebackground.png");
		int index = RandomizationTools.getRandomInteger(1, 2);
		
		graphicsSource = (index == 1) ? "spacebackground.png" : "spacebackground2.png";
		setupSprite();
	}

}
