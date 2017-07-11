package com.universe.exploration.survey;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.universe.exploration.crew.CrewMemberStatus;
import com.universe.exploration.crewmember.CrewMember;

public class SurveyContainerTest {

    @Test
    public void testIsSurveyTeamAcceptableWhenTeamAcceptable() throws Exception {
	List<CrewMember> crew = new ArrayList<CrewMember>();
	crew.add(createAliveCrewMember(CrewMemberStatus.ALIVE));

	SurveyContainer container = new SurveyContainer();
	Assert.assertTrue(container.isSurveyTeamAcceptable(crew));
    }

    @Test
    public void testIsSurveyTeamAcceptableWhenTeamNotAcceptable() throws Exception {
	List<CrewMember> crew = new ArrayList<CrewMember>();
	crew.add(createAliveCrewMember(CrewMemberStatus.KIA));

	SurveyContainer container = new SurveyContainer();
	Assert.assertFalse(container.isSurveyTeamAcceptable(crew));
    }

    private CrewMember createAliveCrewMember(CrewMemberStatus status) {
	CrewMember member = new CrewMember();
	member.setStatus(status);

	return member;
    }
}
