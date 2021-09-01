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
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.PlanetAssetProvider;

import java.util.ArrayList;
import java.util.List;

public class StarSystemFactory {

    private UniverseExploration universeExploration;

    public StarSystemFactory(UniverseExploration universeExploration) {
        this.universeExploration = universeExploration;
    }

    public StarSystem createStarSystem() {
        StarSystem starSystem = new StarSystem();

        int planetCount = 3;

        List<Planet> planets = new ArrayList<>();

        for (int x = 0; x < planetCount; x++) {
            Planet planet = createPlanet();
            if (x == 0) {
                planet.setPosition(PlanetController.PlanetPosition.CENTER.calculateNewPositionX(planet), GdxHelper.getScreenCenterY() - planet.getHeight() / 2);
            } else {
                if (x == 1) {
                    planet.setPosition(PlanetController.PlanetPosition.RIGHT.calculateNewPositionX(planet), GdxHelper.getScreenCenterY() - planet.getHeight() / 2);
                } else {
                    planet.setPosition(PlanetController.PlanetPosition.FAR_RIGHT.calculateNewPositionX(planet), GdxHelper.getScreenCenterY() - planet.getHeight() / 2);
                }
            }

            planets.add(planet);
        }

        starSystem.setPlanets(planets);
        return starSystem;
    }

    private Planet createPlanet() {
        PlanetComponent planetComponent = new PlanetComponent.Builder()
                .withName("Name")
                .withPlanetType(PlanetType.EARTHLIKE)
                .build();
        Texture planetTexture = universeExploration.getAssetManager().getAsset(PlanetAssetProvider.PlanetAsset.EARTHLIKE);
        Planet earthLike = new Planet(planetComponent, planetTexture);

        earthLike.setOrigin(earthLike.getWidth() / 2, earthLike.getHeight() / 2);

        RepeatAction action = Actions.repeat(RepeatAction.FOREVER, Actions.rotateBy(360, 60000));

        earthLike.addAction(action);

        return earthLike;
    }
}
