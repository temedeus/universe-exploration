/**
 * 
 */
package com.universe.exploration.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.starsystem.components.CelestialComponent;

/**
 * 
 * Maintains all the sprites used in the current game scene.
 * Basically works as a controller between model and view.
 * 
 * @author 4.8.2015 Teemu Puurunen 
 */
public class SpriteContainer {
	/** 
	 * Planets
	 */
	private ArrayList<PlanetGfxContainer> graphicsGfxContainer;

	public SpriteContainer() {
		graphicsGfxContainer = new ArrayList<PlanetGfxContainer>();
	}
	
	/**
	 * Add single star system object into arraylist
	 * @param starsystemObject
	 */
	public void addStarSystemObject(CelestialComponent starSystemComponent) {

		if(starSystemComponent instanceof PlanetCelestialComponent) {
			graphicsGfxContainer.add(new PlanetGfxContainer(starSystemComponent));
		}	
	}
	
	/**
	 * Add multiple star system objects into arraylist
	 * @param starsystemObjects
	 */
	public void addMultipleStarSystemObjects(List<CelestialComponent> starSystemComponents) {
		for(CelestialComponent starSystemComponent : starSystemComponents) {
			addStarSystemObject(starSystemComponent);
		}
	}
	
	public ArrayList<Sprite> getPlanetSprites() {
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		for(PlanetGfxContainer graphicsGfx : graphicsGfxContainer) {
			sprites.add(graphicsGfx.getSprite());
		
		}
		return sprites;
	}
	
	/**
	 * Draw sprites on screen and update their position and angles
	 */
	public void update() {
		for(PlanetGfxContainer graphicsGfx : graphicsGfxContainer) {
			if(graphicsGfx instanceof PlanetGfxContainer) {
				try {
					graphicsGfx.getCelestialBodyGfxModel().updateSpriteData();
					graphicsGfx.updateSpritePosition();
					
				} catch(NullPointerException e) { }	
			}
		}
	}
}
