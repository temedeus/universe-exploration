package com.universe.exploration.spritecontainer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.universe.exploration.common.CoreConfiguration;
import com.universe.exploration.spritecontainer.data.SpriteContainerState;
import com.universe.exploration.starsystem.components.CelestialComponent;

abstract class SpriteContainer {
    protected CelestialComponent starSystemComponent;

    protected SpriteContainerState spriteContainerState;

    /**
     * Sprite containing graphics item
     */
    protected Sprite smallVersion;

    /**
     * Close-up version of the sprite.
     */
    protected Sprite enlarged;

    /**
     * Sprite size
     */
    protected int spriteSize;

    /**
     * Graphics source
     */
    protected String graphicsSource;

    public SpriteContainer(CelestialComponent starSystemComponent) {
        this(starSystemComponent.getSpriteSize(), starSystemComponent.getGraphicsFile());
        this.starSystemComponent = starSystemComponent;
        spriteContainerState = new SpriteContainerState();
        spriteContainerState.setStarSystemComponent(starSystemComponent);
    }

    public SpriteContainer(int spriteSize, String graphicsSource) {
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
        smallVersion.setOriginCenter();
    }

    /**
     * Initialize sprite and set its basic properties
     */
    public void setupDetailed() {
        Texture texture = new Texture(Gdx.files.internal(graphicsSource));
        enlarged = new Sprite(texture);
        enlarged.setSize(CoreConfiguration.ENLARGED_PLANET_SPRITE_SIZE, CoreConfiguration.ENLARGED_PLANET_SPRITE_SIZE);
        enlarged.setOrigin(enlarged.getWidth() / 2, enlarged.getHeight() / 2);
        enlarged.setPosition(-(enlarged.getWidth() / 2) - 600, -(enlarged.getWidth() / 2 + 600));
    }

    public void updateSpritePosition() {
        smallVersion.setPosition(spriteContainerState.getPositionX() - smallVersion.getWidth() / 2,
                spriteContainerState.getPositionY() - smallVersion.getHeight() / 2);
    }

    /**
     * @return the componentType
     */
    public CelestialComponent getComponentType() {
        return starSystemComponent;
    }

    /**
     * @return the sprite
     */
    public Sprite getSprite() {
        return smallVersion;
    }

    /**
     * @return the startBodyGfxModel
     */
    public SpriteContainerState getSpriteContainerState() {
        return spriteContainerState;
    }

    /**
     * @return the enlarged
     */
    public Sprite getEnlarged() {
        return enlarged;
    }
}
