package com.universe.exploration.graphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class SystemStar extends Graphics {
	
	/**
	 * Default star type.
	 */
	private String planetType = "star1";
	
	/**
	 * @return the planetType
	 */
	public String getPlanetType() {
		return planetType;
	}

	/**
	 * @param planetType the planetType to set
	 */
	public void setPlanetType(String planetType) {
		this.planetType = planetType;
	}

	/**
	 * Set planet type.
	 * @param pt
	 */
	public void setStarType(String pt) {
		this.planetType = pt;
	}
	
	public Sprite getItem() {
		Texture texture = new Texture(Gdx.files.internal(this.planetType + ".png"));
		//texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite = new Sprite(texture);
		sprite.setSize(4096,4096);
		sprite.setOrigin(2048, 2048);
		sprite.setCenter((float)2048, (float)2048);
        return sprite;
	}
}
