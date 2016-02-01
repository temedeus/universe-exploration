package com.universe.exploration.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.universe.exploration.CoreConfiguration;
import com.universe.exploration.model.CelestialBodyGfxModel;
import com.universe.exploration.starsystem.components.CelestialComponent;

abstract class GraphicsGfxContainer implements IGraphicsGfxContainer {

    /**
     * TODO: constructor takes {@link CelestialBodyGfxModel} instead of
     * {@link CelestialComponent}
     */
    protected CelestialComponent starSystemComponent;

    protected CelestialBodyGfxModel celestialBodyGfxModel;

    /**
     * Generic texture
     */
    protected Texture img;

    /**
     * Sprite containing graphics item
     */
    protected Sprite smallVersion;

    protected Sprite enlarged;

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

    public GraphicsGfxContainer(CelestialComponent starSystemComponent) {
	this(starSystemComponent.getSpriteSize(), starSystemComponent.getGraphicsFile());
	this.starSystemComponent = starSystemComponent;
	celestialBodyGfxModel = new CelestialBodyGfxModel();
	celestialBodyGfxModel.setStarSystemComponent(starSystemComponent);
    }

    public GraphicsGfxContainer(int spriteSize, String graphicsSource) {
	this.spriteSize = spriteSize;
	this.graphicsSource = graphicsSource;

	setupSmallVersion();
	setupDetailed();
    }

    /**
     * Initialize sprite and set its basic properties
     */
    public void setupSmallVersion() {
	Texture texture = new Texture(Gdx.files.internal(graphicsSource));
	smallVersion = new Sprite(texture);
	smallVersion.setSize(this.spriteSize, this.spriteSize);
	smallVersion.setOrigin(this.spriteSize / 2, this.spriteSize / 2);
    }

    /**
     * Initialize sprite and set its basic properties
     */
    public void setupDetailed() {
	Texture texture = new Texture(Gdx.files.internal(graphicsSource));
	enlarged = new Sprite(texture);
	enlarged.setSize(CoreConfiguration.ENLARGED_PLANET_SPRITE_SIZE, CoreConfiguration.ENLARGED_PLANET_SPRITE_SIZE);
	enlarged.setOrigin(enlarged.getWidth() / 2, enlarged.getHeight() / 2);
	enlarged.setPosition(-(enlarged.getWidth() / 2), -(enlarged.getWidth() / 2));
    }

    public void updateSpritePosition() {
	smallVersion.setPosition(celestialBodyGfxModel.getPositionX(), celestialBodyGfxModel.getPositionY());
    }

    /**
     * @return the spriteSize
     */
    public int getSpriteSize() {
	return spriteSize;
    }

    /**
     * @param spriteSize
     *            the spriteSize to set
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
     * @param graphicsSource
     *            the graphicsSource to set
     */
    public void setGraphicsSource(String graphicsSource) {
	this.graphicsSource = graphicsSource;
    }

    /**
     * @return the componentType
     */
    public CelestialComponent getComponentType() {
	return starSystemComponent;
    }

    /**
     * @param componentType
     *            the componentType to set
     */
    public void setComponentType(CelestialComponent componentType) {
	this.starSystemComponent = componentType;
	this.setCelestialBodyGfxModel(new CelestialBodyGfxModel());
	this.celestialBodyGfxModel.setStarSystemComponent(componentType);
    }

    /**
     * @return the sprite
     */
    public Sprite getSprite() {
	return smallVersion;
    }

    /**
     * @param sprite
     *            the sprite to set
     */
    public void setSprite(Sprite sprite) {
	this.smallVersion = sprite;
    }

    /**
     * @return the startBodyGfxModel
     */
    public CelestialBodyGfxModel getCelestialBodyGfxModel() {
	return celestialBodyGfxModel;
    }

    /**
     * @param startBodyGfxModel
     *            the startBodyGfxModel to set
     */
    public void setCelestialBodyGfxModel(CelestialBodyGfxModel startBodyGfxModel) {
	this.celestialBodyGfxModel = startBodyGfxModel;
    }

    /**
     * @return the enlarged
     */
    public Sprite getEnlarged() {
	return enlarged;
    }

    /**
     * @param enlarged
     *            the enlarged to set
     */
    public void setEnlarged(Sprite enlarged) {
	this.enlarged = enlarged;
    }
}
