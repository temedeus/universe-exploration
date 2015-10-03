package com.universe.exploration.ueui.data;

import org.junit.Test;

import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import common.universe.exploration.common.Lifeforms;


public class PlanetSurveyTest {

	@Test
	public void testPlanetSurveyProvidesProperPairs() throws Exception {
		PlanetCelestialComponent cc = new PlanetCelestialComponent();
		
		cc.setLifeforms(Lifeforms.ANIMAL);
		cc.setComponentName("test");
		cc.setOrbitalRadius(22);
		
		PlanetSurvey ps = new PlanetSurvey(cc);
		for(DataAndValuePair pair : ps.getPairList()) {
			System.out.println(pair.getLabel().getText() + " / " + pair.getValue().getText());
		}
		
	}

}
