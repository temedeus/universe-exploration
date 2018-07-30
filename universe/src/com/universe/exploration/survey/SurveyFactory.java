/**
 *
 */
package com.universe.exploration.survey;

import com.universe.exploration.casualty.Casualty;
import com.universe.exploration.casualty.CasualtyFactory;
import com.universe.exploration.crew.CrewMemberStatus;
import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.resource.ResourcesFoundFactory;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for creating {@link Survey} instances.
 *
 * @author 25.10.2015 Teemu Puurunen
 */
public class SurveyFactory {

    /**
     * Create new survey.
     * s
     * @param startDay
     * @param surveyLength
     * @param surveyTeam
     * @param planet
     * @param surveyName
     * @return
     */
    public Survey createSurvey(int startDay, int surveyLength, List<CrewMember> surveyTeam, PlanetCelestialComponent planet,
                               String surveyName) {
        ResourcesFoundFactory rff = new ResourcesFoundFactory();
        Survey survey = new Survey();

        survey.setSurveyName(surveyName);
        survey.setSurveyStartDay(startDay);
        survey.setSurveyEndDay(startDay + surveyLength);
        survey.setResourcesFound(rff.generateFoundResource(surveyTeam, planet));
        setMemberStatusOnSurvey(surveyTeam);

        survey.setMortalities(createCasualtyList(surveyTeam, planet));

        return survey;
    }

    private void setMemberStatusOnSurvey(List<CrewMember> surveyTeam) {
        surveyTeam.forEach(member -> member.setStatus(CrewMemberStatus.ONSURVEY));
    }

    private List<Casualty> createCasualtyList(List<CrewMember> surveyTeam, PlanetCelestialComponent planet) {
        CasualtyFactory casualtyFactory = new CasualtyFactory(planet);
        List<Casualty> casualties = new ArrayList<>();

        for (CrewMember surveyTeamMember : surveyTeam) {
            Casualty casualty = casualtyFactory.createCasualty(surveyTeamMember);
            if (casualty != null) {
                casualties.add(casualty);
            }
        }

        return casualties;
    }
}
