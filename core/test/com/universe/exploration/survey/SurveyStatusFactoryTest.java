package com.universe.exploration.survey;

import org.junit.Assert;
import org.junit.Test;

import com.universe.exploration.common.Lifeforms;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;


public class SurveyStatusFactoryTest {

	@Test
	public void testCreateSurveyStatusNoMoreMortalitiesThanCrewMembers() throws Exception { 
		SurveyStatusFactory ssf = new SurveyStatusFactory();
		SurveyStatus ss = ssf.createSurveyStatus(5, 7, createTestPlanet());
		
		Assert.assertTrue(ss.getCrewmenInSurveyTeam() >= ss.getMortalities().size());
	}
	
	@Test
	public void testCreateSurveyStatus() throws Exception { 
		SurveyStatusFactory ssf = new SurveyStatusFactory();
		SurveyStatus ss = ssf.createSurveyStatus(5, 7, createTestPlanet());
		
		System.out.println(ss.toString());
		System.out.println("Mortalities:\n");
		
		for(Mortality mortality : ss.getMortalities()) {
			System.out.println(mortality.toString());
		}
	}

	private PlanetCelestialComponent createTestPlanet() {
		PlanetCelestialComponent planet = new PlanetCelestialComponent();
		planet.setLifeforms(Lifeforms.CIVILIZED);
		planet.setOxygenFound(true);
		planet.setWaterFound(true);
		planet.setFoodFound(true);
		
		return planet;
	}
}
