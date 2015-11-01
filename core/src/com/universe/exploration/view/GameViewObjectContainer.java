/**
 * 
 */
package com.universe.exploration.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.universe.exploration.starsystem.components.CelestialComponent;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * 
 * Maintains all the views used in the current game scene.
 * Basically works as a controller between model and view.
 * 
 * @author 4.8.2015 Teemu Puurunen 
 */
public class GameViewObjectContainer {
	/** 
	 * Planets
	 */
	private ArrayList<PlanetGfxContainer> graphicsGfxContainer;

	public GameViewObjectContainer() {
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

	public ArrayList<String> getPlanetNames() {
		ArrayList<String> names = new ArrayList<String>();
		
		for(PlanetGfxContainer graphicsGfx : graphicsGfxContainer) {
			String tmp = ((PlanetCelestialComponent)graphicsGfx.getCelestialBodyGfxModel().getStarSystemComponent()).getComponentName();
			names.add(tmp);
		}
		
		return names;
	}
	
	/**
	 * Returns null if no matchin planet found.
	 * @param coordinates
	 * @return
	 */
	public PlanetGfxContainer getPlanetWithCoordinatesWithinBoundaries(Vector3 coordinates) {
		for(PlanetGfxContainer graphicsGfx : graphicsGfxContainer) {
			if(graphicsGfx instanceof PlanetGfxContainer) {
				try {
					Rectangle planetRectangle = graphicsGfx.getSprite().getBoundingRectangle();
					planetRectangle.x -= 5;
					planetRectangle.y -= 5;
					planetRectangle.width += 50;
					planetRectangle.height += 50;
					
					if(planetRectangle.contains(coordinates.x, coordinates.y)) {
						return graphicsGfx;
					}
				} catch(NullPointerException e) { }	
			}
		}
		
		return null;
	}
}
