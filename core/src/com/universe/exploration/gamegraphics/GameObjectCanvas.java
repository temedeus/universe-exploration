package com.universe.exploration.gamegraphics;

import java.util.List;

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
import com.universe.exploration.starsystem.StarSystem;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

public class GameObjectCanvas {
    private SpriteBatch liveComponentBatch;
    private SpriteBatch backgroundBatch;
    private Sprite space;

    private StarSystem starSystem;

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

    private float varyingRadius = 10;

    private StarWrapper starWrapper;

    /**
     * Generates graphical representation based on given star system
     * 
     * @param iua
     */
    public GameObjectCanvas(StarSystem starSystem) {
	initBasicSetup();

	this.starSystem = starSystem;

	// Space background
	SpaceBackgroundGfxContainer spaceBgGFX = new SpaceBackgroundGfxContainer();
	space = spaceBgGFX.getSprite();

	// Generate system star.
	SystemStarGfxContainer ss = new SystemStarGfxContainer(this.starSystem.getSystemstar());

	starWrapper = new StarWrapper(ss.getSprite());

	List<PlanetCelestialComponent> listOfPlanets = starSystem.getPlanets();

	planetHandler = new PlanetHandler();

	for (PlanetCelestialComponent planet : listOfPlanets) {
	    planetHandler.addStarPlanet(planet);
	}

	// Initially select the first planet (visual borders).
	if (listOfPlanets.size() > 0) {
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
	planetHandler.update(selectedPlanet.getSelectedPlanet());

	// Background first, next star and then planets.

	starWrapper.getStar().draw(liveComponentBatch);

	for (Sprite sprite : planetHandler.getPlanetSprites()) {
	    sprite.draw(liveComponentBatch);
	}

	if (UniverseExploration.gameStatus.isPlanetaryMovementActive()) {
	    drawEnhancement();
	}

	liveComponentBatch.end();
    }

    /**
     * Emphasize planet. (Create a pulsating circle around it.)
     */
    private void drawEnhancement() {
	varyingRadius += 1;
	if (varyingRadius >= 60) {
	    varyingRadius = 10;
	}

	if (planetHandler.getPlanetCount() > 0) {
	    shapeRenderer.setColor(Color.BLUE);
	    shapeRenderer.begin(ShapeType.Line);
	    shapeRenderer.circle(selectedPlanet.getSelectedPlanet().getSprite().getX()
		    + selectedPlanet.getSelectedPlanet().getSprite().getWidth() / 2, selectedPlanet.getSelectedPlanet().getSprite().getY()
		    + selectedPlanet.getSelectedPlanet().getSprite().getHeight() / 2, varyingRadius);
	    shapeRenderer.end();
	}
    }

    public void checkIfHitCoordinatesMatchPlanets() {
	int x1 = Gdx.input.getX();
	int y1 = Gdx.input.getY();
	Vector3 input = new Vector3(x1, y1, 0);
	camera.unproject(input);

	try {
	    PlanetGfxContainer pgfx = planetHandler.getPlanetWithCoordinatesWithinBoundaries(input);
	    if (pgfx != null) {
		firePlanetClickListener(pgfx);
	    }
	} catch (NullPointerException e) {

	}
    }

    public void setSelectedPlanet(PlanetCelestialComponent planet) {
	selectedPlanet.setSelectedPlanet(planetHandler.getPlanetGfxContainerByComponent(planet));
    }

    private void firePlanetClickListener(PlanetGfxContainer pgfx) {
	selectedPlanet.setSelectedPlanet(pgfx);
	planetClickListener.handleEventClassEvent(new UEEvent(pgfx));
    }

    public void handleBackgroundBatch() {
	backgroundBatch.setProjectionMatrix(backgroundCamera.combined);
	backgroundBatch.begin();

	space.draw(backgroundBatch);
	space.setPosition(CoreConfiguration.SPACE_BACKGROUND_POSITION_X, CoreConfiguration.SPACE_BACKGROUND_POSITION_Y);

	drawSelectedPlanetWhenValid();

	backgroundCamera.update();
	backgroundBatch.end();
    }

    private void drawSelectedPlanetWhenValid() {
	// It is perfectly normal scenario that there is no selected planet (no
	// one ever instantiates it - hence the null check).
	if (selectedPlanet != null) {
	    boolean hidePlanet = !UniverseExploration.gameStatus.isPlanetaryMovementActive() && starWrapper.isAlphaReachedMinimum();

	    selectedPlanet.handleAlpha((hidePlanet) ? false : true);
	    selectedPlanet.getSelectedPlanet().getEnlarged().draw(backgroundBatch);
	}
    }

    /**
     * Update camera on canvas
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
     * @param planetClickListener
     *            the planetClickListener to set
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
