package com.universe.exploration.graphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.universe.exploration.Configuration;
import com.universe.exploration.universe.BackgroundAssembler;

public class SpaceBackground extends Graphics {
	
	public Sprite getItem() {
		Texture texture = new Texture(Gdx.files.internal("spacebackground1.png"));
		sprite = new Sprite(texture);
		sprite.setSize(1024,1024);
		sprite.setOrigin(512, 512);
		
        return sprite;
	}
}
