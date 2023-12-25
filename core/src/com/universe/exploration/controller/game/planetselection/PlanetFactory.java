package com.universe.exploration.controller.game.planetselection;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.universe.exploration.component.starsystem.Planet;
import com.universe.exploration.model.starsystem.PlanetComponent;
import com.universe.exploration.model.starsystem.PlanetType;
import com.universe.exploration.utils.gameassetmanager.GameAssetManager;

import java.util.Random;

public class PlanetFactory {
    private GameAssetManager gameAssetManager;

    public PlanetFactory(GameAssetManager gameAssetManager) {
        this.gameAssetManager = gameAssetManager;
    }

    public Planet createPlanet() {
        Random random = new Random();
        int index = random.ints(0, PlanetType.values().length - 1)
                .findFirst()
                .getAsInt();
        PlanetType planetType = PlanetType.values()[index];
        PlanetComponent planetComponent = new PlanetComponent.Builder()
                .withName("Name")
                .withPlanetType(planetType)
                .build();
        Texture planetTexture = gameAssetManager.getAsset(planetComponent.getPlanetType().getPlanetAsset());
        Planet planet = new Planet(planetComponent, planetTexture);

        planet.setOrigin(planet.getWidth() / 2, planet.getHeight() / 2);

        RepeatAction action = Actions.repeat(RepeatAction.FOREVER, Actions.rotateBy(360, 60000));
        planet.addAction(action);

        return planet;
    }
}
