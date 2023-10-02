package com.universe.exploration.component.starsystem;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.universe.exploration.model.gamecharacter.GameCharacter;
import com.universe.exploration.model.starsystem.PlanetComponent;

import java.util.ArrayList;
import java.util.List;

public class Planet extends Image {
    private PlanetComponent planetComponent;

    public PlanetComponent getPlanetComponent() {
        return planetComponent;
    }

    public List<GameCharacter> getGameCharacters() {
        return gameCharacters;
    }

    private List<GameCharacter> gameCharacters;

    public Planet(PlanetComponent planetComponent, Texture texture) {
        super(texture);
        this.planetComponent = planetComponent;
        this.gameCharacters = new ArrayList<>();
    }

    public void addGameCharacter(GameCharacter gameCharacter) {
        this.gameCharacters.add(gameCharacter);
    }
}
