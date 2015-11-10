package com.universe.exploration.view;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.universe.exploration.GdxHelper;
import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.starsystem.StarSystem;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;


public class GameObjectCanvas {
	private SpriteBatch liveComponentBatch;
	private SpriteBatch backgroundBatch;
	private Sprite space;
	private BitmapFont font;
	private Sprite star;
	private StarSystem starSystem;
	private boolean gameStatusPaused;

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
	
	/**
	 * Generates graphical representation based on given star system
	 * 
	 * @param iua
	 */
	public GameObjectCanvas(StarSystem starSystem) {
		this.starSystem = starSystem;
		
		liveComponentBatch = new SpriteBatch();
		backgroundBatch = new SpriteBatch();
		
		backgroundCamera = new OrthographicCamera(1920, 1080);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        
		// Space background
		SpaceBackgroundGfxContainer spaceBgGFX = new SpaceBackgroundGfxContainer();
		space = spaceBgGFX.getSprite();
		
		// Generate system star.
		SystemStarGfxContainer ss = new SystemStarGfxContainer(this.starSystem.getSystemstar());
		
		star = ss.getSprite();

		List<PlanetCelestialComponent> listOfPlanets = starSystem.getPlanets();

		gameViewObjectContainer = new GameViewObjectContainer();
		
		for(PlanetCelestialComponent planet : listOfPlanets) {
			gameViewObjectContainer.addStarSystemObject(planet);
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
		
		//TODO: bg and other objects are handled in different batches because game is
		// suppose to feature zooming/moving camera in the future. Find out if 1 batch is okay after all
		
		handleBackgroundBatch();
		handleLiveComponentBatch();
	}
	
	public void handleLiveComponentBatch() {
		liveComponentBatch.setProjectionMatrix(camera.combined);
		liveComponentBatch.begin();
		
		// TODO: sort this offset. It likely has something to do with initiating the sprite and its offsets etc.
		float starX = GdxHelper.getScreenCenterX() - this.star.getScaleX() * 2 - 2800;
		float starY = GdxHelper.getScreenCenterY() - this.star.getScaleY() * 2 - 2600;
		
		star.rotate((float)0.1);
		star.setPosition(starX, starY);
		
		gameViewObjectContainer.update();
		
		// Background first, next star and then planets.
		
		star.draw(liveComponentBatch);

		for(Sprite sprite : gameViewObjectContainer.getPlanetSprites()) {
			sprite.draw(liveComponentBatch);
		}
		
		liveComponentBatch.end();
	}
	
	public void checkIfHitCoordinatesMatchPlanets() {
		int x1 = Gdx.input.getX();
		int y1 = Gdx.input.getY();
		Vector3 input = new Vector3(x1, y1, 0);
		camera.unproject(input);

		try {
			PlanetGfxContainer pgfx = gameViewObjectContainer.getPlanetWithCoordinatesWithinBoundaries(input);
			if(pgfx != null) {
				firePlanetClickListener(pgfx);
			}
		} catch(NullPointerException e) {
			
		}
	}
	
	private void firePlanetClickListener(PlanetGfxContainer pgfx) {
		planetClickListener.handleEventClassEvent(new UEEvent(pgfx));
	}
	
	public void handleBackgroundBatch() {
		backgroundBatch.setProjectionMatrix(backgroundCamera.combined);
		backgroundBatch.begin();
		
		space.draw(backgroundBatch);
		space.setPosition(-1000, -600);
		
		backgroundCamera.update();
		
		backgroundBatch.end();
	}

	/**
	 * Update camera on canvas
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
	 * @return the gameStatusPaused
	 */
	public boolean isGameStatusPaused() {
		return gameStatusPaused;
	}

	/**
	 * @param gameStatusPaused the gameStatusPaused to set
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
