/**
 *
 */
package com.universe.exploration.survey;

import com.universe.exploration.crew.CrewMemberStatus;
import com.universe.exploration.crewmember.CrewMember;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains list of currently active surveys. Can be used to track down survey
 * that should end. In practice maintains an {@link ArrayList} of {@link Survey}
 *
 * @author 28.10.2015 Teemu Puurunen
 */
public class SurveyContainer extends ArrayList<Survey> {

    private static final long serialVersionUID = -3335075506066292326L;

    /**
     * <p>
     * Removes surveyStatus from ArrayList if survey is over. We immediately
     * return a {@link Survey} because this check is run in a loop. In a normal
     * scenario surveys should not end simultaneously anyway.
     * </p>
     *
     * @param currentDay Day the player is currently at.
     * @return {@link Survey} Info on the ended survey and team status.
     */
    public Survey findAndRemoveOpenSurvey(int currentDay) {
        for (Survey surveyStatus : this) {
            if (currentDay >= surveyStatus.getSurveyEndDay()) {
                remove(surveyStatus);
                return surveyStatus;
            }
        }

        return null;
    }

    // Return false if there are any crewmembers in any other status than alive.
    public boolean isSurveyTeamAcceptable(List<CrewMember> crewmen) {
        return !crewmen.stream()
                .filter(member -> member.getStatus() != CrewMemberStatus.ALIVE)
                .findAny()
                .isPresent();
    }
}
