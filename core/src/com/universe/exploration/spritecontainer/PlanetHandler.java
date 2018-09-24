/**
 *
 */
package com.universe.exploration.spritecontainer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Maintains planets.
 *
 * @author 15.1.2017 Teemu Puurunen
 */
public class PlanetHandler {
    private boolean planetaryMovement = true;

    /**
     * Planets
     */
    private List<PlanetSprite> planets;

    /**
     * Constructor cleans the planet list.
     */
    public PlanetHandler() {
        planets = new ArrayList<>();
    }

    /**
     * Add single star system object into arraylist
     *
     * @param celestialComponent
     */
    public void addPlanet(PlanetCelestialComponent celestialComponent) {
        planets.add(new PlanetSprite(celestialComponent));
    }

    public PlanetSprite getPlanetAtIndex(int i) {
        return planets.get(i);
    }

    /**
     * Draw sprites on screen and update their position and angles
     */
    public void update(PlanetSprite selected) {
        if (!planetaryMovement)
            return;

        planets.forEach(planet -> {
            planet.setPlanetSelected(planet.equals(selected));
            planet.getSpriteContainerState().updateSpriteData();
            planet.updateSpritePosition();
            planet.handleZooming();
        });
    }

    public PlanetSprite getPlanetSpriteByComponent(PlanetCelestialComponent planet) {
        return planets.stream()
                .filter(planetSprite -> planetSprite.getSpriteContainerState().getStarSystemComponent() == planet)
                .findFirst()
                .orElse(null);
    }

    /**
     * Draw sprites on the given spritebatch.
     *
     * @param batch
     */
    public void drawPlanets(SpriteBatch batch) {
        planets.forEach(planet -> planet.getSprite().draw(batch));
    }

    /**
     * Returns null if no matching planet found.
     *
     * @param coordinates
     * @return
     */
    public PlanetSprite getPlanetWithCoordinatesWithinBoundaries(Vector3 coordinates) {
        for (PlanetSprite planetSprite : planets) {
            // Enlarge hit area a wee bit.
            Rectangle planetRectangle = planetSprite.getSprite().getBoundingRectangle();
            planetRectangle.x -= 5;
            planetRectangle.y -= 5;
            planetRectangle.width += 50;
            planetRectangle.height += 50;

            if (planetRectangle.contains(coordinates.x, coordinates.y)) {
                return planetSprite;
            }
        }

        return null;
    }

    public int getPlanetCount() {
        return planets.size();
    }

    /**
     * @param planetaryMovement the planetaryMovement to set
     */
    public void setPlanetaryMovement(boolean planetaryMovement) {
        this.planetaryMovement = planetaryMovement;
    }

    /**
     * Draws orbit that the planet follows.
     *
     * @param selected Selected planet.
     * @param render   Shaperenderer with the appropriate camera combined.
     */
    public void drawOrbits(PlanetSprite selected, ShapeRenderer render) {
        for (PlanetSprite planetSprite : planets) {
            float radius = (float) ((PlanetCelestialComponent) planetSprite.spriteContainerState
                    .getStarSystemComponent()).getOrbitalRadius();

            float alpha = (planetSprite.equals(selected)) ? 0.5f : 0.1f;

            render.setColor(new Color(255, 255, 255, alpha));
            render.begin(ShapeType.Line);
            render.circle(0f, 0f, radius);

            render.end();
        }

    }
}
