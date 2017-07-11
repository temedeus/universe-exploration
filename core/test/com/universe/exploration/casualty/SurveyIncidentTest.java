package com.universe.exploration.casualty;

import java.util.List;

import org.junit.Test;

import com.universe.exploration.crewmember.attribute.CrewMemberAttribute;

public class SurveyIncidentTest {

	@Test
	public void testListOfContributingAttributes() throws Exception {
		List<Class<? extends CrewMemberAttribute>> attributeClazzes = SurveyIncident.ATTACKED_BY_ANIMALS
				.listOfContributingAttributes();

		for (Class<? extends CrewMemberAttribute> attributeClazz : attributeClazzes) {
			System.out.println(attributeClazz.getName());
		}
	}
}
