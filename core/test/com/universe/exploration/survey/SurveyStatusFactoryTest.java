package com.universe.exploration.survey;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.universe.exploration.casualty.Casualty;
import com.universe.exploration.common.Lifeforms;
import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

public class SurveyStatusFactoryTest {

    @Test
    public void testCreateSurveyStatusNoMoreMortalitiesThanCrewMembers() throws Exception {
	SurveyStatusFactory ssf = new SurveyStatusFactory();
	SurveyStatus ss = ssf.createSurveyStatus(5, generateTestListOfCrewmen(), createTestPlanet());

	Assert.assertTrue(ss.getCrewmenInSurveyTeam() >= ss.getMortalities().size());
    }

    @Test
    public void testCreateSurveyStatus() throws Exception {
	SurveyStatusFactory ssf = new SurveyStatusFactory();
	SurveyStatus ss = ssf.createSurveyStatus(5, generateTestListOfCrewmen(), createTestPlanet());

	System.out.println(ss.toString());
	System.out.println("Mortalities:\n");

	for (Casualty mortality : ss.getMortalities()) {
	    System.out.println(mortality.toString());
	}
    }

    private List<CrewMember> generateTestListOfCrewmen() {
	List<CrewMember> crew = new ArrayList<CrewMember>();
	
	return crew;
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
