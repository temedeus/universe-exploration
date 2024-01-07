package com.universe.exploration.controller.game.planetselection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.component.starsystem.Planet;
import com.universe.exploration.controller.ControllerBase;
import com.universe.exploration.model.starsystem.StarSystem;
import com.universe.exploration.utils.GdxHelper;

import java.util.ArrayList;
import java.util.List;

public class PlanetSelectionController extends ControllerBase {

    private List<Planet> planets;

    private int planetIndex;

    public PlanetSelectionController(UniverseExploration universeExploration) {
        super(universeExploration);
    }

    public void generatePlanets() {
        if(universeExploration.getGamestatus().getPlanets() == null) {
            StarSystem starSystem = new StarSystemFactory(universeExploration).createStarSystem();
            this.planets = starSystem.getPlanets();
            planetIndex = 0;
        } else {
            this.planets = universeExploration.getGamestatus().getPlanets();
        }
    }

    public void moveSelectedPlanetLeft() {
        if (planets.size() == 1 || planetIndex == 0) {
            return;
        }

        planetIndex--;

        Planet selectedPlanet = planets.get(planetIndex);
        selectedPlanet.addAction(createMovePlanetAction(selectedPlanet, PlanetPosition.CENTER));

        Planet planet = planets.get(planetIndex + 1);
        planet.addAction(createMovePlanetAction(planet, PlanetPosition.RIGHT));

        if (planetIndex - 1 >= 0) {
            Planet leftPlanet = planets.get(planetIndex - 1);
            leftPlanet.addAction(createMovePlanetAction(leftPlanet, PlanetPosition.LEFT));
        }

        if (planetIndex - 2 >= 0) {
            Planet farLeftPlanet = planets.get(planetIndex - 2);
            farLeftPlanet.addAction(createMovePlanetAction(farLeftPlanet, PlanetPosition.FAR_LEFT));
        }

        if (planetIndex + 2 <= planets.size() - 1) {
            Planet farRightPlanet = planets.get((planetIndex + 2));
            farRightPlanet.addAction(createMovePlanetAction(farRightPlanet, PlanetPosition.FAR_RIGHT));
        }
    }

    public void moveSelectedPlanetRight() {
        if (planets.size() == 1 || planetIndex == planets.size() - 1) {
            return;
        }

        planetIndex++;

        Planet selectedPlanet = planets.get(planetIndex);
        selectedPlanet.addAction(createMovePlanetAction(selectedPlanet, PlanetPosition.CENTER));

        Planet leftPlanet = planets.get(planetIndex - 1);
        leftPlanet.addAction(createMovePlanetAction(leftPlanet, PlanetPosition.LEFT));

        if (planetIndex - 2 >= 0) {
            Planet farLeftPlanet = planets.get(planetIndex - 2);
            farLeftPlanet.addAction(createMovePlanetAction(farLeftPlanet, PlanetPosition.FAR_LEFT));
        }

        if (planetIndex + 1 <= planets.size() - 1) {
            Planet rightPlanet = planets.get(planetIndex + 1);
            rightPlanet.addAction(createMovePlanetAction(rightPlanet, PlanetPosition.RIGHT));
        }

        if (planetIndex + 2 <= planets.size() - 1) {
            Planet rightPlanet = planets.get(planetIndex + 2);
            rightPlanet.addAction(createMovePlanetAction(rightPlanet, PlanetPosition.FAR_RIGHT));
        }
    }

    private MoveToAction createMovePlanetAction(Planet planet, PlanetPosition planetPosition) {
        MoveToAction moveAction = new MoveToAction();
        moveAction.setPosition(planetPosition.calculateNewPositionX(planet), GdxHelper.getScreenCenterY() - planet.getHeight() / 2);
        moveAction.setDuration(10f);
        return moveAction;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public enum PlanetPosition {
        FAR_LEFT {
            @Override
            public float calculateNewPositionX(Planet planet) {
                return 0 - planet.getWidth();
            }
        }, LEFT {
            @Override
            public float calculateNewPositionX(Planet planet) {
                return 0 - (float) (planet.getWidth() * 0.75);
            }
        }, CENTER, RIGHT {
            @Override
            public float calculateNewPositionX(Planet planet) {
                return Gdx.graphics.getWidth() - planet.getWidth() / 4;
            }
        }, FAR_RIGHT {
            @Override
            public float calculateNewPositionX(Planet planet) {
                return Gdx.graphics.getWidth();
            }
        };

        public float calculateNewPositionX(Planet planet) {
            return GdxHelper.getScreenCenterX() - planet.getWidth() / 2;
        }
    }
}
