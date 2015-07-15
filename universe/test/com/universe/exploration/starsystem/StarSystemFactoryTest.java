package com.universe.exploration.starsystem;

import org.junit.Test;


public class StarSystemFactoryTest {

	@Test
	public void testMakeUniverse() throws Exception {
		StarSystemFactory ssf = new StarSystemFactory();
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

}
