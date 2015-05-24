package com.universe.exploration.graphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Spaceship extends Graphics {
	
	public Sprite getItem() {
		Texture texture = new Texture(Gdx.files.internal("spaceship.png"));
		sprite = new Sprite(texture);
		sprite.setSize(8,8);
		sprite.setOrigin(4, 4);
		
        return sprite;
	}
}
