package com.universe.exploration.common.tools;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;


public class TextManipulationToolsTest {

	@Test
	public void testJoinArrayListStringJoinsStringsProperlyWithSeparator() throws Exception {
		ArrayList<String> testStringList = new ArrayList<String>();
		testStringList.add("blaa");
		testStringList.add("bloo");
		testStringList.add("blee");
		
		String joined = TextManipulationTools.joinArrayListString(testStringList, ", ");
		
		Assert.assertEquals("blaa, bloo, blee", joined);
	}

}
