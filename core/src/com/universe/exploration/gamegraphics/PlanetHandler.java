/**
 * 
 */
package com.universe.exploration.gamegraphics;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.universe.exploration.starsystem.components.CelestialComponent;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

/**
 * 
 * Maintains all the views used in the current game scene. Basically works as a
 * controller between model and view.
 * 
 * 
 * @author 4.8.2015 Teemu Puurunen
 */
public class PlanetHandler {
    private boolean planetaryMovement = true;

    /**
     * Planets
     */
    private List<PlanetGfxContainer> planetGfxContainer;

    private List<Sprite> orbitEnhancer = new ArrayList<Sprite>();

    public PlanetHandler() {
	planetGfxContainer = new ArrayList<PlanetGfxContainer>();

	// orbitEnhancer.add(new Sprite(new Texture(Gdx.app.)))
    }

    /**
     * Add single star system object into arraylist
     * 
     * @param starsystemObject
     */
    public void addStarPlanet(CelestialComponent starSystemComponent) {

	if (starSystemComponent instanceof PlanetCelestialComponent) {
	    planetGfxContainer.add(new PlanetGfxContainer(starSystemComponent));
	}
    }

    public PlanetGfxContainer getPlanetGfxContainerAtIndex(int i) {
	return planetGfxContainer.get(i);
    }

    /**
     * Add multiple star system objects into arraylist
     * 
     * @param starsystemObjects
     */
    public void addMultiplePlanets(List<CelestialComponent> starSystemComponents) {
	for (CelestialComponent starSystemComponent : starSystemComponents) {
	    addStarPlanet(starSystemComponent);
	}
    }

    private List<Sprite> getPlanetSprites() {
	List<Sprite> sprites = new ArrayList<Sprite>();
	for (PlanetGfxContainer graphicsGfx : planetGfxContainer) {
	    sprites.add(graphicsGfx.getSprite());
	}
	return sprites;
    }

    /**
     * Draw sprites on screen and update their position and angles
     */
    public void update(PlanetGfxContainer selected) {
	if (!planetaryMovement)
	    return;

	for (PlanetGfxContainer graphicsGfx : planetGfxContainer) {
	    graphicsGfx.setPlanetSelected(graphicsGfx.equals(selected));
	    graphicsGfx.getCelestialBodyGfxModel().updateSpriteData();
	    graphicsGfx.updateSpritePosition();
	    graphicsGfx.handleZooming();
	}
    }

    public PlanetGfxContainer getPlanetGfxContainerByComponent(PlanetCelestialComponent planet) {
	for (PlanetGfxContainer graphcisGfx : planetGfxContainer) {
	    if (graphcisGfx.getComponentType() == planet) {
		return graphcisGfx;
	    }
	}

	return null;
    }

    public void drawPlanets(SpriteBatch batch) {
	for (Sprite sprite : getPlanetSprites()) {
	    sprite.draw(batch);
	}
    }

    /**
     * Returns null if no matching planet found.
     * 
     * @param coordinates
     * @return
     */
    public PlanetGfxContainer getPlanetWithCoordinatesWithinBoundaries(Vector3 coordinates) {
	for (PlanetGfxContainer graphicsGfx : planetGfxContainer) {
	    // Enlarge hit area a wee bit.
	    Rectangle planetRectangle = graphicsGfx.getSprite().getBoundingRectangle();
	    planetRectangle.x -= 5;
	    planetRectangle.y -= 5;
	    planetRectangle.width += 50;
	    planetRectangle.height += 50;

	    if (planetRectangle.contains(coordinates.x, coordinates.y)) {
		return graphicsGfx;
	    }
	}

	return null;
    }

    public int getPlanetCount() {
	return planetGfxContainer.size();
    }

    /**
     * @return the planetaryMovement
     */
    public boolean isPlanetaryMovement() {
	return planetaryMovement;
    }

    /**
     * @param planetaryMovement
     *            the planetaryMovement to set
     */
    public void setPlanetaryMovement(boolean planetaryMovement) {
	this.planetaryMovement = planetaryMovement;
    }

    public void drawOrbits(PlanetGfxContainer selected, ShapeRenderer render) {
	for (PlanetGfxContainer graphicsGfx : planetGfxContainer) {
	    float radius = (float) ((PlanetCelestialComponent) graphicsGfx.celestialBodyGfxModel.getStarSystemComponent())
		    .getOrbitalRadius();

	    float alpha = (graphicsGfx.equals(selected)) ? 0.5f : 0.1f;

	    render.setColor(new Color(255, 255, 255, alpha));
	    render.begin(ShapeType.Line);
	    render.circle(0f, 0f, radius);
	    render.end();
	}

    }
}
