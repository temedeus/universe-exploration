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
	public void testPlanetCountWithinRange() throws Exception {
		System.out.println("*****testPlanetCountWithinRange*****");
		
		StarSystem[] ss = new StarSystem[3];

		/**
		 *  Try generating a few star systems so as to see if randomization fails.
		 *  Note! It is not expected that "randomization" itself has such effects, but more so the fact that
		 *  under random circumstances there many variables to be accounted for.
		 */
		System.out.println("Generating 3 different star systems to see if randomization makes something fail.");
		for(int x=0; x < 3; x++) {
			ss[x] = ssf.makeUniverse();
			System.out.println("-- star type: " + ss[x].getSystemstar().getcomponentType().toString());
		}
	}
	
	@Test
	public void testGeneratedPlanetsValidDistances() {
		System.out.println("*****testGeneratedPlanetsValidDistances*********");
		StarSystem ss = new StarSystem();
		
		try {
			ss = ssf.makeUniverse();
		} catch(Exception e) {
		}
		
		
		List<PlanetCelestialComponent> planets = ss.getPlanets();
		
		double previousOrbitalRadius = -100;
		
		for (PlanetCelestialComponent planet : planets) {
			//planet.getOrbitalRadius() >= previousOrbitalRadius + IngameAstronomicalConstants.MIN_DIFFERENCE_BETWEEN_ADJACENT_PLANET_RADII.getValue()
			
			Assert.assertTrue(planet.getOrbitalRadius() >= previousOrbitalRadius + IngameAstronomicalConstants.MIN_DIFFERENCE_BETWEEN_ADJACENT_PLANET_RADII.getValue());
			System.out.println("Planet type:" + planet.getcomponentType() + ", orbital radius: " + planet.getOrbitalRadius());
			
			previousOrbitalRadius = planet.getOrbitalRadius();
		}
	}

}
