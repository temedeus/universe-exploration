package com.universe.exploration.view;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.starsystem.StarSystem;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

public class GameObjectCanvas {
	private SpriteBatch liveComponentBatch;
	private SpriteBatch backgroundBatch;
	private Sprite space;
	private BitmapFont font;

	private StarSystem starSystem;
	private boolean gameStatusPaused;
	private ShapeRenderer shapeRenderer;
	private PlanetGfxContainer selectedPlanet;

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
	private GameViewObjectContainer gameViewObjectContainer;

	private UEListener planetClickListener;

	private float varyingRadius = 10;

	private StarWrapper starWrapper;

	/**
	 * Generates graphical representation based on given star system
	 * 
	 * @param iua
	 */
	public GameObjectCanvas(StarSystem starSystem) {
		this.starSystem = starSystem;

		// For drawing miscellaneous shapes.
		shapeRenderer = new ShapeRenderer();

		liveComponentBatch = new SpriteBatch();
		backgroundBatch = new SpriteBatch();

		backgroundCamera = new OrthographicCamera(1920, 1080);

		font = new BitmapFont();
		font.setColor(Color.WHITE);

		// Space background
		SpaceBackgroundGfxContainer spaceBgGFX = new SpaceBackgroundGfxContainer();
		space = spaceBgGFX.getSprite();

		// Generate system star.
		SystemStarGfxContainer ss = new SystemStarGfxContainer(
				this.starSystem.getSystemstar());

		starWrapper = new StarWrapper(ss.getSprite());

		List<PlanetCelestialComponent> listOfPlanets = starSystem.getPlanets();

		gameViewObjectContainer = new GameViewObjectContainer();

		for (PlanetCelestialComponent planet : listOfPlanets) {
			gameViewObjectContainer.addStarPlanet(planet);
		}

		// Initially select the first planet (visual borders).
		if (listOfPlanets.size() > 0) {
			selectedPlanet = gameViewObjectContainer
					.getPlanetGfxContainerAtIndex(0);
		}

		gameStatusPaused = false;
	}

	public void destroy() {
		liveComponentBatch.dispose();
		backgroundBatch.dispose();
		font.dispose();
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

		gameViewObjectContainer.setPlanetaryMovement(UniverseExploration.planetaryMovement);
		gameViewObjectContainer.update();

		// Background first, next star and then planets.

		starWrapper.getStar().draw(liveComponentBatch);

		for (Sprite sprite : gameViewObjectContainer.getPlanetSprites()) {
			sprite.draw(liveComponentBatch);
		}

		if (UniverseExploration.planetaryMovement) {
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

		if (gameViewObjectContainer.getPlanetCount() > 0) {
			shapeRenderer.setColor(Color.BLUE);
			shapeRenderer.begin(ShapeType.Line);
			shapeRenderer
					.circle(selectedPlanet.getSprite().getX()
							+ selectedPlanet.getSprite().getWidth() / 2,
							selectedPlanet.getSprite().getY()
									+ selectedPlanet.getSprite().getHeight()
									/ 2, varyingRadius);
			shapeRenderer.end();
		}
	}

	public void checkIfHitCoordinatesMatchPlanets() {
		int x1 = Gdx.input.getX();
		int y1 = Gdx.input.getY();
		Vector3 input = new Vector3(x1, y1, 0);
		camera.unproject(input);

		try {
			PlanetGfxContainer pgfx = gameViewObjectContainer
					.getPlanetWithCoordinatesWithinBoundaries(input);
			if (pgfx != null) {
				firePlanetClickListener(pgfx);
			}
		} catch (NullPointerException e) {

		}
	}

	public void setSelectedPlanet(PlanetCelestialComponent planet) {
		selectedPlanet = gameViewObjectContainer.getPlanetGfxContainerByComponent(planet);
	}

	private void firePlanetClickListener(PlanetGfxContainer pgfx) {
		planetClickListener.handleEventClassEvent(new UEEvent(pgfx));
	}

	public void handleBackgroundBatch() {
		backgroundBatch.setProjectionMatrix(backgroundCamera.combined);
		backgroundBatch.begin();

		space.draw(backgroundBatch);
		space.setPosition(-1000, -600);

		if(!UniverseExploration.planetaryMovement) {
			selectedPlanet.getEnlarged().draw(backgroundBatch);
		}
		
		backgroundCamera.update();
		backgroundBatch.end();
	}

	private void setupZoomedInPlanet() {
		float x = 800;
		float y = 600;
		Texture texture = new Texture(Gdx.files.internal(selectedPlanet.getGraphicsSource()));
		Sprite sprite = new Sprite(texture);
		sprite.setSize(x, y);
		sprite.setOrigin(x / 2, y / 2);
		sprite.setPosition(50, 50);
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
	 * @return the gameStatusPaused
	 */
	public boolean isGameStatusPaused() {
		return gameStatusPaused;
	}

	/**
	 * @param gameStatusPaused
	 *            the gameStatusPaused to set
	 */
	public void setGameStatusPaused(boolean gameStatusPaused) {
		this.gameStatusPaused = gameStatusPaused;
	}

	/**
	 * @return the gameViewObjectContainer
	 */
	public GameViewObjectContainer getGameViewObjectContainer() {
		return gameViewObjectContainer;
	}
}
