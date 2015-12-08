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
 * TODO: currently deals with planets only :D FIX THIS!!! 
 * ...or rename it at least :D
 * 
 * @author 4.8.2015 Teemu Puurunen 
 */
public class GameViewObjectContainer {
	private boolean planetaryMovement = true;
	
	/** 
	 * Planets
	 */
	private ArrayList<PlanetGfxContainer> planetGfxContainer;

	public GameViewObjectContainer() {
		planetGfxContainer = new ArrayList<PlanetGfxContainer>();
	}
	
	/**
	 * Add single star system object into arraylist
	 * @param starsystemObject
	 */
	public void addStarPlanet(CelestialComponent starSystemComponent) {

		if(starSystemComponent instanceof PlanetCelestialComponent) {
			planetGfxContainer.add(new PlanetGfxContainer(starSystemComponent));
		}	
	}
	
	public PlanetGfxContainer getPlanetGfxContainerAtIndex(int i) {
		return planetGfxContainer.get(i);
	}
	
	/**
	 * Add multiple star system objects into arraylist
	 * @param starsystemObjects
	 */
	public void addMultiplePlanets(List<CelestialComponent> starSystemComponents) {
		for(CelestialComponent starSystemComponent : starSystemComponents) {
			addStarPlanet(starSystemComponent);
		}
	}
	
	public ArrayList<Sprite> getPlanetSprites() {
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		for(PlanetGfxContainer graphicsGfx : planetGfxContainer) {
			sprites.add(graphicsGfx.getSprite());
		
		}
		return sprites;
	}
	
	/**
	 * Draw sprites on screen and update their position and angles
	 */
	public void update() {
		if(!planetaryMovement)
			return;
		
		for(PlanetGfxContainer graphicsGfx : planetGfxContainer) {
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
		
		for(PlanetGfxContainer graphicsGfx : planetGfxContainer) {
			String tmp = ((PlanetCelestialComponent)graphicsGfx.getCelestialBodyGfxModel().getStarSystemComponent()).getComponentName();
			names.add(tmp);
		}
		
		return names;
	}
	
	public PlanetGfxContainer getPlanetGfxContainerByComponent(PlanetCelestialComponent planet) {
		for(PlanetGfxContainer graphcisGfx : planetGfxContainer) {
			if(graphcisGfx.getComponentType() == planet) {
				return graphcisGfx;
			}
		}
		
		return null;
	}
	/**
	 * Returns null if no matching planet found.
	 * @param coordinates
	 * @return
	 */
	public PlanetGfxContainer getPlanetWithCoordinatesWithinBoundaries(Vector3 coordinates) {
		for(PlanetGfxContainer graphicsGfx : planetGfxContainer) {
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
				} catch(NullPointerException e) { 
					// Shouldn't happen
				}
			}
		}
		
		return null;
	}
	
	public int getPlanetCount() {
		return planetGfxContainer.size();
	}
}
