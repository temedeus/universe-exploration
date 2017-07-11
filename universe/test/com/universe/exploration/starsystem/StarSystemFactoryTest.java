package com.universe.exploration.starsystem;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.universe.exploration.common.tools.IngameAstronomicalConstants;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

public class StarSystemFactoryTest {
    StarSystemFactory ssf;

    @Before
    public void executedBeforeEach() {
	ssf = new StarSystemFactory();
    }

    @Test
    public void testGeneratedPlanetsValidDistances() {
	System.out.println("*****testGeneratedPlanetsValidDistances*********");
	StarSystem ss = new StarSystem();

	try {
	    ss = ssf.makeStarSystem();
	} catch (Exception e) {
	}

	List<PlanetCelestialComponent> planets = ss.getPlanets();

	double previousOrbitalRadius = -100;

	for (PlanetCelestialComponent planet : planets) {
	    // planet.getOrbitalRadius() >= previousOrbitalRadius +
	    // IngameAstronomicalConstants.MIN_DIFFERENCE_BETWEEN_ADJACENT_PLANET_RADII.getValue()

	    Assert.assertTrue(planet.getOrbitalRadius() >= previousOrbitalRadius
		    + IngameAstronomicalConstants.MIN_DIFFERENCE_BETWEEN_ADJACENT_PLANET_RADII.getValue());

	    previousOrbitalRadius = planet.getOrbitalRadius();
	}
    }

}
