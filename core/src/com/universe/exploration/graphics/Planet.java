package com.universe.exploration.graphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Planet extends Graphics {
	
	/**
	 * Default planet type.
	 */
	private String planetType = "planet2.png";
	
	/**
	 * Set planet type.
	 * @param pt
	 */
	public void setPlanetType(String pt) {
		this.planetType = pt;
	}
	
	public Sprite getItem() {
		Texture texture = new Texture(Gdx.files.internal(this.planetType));

		sprite = new Sprite(texture);
		sprite.setSize(64,64);
		sprite.setOrigin(32, 32);
		
        return sprite;
	}
}
