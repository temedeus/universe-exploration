package com.universe.exploration.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.universe.exploration.model.StarsystemBodyGfxModel;
import com.universe.exploration.starsystem.components.StarSystemComponent;

abstract class GraphicsGfxContainer implements IGraphicsGfxContainer {
	
	/**
	 * TODO: constructor takes {@link StarsystemBodyGfxModel} instead of {@link StarSystemComponent}
	 */
	protected StarSystemComponent starSystemComponent;

	protected StarsystemBodyGfxModel starSystemBodyGfxModel;

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
	 * Sprite size
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
	
	public GraphicsGfxContainer() {
		// Empty constructor if one does not wish to install component yet
	}
	
	public GraphicsGfxContainer(int spriteSize, String graphicsSource) {
		this.spriteSize = spriteSize;
		this.graphicsSource = graphicsSource;
		
		setupSprite();
	}
	
	public GraphicsGfxContainer(StarSystemComponent starSystemComponent) {
		this.starSystemComponent = starSystemComponent;
		
		graphicsSource = this.starSystemComponent.getcomponentType().getGraphicsFile();
		spriteSize = this.starSystemComponent.getSpriteSize();
		
		setupSprite();
	}
	
	/**
	 * Initialize sprite and set its basic properties
	 */
	public void setupSprite() {
		Texture texture = new Texture(Gdx.files.internal(graphicsSource));
		sprite = new Sprite(texture);
		sprite.setSize(this.spriteSize, this.spriteSize);
		sprite.setOrigin(this.spriteSize / 2, this.spriteSize / 2);
	}

	public void updateSpritePosition() {
		sprite.setPosition(starSystemBodyGfxModel.getPositionX(), starSystemBodyGfxModel.getPositionY());
	}
	
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
		return starSystemComponent;
	}

	/**
	 * @param componentType the componentType to set
	 */
	public void setComponentType(StarSystemComponent componentType) {
		this.starSystemComponent = componentType;
	}
	
	/**
	 * @return the sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * @param sprite the sprite to set
	 */
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	/**
	 * @return the startBodyGfxModel
	 */
	public StarsystemBodyGfxModel getStarSystemBodyGfxModel() {
		return starSystemBodyGfxModel;
	}

	/**
	 * @param startBodyGfxModel the startBodyGfxModel to set
	 */
	public void setStarSystemBodyGfxModel(StarsystemBodyGfxModel startBodyGfxModel) {
		this.starSystemBodyGfxModel = startBodyGfxModel;
	}
}
