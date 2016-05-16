/**
 * 
 */
package com.universe.exploration.spritecontainer;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
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
    private List<PlanetSpriteContainer> planetContainer;

    /**
     * Constructor cleans the planet list.
     */
    public PlanetHandler() {
	planetContainer = new ArrayList<PlanetSpriteContainer>();
    }

    /**
     * Add single star system object into arraylist
     * 
     * @param starsystemObject
     */
    public void addStarPlanet(CelestialComponent starSystemComponent) {

	if (starSystemComponent instanceof PlanetCelestialComponent) {
	    planetContainer.add(new PlanetSpriteContainer(starSystemComponent));
	}
    }

    public PlanetSpriteContainer getPlanetGfxContainerAtIndex(int i) {
	return planetContainer.get(i);
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

    /**
     * Draw sprites on screen and update their position and angles
     */
    public void update(PlanetSpriteContainer selected) {
	if (!planetaryMovement)
	    return;

	for (PlanetSpriteContainer graphicsGfx : planetContainer) {
	    graphicsGfx.setPlanetSelected(graphicsGfx.equals(selected));
	    graphicsGfx.getCelestialBodyGfxModel().updateSpriteData();
	    graphicsGfx.updateSpritePosition();
	    graphicsGfx.handleZooming();
	}
    }

    public PlanetSpriteContainer getPlanetGfxContainerByComponent(PlanetCelestialComponent planet) {
	for (PlanetSpriteContainer graphcisGfx : planetContainer) {
	    if (graphcisGfx.getComponentType() == planet) {
		return graphcisGfx;
	    }
	}

	return null;
    }

    /**
     * Draw sprites on the given spritebatch.
     * 
     * @param batch
     */
    public void drawPlanets(SpriteBatch batch) {
	for (PlanetSpriteContainer graphicsGfx : planetContainer) {
	    graphicsGfx.getSprite().draw(batch);
	}
    }

    /**
     * Returns null if no matching planet found.
     * 
     * @param coordinates
     * @return
     */
    public PlanetSpriteContainer getPlanetWithCoordinatesWithinBoundaries(Vector3 coordinates) {
	for (PlanetSpriteContainer graphicsGfx : planetContainer) {
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
	return planetContainer.size();
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

    /**
     * Draws orbit that the planet follows.
     * 
     * @param selected
     *            Selected planet.
     * @param render
     *            Shaperenderer with the appropriate camera combined.
     * 
     */
    public void drawOrbits(PlanetSpriteContainer selected, ShapeRenderer render) {
	for (PlanetSpriteContainer graphicsGfx : planetContainer) {
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
