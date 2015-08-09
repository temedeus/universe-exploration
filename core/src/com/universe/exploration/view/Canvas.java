package com.universe.exploration.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.universe.exploration.starsystem.StarSystem;
import com.universe.exploration.starsystem.components.PlanetAbstractComponent;
import com.universe.exploration.starsystem.components.CelestialComponent;

public class Canvas {
	private SpriteBatch batch;
	private Sprite space;
	private BitmapFont font;
	private Sprite star;
	private StarSystem ua;
	private Sprite planet;
	float px = 0;
	float py = 0;
	float degree = 0;
	private OrthographicCamera camera;
	private PlanetGfxContainer[] planetcontainer;
	
	private SpriteContainer spriteContainer;
	
	/**
	 * Generates graphical representation based on given star system
	 * 
	 * @param iua
	 */
	public Canvas(StarSystem iua) {
		this.ua = iua;
		
		this.batch = new SpriteBatch();
		
        this.font = new BitmapFont();
        this.font.setColor(Color.WHITE);

        //this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        //this.stage = new Stage();
        
		// Space background
		SpaceBackgroundGfxContainer spaceBgGFX = new SpaceBackgroundGfxContainer();
		this.space = spaceBgGFX.getSprite();
		
		// Generate system star.
		SystemStarGfxContainer ss = new SystemStarGfxContainer(this.ua.getSystemstar());
		
		star = ss.getSprite();

		List<PlanetAbstractComponent> listOfPlanets = ua.getPlanets();

		spriteContainer = new SpriteContainer();
		
		for(PlanetAbstractComponent planet : listOfPlanets) {
			spriteContainer.addStarSystemObject(planet);
		}

	}
	
	public void destroy() {
		this.batch.dispose();
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
		
		
		
		this.batch.setProjectionMatrix(this.camera.combined);
		this.batch.begin();
		
		//this.planet.setPosition(this.getScreenCenterX() + this.planetX - this.planet.getScaleX() / 2, this.getScreenCenterY() + this.planetY - this.planet.getScaleY() / 2);
		
		float starX = this.getScreenCenterX() - this.star.getScaleX() * 2 - 2000;
		float starY = this.getScreenCenterY() - this.star.getScaleY() * 2 - 2000;
		
		this.star.rotate((float)0.1);

		this.star.setPosition(starX-2000, starY-2000); // TODO: solve what the hell is this 2000 offset?
		
		spriteContainer.update();
		
		// Background first, next star and then planets.
		this.space.draw(batch);
		this.star.draw(batch);

		for(Sprite sprite : spriteContainer.getSprites()) {
			sprite.draw(batch);
		}
		
		this.batch.end();
	}

	/**
	 * Update camera on canvas
	 * @param cam
	 */
	public void updateCameraOnCanvas(OrthographicCamera cam) {
		this.camera = cam;
	}
}
