package com.universe.exploration.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.universe.exploration.universe.Universe;

public class Canvas {
	private SpriteBatch batch;
	private Sprite player;
	private Sprite space;
	private BitmapFont font;
	private Sprite star;
	private Universe ua;
	private Skin skin;
	private Stage stage;
	private Sprite planet;
	float px = 0;
	float py = 0;
	float degree = 0;
	private OrthographicCamera camera;
	
	public Canvas(Universe iua) {
		this.ua = iua;
		
		this.batch = new SpriteBatch();
		
        this.font = new BitmapFont();
        this.font.setColor(Color.WHITE);

        //this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        //this.stage = new Stage();
        
        // Player
		Spaceship pGfx = new Spaceship();
		this.player = pGfx.getItem();
		this.player.setPosition(this.playerGfxPositionX(), this.playerGfxPositionY());
		
		// Space background
		SpaceBackground spaceBgGFX = new SpaceBackground();
		this.space = spaceBgGFX.getItem();
		
		for(int x = 0; x < 3; x++) {
			//this.space[x] = spaceBgGFX.getItem();
		}
		
		// Generate system star.
		SystemStar ss = new SystemStar();
		ss.setStarType(this.ua.getStarType());
		this.star = ss.getItem();
		
		// Planet
		Planet p = new Planet();
		this.planet = p.getItem();
	}
	
	public void destroy() {
		this.batch.dispose();
		this.font.dispose();
	}
	
	/**
	 * Player graphical position X (relative to draw area size)
	 * @return float Position X
	 */
	public float playerGfxPositionX() {
		float w = Gdx.graphics.getWidth();
		return (w/2 - this.player.getWidth()/2);
	}
	
	/**
	 * Player graphical position Y (relative to draw area size)
	 * @return float Position Y
	 */
	public float playerGfxPositionY() {
        float h = Gdx.graphics.getHeight();
        return (h/2 - this.player.getHeight()/2);
	}
	
	/**
	 * renderGame
	 */
	public void renderGame() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.batch.setProjectionMatrix(this.camera.combined);
		this.batch.begin();
		this.space.draw(batch);
		this.star.draw(batch);
		this.planet.draw(batch);

		this.star.rotate((float)0.1);
		this.player.draw(batch);
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
