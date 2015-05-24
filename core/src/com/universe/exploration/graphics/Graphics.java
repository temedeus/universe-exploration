package com.universe.exploration.graphics;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

abstract class Graphics implements IGraphics {
	/**
	 * Generic pixmap
	 */
	protected Pixmap pixmap;
	
	/**
	 * Generic texture 
	 */
	protected Texture img;
	
	/**
	 * Sprite containing graphics item
	 */
	protected Sprite sprite;
	
	/**
	 * Sprite source string
	 */
	protected String spriteSource;
	
	protected void setupSprite() {};
}
