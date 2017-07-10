package com.universe.exploration.userinterface.data.container;

import org.junit.Assert;
import org.junit.Test;

import com.universe.exploration.userinterface.data.DataPair;
import com.universe.exploration.userinterface.data.TestDataPair;
import com.universe.exploration.userinterface.data.container.DataPairContainer;

public class PlanetSurveyTest {

	@Test
	public void testDataPairContainerDoesNotCorruptData() throws Exception {
		String[][] captionValuaPairTable = { new String[] { "Orange: ", "orange" }, new String[] { "Apple: ", "red" },
				new String[] { "Banana: ", "yellow" } };

		DataPairContainer ps = new DataPairContainer();

		for (String[] pair : captionValuaPairTable) {
			ps.add(new TestDataPair("id_" + pair[0].toLowerCase(), pair[0], pair[1]));
		}

		int x = 0;

		for (DataPair pair : ps.getPairList()) {
			String title = ((TestDataPair) pair).getLabelToString();
			String value = ((TestDataPair) pair).getValueToString();

			Assert.assertTrue(title == captionValuaPairTable[x][0] && value == captionValuaPairTable[x][1]);
			System.out.println(title + " <SEPARATOR> " + value);

			x++;
		}
	}
}
