package com.universe.exploration.common.tools;

import org.junit.Test;


public class MathToolsTest {

	@Test
	public void testCalculateIfOddsHit() throws Exception {
		int percent = 30;
		for(int x=0; x < 10; x++) {
			if(MathTools.calculateIfOddsHit(percent)) {
				System.out.println("You hit the " + percent + " % limit.");
			} else {
				System.out.println("Missed it!");
			}
		}
		
	}

}
