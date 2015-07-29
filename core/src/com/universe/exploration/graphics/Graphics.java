package com.universe.exploration.graphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.universe.exploration.starsystem.components.StarSystemComponent;

abstract class Graphics implements IGraphics {
	
	protected StarSystemComponent componentType;

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
	 * 
	 */
	protected int spriteSize;

	/**
	 * Sprite source string
	 */
	protected String spriteSource;
	
	/**
	 * Graphics source
	 */
	protected String graphicsSource;

	public Sprite getItem() {
		Texture texture = new Texture(Gdx.files.internal(this.graphicsSource));
		sprite = new Sprite(texture);
		sprite.setSize(this.spriteSize, this.spriteSize);
		sprite.setOrigin(this.spriteSize / 2, this.spriteSize / 2);
		
        return sprite;
	}
	
	protected void setupSprite() {};
	
	/**
	 * @return the spriteSize
	 */
	public int getSpriteSize() {
		return spriteSize;
	}

	/**
	 * @param spriteSize the spriteSize to set
	 */
	public void setSpriteSize(int spriteSize) {
		this.spriteSize = spriteSize;
	}
	
	/**
	 * @return the graphicsSource
	 */
	public String getGraphicsSource() {
		return graphicsSource;
	}

	/**
	 * @param graphicsSource the graphicsSource to set
	 */
	public void setGraphicsSource(String graphicsSource) {
		this.graphicsSource = graphicsSource;
	}
	
	/**
	 * @return the componentType
	 */
	public StarSystemComponent getComponentType() {
		return componentType;
	}

	/**
	 * @param componentType the componentType to set
	 */
	public void setComponentType(StarSystemComponent componentType) {
		this.componentType = componentType;
		this.graphicsSource = this.componentType.getcomponentType().getGraphicsFile();
	}
}
