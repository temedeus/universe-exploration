package com.universe.exploration.starsystem;

import org.junit.Test;

public class StarSystemConfigurationTest {

    @Test
    public void testStarSystemConfigurationProvidesValidListings() throws Exception {
	StarSystemConfiguration ssc = new StarSystemConfiguration();
	String[][] planettypes = ssc.getPlanettypeListing();
	String[][] startypes = ssc.getStartypeListing();

	for (String[] planet : planettypes) {
	    System.out.println("" + planet[0] + planet[1]);
	}

	for (String[] star : startypes) {
	    System.out.println("" + star[0] + star[1]);
	}
    }
}
