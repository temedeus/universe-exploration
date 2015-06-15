package com.universe.exploration.graphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.universe.exploration.CoreConfiguration;

public class SpaceBackground extends Graphics {
	
	public Sprite getItem() {
		Texture texture = new Texture(Gdx.files.internal("spacebackground1.png"));
		sprite = new Sprite(texture);
		sprite.setSize(CoreConfiguration.bgSize, CoreConfiguration.bgSize);
		sprite.setOrigin(CoreConfiguration.bgSize / 2, CoreConfiguration.bgSize / 2);
		
        return sprite;
	}
}
