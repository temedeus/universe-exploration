package com.universe.exploration.component.starsystem;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.universe.exploration.model.starsystem.PlanetComponent;

public class Planet  extends Image {
    private PlanetComponent planetComponent;

    public Planet(PlanetComponent planetComponent, Texture texture) {
        super(texture);
        this.planetComponent = planetComponent;
    }
}
