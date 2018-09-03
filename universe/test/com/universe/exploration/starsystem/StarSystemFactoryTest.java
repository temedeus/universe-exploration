package com.universe.exploration.starsystem;

import com.universe.exploration.common.tools.IngameAstronomicalConstants;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class StarSystemFactoryTest {
    StarSystemFactory ssf;

    @Before
    public void executedBeforeEach() {
        ssf = new StarSystemFactory();
    }

    @Test
    public void testGeneratedPlanetsValidDistances() {
        StarSystem ss = ssf.makeStarSystem();

        List<PlanetCelestialComponent> planets = ss.getPlanets();

        double previousOrbitalRadius = -100;

        for (PlanetCelestialComponent planet : planets) {
            Assert.assertTrue(planet.getOrbitalRadius() >= previousOrbitalRadius
                    + IngameAstronomicalConstants.MIN_DIFFERENCE_BETWEEN_ADJACENT_PLANET_RADII.getValue());

            previousOrbitalRadius = planet.getOrbitalRadius();
        }
    }

}
