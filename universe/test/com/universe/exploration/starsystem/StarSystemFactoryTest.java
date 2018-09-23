package com.universe.exploration.starsystem;

import com.universe.exploration.common.tools.IngameAstronomicalConstants;
import com.universe.exploration.common.tools.RandomizationTools;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class StarSystemFactoryTest {
    StarSystemFactory ssf;

    @Mocked({"getRandomInteger"})
    RandomizationTools randomizationTools;

    @Before
    public void executedBeforeEach() {
        ssf = new StarSystemFactory();

        new MockUp<RandomizationTools>() {
            @Mock
            public final int getRandomInteger(int lower, int upper) {
                return 6;
            }
        };
    }

    @Test
    public void testGeneratedPlanetsValidDistances() {
        StarSystem ss = ssf.makeStarSystem();

        List<PlanetCelestialComponent> planets = ss.getPlanets();
        System.out.println("planets " + planets.size());
        double previousOrbitalRadius = -100;
    Assert.assertTrue(planets.size() == 500);
        for (PlanetCelestialComponent planet : planets) {
            Assert.assertTrue(planet.getOrbitalRadius() == previousOrbitalRadius
                    + IngameAstronomicalConstants.MIN_DIFFERENCE_BETWEEN_ADJACENT_PLANET_RADII.getValue());

            previousOrbitalRadius = planet.getOrbitalRadius();
        }
    }

}
