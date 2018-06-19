package com.universe.exploration.spritecontainer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.common.CoreConfiguration;
import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

import java.util.List;

/**
 * This canvas contains planets, system star etc.
 *
 * @author 15.5.2016 Teemu Puurunen
 */
public class GameObjectCanvas {
    private SpriteBatch liveComponentBatch;
    private SpriteBatch backgroundBatch;
    private Sprite space;

    private ShapeRenderer shapeRenderer;

    private SelectedPlanet selectedPlanet;
    /**
     * Camera describing planets etc.
     */
    private OrthographicCamera camera;

    /**
     * Stars background camera (should not move as the other objects do)
     */
    private OrthographicCamera backgroundCamera;

    /**
     * Contains all the game objects (e.g. planets, stars and whatnot)
     */
    private PlanetHandler planetHandler;

    private UEListener planetClickListener;

    private float varyingRadius = CoreConfiguration.PLANET_ENHANCEMENT_MIN;

    private StarWrapper starWrapper;

    /**
     * Generates graphical representation based on given star system
     *
     * @param iua
     */
    public GameObjectCanvas() {
        initBasicSetup();

        // Space background
        SpaceSpriteContainer spaceBgGFX = new SpaceSpriteContainer();
        space = spaceBgGFX.getSprite();

        // Generate system star.
        SystemStarSpriteContainer starSpriteContainer = new SystemStarSpriteContainer(
                UniverseExploration.gameStatus.getStarSystem().getSystemstar());

        starWrapper = new StarWrapper(starSpriteContainer.getSprite());

        List<PlanetCelestialComponent> planetList = UniverseExploration.gameStatus.getStarSystem().getPlanets();

        planetHandler = new PlanetHandler();

        for (PlanetCelestialComponent planet : planetList) {
            planetHandler.addPlanet(planet);
        }

        // Initially select the first planet (visual borders).
        if (planetList.size() > 0) {
            selectedPlanet = new SelectedPlanet();
            selectedPlanet.setSelectedPlanet(planetHandler.getPlanetGfxContainerAtIndex(0));
        }
    }

    private void initBasicSetup() {
        // For drawing miscellaneous shapes.
        shapeRenderer = new ShapeRenderer();

        liveComponentBatch = new SpriteBatch();
        backgroundBatch = new SpriteBatch();

        backgroundCamera = new OrthographicCamera(1920, 1080);

    }

    public void destroy() {
        liveComponentBatch.dispose();
        backgroundBatch.dispose();
    }

    /**
     * Render game
     */
    public void drawGameContent() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // TODO: bg and other objects are handled in different batches because
        // game is
        // suppose to feature zooming/moving camera in the future. Find out if 1
        // batch is okay after all

        handleBackgroundBatch();
        handleLiveComponentBatch();
    }

    public void handleLiveComponentBatch() {
        liveComponentBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        liveComponentBatch.begin();

        starWrapper.update();

        planetHandler.setPlanetaryMovement(UniverseExploration.gameStatus.isPlanetaryMovementActive());

        // Background first, next star and then planets.
        starWrapper.getStar().draw(liveComponentBatch);
        planetHandler.drawPlanets(liveComponentBatch);

        if (UniverseExploration.gameStatus.isPlanetaryMovementActive()) {
            drawEnhancement();
        }

        // It is perfectly normal scenario that there is no selected planet.
        if (selectedPlanet != null) {
            planetHandler.drawOrbits(selectedPlanet.getSelectedPlanet(), shapeRenderer);
            planetHandler.update(selectedPlanet.getSelectedPlanet());
        }

        liveComponentBatch.end();
    }

    /**
     * Emphasize planet create a pulsating circle around it).
     */
    private void drawEnhancement() {
        varyingRadius += CoreConfiguration.PLANET_ENHANCEMENT_INC;
        if (varyingRadius >= CoreConfiguration.PLANET_ENHANCEMENT_MAX) {
            varyingRadius = CoreConfiguration.PLANET_ENHANCEMENT_MIN;
        }

        if (planetHandler.getPlanetCount() > 0) {
            Color navyBlue = new Color(0, 0, 0.5f, 0.7f);
            shapeRenderer.setColor(navyBlue);
            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.circle(
                    selectedPlanet.getSelectedPlanet().getSprite().getX() + selectedPlanet.getSelectedPlanet().getSprite().getWidth() / 2,
                    selectedPlanet.getSelectedPlanet().getSprite().getY() + selectedPlanet.getSelectedPlanet().getSprite().getHeight() / 2,
                    varyingRadius);
            shapeRenderer.end();
        }
    }

    public void checkIfHitCoordinatesMatchPlanets() {
        int x1 = Gdx.input.getX();
        int y1 = Gdx.input.getY();
        Vector3 input = new Vector3(x1, y1, 0);
        camera.unproject(input);

        try {
            PlanetSprite planetSprite = planetHandler.getPlanetWithCoordinatesWithinBoundaries(input);
            if (planetSprite != null) {
                firePlanetClickListener(planetSprite);
            }
        } catch (NullPointerException e) {
            // TODO: verify if this null check is useless.
        }
    }

    public void setSelectedPlanet(PlanetCelestialComponent planet) {
        selectedPlanet.setSelectedPlanet(planetHandler.getPlanetSpriteByComponent(planet));
    }

    private void firePlanetClickListener(PlanetSprite pgfx) {
        selectedPlanet.setSelectedPlanet(pgfx);
        planetClickListener.handleEventClassEvent(new UEEvent(pgfx));
    }

    public void handleBackgroundBatch() {
        backgroundBatch.setProjectionMatrix(backgroundCamera.combined);
        backgroundBatch.begin();

        space.draw(backgroundBatch);
        space.setPosition(CoreConfiguration.SPACE_BACKGROUND_POSITION_X, CoreConfiguration.SPACE_BACKGROUND_POSITION_Y);

        drawSelectedEnlargedPlanet();

        backgroundCamera.update();
        backgroundBatch.end();
    }

    /**
     * <p>
     * Draw close-up of the selected planet. This version of the planet is drawn
     * when you select planet details.
     * </p>
     * <p>
     * Note! Do not confuse with the larger planet that is found in the main
     * game screen when you select a planet from the planet selection.
     * </p>
     */
    private void drawSelectedEnlargedPlanet() {
        // It is perfectly normal scenario that there is no selected planet.
        if (selectedPlanet != null) {
            // Draw the planet only if planetary movement is paused and the star
            // has been set invisible already (otherwise this looks kind of
            // goofy).
            boolean hidePlanet = !UniverseExploration.gameStatus.isPlanetaryMovementActive() && starWrapper.isAlphaReachedMinimum();

            selectedPlanet.handleAlpha((hidePlanet) ? false : true);
            selectedPlanet.getSelectedPlanet().getEnlarged().draw(backgroundBatch);
        }
    }

    /**
     * Update camera on canvas.
     *
     * @param cam
     */
    public void updateCameraOnCanvas(OrthographicCamera cam) {
        this.camera = cam;
    }

    /**
     * @return the planetClickListener
     */
    public UEListener getPlanetClickListener() {
        return planetClickListener;
    }

    /**
     * @param planetClickListener the planetClickListener to set
     */
    public void setPlanetClickListener(UEListener planetClickListener) {
        this.planetClickListener = planetClickListener;
    }

    /**
     * @return the gameViewObjectContainer
     */
    public PlanetHandler getGameViewObjectContainer() {
        return planetHandler;
    }
}
