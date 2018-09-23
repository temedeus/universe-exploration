package com.universe.exploration.survey;

import com.universe.exploration.crew.CrewMemberStatus;
import com.universe.exploration.crewmember.CrewMember;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SurveyContainerTest {

    @Test
    public void testIsSurveyTeamAcceptableWhenTeamAcceptable()  {
        List<CrewMember> crew = new ArrayList<>();
        crew.add(createAliveCrewMember(CrewMemberStatus.ALIVE));

        SurveyContainer container = new SurveyContainer();
        Assert.assertTrue(container.isSurveyTeamAcceptable(crew));
    }

    @Test
    public void testIsSurveyTeamAcceptableWhenTeamNotAcceptable() {
        List<CrewMember> crew = new ArrayList<>();
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
