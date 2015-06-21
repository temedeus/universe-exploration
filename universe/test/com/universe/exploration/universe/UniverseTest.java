package com.universe.exploration.universe;

import org.junit.Test;

public class UniverseTest {

	@Test(expected=PlanetCountOutOfRangeException.class)
	public void testSetPlanetCountWithMaximumValue() throws Exception {
		UniverseConfiguration uConf = new UniverseConfiguration();
		int maxVal = uConf.getMaxPlanetCount();
		
		Universe ue = new Universe(new UniverseConfiguration());
		ue.setPlanetCount(maxVal+1);
	}

	@Test(expected=PlanetCountOutOfRangeException.class)
	public void testSetPlanetCountWithMinimumValue() throws Exception {
		UniverseConfiguration uConf = new UniverseConfiguration();
		int minVal = uConf.getMinPlanetCount();
		
		Universe ue = new Universe(new UniverseConfiguration());
		ue.setPlanetCount(minVal-1);
	}
}
