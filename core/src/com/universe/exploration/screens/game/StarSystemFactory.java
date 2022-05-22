package com.universe.exploration.screens.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.component.starsystem.Planet;
import com.universe.exploration.model.starsystem.PlanetComponent;
import com.universe.exploration.model.starsystem.PlanetType;
import com.universe.exploration.model.starsystem.StarSystem;
import com.universe.exploration.utils.GdxHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class StarSystemFactory {

    private UniverseExploration universeExploration;

    public StarSystemFactory(UniverseExploration universeExploration) {
        this.universeExploration = universeExploration;
    }

    public StarSystem createStarSystem() {
        StarSystem starSystem = new StarSystem();

        final int planetCount = 10;

        List<Planet> planets = new ArrayList<>();

        IntStream.range(0, planetCount).forEach(count -> {
            Planet planet = createPlanet();
            switch (count) {
                case 0:
                    planet.setPosition(PlanetController.PlanetPosition.CENTER.calculateNewPositionX(planet), GdxHelper.getScreenCenterY() - planet.getHeight() / 2);
                case 1:
                    planet.setPosition(PlanetController.PlanetPosition.RIGHT.calculateNewPositionX(planet), GdxHelper.getScreenCenterY() - planet.getHeight() / 2);
                default:
                    planet.setPosition(PlanetController.PlanetPosition.FAR_RIGHT.calculateNewPositionX(planet), GdxHelper.getScreenCenterY() - planet.getHeight() / 2);
            }

            planets.add(planet);
        });

        starSystem.setPlanets(planets);
        return starSystem;
    }

    private Planet createPlanet() {
        Random random = new Random();
        int index = random.ints(0, PlanetType.values().length - 1)
                .findFirst()
                .getAsInt();
        PlanetType planetType = PlanetType.values()[index];
        PlanetComponent planetComponent = new PlanetComponent.Builder()
                .withName("Name")
                .withPlanetType(planetType)
                .build();
        Texture planetTexture = universeExploration.getAssetManager().getAsset(planetComponent.getPlanetType().getPlanetAsset());
        Planet planet = new Planet(planetComponent, planetTexture);

        planet.setOrigin(planet.getWidth() / 2, planet.getHeight() / 2);

        RepeatAction action = Actions.repeat(RepeatAction.FOREVER, Actions.rotateBy(360, 60000));

        planet.addAction(action);

        return planet;
    }
}
