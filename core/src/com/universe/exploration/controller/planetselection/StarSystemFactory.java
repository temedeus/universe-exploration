package com.universe.exploration.controller.planetselection;

import com.universe.exploration.UniverseExploration;
import com.universe.exploration.component.starsystem.Planet;
import com.universe.exploration.model.starsystem.StarSystem;
import com.universe.exploration.utils.GdxHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class StarSystemFactory {

    private PlanetFactory planetFactory;

    public StarSystemFactory(UniverseExploration universeExploration) {
        this.planetFactory = new PlanetFactory(universeExploration.getAssetManager());
    }

    public StarSystem createStarSystem() {
        StarSystem starSystem = new StarSystem();

        final int planetCount = 10;

        List<Planet> planets = new ArrayList<>();

        IntStream.range(0, planetCount).forEach(planetPosition -> {
            Planet planet = planetFactory.createPlanet();
            switch (planetPosition) {
                case 0:
                    planet.setPosition(PlanetSelectionController.PlanetPosition.CENTER.calculateNewPositionX(planet), GdxHelper.getScreenCenterY() - planet.getHeight() / 2);
                case 1:
                    planet.setPosition(PlanetSelectionController.PlanetPosition.RIGHT.calculateNewPositionX(planet), GdxHelper.getScreenCenterY() - planet.getHeight() / 2);
                default:
                    planet.setPosition(PlanetSelectionController.PlanetPosition.FAR_RIGHT.calculateNewPositionX(planet), GdxHelper.getScreenCenterY() - planet.getHeight() / 2);
            }

            planets.add(planet);
        });

        starSystem.setPlanets(planets);
        return starSystem;
    }
}
