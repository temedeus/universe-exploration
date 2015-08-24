package com.universe.exploration.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.universe.exploration.starsystem.StarSystem;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.starsystem.components.CelestialComponent;

public class Canvas {
	private SpriteBatch liveComponentBatch;
	private SpriteBatch backgroundBatch;
	private Sprite space;
	private BitmapFont font;
	private Sprite star;
	private StarSystem ua;
	
	private OrthographicCamera camera;
	
	private OrthographicCamera backgroundCamera;
	
	private PlanetGfxContainer[] planetcontainer;
	
	private SpriteContainer spriteContainer;
	
	/**
	 * Generates graphical representation based on given star system
	 * 
	 * @param iua
	 */
	public Canvas(StarSystem iua) {
		this.ua = iua;
		
		liveComponentBatch = new SpriteBatch();
		backgroundBatch = new SpriteBatch();
		
		backgroundCamera = new OrthographicCamera(1920, 1080);

        font = new BitmapFont();
        font.setColor(Color.WHITE);

		// Space background
		SpaceBackgroundGfxContainer spaceBgGFX = new SpaceBackgroundGfxContainer();
		space = spaceBgGFX.getSprite();
		
		// Generate system star.
		SystemStarGfxContainer ss = new SystemStarGfxContainer(this.ua.getSystemstar());
		
		star = ss.getSprite();

		List<PlanetCelestialComponent> listOfPlanets = ua.getPlanets();

		spriteContainer = new SpriteContainer();
		
		for(PlanetCelestialComponent planet : listOfPlanets) {
			spriteContainer.addStarSystemObject(planet);
		}

	}
	
	public void destroy() {
		this.liveComponentBatch.dispose();
		this.font.dispose();
	}
	
	/**
	 * Get screen center X
	 * @return float
	 */
	public float getScreenCenterX() {
		float w = Gdx.graphics.getWidth();
		return (w/2);
	}
	
	/**
	 * Get screen center Y
	 * @return float
	 */
	public float getScreenCenterY() {
        float h = Gdx.graphics.getHeight();
        return (h/2);
	}
	
	/**
	 * Render game
	 */
	public void drawGameContent() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		backgroundBatch.setProjectionMatrix(backgroundCamera.combined);
		
		backgroundBatch.begin();
		space.draw(backgroundBatch);
		space.setPosition(-1000, -600);
		backgroundCamera.update();
		
		backgroundBatch.end();
		
		liveComponentBatch.setProjectionMatrix(camera.combined);
		liveComponentBatch.begin();
		
		//this.planet.setPosition(this.getScreenCenterX() + this.planetX - this.planet.getScaleX() / 2, this.getScreenCenterY() + this.planetY - this.planet.getScaleY() / 2);
		
		// TODO: sort this offset. It likely has something to do with initiating the sprite and its offsets etc.
		float starX = this.getScreenCenterX() - this.star.getScaleX() * 2 - 2600;
		float starY = this.getScreenCenterY() - this.star.getScaleY() * 2 - 2600;
		
		star.rotate((float)0.1);

		star.setPosition(starX, starY); // TODO: solve what the hell is this 2000 offset?
		
		spriteContainer.update();
		
		// Background first, next star and then planets.
		
		star.draw(liveComponentBatch);

		for(Sprite sprite : spriteContainer.getPlanetSprites()) {
			sprite.draw(liveComponentBatch);
		}
		
		liveComponentBatch.end();
	}

	/**
	 * Update camera on canvas
	 * @param cam
	 */
	public void updateCameraOnCanvas(OrthographicCamera cam) {
		this.camera = cam;
	}
}
