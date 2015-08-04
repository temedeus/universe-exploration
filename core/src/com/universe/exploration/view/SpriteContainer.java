/**
 * 
 */
package com.universe.exploration.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.universe.exploration.starsystem.components.PlanetAbstractComponent;
import com.universe.exploration.starsystem.components.StarSystemComponent;

/**
 * 
 * Maintains all the sprites used in the current game scene
 * 
 * @author 4.8.2015 Teemu Puurunen 
 */
public class SpriteContainer {
	private ArrayList<GraphicsGfxContainer> graphicsGfxContainer;

	public SpriteContainer() {
		graphicsGfxContainer = new ArrayList<GraphicsGfxContainer>();
	}
	
	/**
	 * Add single star system object into arraylist
	 * @param starsystemObject
	 */
	public void addStarSystemObject(StarSystemComponent starSystemComponent) {

		if(starSystemComponent instanceof PlanetAbstractComponent) {
			graphicsGfxContainer.add(new PlanetGfxContainer(starSystemComponent));
		}	
	}
	
	/**
	 * Add multiple star system objects into arraylist
	 * @param starsystemObjects
	 */
	public void addMultipleStarSystemObjects(List<StarSystemComponent> starSystemComponents) {
		for(StarSystemComponent starSystemComponent : starSystemComponents) {
			addStarSystemObject(starSystemComponent);
		}
	}
	
	/**
	 * Draw sprites on screen and update their position and angles
	 * @param batch
	 */
	public void update(Batch batch) {
		for(GraphicsGfxContainer graphicsGfxContainer : graphicsGfxContainer) {
			graphicsGfxContainer.startBodyGfxModel.updateSpriteData();
		}
		
		for(GraphicsGfxContainer graphicsGfxContainer : graphicsGfxContainer) {
			graphicsGfxContainer.getSprite().draw(batch);
		}
	}
}
